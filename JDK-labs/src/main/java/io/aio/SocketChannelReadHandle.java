package io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 负责对每一个socketChannel的读数据事件进行监听
 * 每一个socketChannel都会有一个独立工作的SocketChannel
 */
public class SocketChannelReadHandle implements CompletionHandler<Integer,StringBuffer> {

    private AsynchronousSocketChannel socketChannel;

    private ByteBuffer byteBuffer;

    public SocketChannelReadHandle(AsynchronousSocketChannel socketChannel, ByteBuffer byteBuffer) {
        this.socketChannel = socketChannel;
        this.byteBuffer = byteBuffer;
    }

    @Override
    public void completed(Integer result, StringBuffer stringBuffer) {
        //客户端主动终止了socket，服务器端终止就可以了
        if(result == -1){
            try {
                this.socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        this.byteBuffer.flip();
        byte[] contexts = new byte[1024];
        this.byteBuffer.get(contexts,0,result);
        this.byteBuffer.clear();
        stringBuffer = new StringBuffer();
        //继续监听
        this.socketChannel.read(this.byteBuffer,stringBuffer,this);
    }

    @Override
    public void failed(Throwable throwable, StringBuffer stringBuffer) {
        try {
            System.out.println("客户端异常关闭");
            this.socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

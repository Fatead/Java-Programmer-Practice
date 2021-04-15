package io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 处理器类，专门用来相应ServerSocketChannel的事件
 */
public class ServerSocketChannelHandle implements CompletionHandler<AsynchronousSocketChannel,Void> {

    private AsynchronousServerSocketChannel serverSocketChannel;

    public ServerSocketChannelHandle(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }


    @Override
    public void completed(AsynchronousSocketChannel asynchronousSocketChannel, Void attachment) {
        this.serverSocketChannel.accept(attachment,this);
        ByteBuffer readBuffer = ByteBuffer.allocate(50);
        asynchronousSocketChannel.read(readBuffer,new StringBuffer(),new SocketChannelReadHandle(asynchronousSocketChannel,readBuffer));
    }

    @Override
    public void failed(Throwable throwable, Void aVoid) {
        System.out.println("异常");
    }

}

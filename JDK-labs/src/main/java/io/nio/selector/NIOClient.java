package io.nio.selector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置通道为非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器端的IP和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
        //连接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("客户端可以做其他工作");
            }
        }
        //连接成功则发送数据
        String str = "Hello";
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
        //发送数据，将buffer的数据写入到channel
        socketChannel.write(wrap);
        System.in.read();
    }

}

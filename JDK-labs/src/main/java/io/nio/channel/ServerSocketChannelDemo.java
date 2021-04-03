package io.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {


    /**
     * 基于ServerSocketChannel创建Socket
     * @throws IOException
     */
    public static void connectByOpen() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel!=null){
                //执行业务逻辑

            }
        }
    }


}

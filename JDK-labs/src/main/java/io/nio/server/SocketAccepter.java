package io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;

/**
 * Java NIO中的ServerSocketChannel是一个可以监听新进来TCP连接的通道，
 * 类似于标准IO中的ServerSocket
 */
public class SocketAccepter implements Runnable {

    private int tcpPort = 0;
    private ServerSocketChannel serverSocket = null;
    private Queue socketQueue = null;

    public SocketAccepter(int tcpPort, Queue socketQueue){
        this.tcpPort = tcpPort;
        this.socketQueue = socketQueue;
    }

    @Override
    public void run() {
        try {
            //使用ServerSocketChannel.open()创建serverSocket实例
            this.serverSocket = ServerSocketChannel.open();
            //使用ServerSocketChannel监听指定的TCP端口
            this.serverSocket.bind(new InetSocketAddress(tcpPort));
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        //对于指定的端口进行轮询
        while (true){
            try {
                SocketChannel socketChannel = this.serverSocket.accept();
                System.out.println("Socket连接建立：" + socketChannel);
                //将新建立的SocketChannel加入倒SocketQueue中进行管理
                this.socketQueue.add(socketChannel);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
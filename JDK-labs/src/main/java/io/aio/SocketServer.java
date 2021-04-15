package io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {


    private static final Object waitObject = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {
        //创建一个线程池用于得到操作系统IO事件通知
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open();
        //设置要监听的端口
        serverSocket.bind(new InetSocketAddress("0.0.0.0",83));
        serverSocket.accept(null, new ServerSocketChannelHandle(serverSocket));
        synchronized (waitObject){
            waitObject.wait();
        }
    }

}

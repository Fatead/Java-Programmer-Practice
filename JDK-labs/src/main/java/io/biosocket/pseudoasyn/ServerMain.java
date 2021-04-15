package io.biosocket.pseudoasyn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 基于线程池的BIO
 * @Author zmr
 */
public class ServerMain {


    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动");
        ServerSocket ss = new ServerSocket(9999);
        ExecutorService es = new ThreadPoolExecutor(
                2,
                4,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        while (true) {
            Socket socket = ss.accept();
            es.execute(new ServerThreadReader(socket));
        }
    }

}

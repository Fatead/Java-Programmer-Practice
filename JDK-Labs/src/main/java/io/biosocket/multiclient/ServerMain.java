package io.biosocket.multiclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zmr
 */
public class ServerMain {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动");
        ServerSocket ss = new ServerSocket(9999);
        while (true) {
            Socket socket = ss.accept();
            (new ServerThreadReader(socket)).start();
        }
    }
}

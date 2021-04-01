package io.biosocket.singleinput;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @Author zmr
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动");
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.println("服务器你好");
        ps.flush();
    }
}

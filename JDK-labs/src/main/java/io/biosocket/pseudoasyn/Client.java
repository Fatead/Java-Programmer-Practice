package io.biosocket.pseudoasyn;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author zmr
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动");
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            String msg = scanner.nextLine();
            ps.println(msg);
            ps.flush();
        }
    }
}

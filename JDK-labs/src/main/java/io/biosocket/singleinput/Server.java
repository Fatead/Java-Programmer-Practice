package io.biosocket.singleinput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zmr
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动");
        ServerSocket ss = new ServerSocket(9999);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String msg;
        if ((msg = br.readLine()) != null) {
            System.out.println("服务器端收到" + msg);
        }
    }
}

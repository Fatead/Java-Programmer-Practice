package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

    public static void main(String[] args) {
        try {
            //InetAddress inetAddress = InetAddress.getByName("198,168,10.14");
            InetAddress inetAddress1 = InetAddress.getByName("www.baidu.com");
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
            System.out.println(inetAddress1);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}

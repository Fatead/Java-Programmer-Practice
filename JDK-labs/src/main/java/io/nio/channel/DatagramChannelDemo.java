package io.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel是一个可以收发UDP包的通道，因为UDP是面向无连接的网络协议，
 * 所以不能像其他通道那样读取和写入，它发送和接收的是数据包
 */
public class DatagramChannelDemo {

    /**
     * 创建DatagramChannel并接收某一个端口的数据包
     * @throws IOException
     */
    public static void createDatagramChannel() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(9999));
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        byteBuffer.clear();
        //receive()方法会将接收到的数据包复制到指定的buffer，如果buffer容纳不下接收到的数据，多余的数据会被丢弃
        datagramChannel.receive(byteBuffer);
    }


    /**
     * 使用UDP发送数据到某一个socket
     * @throws IOException
     */
    public static void sendData() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        String sendData = "new String to write" + System.currentTimeMillis();
        byteBuffer.clear();
        byteBuffer.put(sendData.getBytes());
        byteBuffer.flip();
        int sendResult = datagramChannel.send(byteBuffer,new InetSocketAddress("www.someAddress",80));
    }



}

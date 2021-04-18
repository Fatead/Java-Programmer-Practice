package io.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufDemo {


    private static void printBuf(ByteBuf byteBuf){
        System.out.println("readerIndex:" + byteBuf.readerIndex());
        System.out.println("writerIndex:" + byteBuf.writerIndex());
        System.out.println("capacity:" + byteBuf.capacity());
        System.out.println("max capacity:" + byteBuf.maxCapacity() + "\r\n");
    }

    public static void main(String[] args) {
        //创建一个缓冲区，capacity为10。将数据存储在JVM的堆空间中，这种模式被称为支撑数组
        ByteBuf byteBuf = Unpooled.buffer(10);
        printBuf(byteBuf);
        String msg = "some message";
        byteBuf.writeBytes(msg.getBytes());
        printBuf(byteBuf);
        while (byteBuf.isReadable()){
            System.out.print((char)byteBuf.readByte());
        }
        System.out.println();
        printBuf(byteBuf);
    }

}

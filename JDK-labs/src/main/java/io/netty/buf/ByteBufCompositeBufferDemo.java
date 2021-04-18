package io.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

public class ByteBufCompositeBufferDemo {

    public static void main(String[] args) {
        //创建一个堆缓冲区
        ByteBuf heapBuf = Unpooled.buffer(3);
        String way = "way";
        heapBuf.writeBytes(way.getBytes());

        //创建一个直接缓冲区
        ByteBuf directBuf = Unpooled.directBuffer(3);
        String lau = "lau";
        directBuf.writeBytes(lau.getBytes());

        //创建一个复合缓冲区
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer(10);
        compositeByteBuf.addComponents(heapBuf,directBuf);

        if (!compositeByteBuf.hasArray()){
            for (ByteBuf byteBuf : compositeByteBuf) {
                int offset = byteBuf.readerIndex();
                int length = byteBuf.readableBytes();
                byte[] array = new byte[length];
                byteBuf.getBytes(offset,array);
                System.out.println(Arrays.toString(array));
            }
        }

    }

}

package io.nio.buffer;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        for (int i = 0; i < 1024; i++) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.flip();
        //获得一个只读的buffer,这个buffer不能再接收写入
        byteBuffer.asReadOnlyBuffer();
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }

}

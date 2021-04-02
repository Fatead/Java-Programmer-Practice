package io.nio.buffer;

import java.nio.ByteBuffer;

public class CreateBuffer {

    /**
     * 通过wrapper方法包装数组生成buffer
     */
    private static void createBufferByArray(){
        byte[] bytes = new byte[1024];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }
    }


    /**
     * 调用Buffer的allocate方法生成Buffer对象
     */
    public static void createBufferByAllocate(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }
    }

    public static void main(String[] args) {
        createBufferByArray();
        createBufferByAllocate();
    }

}
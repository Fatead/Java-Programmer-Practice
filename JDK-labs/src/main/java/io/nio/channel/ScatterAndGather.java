package io.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Scatter：指在读操作时，从Channel中读取的数据将被写入到多个buffer中。
 * buffer首先被插入到数组中，然后再将数组作为channel.read()的输入参数，read()方法按照buffer在数组中的顺序将从
 * channel中读取的数据写入到buffer，当一个buffer被写满后，channel紧接着向另一个buffer中写。
 *
 * Gather: 指在写操作时，多个buffer中的数据写入到同一个Channel中
 *
 */
public class ScatterAndGather {


    public static void scatterRead(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {header,body};
        fileChannel.read(byteBuffers);
    }

    public static void GatheringWrite(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {header,body};
        fileChannel.write(byteBuffers);
    }

}
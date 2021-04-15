package io.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可以让文件直接在内存（堆外内存）修改，操作系统不需要再拷贝一次
 */
public class MappedBufferDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Zero\\IdeaProjects\\ProgramPractice\\JDK-labs\\src\\main\\resources\\ioFile\\testIn.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1：读写模式
         * 参数2：可以直接修改的起始位置
         * 参数3：映射内存的大小，即将文件中的多少个字节映射到内存上
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'H');
        randomAccessFile.close();
    }
}

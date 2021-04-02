package io.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileByChannel {

    /**
     * 使用FileChannel读取文件内容
     * @param filePath
     * @throws IOException
     */
    public static void readContent(String filePath) throws IOException {
        //RandomAccessFile既可以读取文件内容，也可以向文件输出数据，同时支持随机访问的方式，程序可以直接跳转到文件的任意位置读写数据
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        int bytesRead = 0;
        while ((bytesRead = fileChannel.read(byteBuffer))!=-1){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
            byteBuffer.clear();
        }
        randomAccessFile.close();
    }


    public static void main(String[] args) throws IOException {
        readContent("C:\\Users\\Zero\\IdeaProjects\\ProgramPractice\\JDK-labs\\src\\main\\resources\\ioFile\\testIn.txt");
    }

}

package io.nio.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel能够直接连接输入输出的文件通道，将数据直接写入到目标文件中去，效率更高
 *
 *
 */
public class CopyFile {

    /**
     * ByteBuffer.allocate() 产生的内存开销是在JVM中的
     * ByteBuffer.allocateDirect()  系统级的内存分类，适合数据量大的情况
     * @param inFile
     * @param outFile
     * @throws IOException
     */
    public static void copyFileContent(String inFile,String outFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        //分别从输入输出流中获取输入通道和输出通道
        FileChannel fileChannelIn = fileInputStream.getChannel();
        FileChannel fileChannelOut = fileOutputStream.getChannel();
        //创建字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            //读入之前先清空缓冲区
            byteBuffer.clear();
            //从输入通道中读取缓冲区大小（1024字节）的数据，放到缓冲对象中
            int result = fileChannelIn.read(byteBuffer);
            if(result == -1)return;
            //缓冲区切换成写模式
            byteBuffer.flip();
            fileChannelOut.write(byteBuffer);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java CopyFile infile outfile");
            System.exit(1);
        }
        String inFile = args[0];
        String outFile = args[1];
        copyFileContent(inFile,outFile);
    }
}

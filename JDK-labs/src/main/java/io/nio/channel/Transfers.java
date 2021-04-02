package io.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 将数据从一个Channel中转移到另一个Channel中
 *
 */
public class Transfers {

    /**
     * 将数据从一个文件转移到另一个文件
     * @param fromFilePath
     * @param toFilePath
     */
    public static void transferDataFrom(String fromFilePath,String toFilePath){
        try {
            RandomAccessFile fromFile = new RandomAccessFile(fromFilePath,"rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile(toFilePath,"rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel,position,count);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void transferDataTo(String fromFilePath,String toFilePath){
        try {
            RandomAccessFile fromFile = new RandomAccessFile(fromFilePath,"rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile(toFilePath,"rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            fromChannel.transferTo(position,count,toChannel);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        transferDataFrom("C:\\Users\\Zero\\IdeaProjects\\ProgramPractice\\JDK-labs\\src\\main\\resources\\ioFile\\testIn.txt","C:\\Users\\Zero\\IdeaProjects\\ProgramPractice\\JDK-labs\\src\\main\\resources\\ioFile\\testOut.txt");
    }

}

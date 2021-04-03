package io.nio.server;

/**
 * 一个共享的消息缓冲区，一条消息占用buffer中的一个Section，如果这条消息超过了其Section的大小，
 * 就会被拷贝到一个更大的Section中，之前使用的较小的Section所占的内存空间就会被释放
 */
public class MessageBuffer {

    public static final int KB = 1024;
    public static final int MB = 1024*KB;


    private static final int CAPACITY_SMALL = 4*KB;
    private static final int CAPACITY_MEDIUM = 128*KB;
    private static final int CAPACITY_LARGE = MB;

    byte[] smallMessageBuffer  = new byte[1024 * 4   * KB]; //4MB的空间大小,对应着1024个section
    byte[] mediumMessageBuffer = new byte[128  * 128 * KB]; //16MB的空间大小,对应着128个section
    byte[] largeMessageBuffer  = new byte[16   * 1   * MB]; //16MB的空间大小,对应着16个section





}

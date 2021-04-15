package io.nio.server.messgae;

import io.nio.server.QueueIntFlip;

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

    QueueIntFlip smallMessageBufferFreeBlocks = new QueueIntFlip(1024);  //1024个空闲的section
    QueueIntFlip mediumMessageBufferFreeBlocks = new QueueIntFlip(128);  //128个空闲的section
    QueueIntFlip largeMessageBufferFreeBlocks = new QueueIntFlip(16);    //16个空闲的section

    public MessageBuffer() {

    }

    public Message getMessage(){
        int nextFreeSmallBlock = this.smallMessageBufferFreeBlocks.take();
        if(nextFreeSmallBlock == -1)return null;
        Message message = new Message(this);
        message.sharedArray = this.smallMessageBuffer;
        message.capacity = CAPACITY_SMALL;
        message.offset = nextFreeSmallBlock;
        message.length = 0;
        return message;
    }

    /**
     * 扩展该消息所占的section空间，如果当前消息占有的是small section，就切换到medium
     * 如果当前占用的是medium，就切换到large
     * @return
     */
    public boolean expandMessage(Message message){
        if(message.capacity == CAPACITY_SMALL){

        }else if(message.capacity == CAPACITY_MEDIUM){

        }else {
            //已经占有最大空间，不能继续扩展了
            return false;
        }
        return true;
    }

    /**
     * 将Message从一个较小的section移动到一个较大的section中，以便实现消息的扩容
     * @return
     */
    public boolean moveMessage(Message message, QueueIntFlip srcBlockQueue, QueueIntFlip destBlockQueue, byte[] dest, int newCapacity){
        int nextFreeBlock = destBlockQueue.take();
        if(nextFreeBlock == -1){
            return false;
        }
        System.arraycopy(message.sharedArray,message.offset,dest,nextFreeBlock,message.length);
        srcBlockQueue.put(message.offset);
        return true;
    }

}

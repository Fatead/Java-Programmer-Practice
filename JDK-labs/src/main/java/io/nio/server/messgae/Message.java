package io.nio.server.messgae;

import java.nio.ByteBuffer;

public class Message {

    private MessageBuffer messageBuffer = null;

    //根据消息的出入可以分为源socket的ID或者目的socket的ID
    public long socketId = 0;

    public byte[] sharedArray = null;
    public int offset = 0;    //message在shareArray中的开始位置
    public int capacity = 0;  //message在ShareArray中所占section的大小
    public int length = 0;    //

    public Object metaData = null;

    public Message(MessageBuffer messageBuffer){
        this.messageBuffer = messageBuffer;
    }


//    /**
//     * 将数据从byteBuffer中写入message中
//     * @param byteBuffer
//     * @return
//     */
//    public int writeToMessage(ByteBuffer byteBuffer){
//
//    }
//
//    /**
//     * 将数据从字节数组中写入message
//     * @param byteArray
//     * @return
//     */
//    public int writeToMessage(byte[] byteArray){
//
//    }
//
//
//    public int writeToMessage(byte[] byteArray, int offset, int length){
//
//    }

    public void writePartialMessageToMessage(Message message,int endIndex){

    }

    public int writeToByteBuffer(ByteBuffer byteBuffer){
        return 0;
    }


}

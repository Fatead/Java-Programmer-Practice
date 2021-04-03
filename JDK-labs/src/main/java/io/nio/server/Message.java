package io.nio.server;

public class Message {

    private MessageBuffer messageBuffer = null;

    //根据消息的出入可以分为源socket的ID或者目的socket的ID
    public long socketId = 0;

    public byte[] sharedArray = null;
    public int offset = 0;
    public int capacity = 0;
    public int length = 0;


}

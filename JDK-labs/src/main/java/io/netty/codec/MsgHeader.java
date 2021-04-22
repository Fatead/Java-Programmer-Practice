package io.netty.codec;

public class MsgHeader {
    //消息类型
    private byte msgType;
    //消息长度
    private int len;

    public MsgHeader() {
    }

    public MsgHeader(byte msgType, int len) {
        this.msgType = msgType;
        this.len = len;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

}

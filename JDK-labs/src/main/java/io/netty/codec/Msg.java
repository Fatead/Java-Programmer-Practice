package io.netty.codec;

public class Msg {

    //消息头
    private MsgHeader msgHeader = new MsgHeader();
    //消息体
    private String body;


    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

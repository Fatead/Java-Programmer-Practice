package com.example.demo.message;

/**
 * 发送消息响应结果的Message
 */
public class SendResponse implements Message{


    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 相应状态码
     */
    private Integer code;


    private String message;

    public String getMsgId() {
        return msgId;
    }

    public SendResponse setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public SendResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

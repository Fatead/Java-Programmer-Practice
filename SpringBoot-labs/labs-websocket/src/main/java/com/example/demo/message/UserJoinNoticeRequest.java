package com.example.demo.message;

/**
 * 用户加入群聊的通知Message
 */
public class UserJoinNoticeRequest implements Message {
    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public UserJoinNoticeRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}

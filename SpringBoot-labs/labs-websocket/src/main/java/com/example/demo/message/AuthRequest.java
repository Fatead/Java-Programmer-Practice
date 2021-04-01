package com.example.demo.message;

/**
 * 用户认证的请求
 */
public class AuthRequest implements Message {


    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证token
     */
    private String accessToken;


    public String getAccessToken() {
        return accessToken;
    }

    public AuthRequest setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}

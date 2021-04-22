package com.example.feignuserservice.response;

public class UserResponse {

    public Integer getId() {
        return id;
    }

    public UserResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserResponse setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    private Integer id;
    private String name;
    private Integer gender;
}

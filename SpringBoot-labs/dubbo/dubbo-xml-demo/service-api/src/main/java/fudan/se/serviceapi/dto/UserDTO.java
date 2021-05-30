package fudan.se.serviceapi.dto;

import java.io.Serializable;

/**
 * 用户信息DTO
 *
 */
public class UserDTO implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    private Integer gender;

}

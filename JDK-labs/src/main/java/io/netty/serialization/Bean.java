package io.netty.serialization;

import java.io.Serializable;

public class Bean implements Serializable {

    private static final long serialVersionUID = 3235432002462705915L;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}

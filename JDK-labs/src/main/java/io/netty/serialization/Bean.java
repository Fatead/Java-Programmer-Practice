package io.netty.serialization;

import java.io.Serializable;

public class Bean implements Serializable {

    private static final long serialVersionUID = 3235432002462705915L;

    private int age;

    public int getAge() {
        return age;
    }

    public Bean setAge(int age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bean setName(String name) {
        this.name = name;
        return this;
    }

    private String name;

    @Override
    public String toString() {
        return "Bean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

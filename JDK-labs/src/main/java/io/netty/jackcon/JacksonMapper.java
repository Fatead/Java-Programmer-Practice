package io.netty.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getInstance(){
        return mapper;
    }

}

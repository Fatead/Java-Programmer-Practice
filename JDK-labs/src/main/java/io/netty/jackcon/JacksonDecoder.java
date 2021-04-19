package io.netty.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.InputStream;
import java.util.List;

public class JacksonDecoder<T> extends ByteToMessageDecoder {


    private final Class<T> clazz;

    public JacksonDecoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        InputStream inputStream = new ByteBufInputStream(byteBuf);
        ObjectMapper mapper = JacksonMapper.getInstance();
        out.add(mapper.readValue(inputStream,clazz));
    }

}

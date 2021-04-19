package io.netty.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class JacksonEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        ObjectMapper mapper = JacksonMapper.getInstance();
        byte[] body = mapper.writeValueAsBytes(o);
        byteBuf.writeBytes(body);
    }

}

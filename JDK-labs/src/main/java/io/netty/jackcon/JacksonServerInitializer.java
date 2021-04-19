package io.netty.jackcon;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class JacksonServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        channelPipeline.addLast(new JacksonDecoder<JacksonBean>(JacksonBean.class));
        channelPipeline.addLast(new JacksonEncoder());
        channelPipeline.addLast(new JacksonServerHandler());
    }
}

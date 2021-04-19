package io.netty.jackcon;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class JacksonClient {

    private static final String host = "localhost";
    private static final int port = 8082;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new JacksonClientInitializer());
            Channel channel = bootstrap.connect(host,port).sync().channel();
            JacksonBean user = new JacksonBean();
            user.setAge(12);
            user.setName("jack");
            channel.write(user);
            channel.flush();
            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

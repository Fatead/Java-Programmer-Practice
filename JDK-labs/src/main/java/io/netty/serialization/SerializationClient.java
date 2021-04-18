package io.netty.serialization;

import com.sun.jdi.BooleanType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SerializationClient {


    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SerializationClientInitializer());
            Channel channel = bootstrap.connect(host,port).sync().channel();
            for (int i = 0; i <10 ; i++) {
                Bean bean = new Bean().setAge(i).setName("asd");
                channel.write(bean);
            }
            channel.flush();
            channel.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    private static final String host = "localhost";
    private static final int  port = 8082;

}

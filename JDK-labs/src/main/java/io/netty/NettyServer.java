package io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

/**
 * 服务端：接收客户端请求并打印，同时给客户端发送一个消息回执
 *
 *
 */
public class NettyServer {

    private static int HEADER_LENGTH = 4;

    /**
     * BootStrap在netty应用程序中负责引导服务器端和客户端，netty包含了两种不同类型的引导
     * 1.使用服务器的ServerBootStrap,用户接收客户端连接以及为已接受的连接创建子通道
     * 2.用于客户端的BootStrap,不接受新的连接，并且在父类通道完成一些操作
     * @param port
     */
//    public void bind(int port){
//        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
//                Executors.newCachedThreadPool()));
//        //构造对应的pipeline
//        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//            @Override
//            public ChannelPipeline getPipeline() throws Exception {
//                ChannelPipeline pipelines = Channels.pipeline();
//                pipelines.addLast(MessageHandler.class.getName(), new MessageHandler());
//                return pipelines;
//            }
//        });
//        bootstrap.bind(new InetSocketAddress(port));
//    }
//
//
//    // 处理消息
//    static class MessageHandler extends SimpleChannelHandler {
//
//        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//            // 接收客户端请求
//            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
//            String message = new String(buffer.readBytes(buffer.readableBytes()).array(), "UTF-8");
//            System.out.println("<服务端>收到内容=" + message);
//
//            // 给客户端发送回执
//            byte[] body = "服务端已收到".getBytes();
//            byte[] header = ByteBuffer.allocate(HEADER_LENGTH).order(ByteOrder.BIG_ENDIAN).putInt(body.length).array();
//            Channels.write(ctx.getChannel(), ChannelBuffers.wrappedBuffer(header, body));
//            System.out.println("<服务端>发送回执,time=" + System.currentTimeMillis());
//
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            new NettyServer().bind(1088);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ;
//    }

}

package io.nio.chatsystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊系统服务器端代码
 */
public class ChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6666;

    public ChatServer(){
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        try {
            while (true){
                int count =  selector.select(2000);
                if(count>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector,SelectionKey.OP_READ);
                            //给出提示信息
                            System.out.println(sc.getRemoteAddress() + "上线了");
                        }else if(key.isReadable()){
                            //通道发生read事件,处理读
                            readData(key);
                        }
                        //当前的key删除，防止重复处理
                        iterator.remove();
                    }
                }else {
                    System.out.println("等待...");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }

    /**
     * 读取客户端的消息
     */
    private void readData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = null;
        try {
            //取到关联的channel
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            int count = socketChannel.read(byteBuffer);
            if(count>0){
                String s = new String(byteBuffer.array());
                System.out.println("客户端接收到的消息为：" + s);
                //把这个消息转发给其他的客户端
                sendInfoToOtherClient(s,socketChannel);
            }
        }catch (Exception e){
            System.out.println(socketChannel.getRemoteAddress() + "离线了");
            key.cancel();
            socketChannel.close();
        }
    }


    /**
     * 将消息转发到所有的其他socketChannel
     * @param msg
     * @param socketChannel
     */
    private void sendInfoToOtherClient(String msg,SocketChannel socketChannel ) throws IOException {
        System.out.println("服务器转发消息中...");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel && targetChannel!= socketChannel){
                SocketChannel channel = (SocketChannel) targetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                channel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }

}

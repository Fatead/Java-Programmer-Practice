package io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {

    public static void selectChannels() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
        //使用Selector.open()方法创建一个selector实例
        Selector selector = Selector.open();
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        SelectionKey key = serverSocketChannel.register(selector,SelectionKey.OP_READ);
        //轮询各个channel,检查是否有已经准备好可以进行读写的channel
        while (true){
            //返回有事件发生的通道的个数
            int readyChannels = selector.selectNow();
            //如果所有的通道都没有事件发生，直接返回
            if(readyChannels == 0)continue;
            //得到有事件发生的selectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key1 = keyIterator.next();
                if(key1.isAcceptable()){
                    //处理接收就绪的代码逻辑
                }else if(key1.isConnectable()){
                    //处理连接就绪的代码逻辑
                }else if(key1.isReadable()){
                    //处理读就绪的代码逻辑
                }else if(key1.isWritable()){
                    //处理写就绪的代码逻辑
                }
                keyIterator.remove();
            }
        }
    }

}

# Java NIO Channel
Channels也称通道，类似于stream，但是有以下区别
+ Channel是双向的，同时支持读写，但是stream通常是单向的，分别为InputStream和OutputStream
+ Channels可以实现异步读写
+ Channels需要从Buffer中进行读写操作

## FileChannel
用于连接文件的Channel，FileChannel只能运行在非阻塞模式

## SocketChannel
用于连接TCP网络Socket的Channel，创建方式有以下两种
1. 本地创建SocketChannel，连接别处的服务器
2. 当一个新的连接到达ServerSocketChannel的时候，一个SocketChannel会被创建


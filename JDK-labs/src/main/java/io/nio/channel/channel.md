# Java NIO Channel
Channels也称通道，类似于stream，但是有以下区别
+ Channel是双向的，同时支持读写，但是stream通常是单向的，分别为InputStream和OutputStream
+ Channels可以实现异步读写
+ Channels需要从Buffer中进行读写操作

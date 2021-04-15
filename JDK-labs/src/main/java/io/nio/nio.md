# Java NIO
这部分的实现的参考教程：http://tutorials.jenkov.com/java-nio/index.html
### NIO三大组件 Selector\Channel\Buffer
+ 每个channel都会对应一个buffer
+ selector对应一个线程，一个线程对应多个channel(连接)
+ 多个channel注册到一个selector
+ 程序切换到那个selector是由事件决定的
+ selector会根据不同的事件在各个通道上切换
+ Buffer是一个内存块，底层是由数组实现的
+ 数据的读取或者写入是通过Buffer
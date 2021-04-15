# Java AIO
### 基本介绍
JDK 7 引入了Asynchronous IO,即AIO,在IO编程中，常用到两种模式：Reactor和Proactor。Java的NIO就是Reactor,
当有事件触发时，服务器端得到通知，并进程相应的处理。

AIO引入异步通道的概念，采用Proactor的模式，特点是先有操作系统完成后才通知服务器端程序启动线程去处理，一般用于连接数较多并且连接时间较长的应用。
目前AIO还没有广泛应用。
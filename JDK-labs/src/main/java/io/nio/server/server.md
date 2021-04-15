# 基于Java NIO实现非阻塞的服务器

### BIO和NIO
对于BIO来说，如果要实现对于对于大量的数据流进行处理就要创建出对应数量的线程进行管理，
而创建和管理大量线程是非常消耗系统资源的。而NIO只用一个Selector就可以对所有的channel进行
管理，selector通过select()或者selectNow()获取就绪的channel。

## 读取部分消息

当我们从一个Selectable Channel读取一个数据包的时候，我们不知道这个数据包相比
于源文件是否有丢失或者重复数据，所以Message可能有以下几种情况：数据丢失、和原有数据一致
或者比原始数据更多。
有两个关键问题需要解决：
1. 判断是否能从数据包中获取完整的消息
2. 在其余消息到达之前处理已到达的部分消息

使用消息读取器（Message Reader）在数据包中寻找是否存在至少一个完整消息体的
数据，如果一个数据包中包含一个或者多个完整的消息体，那么这些消息就能够被发送到管道进行处理。
为了避免混合来自于不同channel的消息，我们对每一个channel使用一个Message Reader。

在从selector得到可以读取数据的channel实例后，与该channel相关联的Message Reader读取数据并尝试
将其分解为Message，以便对其进行后续处理。
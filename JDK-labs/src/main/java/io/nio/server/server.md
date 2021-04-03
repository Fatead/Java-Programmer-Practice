# 基于Java NIO实现非阻塞的服务器

### BIO和NIO
对于BIO来说，如果要实现对于对于大量的数据流进行处理就要创建出对应数量的线程进行管理，
而创建和管理大量线程是非常消耗系统资源的。而NIO只用一个Selector就可以对所有的channel进行
管理，selector通过select()或者selectNow()获取就绪的channel。

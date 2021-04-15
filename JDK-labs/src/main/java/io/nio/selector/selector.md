# Java NIO Selector
Selector可以用于检查一个或者更多的Java NIO Channel实例，确定哪个channels准备好了读数据或者写数据，
这是单线程管理多channels或者多网络连接的一种方式。
### Selector的优势
Selector的优势主要在于可以用一个线程处理所有的Channels,因为在操作系统中新建线程和线程切换的代价是非常高的，
所以Selector可以极大地提升计算机资源的使用效率

### 创建Selector
`Selector selector = Selector.open()`

### 将Channels注册到Selector
为了使用Channels来管理Selector，必须先将Channels注册到Selector
```
//首先将Channel设置为非阻塞模式
channel.configureBlocking(false);
SelectionKey key = channel.register(selector, SelectionKey.OP_PEAD);
```
需要注意，Selector管理的Channel必须要处于非阻塞的模式
### Selector可以监听的事件
通道触发了一个事件就意味着该通道已经就绪以处理该事件
1. Connect : 连接就绪，一个channel成功连接了另一个服务器
2. Accept：接收就绪，一个channel准备好接收新进入的连接
3. Read：读就绪
4. Write：写就绪

### SelectionKey 
一个SelectionKey表示一个特定的通道对象和一个特定的选择器对象之间的注册关系

### selector相关方法说明

selector.select()  ->阻塞
selector.select(1000)   -> 阻塞1000毫秒，在1000毫秒后返回
selector.wakeup()  -> 唤醒selector
selector.selectorNow() -> 不阻塞，直接返回
  
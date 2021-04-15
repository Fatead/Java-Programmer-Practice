# Netty高性能通信
Netty作为异步高性能的通信框架，往往作为基础通信组件被RPC框架使用，例如阿里Dubbo，使用Netty作为
基础通信组件，用于实现各个进程节点之间的内部通信
### HTTP请求
完整的HTTP Request是由下面几个部分组成

1. HttpRequest 包含请求的头信息
2. 多个连续的HttpContent,里面封装了数据
3. LastHttpContent 标记了HTTP request的结束

一次http请求并不是通过一次会话完成的，中间可能由很多次的连接，那么我们如果要
使用channel来处理一次完整的http请求，我们只处理消息是FullHttpRequest的channel
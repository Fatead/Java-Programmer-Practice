# WebSocket

HTML5 WebSocket设计出来的目的就是使客户端浏览器具备像C/S框架下桌面系统的即时通讯能力，实现了浏览器和服务器的全双工通信。
+ 优点：双向通信、事件驱动、异步、使客户端能够实现真正意义上的推送功能
+ 使用实例（场景）：社交聊天、弹幕、多玩家玩游戏、协同编辑、股票基金实时报价、视频会议、基于位置的应用

## 实现方式
### 1. 继承websocket中的基类
### 2. 使用注解 @ServerEndpoint
注解、成员函数的介绍
+ @OnOpen 有连接时的触发函数，我们可以在用户连接时记录用户连接带的参数
+ @OnClose 连接关闭时调用的方法
+ @OnMessage 收到消息时调用的函数
+ Session 每个Session代表了两个web socket断点的会话。当websocket握手成功
后，websocket就会提供一个打开的Session,可以通过这个Session来对另一个端点发送数据，如果Session关闭后发送数据将会报错
+ Session.getBasicRemote().sendText("message") 向该session连接的用户发送字符串的数据
+ @OnError 发生错误时调用的函数



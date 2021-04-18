# Codec
Codec即为编解码器，将原始字节数据于目标程序数据格式进行互转。网络中都是以字节码的数据形式来传输
数据的，Codec由两部分组成：decoder(解码器)和encoder(编码器)
## Decoder 解码器
解码器ChannelInboundHandler的抽象实现，Netty提供的解码器主要有下面两类
+ 解码字节到消息-ByteToMessageDecoder
+ 解码消息到消息
### ByteToMessageDecoder
将字节转为消息
### MessageToMessageDecoder
## Encoder 编码器
编码器实现了ChannelOutboundHandler，有下面两种
+ 编码从消息到字节
+ 编码从消息到消息
### MessageToByteEncoder
### MessageToMessageEncoder
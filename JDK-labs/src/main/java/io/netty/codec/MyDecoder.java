package io.netty.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MyDecoder extends LengthFieldBasedFrameDecoder {

    public MyDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }
}

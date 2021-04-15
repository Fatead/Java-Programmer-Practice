package io.nio.server.http;

import io.nio.server.Socket;
import io.nio.server.messgae.IMessageReader;
import io.nio.server.messgae.Message;
import io.nio.server.messgae.MessageBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HttpMessageReader implements IMessageReader {

    private MessageBuffer messageBuffer = null;

    private List<Message> messages = new ArrayList<>();

    private Message nextMessage = null;


    @Override
    public void init(MessageBuffer readMessageBuffer) {
        this.messageBuffer = readMessageBuffer;

    }

    @Override
    public void read(Socket socket, ByteBuffer byteBuffer) throws IOException {

    }

    @Override
    public List<Message> getMessages() {
        return null;
    }

}

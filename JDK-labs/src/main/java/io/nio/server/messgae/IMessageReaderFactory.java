package io.nio.server.messgae;

import io.nio.server.messgae.IMessageReader;

public interface IMessageReaderFactory {

    public IMessageReader createMessageReader();

}

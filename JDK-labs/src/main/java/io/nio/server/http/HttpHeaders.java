package io.nio.server.http;

/**
 * 定义了五种 Http方法，分别是get\post\put\head\delete
 * 还定义了http连接有关的一些属性：
 */
public class HttpHeaders {

    public final static int HTTP_METHOD_GET = 1;
    public final static int HTTP_METHOD_POST = 2;
    public final static int HTTP_METHOD_PUT = 3;
    public final static int HTTP_METHOD_HEAD = 4;
    public final static int HTTP_METHOD_DELETE = 5;

    public int httpMethod = 0;
    public int hostStartIndex = 0;
    public int hostEndIndex = 0;
    public int contentLength = 0;
    public int bodyStartIndex = 0;
    public int bodyEndIndex = 0;

}

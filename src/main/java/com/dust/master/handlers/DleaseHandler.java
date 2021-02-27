package com.dust.master.handlers;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;


/**
 * 处理程序接口
 */
public interface DleaseHandler {

    /**
     * 处理
     * @param socket
     * @param buffer
     */
    void handle(NetSocket socket, Buffer buffer);

}

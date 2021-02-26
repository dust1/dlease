package com.dust.master.handlers;

import io.vertx.core.net.NetSocket;

import java.nio.Buffer;

/**
 * 处理程序接口
 */
public interface DleaseHandler {

    /**
     * 处理
     * @param socket
     * @param buffer
     */
    void hanndle(NetSocket socket, Buffer buffer);

}

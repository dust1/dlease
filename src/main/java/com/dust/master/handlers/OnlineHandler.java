package com.dust.master.handlers;

import java.util.Optional;

import com.dust.master.metadata.ClientManager;
import com.dust.util.pojo.ClientOnlinePojo;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

/**
 * 处理客户端上线请求的处理程序
 */
public class OnlineHandler implements DleaseHandler {

    public static OnlineHandler create(ClientManager manager) {
        return new OnlineHandler();
    }

    @Override
    public void handle(NetSocket socket, Buffer buffer) {
        Optional<ClientOnlinePojo> optional = ClientOnlinePojo.create(buffer);
        if (!optional.isPresent()) {
            //传递错误数据，将其关闭
            socket.close();
            return;
        }
        ClientOnlinePojo pojo = optional.get();

    }

}

package com.dust.master;

import java.net.Socket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;

public class MyFirstVerticle extends AbstractVerticle {
    
    @Override
    public void start() {
        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            //TODO 接收到消息后将消息转成Java对象并解析。
            //这里只会接收一下几种消息
            //1. client上线
            //2. client申请租约
            //3. client请求续租
            //后续可能有更多再说
            socket.handler(buffer -> {
                System.out.println(buffer.toString()); 
            });
        });
        
        server.listen(8080, "0.0.0.0", res -> {
            if (res.succeeded()) {
                System.out.println("Server is new listining");
            } else {
                System.out.println("Failed to bind!");
                res.cause().printStackTrace();
            }
        });
    }

}
  
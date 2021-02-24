package com.dust.master;

import java.net.Socket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;

public class MyFirstVerticle extends AbstractVerticle {
    
    @Override
    public void start() {
        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
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

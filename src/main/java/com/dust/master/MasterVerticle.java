package com.dust.master;

import com.dust.master.handlers.MasterHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;

/**
 * 主程序启动器
 */
public class MasterVerticle  extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        NetServer server = vertx.createNetServer();
        MasterHandler handler = MasterHandler.create();
        server.connectHandler(handler::handler);

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

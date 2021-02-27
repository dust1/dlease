package com.dust.master;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest  {
    
    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MasterVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDwon(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();
        NetClient client = vertx.createNetClient();
        client.connect(8080, "localhost", res -> {
            if (res.succeeded()) {
                NetSocket socket = res.result();
                socket.handler(b -> {
                    System.out.println(b.toString());
                    context.assertTrue(b.toString().contains("OK"));
                    async.complete();
                });
                byte[] datas = new byte[23];
                Buffer bu = Buffer.buffer()
                .appendString("dlease")
                .appendInt(1)
                .appendString("qwertyui8u7y6tyw123wqw2we2132w")
                .appendInt(1)
                .appendBytes(datas);
                socket.write(bu);
            } else {

            }
        });
    }

}

package com.dust.master;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {
     @Override
     public void start() {
        vertx.deployVerticle(MasterVerticle.class.getName());
     }
}

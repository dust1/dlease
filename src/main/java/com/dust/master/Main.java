package com.dust.master;

import java.util.Arrays;

import com.dust.util.tool.ByteTool;

import io.vertx.core.Vertx;

public class Main {
    
    public static void main(String[] args) {
        // Vertx vertx = Vertx.vertx();
        // vertx.deployVerticle(MyFirstVerticle.class.getName());
        byte[] bytes = {0, 0};
        System.out.println(ByteTool.byteToInt(bytes, 0, 2));
        System.out.println(Arrays.toString(ByteTool.intToByteWith16Bit(65536)));
    }
}

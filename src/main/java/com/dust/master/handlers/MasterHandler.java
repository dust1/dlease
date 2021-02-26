package com.dust.master.handlers;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息处理
 */
public class MasterHandler {

    private Map<String, DleaseHandler> handlerMap;

    /**
     * 创建处理Master消息接收的静态函数
     * @return
     */
    public static MasterHandler create() {
       //TODO 这里应该要传入配置参数，但是具体的需要另外设计,暂时只返回消息处理对象
        return new MasterHandler();
    }

    private MasterHandler() {
        this.handlerMap = new HashMap<>();
    }

    /**
     * 初始化主要消息处理
     */
    private void init() {
        //TODO 这里后续可以用注解来动态获取，减少维护成本
        handlerMap.put("online", new OnlineHandler());
    }

    /**
     * 消息处理方法
     */
    public void handler(NetSocket socket) {
        //TODO 接收到消息后将消息转成Java对象并解析。
        //这里只会接收一下几种消息
        //1. client上线
        //2. client申请租约
        //3. client请求续租
        //后续可能有更多再说
        socket.handler(buffer -> {
            Buffer b = buffer.getBuffer(0, 6);
            System.out.println(Arrays.toString(b.getBytes()));
            Buffer bc = buffer.getBuffer(7, 38);
            System.out.println(bc.toString());
            System.out.println(buffer.toString());
            socket.write("OK");
        });
    }

}

package com.dust.master.handlers;

import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.dust.master.metadata.ClientManager;
import com.dust.util.tool.ByteTool;

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
        init();
    }

    /**
     * 初始化主要消息处理
     */
    private void init() {
        ClientManager manager = ClientManager.create();
        //TODO 这里后续可以用注解来动态获取，减少维护成本
        handlerMap.put("online", OnlineHandler.create(manager));
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

        //关闭处理程序
        socket.closeHandler(v -> {
            //TODO 
            System.out.println("断开连接");
        });

        //异常处理程序
        socket.exceptionHandler(err -> {
            //TODO 
        });

        //正常处理程序 - 读取数据
        socket.handler(buffer -> {
            Optional<String> keyOptional = ByteTool.checkHeaderAndReadKey(buffer);
            if (!keyOptional.isPresent()) {
                //另一端的地址
                SocketAddress address = socket.remoteAddress();
                //TODO 如果是同一个IP多次发送异常数据，则暂时将这个IP拉黑
                String errHost = address.host();
                System.out.println(errHost);
                socket.close();
                return;
            }
            String key = keyOptional.get();
            DleaseHandler handler = handlerMap.get(key);
            handler.handle(socket, buffer);
        });
    }

}

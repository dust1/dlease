package com.dust.util.pojo;

import java.util.Optional;

import com.dust.util.tool.ByteTool;

import io.vertx.core.buffer.Buffer;

/**
 * 客户端上线
 */
public class ClientOnlinePojo {

    /**
     * 客户端id
     */
    private String clientID;

    /**
     * 客户端id的偏移量与长度
     */
    private static final int CLIENT_ID_OFFSET = 7;
    private static final int CLIENT_ID_LEN    = 32;

    /**
     * 客户端上线携带的数据
     */
    private Buffer data;

    /**
     * 数据校验
     */
    private static final int DATA_CHECK = 40;

    /**
     * 
     * @param buffer 传递过来的字节数据，首位用于区分是哪种信息
     * @return
     */
    public static Optional<ClientOnlinePojo> create(Buffer buffer) {
        // if (!ByteTool.checkHeader(buffer) || buffer.length() < 40) {
        //     return Optional.empty();
        // }
        // String clientId = buffer.getBuffer(CLIENT_ID_OFFSET, CLIENT_ID_LEN).toString();
        // Buffer data = null;
        // if (buffer.getByte(DATA_CHECK) == 1) {
        //     int dataLen = ByteTool.byteToInt(buffer.getBytes(41, 43), 0, 2);
        //     data = buffer.getBuffer(44, 44 + dataLen);
        // }
        // return Optional.of(new ClientOnlinePojo(clientId, data));
        return null;
    }

    private ClientOnlinePojo(String clientID, Buffer data) {
        this.clientID = clientID;
        this.data = data;
    }

}

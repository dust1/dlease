package com.dust.util.tool;

import java.util.Arrays;
import java.util.Optional;

import io.vertx.core.buffer.Buffer;

/**
 * 字节数据相关工具类
 */
public class ByteTool {
        
    //校验请求头，toString()后结果为"dlease"
    private static final byte[] DLEASE = {100, 108, 101, 97, 115, 101};
    //校验的请求信息
    private static final String[] KEYS = {"online"};

    //消息最小的长度：校验头：6、请求类型：1、发起ID：32
    private static final int MIN_LEN = 39;

    /**
     * 校验Buffer对象是否是本系统用于网络传输的对象
     * 并获取其携带的请求头信息，用于表示该请求是什么类型
     * @param buffer
     * @return 如果Optional为空，则表示该请求头非法；否则返回Buffer转化后的byte[]数组
     */
    public static Optional<String> checkHeaderAndReadKey(Buffer buffer) {
        if (buffer.length() < MIN_LEN) {
            return Optional.empty();
        }
        byte[] header = buffer.getBytes(0, DLEASE.length);
        if (!Arrays.equals(header, DLEASE)) {
            return Optional.empty();
        }
        int index = buffer.getByte(DLEASE.length + 1) & 0xf;
        if (index < 0 || index >= KEYS.length) {
            return Optional.empty();
        }
        return Optional.of(KEYS[index]);
    }

    public static int byteToInt(byte[] bytes, int start, int len) {
        int res = 0;
        for (int i = 0; i < len; i++) {
            res |= (bytes[start + i] & 0xff) << (len - i - 1) * 8;
        }
        return res;
    }

    public static byte[] intToByteWith16Bit(int i) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((i >> 8) & 0xff);
        bytes[1] = (byte) (i & 0xff);
        return bytes;
    }

}

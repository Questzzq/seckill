package com.vi.seckill.utils;

import java.util.UUID;

/**
 * @author Eric Tseng
 * @description UUIDUtil
 * @since 2022/2/19 19:05
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

package com.vi.seckill.common;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric Tseng
 * @description ThreadLocalCommon
 * @since 2022/2/21 18:46
 */
public class ThreadLocalCommon {
    public static final String LOG_UUID = "logUuid";
    public static final String LOGIN_USER_ID = "userId";
    public static final String START_TIME = "startTime";
    public static final String EXCEPTION = "exception";

    private static ThreadLocal<Map<String, Object>> LOCAL = new TransmittableThreadLocal<>();

    public static void setLogUuid(String uuid) {
        set(LOG_UUID, uuid);
    }

    public static String getLogUuid() {
        return (String) get(LOG_UUID);
    }

    public static void setLoginUserData(String loginUserId) {
        set(LOGIN_USER_ID, loginUserId);
    }

    public static String getLoginUserData() {
        return (String) get(LOGIN_USER_ID);
    }

    public static void setStartTime(Long startTime) {
        set(START_TIME, startTime);
    }

    public static Long getStartTime() {
        return (Long) get(START_TIME);
    }

    public static void setException(Exception exception) {
        set(EXCEPTION, exception);
    }

    public static Exception getException() {
        return (Exception) get(EXCEPTION);
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        LOCAL.set(map);
    }

    public static Object get(String key) {
        Map<String, Object> map = LOCAL.get();
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    public static void remove() {
        LOCAL.remove();
    }
}

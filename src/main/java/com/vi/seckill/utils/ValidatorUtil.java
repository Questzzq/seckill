package com.vi.seckill.utils;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @author Eric Tseng
 * @description ValidatorUtil
 * @since 2022/2/19 17:04
 */
public class ValidatorUtil {
    public static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile) {
        if(StringUtils.isEmpty(mobile)) {
            return false;
        }
        return mobile_pattern.matcher(mobile).matches();
    }
}

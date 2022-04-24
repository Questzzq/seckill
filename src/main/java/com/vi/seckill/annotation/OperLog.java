package com.vi.seckill.annotation;

import java.lang.annotation.*;

/**
 * 是否开启日志记录
 * @author Eric Tseng
 * @description OperLog
 * @since 2022/3/6 18:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    boolean required() default true;
}

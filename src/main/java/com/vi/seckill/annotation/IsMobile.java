package com.vi.seckill.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Eric Tseng
 * @description IsMobile
 * @since 2022/2/19 16:53
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { })
public @interface IsMobile {
    boolean required() default true;
    String message() default "手机号码格式错误";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

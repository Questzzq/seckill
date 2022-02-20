package com.vi.seckill.exception;

import com.vi.seckill.common.ResBean;
import com.vi.seckill.common.ResBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @author Eric Tseng
 * @description GlobalExceptionHandler
 * @since 2022/2/19 17:52
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResBean ExceptionHandler(Exception e) {
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return ResBean.error(ex.getResBeanEnum());
        } else if(e instanceof BindException) {
            BindException ex = (BindException) e;
            ResBean resBean = ResBean.error(ResBeanEnum.BIND_ERROR);
            resBean.setMessage("参数校验异常: " + ex.getBindingResult().getAllErrors().get(0));
        }
        return ResBean.error(ResBeanEnum.ERROR);
    }
}

package com.vi.seckill.handler;

import com.vi.seckill.annotation.OperLog;
import com.vi.seckill.common.ThreadLocalCommon;
import com.vi.seckill.pojo.SysLog;
import com.vi.seckill.service.impl.ErrLogServiceImpl;
import com.vi.seckill.service.impl.SysLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Eric Tseng
 * @description OperLogInterceptor
 * @since 2022/3/6 18:24
 */
@Aspect
@Component
public class OperLogInterceptor {
    @Autowired
    private SysLogServiceImpl sysLogService;
    @Autowired
    private ErrLogServiceImpl errLogService;

    /**
     * 设置操作日志切入点，记录操作日志，在注解的位置切入代码
     */
    @Pointcut("@annotation(com.vi.seckill.annotation.OperLog)")
    public void operLogPointcut() {}

    /**
     * 设置操作异常切入点记录异常日志，扫描所有controller包下操作
     */
    @Pointcut("execution(* com.vi.seckill.controller..*.*(..)))")
    public void operExceptionPointcut() {}

    @AfterReturning(value = "operLogPointcut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从requestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        SysLog log = new SysLog();
        try {
            log.setUuid(ThreadLocalCommon.getLogUuid());
            // 从切面织入点通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取织入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            // OperLog operLog = method.getAnnotation(OperLog.class);
            // if(operLog != null) {}
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className+"."+methodName;
            log.setClassMethod(methodName);
            // 请求的参数
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

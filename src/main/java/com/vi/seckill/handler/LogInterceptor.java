package com.vi.seckill.handler;

import com.vi.seckill.common.ThreadLocalCommon;
import com.vi.seckill.service.IErrLogService;
import com.vi.seckill.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Eric Tseng
 * @description LogInterceptor
 * @since 2022/2/21 19:13
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "500";

    @Autowired
    private ISysLogService logService;
    @Autowired
    private IErrLogService errLogService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object object) {
        long startTime = System.currentTimeMillis();
        ThreadLocalCommon.setStartTime(startTime);
        ThreadLocalCommon.setLogUuid(UUID.randomUUID().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        long endTime = System.currentTimeMillis();
        long excTime = endTime - ThreadLocalCommon.getStartTime();
        if (ThreadLocalCommon.getException() == null) {
            logService.insertSysLog(excTime, SUCCESS_CODE, request.getRequestURI(), request.getRemoteHost() + "-" + request.getRemoteAddr());
        } else {
            errLogService.insertErrLog(excTime, ERROR_CODE, request.getRequestURI(), request.getRemoteHost() + "-" + request.getRemoteAddr());
        }
        log.info(ThreadLocalCommon.getLogUuid() + " execute time: " + excTime);
    }
}

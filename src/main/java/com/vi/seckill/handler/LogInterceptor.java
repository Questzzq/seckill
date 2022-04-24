package com.vi.seckill.handler;

import com.vi.seckill.common.ThreadLocalCommon;
import com.vi.seckill.pojo.ErrLog;
import com.vi.seckill.pojo.SysLog;
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
        // todo 保存日志
        // int code = response.getStatus();
        if (ThreadLocalCommon.getException() == null) {
            SysLog log = new SysLog()
                    .setExcTime(excTime)
                    .setCode(SUCCESS_CODE)
                    .setUri(request.getRequestURI())
                    .setUsrId(ThreadLocalCommon.getLoginUserData())
                    .setUuid(ThreadLocalCommon.getLogUuid())
                    .setIp(request.getRemoteHost() + "-" + request.getRemoteAddr());
            logService.insertSysLog(log);
        } else {
            ErrLog log = new ErrLog()
                    .setExcTime(excTime)
                    .setCode(ERROR_CODE)
                    .setExp(ex.getMessage().substring(0, 2000))
                    .setUri(request.getRequestURI())
                    .setUsrId(ThreadLocalCommon.getLoginUserData())
                    .setUuid(ThreadLocalCommon.getLogUuid())
                    .setIp(request.getRemoteHost() + "-" + request.getRemoteAddr());
            errLogService.insertErrLog(log);
        }
        log.info(ThreadLocalCommon.getLogUuid() + " execute time: " + excTime);
        // 清理资源，防止OOM
        ThreadLocalCommon.remove();
    }
}

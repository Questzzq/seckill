package com.vi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vi.seckill.common.ThreadLocalCommon;
import com.vi.seckill.mapper.SysLogMapper;
import com.vi.seckill.pojo.SysLog;
import com.vi.seckill.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-03-06
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Async
    @Override
    public void insertSysLog(long excTime, String code, String requestUri, String ip) {
        try {
            Thread.sleep(5000);
            System.out.println("---------------------");
            SysLog log = new SysLog()
                    .setExcTime(excTime)
                    .setCode(code)
                    .setUri(requestUri)
                    .setUsrId(ThreadLocalCommon.getLoginUserData())
                    .setUuid(ThreadLocalCommon.getLogUuid())
                    .setIp(ip);
            sysLogMapper.insert(log);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ThreadLocalCommon.remove();
        }
    }
}

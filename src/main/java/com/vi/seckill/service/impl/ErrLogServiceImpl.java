package com.vi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vi.seckill.common.ThreadLocalCommon;
import com.vi.seckill.mapper.ErrLogMapper;
import com.vi.seckill.pojo.ErrLog;
import com.vi.seckill.service.IErrLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-03-06
 */
@Service
public class ErrLogServiceImpl extends ServiceImpl<ErrLogMapper, ErrLog> implements IErrLogService {
    @Autowired
    private ErrLogMapper errLogMapper;

    @Override
    @Async
    public void insertErrLog(long excTime, String code, String requestUri, String ip) {
        try {
            Thread.sleep(5000);
            System.out.println("---------------------");
            System.out.println(ThreadLocalCommon.getException().toString());
            ErrLog log = new ErrLog()
                    .setExcTime(excTime)
                    .setCode(code)
                    .setExp(ThreadLocalCommon.getException().toString())
                    .setUri(requestUri)
                    .setUsrId(ThreadLocalCommon.getLoginUserData())
                    .setUuid(ThreadLocalCommon.getLogUuid())
                    .setIp(ip);
            errLogMapper.insert(log);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ThreadLocalCommon.remove();
        }
    }
}

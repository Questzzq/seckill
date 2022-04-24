package com.vi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public void insertErrLog(ErrLog log) {
        errLogMapper.insert(log);
    }
}

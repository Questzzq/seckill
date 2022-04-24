package com.vi.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vi.seckill.pojo.ErrLog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-03-06
 */
public interface IErrLogService extends IService<ErrLog> {
    void insertErrLog(ErrLog log);
}

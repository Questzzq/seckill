package com.vi.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vi.seckill.pojo.SysLog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-03-06
 */
public interface ISysLogService extends IService<SysLog> {
    void insertSysLog(SysLog log);
}

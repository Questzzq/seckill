package com.vi.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vi.seckill.common.ResBean;
import com.vi.seckill.pojo.User;
import com.vi.seckill.vo.LoginVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@Service
public interface IUserService extends IService<User> {
    /**
     * 登录接口
     * @param loginVo
     * @return
     */
    public ResBean doLogin(LoginVo loginVo);

    /**
     * 注册功能
     * @param loginVo
     * @return
     */
    public ResBean signUp(LoginVo loginVo);

    ResBean getUser();
}

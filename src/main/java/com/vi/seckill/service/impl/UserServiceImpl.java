package com.vi.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vi.seckill.common.ResBean;
import com.vi.seckill.common.ResBeanEnum;
import com.vi.seckill.exception.GlobalException;
import com.vi.seckill.mapper.UserMapper;
import com.vi.seckill.pojo.User;
import com.vi.seckill.service.UserService;
import com.vi.seckill.utils.JwtUtil;
import com.vi.seckill.utils.MD5Util;
import com.vi.seckill.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResBean doLogin(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        User user = userMapper.selectById(mobile);
        if (null == user) {
            throw new GlobalException(ResBeanEnum.LOGIN_ERROR);
        }
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(ResBeanEnum.LOGIN_ERROR);
        }
        String token = JwtUtil.createToken(user);
        return ResBean.success(token);
    }

    @Override
    public ResBean signUp(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        User user = userMapper.getUser(mobile);
        if (user != null) {
            throw new GlobalException(ResBeanEnum.SIGNUP_ERROR);
        }
        User newUser = new User();
        newUser.setId(Long.valueOf(mobile));
        newUser.setNickname("gen");
        newUser.setPassword(loginVo.getPassword());
        userMapper.insert(newUser);
        return ResBean.success();
    }

    @Override
    public ResBean getUser() {
        User user = userMapper.selectById("15918781086");
        return ResBean.success(user);
    }
}

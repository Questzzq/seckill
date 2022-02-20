package com.vi.seckill.controller;


import com.vi.seckill.annotation.PassToken;
import com.vi.seckill.common.ResBean;
import com.vi.seckill.service.impl.UserServiceImpl;
import com.vi.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PassToken
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResBean signUp(@RequestBody LoginVo loginVo) {
        return userService.signUp(loginVo);
    }

    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResBean login(@RequestBody LoginVo loginVo) {
        return userService.doLogin(loginVo);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResBean getUser() {
        return userService.getUser();
    }
}

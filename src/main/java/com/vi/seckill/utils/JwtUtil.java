package com.vi.seckill.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vi.seckill.common.ResBeanEnum;
import com.vi.seckill.exception.GlobalException;
import com.vi.seckill.pojo.User;
import org.apache.commons.lang3.StringUtils;

public class JwtUtil {
    /**
     * 生成token，密钥是用户的密码，为空会返回异常
     * withAudience()存入需要保存在token的信息，这里是把用户id存进token中
     *
     * @param user
     * @return
     */
    public static String createToken(User user) {
        String token;
        token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        if (StringUtils.isEmpty(token)) {
            throw new GlobalException(ResBeanEnum.ERROR);
        }
        return token;
    }

    public static String getLoginUserId(String token) {
        String loginUserId = "";
        return loginUserId;
    }
}

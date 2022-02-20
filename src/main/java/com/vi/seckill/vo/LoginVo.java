package com.vi.seckill.vo;

import com.vi.seckill.annotation.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Eric Tseng
 * @description LoginVo
 * @since 2022/2/19 16:51
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}

package com.vi.seckill.exception;

import com.vi.seckill.common.ResBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eric Tseng
 * @description 全局异常处理类
 * @since 2022/2/19 17:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private ResBeanEnum resBeanEnum;
}

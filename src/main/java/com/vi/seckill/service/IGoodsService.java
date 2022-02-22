package com.vi.seckill.service;

import com.vi.seckill.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vi.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-20
 */
public interface IGoodsService extends IService<Goods> {
    List<GoodsVo> findGoodsVo();

    GoodsVo getDetailByGoodsId(Long id);
}

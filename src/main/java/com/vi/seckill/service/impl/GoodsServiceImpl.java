package com.vi.seckill.service.impl;

import com.vi.seckill.pojo.Goods;
import com.vi.seckill.mapper.GoodsMapper;
import com.vi.seckill.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vi.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-20
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo getDetailByGoodsId(Long id) {
        return goodsMapper.getDetailByGoodsId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveGood(Goods goods) {
        goodsMapper.saveGood(goods);
        int i = 1/0;
        System.out.println(i);
        return goodsMapper.saveGood(goods);
    }
}

package com.vi.seckill.controller;


import com.vi.seckill.common.ResBean;
import com.vi.seckill.pojo.Goods;
import com.vi.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResBean getList() {
        return ResBean.success(goodsService.findGoodsVo());
    }

    @RequestMapping(value = "/getDetail/{id}", method = RequestMethod.GET)
    public ResBean getDetail(@PathVariable Long id) {
        return ResBean.success(goodsService.getDetailByGoodsId(id));
    }

    @PostMapping("good")
    public ResBean saveGood(@RequestBody Goods goods) {
        return ResBean.success(goodsService.saveGood(goods));
    }

}

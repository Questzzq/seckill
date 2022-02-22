package com.vi.seckill.controller;


import com.vi.seckill.common.ResBean;
import com.vi.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

}

package com.zlead.member.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.OrderGoodsEntity;
import com.zlead.reception.service.OrderGoodsService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;




/**
 * 订单商品
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */
@Controller
@RequestMapping("/zlead/ordergoods")
public class MemberOrderGoodsMgrController {
    @Resource
    private OrderGoodsService orderGoodsService;

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<OrderGoodsEntity>  list = orderGoodsService.getPage(params, pageBounds);
        jsonResult =  new JsonResult(1,"列表信息",list,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
		OrderGoodsEntity tOrderGoods = orderGoodsService.findById(id);

        jsonResult =  new JsonResult(1,"详细信息",tOrderGoods,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(@Validated OrderGoodsEntity tOrderGoods){
        JsonResult jsonResult = null;
        orderGoodsService.save(tOrderGoods);

        jsonResult =  new JsonResult(1,"保存成功",tOrderGoods,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated OrderGoodsEntity tOrderGoods){
        JsonResult jsonResult = null;
        orderGoodsService.save(tOrderGoods);

        jsonResult =  new JsonResult(1,"修改成功",tOrderGoods,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
        orderGoodsService.delete(id);

        jsonResult =  new JsonResult(1,"删除成功","",true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

}

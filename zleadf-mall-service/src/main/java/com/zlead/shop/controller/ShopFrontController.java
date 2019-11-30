package com.zlead.shop.controller;

import java.util.HashMap;
import java.util.Map;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.reception.service.ShopService;
import com.zlead.reception.service.MemberService; 
import com.zlead.utils.LoginUtil;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;




/**
 * 店铺
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
@Controller
@RequestMapping("/zlead/shopfront")
public class ShopFrontController {
    @Resource
    private ShopService shopService;
    
    @Resource
    private MemberService memberService;
    
    @Resource
    private LoginUtil loginUtil;

   
    /**
     * 查询企业和供应商
     * @param pageNum
     * @param size
     * @param shopType
     * @return
     */
    @RequestMapping("/shopList")
    @ResponseBody
    public JsonResult shoplist(@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size
                           ){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        params.put("shopTypeall",1);
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<ShopEntity>  list = shopService.getPage(params, pageBounds);
        if(list!=null&&list.size()>0){
            Page page =  new Page<ShopEntity>(list, list.getPagination());
            jsonResult =  new JsonResult(1,"列表信息",page,true);
        }else{
            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 信息
     */
    @RequestMapping("/shopinfo2")
    @ResponseBody
    public JsonResult shopinfo2(@RequestParam("shopId") Long id){
        System.out.println("传入的id为......:"+id);
        JsonResult jsonResult = null;
		ShopEntity tShop = shopService.findById(id);
        //System.out.println(tShop.getShopName());
        jsonResult =  new JsonResult(1,"详细信息",tShop,true);
        String result = JsonUtil.getJson(jsonResult);
        System.out.println(result);
        return jsonResult;
    }


     
}

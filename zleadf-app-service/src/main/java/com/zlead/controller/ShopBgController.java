package com.zlead.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.SysUserEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.service.ShopBgService;
import com.zlead.utils.LoginUtil;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;




/**
 * 第三方入驻店铺--老版本-暂不用
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
@Controller
@RequestMapping("/zlead/tshopBg")
public class ShopBgController {
    @Resource
    private ShopBgService shopBgService;

    @Resource
    private LoginUtil loginUtil;

    /**
     * 查询企业和供应商
     * @param pageNum
     * @param size
     * @param pageNum
     * @param size
     * @return
     */
    @RequestMapping("/shopList")
    @ResponseBody
    public JsonResult list(@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size
                           ){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        params.put("shopTypeall",1);
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<ShopEntity>  list = shopBgService.getPage(params, pageBounds);
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
    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(@RequestParam("shopId") Long id){
        System.out.println("传入的id为......:"+id);
        JsonResult jsonResult = null;
		ShopEntity tShop = shopBgService.findById(id);
        //System.out.println(tShop.getShopName());
        jsonResult =  new JsonResult(1,"详细信息",tShop,true);
        String result = JsonUtil.getJson(jsonResult);
        System.out.println(result);
        return jsonResult;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(@Validated ShopEntity tShop){
        JsonResult jsonResult = null;
        shopBgService.save(tShop);

        jsonResult =  new JsonResult(1,"保存成功",tShop,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated ShopEntity tShop){
        JsonResult jsonResult = null;
        shopBgService.save(tShop);

        jsonResult =  new JsonResult(1,"修改成功",tShop,true);
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
        shopBgService.delete(id);

        jsonResult =  new JsonResult(1,"删除成功","",true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 供应商或者代理商或企业审核
     */
    @RequestMapping("/shopAudit")
    @ResponseBody
    public JsonResult shopAudit(HttpServletRequest request, ShopEntity shopEntity){
        JsonResult jsonResult = null;
        SysUserEntity u= loginUtil.getLoginSysUser(request);
        boolean b = shopBgService.auditShop(shopEntity,u);
        if(b){
            jsonResult =  new JsonResult(1,"操作成功","",true);
        }else{
            jsonResult =  new JsonResult(2,"操作失败","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

}

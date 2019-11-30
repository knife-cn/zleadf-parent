package com.zlead.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ProductEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.service.SupplierService;
import com.zlead.service.ProductBgService;
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
 * 供应商信息管理--老版本-暂不用
 */
@Controller
@RequestMapping("/zlead/supplierBg")
public class SupplierBgController {
    @Resource
    private SupplierService supplierService;

    @Resource
    private ProductBgService productBgService;

    @Resource
    private LoginUtil loginUtil;
    /**
     * 查询供应商的产品
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResult supplierProduct(HttpServletRequest request,
                                      @RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                                      @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //获取当前登录的用户信息
        //供应商使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"未登录","",false);
            return jsonResult;
        }
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<ProductEntity> list = supplierService.getPage(params, pageBounds,member);
        if(list!=null&&list.size()>0){
            Page page =  new Page<ProductEntity>(list, list.getPagination());
            jsonResult =  new JsonResult(1,"列表信息",page,true);
        }else{
            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除产品
     */
    @RequestMapping("/productDelete")
    @ResponseBody
    public JsonResult productDelete(HttpServletRequest request,@RequestParam("id") Long id){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        //供应商使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  productBgService.productDelete(id);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存产品
     */
    @RequestMapping("/productSave")
    @ResponseBody
    public JsonResult productSave(HttpServletRequest request, @Validated ProductEntity tProduct){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        //供应商使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  supplierService.supplierProductSave(tProduct,member,request);
            if(b){
                jsonResult =  new JsonResult(1,"保存成功",tProduct,true);
            }else{
                jsonResult =  new JsonResult(2,"保存失败",tProduct,false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改产品
     */
    @RequestMapping("/productUpdate")
    @ResponseBody
    public JsonResult productUpdate(HttpServletRequest request,@Validated ProductEntity tProduct){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        //供应商使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  productBgService.productUpdate(tProduct,request);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 供应商店铺的信息
     */
    @RequestMapping("/supplierShopInfo")
    @ResponseBody
    public JsonResult supplierShopInfo(HttpServletRequest request){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        //供应商使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            ShopEntity shop = supplierService.supplierShopInfo(member);
            jsonResult =  new JsonResult(1,"供应商信息",shop,true);
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
}

package com.zlead.controller;


import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;

import com.zlead.service.GoodsBgService;
import com.zlead.util.JsonResult;

import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;


/**
 *牵连包
 * @author ytchen
 * @date 2019-01-23 18:04:37
 */
@Controller
@RequestMapping("/zlead/hgoods")
public class HGoodsBgController {
    @Resource
    private GoodsBgService goodsBgService;
    
    @Resource
    private LoginUtil loginUtil;
    


    /**
     * 牵连H -- F
     * H5商品展示
     */
    @RequestMapping("/hgoods")
    @ResponseBody
    public JsonResult hgoods(HttpServletRequest request){
        JsonResult jsonResult = null;
//        SysUserEntity user = loginUtil.getLoginSysUser(request);
        MemberEntity member = loginUtil.getLoginMember(request);
        List<GoodsEntity> Hgoods = goodsBgService.Hgoods();
        if(member!=null){
            jsonResult =  new JsonResult(2,"用户已登录",Hgoods,false);
            return jsonResult;
        }else {
            jsonResult =  new JsonResult(2,"用户未登录","",false);
        }

        return jsonResult;
    }

}

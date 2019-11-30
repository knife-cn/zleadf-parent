package com.zlead.shopmgr.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
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




/**
 * 店铺
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
@Controller
@RequestMapping("/zlead/shopmgr")
public class ShopMgrController {
    @Resource
    private ShopService shopService;
    
    @Resource
    private MemberService memberService;
    
    @Resource
    private LoginUtil loginUtil;

   

    /**
     * 修改商铺信息
     */
    @RequestMapping("/shopUpdate")
    @ResponseBody
    public JsonResult shopUpdate(HttpServletRequest request,ShopEntity shopEntity){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        //店铺管理员修改店铺信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null && loginUtil.isShop(member) && member.getOwnShopid()==shopEntity.getId()){
            //保存店铺的信息
            boolean b = shopService.updateShop(shopEntity,request);

            if(b){
                jsonResult =  new JsonResult(1,"修改成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"修改失败","",true);
            }
        }else{
            jsonResult =  new JsonResult(2,"用户未登录","",true);
        }
//        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
    
    /*
    修改企业信息
     */
    @RequestMapping("/manageBackstage")
    @ResponseBody
    public JsonResult manageBackstage(@Validated ShopEntity shop) {
        JsonResult jsonResult = null;


//        Boolean a = enterpriseService.updateEnterprise(shop);
        if (true) {
            jsonResult = new JsonResult(1, "操作成功", "", true);
        } else {
            jsonResult = new JsonResult(2, "操作失败", "", false);
        }

        return jsonResult;

    }
    
    /**
     * 信息
     */
    @RequestMapping("/shopinfo")
    @ResponseBody
    public JsonResult shopinfo(@RequestParam("shopId") Long id){
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

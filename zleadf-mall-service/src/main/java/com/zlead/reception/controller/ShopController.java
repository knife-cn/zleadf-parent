package com.zlead.reception.controller;

import com.zlead.entity.AdsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.reception.service.AdsService;
import com.zlead.reception.service.MemberService;
import com.zlead.reception.service.ShopService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 店铺--老版本-暂不用
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
@Controller
@RequestMapping("/zlead/shopads")
public class ShopController {
    @Resource
    private ShopService shopService;

    @Resource
    private MemberService memberService;
    @Resource
    private AdsService adsService;


    @Resource
    private LoginUtil loginUtil;

    /**
     * 供应商入驻或代理商入驻
     */
    @RequestMapping("/shopEntry")
    @ResponseBody
    public JsonResult shopEntry(HttpServletRequest request,ShopEntity shopEntity){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            //保存店铺的信息
            boolean b = shopService.saveShop(shopEntity,member,request);
            if(b){
                jsonResult =  new JsonResult(1,"入驻成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"入驻失败","",true);
            }
        }else{
            jsonResult =  new JsonResult(2,"用户未登录","",true);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    @RequestMapping("/newShopEntry")
    public String newShopEntry(HttpServletRequest request,ShopEntity shopEntity){
    		MemberEntity member = loginUtil.getLoginMember(request);
    		if(member!=null){
            ShopEntity shop = shopService.findByWxUnionid(member.getWxUnionId());
            if (shop==null){
                //保存店铺的信息
                boolean b = shopService.saveShopFromSSO(shopEntity,request);
                Long shipId = shopService.findByWxUnionid(member.getWxUnionId()).getId();
                return "redirect:/company/businessCard.action?shopId="+shipId;
            }
                return "redirect:/company/businessCard.action?shopId="+shop.getId() ;
    		}else{
    			return null;
    		}
    }

    /**
     * 微信小程序的企业名片录入
     * @param request
     * @param shopEntity
     * @return
     */
    @RequestMapping("/joinShop")
    @ResponseBody
    public JsonResult joinShop(HttpServletRequest request,ShopEntity shopEntity){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
//        	ShopEntity shop = shopService.findByWxUnionid(member.getWxUnionId());
//        	 if (shop==null){
                //保存店铺的信息
//                boolean b = shopService.saveShopFromSSO(shopEntity,request);
        		boolean b = shopService.saveShop(shopEntity,member,request);
                if(b){
                    jsonResult =  new JsonResult(1,"入驻成功",shopEntity.getId(),true);
                }else{
                    jsonResult =  new JsonResult(2,"入驻失败","",true);
                }
//        	 }
//               return "redirect:/company/businessCard.action?shopId="+shop.getId();

        }else{
        	String strPasswd="111111";
        	  //新增会员信息
        	if(shopEntity.getContactPhone()!=null && shopEntity.getContactPhone().length()>6){
        		strPasswd=shopEntity.getContactPhone().substring(shopEntity.getContactPhone().length()-6);
        	}
            memberService.saveMember(shopEntity.getContactPhone(), strPasswd) ;
            member=memberService.findByPhone(shopEntity.getContactPhone());
            //新增店铺信息
            shopEntity.setMemberid(member.getMemberId());
            //保存店铺的信息
//        	b = shopService.saveShopFromSSO(shopEntity,request);
            boolean b = shopService.saveShop(shopEntity,member,request);
            //会员管理shopid
            memberService.updateMember(member);
            if(b){
                jsonResult =  new JsonResult(1,"入驻成功",shopEntity.getId(),true);
            }else{
                jsonResult =  new JsonResult(2,"入驻失败","",true);
            }
        }
//        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 供应商入驻或代理商入驻
     */
    @RequestMapping("/shopUpdate")
    @ResponseBody
    public JsonResult shopUpdate(HttpServletRequest request,ShopEntity shopEntity){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
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

    /**
     * 轮播图
     * ytchen
     * 19-1-21
     */
    @RequestMapping("/shopads")
    @ResponseBody
    public JsonResult shopadg(HttpServletRequest request){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member!= null) {
            List<ShopEntity> shopEntity = shopService.shopdsLists(member.getId());
            System.out.println(shopEntity.size());
        if (shopEntity.size() == 1) {
            for (int i = 0; i <shopEntity.size() ; i++) {
                Integer id =shopEntity.get(i).getId().intValue();
                List<Integer> list=new ArrayList<Integer>();
                list.add(id);
            List<AdsEntity> adsEntity =adsService.AdsList(list,5);
                jsonResult =  new JsonResult(1,"登录情况下一家获取轮播图成功",adsEntity,true);
            }
        }else if (shopEntity.size() == 2) {

            for (int i = 0; i <shopEntity.size() ; i++) {
                Integer id =shopEntity.get(0).getId().intValue();
                List<Integer> list=new ArrayList<Integer>();
                list.add(id);
                Integer id1 =shopEntity.get(1).getId().intValue();
                List<Integer> list1=new ArrayList<Integer>();
                list1.add(id1);
                List<AdsEntity> adsEntity =adsService.AdsList(list,3);
                List<AdsEntity> adsEntity1 =adsService.AdsList(list1,2);
                adsEntity.addAll(adsEntity1);
                jsonResult =  new JsonResult(1,"登录情况下两家获取轮播图成功",adsEntity,true);
            }

        }else if (shopEntity.size() == 3) {

            for (int i = 0; i <shopEntity.size() ; i++) {
                Integer id = shopEntity.get(0).getId().intValue();
                List<Integer> list = new ArrayList<Integer>();
                list.add(id);
                Integer id1 = shopEntity.get(1).getId().intValue();
                List<Integer> list1 = new ArrayList<Integer>();
                list1.add(id1);
                Integer id2 = shopEntity.get(2).getId().intValue();
                List<Integer> list2 = new ArrayList<Integer>();
                list2.add(id2);
                List<AdsEntity> adsEntity = adsService.AdsList(list, 2);
                List<AdsEntity> adsEntity1 = adsService.AdsList(list1, 2);
                List<AdsEntity> adsEntity2 = adsService.AdsList(list2, 1);
                adsEntity.addAll(adsEntity1);
                adsEntity.addAll(adsEntity2);
                jsonResult = new JsonResult(1, "登录情况下三家获取轮播图成功",adsEntity, true);
            }
        }else if (shopEntity.size() == 4) {

            for (int i = 0; i <shopEntity.size() ; i++) {
                Integer id = shopEntity.get(0).getId().intValue();
                List<Integer> list = new ArrayList<Integer>();
                list.add(id);
                Integer id1 = shopEntity.get(1).getId().intValue();
                List<Integer> list1 = new ArrayList<Integer>();
                list1.add(id1);
                Integer id2 = shopEntity.get(2).getId().intValue();
                List<Integer> list2 = new ArrayList<Integer>();
                list2.add(id2);
                Integer id3 = shopEntity.get(3).getId().intValue();
                List<Integer> list3 = new ArrayList<Integer>();
                list3.add(id3);
                List<AdsEntity> adsEntity = adsService.AdsList(list, 2);
                List<AdsEntity> adsEntity1 = adsService.AdsList(list1, 1);
                List<AdsEntity> adsEntity2 = adsService.AdsList(list2, 1);
                List<AdsEntity> adsEntity3 = adsService.AdsList(list3, 1);
                adsEntity.addAll(adsEntity1);
                adsEntity.addAll(adsEntity2);
                adsEntity.addAll(adsEntity3);
                jsonResult = new JsonResult(1, "登录情况下四家获取轮播图成功",adsEntity, true);
            }

        }else if (shopEntity.size() == 5) {

            for (int i = 0; i <shopEntity.size() ; i++) {
                Integer id = shopEntity.get(0).getId().intValue();
                List<Integer> list = new ArrayList<Integer>();
                list.add(id);
                Integer id1 = shopEntity.get(1).getId().intValue();
                List<Integer> list1 = new ArrayList<Integer>();
                list1.add(id1);
                Integer id2 = shopEntity.get(2).getId().intValue();
                List<Integer> list2 = new ArrayList<Integer>();
                list2.add(id2);
                Integer id3 = shopEntity.get(3).getId().intValue();
                List<Integer> list3 = new ArrayList<Integer>();
                list3.add(id3);
                Integer id4 = shopEntity.get(4).getId().intValue();
                List<Integer> list4 = new ArrayList<Integer>();
                list4.add(id4);
                List<AdsEntity> adsEntity = adsService.AdsList(list, 1);
                List<AdsEntity> adsEntity1 = adsService.AdsList(list1, 1);
                List<AdsEntity> adsEntity2 = adsService.AdsList(list2, 1);
                List<AdsEntity> adsEntity3 = adsService.AdsList(list3, 1);
                List<AdsEntity> adsEntity4 = adsService.AdsList(list4, 1);
                adsEntity.addAll(adsEntity1);
                adsEntity.addAll(adsEntity2);
                adsEntity.addAll(adsEntity3);
                adsEntity.addAll(adsEntity4);
                jsonResult = new JsonResult(1, "登录情况下五家获取轮播图成功",adsEntity, true);
            }

        } else if (shopEntity.size() == 0) {

            System.out.println("未绑定工厂");

        }

        }else {
            //未登录的情况下轮播图展示
            List<ShopEntity> shopEntity = shopService.shopdstop();
            if (shopEntity.size() == 1) {
                for (int i = 0; i <shopEntity.size() ; i++) {
                    Integer id =shopEntity.get(i).getId().intValue();
                    List<Integer> list=new ArrayList<Integer>();
                    list.add(id);
                    List<AdsEntity> adsEntity =adsService.AdsList(list,5);
                    jsonResult =  new JsonResult(1,"未登录情况下一家获取轮播图成功",adsEntity,true);
                }
            }else if (shopEntity.size() == 2) {

                for (int i = 0; i <shopEntity.size() ; i++) {
                    Integer id =shopEntity.get(0).getId().intValue();
                    List<Integer> list=new ArrayList<Integer>();
                    list.add(id);
                    Integer id1 =shopEntity.get(1).getId().intValue();
                    List<Integer> list1=new ArrayList<Integer>();
                    list1.add(id1);
                    List<AdsEntity> adsEntity =adsService.AdsList(list,3);
                    List<AdsEntity> adsEntity1 =adsService.AdsList(list1,2);
                    adsEntity.addAll(adsEntity1);
                    jsonResult =  new JsonResult(1,"未登录情况下两家获取轮播图成功",adsEntity,true);
                }

            }else if (shopEntity.size() == 3) {

                for (int i = 0; i <shopEntity.size() ; i++) {
                    Integer id = shopEntity.get(0).getId().intValue();
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(id);
                    Integer id1 = shopEntity.get(1).getId().intValue();
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(id1);
                    Integer id2 = shopEntity.get(2).getId().intValue();
                    List<Integer> list2 = new ArrayList<Integer>();
                    list2.add(id2);
                    List<AdsEntity> adsEntity = adsService.AdsList(list, 2);
                    List<AdsEntity> adsEntity1 = adsService.AdsList(list1, 2);
                    List<AdsEntity> adsEntity2 = adsService.AdsList(list2, 1);
                    adsEntity.addAll(adsEntity1);
                    adsEntity.addAll(adsEntity2);
                    jsonResult = new JsonResult(1, "未登录情况下三家获取轮播图成功",adsEntity, true);
                }
            }else if (shopEntity.size() == 4) {

                for (int i = 0; i <shopEntity.size() ; i++) {
                    Integer id = shopEntity.get(0).getId().intValue();
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(id);
                    Integer id1 = shopEntity.get(1).getId().intValue();
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(id1);
                    Integer id2 = shopEntity.get(2).getId().intValue();
                    List<Integer> list2 = new ArrayList<Integer>();
                    list2.add(id2);
                    Integer id3 = shopEntity.get(3).getId().intValue();
                    List<Integer> list3 = new ArrayList<Integer>();
                    list3.add(id3);
                    List<AdsEntity> adsEntity = adsService.AdsList(list, 2);
                    List<AdsEntity> adsEntity1 = adsService.AdsList(list1, 1);
                    List<AdsEntity> adsEntity2 = adsService.AdsList(list2, 1);
                    List<AdsEntity> adsEntity3 = adsService.AdsList(list3, 1);
                    adsEntity.addAll(adsEntity1);
                    adsEntity.addAll(adsEntity2);
                    adsEntity.addAll(adsEntity3);
                    jsonResult = new JsonResult(1, "未登录情况下四家获取轮播图成功",adsEntity, true);
                }

            }else if (shopEntity.size() == 5) {

                for (int i = 0; i <shopEntity.size() ; i++) {
                    Integer id = shopEntity.get(0).getId().intValue();
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(id);
                    Integer id1 = shopEntity.get(1).getId().intValue();
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(id1);
                    Integer id2 = shopEntity.get(2).getId().intValue();
                    List<Integer> list2 = new ArrayList<Integer>();
                    list2.add(id2);
                    Integer id3 = shopEntity.get(3).getId().intValue();
                    List<Integer> list3 = new ArrayList<Integer>();
                    list3.add(id3);
                    Integer id4 = shopEntity.get(4).getId().intValue();
                    List<Integer> list4 = new ArrayList<Integer>();
                    list4.add(id4);
                    List<AdsEntity> adsEntity = adsService.AdsList(list, 1);
                    List<AdsEntity> adsEntity1 = adsService.AdsList(list1, 1);
                    List<AdsEntity> adsEntity2 = adsService.AdsList(list2, 1);
                    List<AdsEntity> adsEntity3 = adsService.AdsList(list3, 1);
                    List<AdsEntity> adsEntity4 = adsService.AdsList(list4, 1);
                    adsEntity.addAll(adsEntity1);
                    adsEntity.addAll(adsEntity2);
                    adsEntity.addAll(adsEntity3);
                    adsEntity.addAll(adsEntity4);
                    jsonResult = new JsonResult(1, "未登录情况下五家获取轮播图成功",adsEntity, true);
                }

            }


        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

}

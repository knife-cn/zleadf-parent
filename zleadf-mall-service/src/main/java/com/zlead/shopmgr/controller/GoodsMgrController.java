package com.zlead.shopmgr.controller;


//import com.sun.xml.internal.ws.spi.db.MethodGetter;
import com.zlead.dao.CatDao;
import com.zlead.dao.OaItemStateDao;
import com.zlead.entity.*;
import com.zlead.service.GoodsBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * 商品
 * @author fqf
 * @date 2018-07-25 11:41:37
 */
@Controller
@RequestMapping("/zlead/shopmgr")
public class GoodsMgrController {
    @Resource
    private GoodsBgService goodsBgService;
    
    @Resource
    private LoginUtil loginUtil;
    
    @Resource
    private CatDao catDao;

    @Resource
    private OaItemStateDao oaItemStateDao;//商品上下架接口

    @RequestMapping("/goodslist1")
    @ResponseBody
    public JsonResult goodslist(HttpServletRequest request,@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        
        if(member!=null && loginUtil.isShop(member)){
        	Map params = new HashMap();
        	long shop_id=member.getOwnShopid();
        	params.put("shop_id", shop_id);
        	//页数
        	PageBounds pageBounds = new PageBounds(pageNum,size);
        	PageList<GoodsEntity>  list = goodsBgService.getPage(params, pageBounds);
        	if(list!=null&&list.size()>0){
        		Page page =  new Page<GoodsEntity>(list, list.getPagination());
        		jsonResult =  new JsonResult(1,"列表信息",page,true);
        	}else{
        		jsonResult =  new JsonResult(2,"暂无数据信息","",false);
        	}
        	String result = JsonUtil.getJson(jsonResult);
        }
        return jsonResult;
    }
    
	//分类接口       查询所有分类
	@RequestMapping("catList")
	@ResponseBody
	public JsonResult catList(){
		JsonResult jsonResult = null;
		String result=null;
		Map params = new HashMap();
		List<CatEntity> catList = catDao.getList(params);
		if(catList.size()>0){
			jsonResult =  new JsonResult(1,"获取分类列表成功",catList,true);
		}else{
			jsonResult =  new JsonResult(2,"没有数据","",false);
		}
		
		//result = JsonUtil.getJson(jsonResult);
		//JsonUtil.render(response, result);
        return jsonResult;
	}

    /**
     * 商品上下架查询详情接口
     */
    @RequestMapping(value = "/goodsUpAndDownFrame",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult standUpAndDown(HttpServletRequest request,OaItemState oaItemState){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null){
            Integer itemId = oaItemState.getItemId();
            if (itemId == null || itemId == 0){
                return jsonResult = new JsonResult(2,"获取的商品id有误",false);
            }
            OaItemState oaItemState1 = oaItemStateDao.selectByPrimaryKey(itemId);
            if (oaItemState1 != null){
                jsonResult = new JsonResult(1,"查询成功",oaItemState1,true);
            }else{
                jsonResult = new JsonResult(1,"为查询到该商品的数据","",true);
            }
        }else{
            jsonResult = new JsonResult(2,"用户未登入",false);
        }
        return jsonResult;

    }

    /**
     *
     */
    @RequestMapping("/goodsinfo")
    @ResponseBody
    public JsonResult goodsinfo(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
		GoodsEntity tGoods = goodsBgService.findById(id);

        jsonResult =  new JsonResult(1,"详细信息",tGoods,true);
        //String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存
     */
    @RequestMapping("/goodssave")
    @ResponseBody
    public JsonResult goodssave(@Validated GoodsEntity tGoods){
        JsonResult jsonResult = null;
        goodsBgService.save(tGoods);

        jsonResult =  new JsonResult(1,"保存成功",tGoods,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/goodsupdate")
    @ResponseBody
    public JsonResult goodsupdate(@Validated GoodsEntity tGoods){
        JsonResult jsonResult = null;
            goodsBgService.save(tGoods);

        jsonResult =  new JsonResult(1,"修改成功",tGoods,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除
     */
    @RequestMapping("/goodsdelete")
    @ResponseBody
    public JsonResult delete(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
        goodsBgService.delete(id);

        jsonResult =  new JsonResult(1,"删除成功","",true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
    
    /**
     * 商品上架
     */
    @RequestMapping("/goodsOnSale")
    @ResponseBody
    public JsonResult goodsOnSale(@RequestParam("goodsId") Long goodsId,@RequestParam("stocknum") Long stocknum){
        JsonResult jsonResult = null;
        GoodsEntity goods=goodsBgService.findById(goodsId);
        goods.setIsMarket(1);
        if( goods.getStock()==null){
        	goods.setStock(0);
        }
        if(stocknum!=null){
        	goods.setStock(goods.getStock()+stocknum.intValue());
        }
        goodsBgService.update(goods); 

        jsonResult =  new JsonResult(1,"商品上架成功","",true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 商品修改
     */
    @RequestMapping("/goodsUpdate")
    @ResponseBody
    public JsonResult goodsUpdate(@Validated GoodsEntity tGoods,HttpServletRequest request){
        JsonResult jsonResult = null;
        SysUserEntity user = loginUtil.getLoginSysUser(request);
        //MemberEntity member = loginUtil.getLoginMember(request);
        if(user!=null){
            boolean b =  goodsBgService.goodsUpdate(tGoods);
            if(true){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        return jsonResult;
    }

    
    /**
     * 商品添加后台
     */
    /**
    @RequestMapping("/goodsSave")
    @ResponseBody
    public JsonResult goodsSave(@Validated GoodsEntity tGoods,HttpServletRequest request){
        JsonResult jsonResult = null;
        SysUserEntity user = loginUtil.getLoginSysUser(request);
        //MemberEntity member = loginUtil.getLoginMember(request);
        if(user==null){
            jsonResult =  new JsonResult(2,"用户未登录","",false);
            return jsonResult;
        }
            boolean b =  goodsBgService.sysGoodsSave(tGoods,user);
            if(true){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        return jsonResult;
    }
  **/
}

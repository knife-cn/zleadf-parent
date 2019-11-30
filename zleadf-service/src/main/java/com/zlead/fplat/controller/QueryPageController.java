package com.zlead.fplat.controller;

import com.zlead.dao.GoodsDao;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.fplat.entity.AgentbandList;
import com.zlead.fplat.service.AgentBandListService;
import com.zlead.fplat.service.MarketagentService;
import com.zlead.reception.service.GoodsService;
import com.zlead.service.GoodsBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.ObjectUtils;
import com.zlead.util.StrTools;
import com.zlead.util.ToolsUtils;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.RedisUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * 二级页面 / 搜索页面
 */
@RestController
@RequestMapping("query")
public class QueryPageController {
    private static final Logger logger = LoggerFactory.getLogger(QueryPageController.class);
    @Autowired
    private GoodsBgService goodsBgService;//商品接口
    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private AgentBandListService agentBandListService;

    @Autowired
    private MarketagentService marketagentService;

    /**
     * @param key 关键词
     * @param f   工厂id
     * @param b   品牌
     * @param l   系列
     * @param m   型号
     * @param c   分类
     * @param x   特殊条件
     * @param p   当前页
     * @param s   每页条数
     * @return 检索出来的商品
     */
    @RequestMapping(value = "goods", method = RequestMethod.GET)
    public JsonResult goods(HttpServletRequest request,
                            @RequestParam(value = "key", required = false) String key,
                            @RequestParam(value = "f", required = false) Long f,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "l", required = false) String l,
                            @RequestParam(value = "m", required = false) String m,
                            @RequestParam(value = "c", required = false) String c,
                            @RequestParam(value = "x", required = false) String x,
                            @RequestParam(value = "p", defaultValue = "1") Integer p,
                            @RequestParam(value = "s", defaultValue = "10") Integer s,
                            @RequestParam(value = "sour", required = false) String sour,
                            @RequestParam(value = "catids", required = false) String catids,
                            @RequestParam(value = "brandids", required = false) String brandids,
                            @RequestParam(value = "listids", required = false) String listids
    ) {
        JsonResult result = new JsonResult();
        try {
            sour = null;
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null) {
            	Map<String, Object> dataMap = new HashMap<>();
            	List<Map<String, Object>> goods= new ArrayList<>();
            	Map<String, Object> term = new HashMap<>();
            	int count = 0;
                if (member.getAgentId() != null && member.getAgentId() != 0) {
                    try {
                        redisUtil.saveNaviRedis(request,f,b,l,m,c,key);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Long agentId = member.getAgentId();
//                    result = goodsBgService.query(f, agentId, b, l, m, c, key, x, p, s);
                    //查询分类  c
//                    List<Map<String, Object>> maps = null;

                    //首页
                     if (null!=sour && sour.equals("1") ) {
                    	 term=agentBandListService.queryBandListOrModelList(f, agentId, b, l, m, c, key, x);
                         PageBounds rowBounds = new PageBounds(p,s);
                    	 if (StringUtils.isNotEmpty(catids)){
                             List<Long> catidsl = StrTools.strSpilt(catids);
                             count = goodsDao.QueryCountGoodsByCats(catidsl,agentId);
                             if (count > 0){
                                 goods = goodsDao.QueryGoodsByCats(catidsl,agentId,rowBounds);
                                 for (Map<String, Object> newGoods:goods ) {
                                     String fullName = StrTools.strDistinct(newGoods.get("full_name").toString());
                                     newGoods.put("full_name",fullName);
                                 }
                             }
                         }else if (StringUtils.isNotEmpty(listids)){
                        	//查询系列
                             List<Long> listidsl = StrTools.strSpilt(listids);
                             count = goodsDao.QueryCountGoodsBylist(listidsl,agentId);
                             if (count > 0){
                                 goods = goodsDao.QueryGoodsBylist(listidsl, agentId,rowBounds);
                                 for (Map<String, Object> newGoods:goods ) {
                                     String fullName = StrTools.strDistinct(newGoods.get("full_name").toString());
                                     newGoods.put("full_name",fullName);
                                 }
                             }
                         }else if (StringUtils.isNotEmpty(brandids)){
                        	//查询品牌
                             List<Long> brandidsl = StrTools.strSpilt(brandids);
                             count = goodsDao.QueryCountGoodsBybrand(brandidsl,agentId);
                             if (count > 0){
                                 goods = goodsDao.QueryGoodsBybrand(brandidsl, agentId,rowBounds);
                                 for (Map<String, Object> newGoods:goods ) {
                                     String fullName = StrTools.strDistinct(newGoods.get("full_name").toString());
                                     newGoods.put("full_name",fullName);
                                 }
                             }
                         }else{
                        	 //无参数的情况,选择其有权限查看的商品
                             result = goodsBgService.queryGoodsByPageByCondition(f, agentId, b, l, m, c, key, x, p, s,request);
                             Map<String, Object> resultMap = (Map<String, Object>) result.getData();
                             count = Integer.parseInt(resultMap.get("count").toString());
                             goods = (List<Map<String,Object>>)resultMap.get("goods");
                         }
                        
                        dataMap.put("count", count);
                        dataMap.put("goods", goods);
                        dataMap.put("term", term);
                        result.setCode(1);
                        result.setData(dataMap);
                        result.setSuccess(true); 
                        result.setMessage("查询成功");

                        // 店铺
                    } else if (null!=sour && sour.equals("2")) {
                    	term=agentBandListService.queryBandListOrModelList(f, agentId, b, l, m, c, key, x);
                         PageBounds rowBounds = new PageBounds(p,s);
                         if (StringUtils.isNotEmpty(catids)){
                             List<Long> catidsl = StrTools.strSpilt(catids);
                             count = goodsDao.facQueryCountGoodsByCats(catidsl,agentId,f);
                             if (count > 0){
                                 goods = goodsDao.facQueryGoodsByCats(catidsl,agentId,f,rowBounds);
                                 for (Map<String, Object> newGoods:goods ) {
                                     String fullName = StrTools.strDistinct(newGoods.get("full_name").toString());
                                     newGoods.put("full_name",fullName);
                                 }
                             }
                         }else if (StringUtils.isNotEmpty(listids)){
                             //查询系列
                             List<Long> listidsl = StrTools.strSpilt(listids);
                             count = goodsDao.facQueryCountGoodsBylist(listidsl,agentId,f);
                             if (count > 0){
                                 goods = goodsDao.facQueryGoodsBylist(listidsl, agentId, f,rowBounds);
                                 for (Map<String, Object> newGoods:goods ) {
                                     String fullName = StrTools.strDistinct(newGoods.get("full_name").toString());
                                     newGoods.put("full_name",fullName);
                                 }
                             }
                         }else if (StringUtils.isNotEmpty(brandids)){
                             //查询品牌
                             List<Long> brandidsl = StrTools.strSpilt(brandids);
                             count = goodsDao.facQueryCountGoodsBybrand(brandidsl,agentId,f);
                             if (count > 0){
                                 goods = goodsDao.facQueryGoodsBybrand(brandidsl, agentId, f,rowBounds);
                                 for (Map<String, Object> newGoods:goods ) {
                                     String fullName = StrTools.strDistinct(newGoods.get("full_name").toString());
                                     newGoods.put("full_name",fullName);
                                 }
                             }
                         }else{
                             //无参数的情况,选择其有权限查看的商品
                             result = goodsBgService.queryGoodsByPageByCondition(f, agentId, b, l, m, c, key, x, p, s,request);
                             Map<String, Object> resultMap = (Map<String, Object>) result.getData();
                             count = Integer.parseInt(resultMap.get("count").toString());
                             goods = (List<Map<String,Object>>)resultMap.get("goods");
                         }

                         dataMap.put("count", count);
                         dataMap.put("goods", goods);
                         dataMap.put("term", term);
                         result.setData(dataMap);
                         result.setCode(1);
                         result.setSuccess(true);
                         result.setMessage("查询成功");
                    } else{
                         term = agentBandListService.queryBandListOrModelList(f, agentId, b, l, m, c, key, x);
                         //无参数的情况,选择其有权限查看的商品
                         result = goodsBgService.queryGoodsByPageByCondition(f, agentId, b, l, m, c, key, x, p, s,request);
                         Map<String, Object> resultMap = (Map<String, Object>) result.getData();
                         count = Integer.parseInt(resultMap.get("count").toString());
                         goods = (List<Map<String,Object>>)resultMap.get("goods");
                         dataMap.put("count", count);
                         dataMap.put("goods", goods);
                         dataMap.put("term", term);
                         result.setData(dataMap);
                         result.setCode(1);
                         result.setSuccess(true);
                         result.setMessage("查询成功");
                    }

//                    result = goodsBgService.queryGoodsByPageNew(f, agentId, b, l, m, c, key, x, p, s);

                    //result = goodsBgService.queryGoodsByPageNew(f, agentId, b, l, m, c, key, x, p, s);
                    //result = goodsBgService.queryAgentGoodsByPage(f, agentId, b, l, m, c, key, x, p, s);


                } else {
                    result.setCode(2);
                    result.setSuccess(false);
                    result.setMessage("未关联代理商");
                }
            } else {
                result.setCode(2);
                result.setSuccess(false);
                result.setMessage("用户未登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            result.setCode(2);
            result.setSuccess(false);
            result.setMessage("搜索商品数据失败");
        }
        return result;
    }

    /**
     * 通过商品id查询当前商品是否已下架
     */
    @RequestMapping(value = "goodsIsMarket", method = RequestMethod.GET)
    public JsonResult goodsIsMarket(HttpServletRequest request,
                                    @RequestParam(value = "goodsId", required = true) Long goodsId){
        JsonResult jsonResult = new JsonResult();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && goodsId != null && goodsId != 0){
            GoodsEntity goodsEntity = goodsService.queryGoods(goodsId);

            if (ObjectUtils.isEmpty(goodsEntity) || goodsEntity.getIsMarket() != 1){
                jsonResult.setMessage("商品已下架，无法购买");
                jsonResult.setCode(2);
                jsonResult.setSuccess(false);
                return jsonResult;
            }
            if (goodsEntity.getProdType() == 0){
                //当前商品的代理权限
                AgentbandList agentbandList = agentBandListService.queryAgencyList(member.getAgentId().intValue(), null, goodsEntity.getListId().intValue());
                if ( null == agentbandList){
                    jsonResult.setMessage("该链接已失效！");
                    jsonResult.setData(agentbandList);
                    jsonResult.setCode(2);
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }else{
                    jsonResult.setMessage("可以购买");
                    jsonResult.setData(goodsEntity);
                    jsonResult.setCode(1);
                    jsonResult.setSuccess(true);
                    return jsonResult;
                }
            }else if(goodsEntity.getProdType() == 2 ){
                GoodsPageDto newGoods = marketagentService.queryVaildActivity(member.getAgentId(), goodsId);
                if (null != newGoods){
                    String effDate = newGoods.getEffDate();
                    String expDate = newGoods.getExpDate();
                    long effDateTime = ToolsUtils.getDateToHm(effDate);
                    long expDateTime = ToolsUtils.getDateToHm(expDate);
                    //系统时间
                    long sysTime = System.currentTimeMillis();
                    if (effDateTime > sysTime){
                        jsonResult.setMessage("该商品暂时不能购买！");
                        jsonResult.setData(newGoods);
                        jsonResult.setCode(2);
                        jsonResult.setSuccess(false);
                        return jsonResult;
                    }else if (expDateTime < sysTime){
                        jsonResult.setMessage("该活动商品，活动时间已过！");
                        jsonResult.setData(newGoods);
                        jsonResult.setCode(2);
                        jsonResult.setSuccess(false);
                        return jsonResult;
                    }else{
                        jsonResult.setMessage("可以购买");
                        jsonResult.setData(newGoods);
                        jsonResult.setCode(1);
                        jsonResult.setSuccess(true);
                        return jsonResult;
                    }
                }else {
                    jsonResult.setMessage("活动商品失效");
                    jsonResult.setData("");
                    jsonResult.setCode(2);
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }
            }
        }else {
            jsonResult.setMessage("未获取到用户信息");
            jsonResult.setCode(2);
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

}

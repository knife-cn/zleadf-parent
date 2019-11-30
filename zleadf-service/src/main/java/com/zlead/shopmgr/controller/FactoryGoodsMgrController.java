package com.zlead.shopmgr.controller;

import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.reception.service.GoodsService;
import com.zlead.shopmgr.service.FactoryGoodsService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/zlead/shopmgr")
public class FactoryGoodsMgrController {

    @Autowired
    private FactoryGoodsService factoryGoodsService;

    @Autowired
    private LoginUtil loginUtil;


    @Autowired
    private GoodsService goodsService;

    /**
     * @param factoryId 工厂id
     * @return 工厂的商铺中所有1级分类所关联的商品信息
     */
    @RequestMapping("/goodslist")
    @ResponseBody
    public JsonResult shopGoodList(HttpServletRequest request, @RequestParam("factoryId") Long factoryId) {
        JsonResult result = new JsonResult();
        try {
            MemberEntity member = loginUtil.getLoginMember(request);
            if (member != null && member.getAgentId() != null && member.getAgentId() != 0) {
                //查询工厂的所有一级分类
                List<Map<String, Object>> pactList = factoryGoodsService.findAllCatsByFactoryId(factoryId);
                if (CollectionUtils.isNotEmpty(pactList)) {
                    for (Map<String, Object> pMap : pactList) {
                        Set<Long> catIds = new HashSet<>();
                        //查询二级分类
                        Long cat_id = Long.parseLong(pMap.get("id").toString());
                        catIds.add(cat_id);
                        List<Map<String, Object>> childCats = factoryGoodsService.findChildCatsByPactId(factoryId, cat_id);
                        pMap.put("childCats", childCats);
                        if (CollectionUtils.isNotEmpty(childCats)) {
                            for (Map<String, Object> cMap : childCats) {
                                Long childCatId = Long.parseLong(cMap.get("id").toString());
                                catIds.add(childCatId);
                            }
                        }
                        List<Map<String, Object>> shopGoods = new ArrayList<>();
                        if (CollectionUtils.isNotEmpty(catIds)) {
                            List<Map<String, Object>> brands = factoryGoodsService.findBrandsByFactoryIdAndAgentId(factoryId, member.getAgentId());
                            Set<Long> brandIds = new HashSet<>();
                            Set<Long> listIds = new HashSet<>();
                            if (CollectionUtils.isNotEmpty(brands)) {
                                for (Map<String, Object> brandAndList : brands) {
                                    brandIds.add(Long.parseLong(brandAndList.get("band_id").toString()));
                                    //获取当前品牌关联的系列ids
                                    if (brandAndList.get("list_ids") != null) {
                                        String list_ids = brandAndList.get("list_ids").toString();
                                        if (StringUtils.isNotBlank(list_ids)) {
                                            for (String id : list_ids.split(",")) {
                                                listIds.add(Long.parseLong(id));
                                            }
                                        }
                                    }
                                }
                            }
                            if (CollectionUtils.isNotEmpty(brandIds) && CollectionUtils.isNotEmpty(listIds)) {
                                shopGoods = factoryGoodsService.findShopGoodsByCatIds(factoryId, member.getAgentId(), brandIds, listIds, catIds);
                            }
                        }
                        pMap.put("shopGoods", shopGoods);
                    }
                }
                result.setCode(1);
                result.setSuccess(true);
                result.setData(pactList);
            } else {
                result.setCode(2);
                result.setSuccess(false);
                result.setMessage("用户未登录");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("获取店铺商品数据失败");
            result.setCode(2);
        }
        return result;
    }

    @RequestMapping("/stock")
    @ResponseBody
    public JsonResult shopGoodsStock(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member)) {
            Integer shopStock = factoryGoodsService.findShopStock(member.getOwnShopid());
            params.put("shopStock", shopStock);
            jsonResult = new JsonResult(1, "列表信息", params, true);
        } else {
            jsonResult = new JsonResult(2, "信息获取失败", false);
        }
        return jsonResult;
    }

    @RequestMapping("/goodsTotalPrice")
    @ResponseBody
    public JsonResult goodsTotalPrice(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member)) {
            BigDecimal goodsTotalPrice = factoryGoodsService.findShopGoodsTotalPrice(member.getOwnShopid());
            params.put("goodsTotalPrice", goodsTotalPrice);
            jsonResult = new JsonResult(1, "列表信息", params, true);
        } else {
            jsonResult = new JsonResult(2, "信息获取失败", false);
        }
        return jsonResult;
    }

    @RequestMapping("/shopGoodsList")
    @ResponseBody
    public JsonResult shopGoods(HttpServletRequest request,
                                @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                @RequestParam(value = "size") @Validated @NotNull String pageSize) {
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member)) {
            PageList<GoodsEntity> shopGoodsList = factoryGoodsService.findShopGoodsInfo(member.getOwnShopid(), new PageBounds(Integer.parseInt(pageNum), Integer.parseInt(pageSize)));
            if (shopGoodsList != null && !shopGoodsList.isEmpty()) {
                Page<GoodsEntity> page = new Page<>(shopGoodsList, shopGoodsList.getPagination());
                jsonResult = new JsonResult(1, "列表信息", page, true);
            } else {
                jsonResult = new JsonResult(2, "无列表信息", false);
            }
        } else {
            jsonResult = new JsonResult(2, "信息获取失败", false);
        }
        return jsonResult;
    }


    @RequestMapping("/shopGoodsDetail")
    @ResponseBody
    public JsonResult shopGoods(HttpServletRequest request,
                                @RequestParam(value = "goodsId") @Validated @NotNull Long goodsId) {
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member) && goodsId != null) {
            GoodsEntity goodsDetail = factoryGoodsService.findGoodsDetail(member.getOwnShopid(), goodsId);
            if (goodsDetail != null) {
                params.put("goodsDetail", goodsDetail);
                jsonResult = new JsonResult(1, "列表信息", goodsDetail, true);
            } else {
                jsonResult = new JsonResult(2, "无列表信息", false);
            }
        } else {
            jsonResult = new JsonResult(2, "信息获取失败", false);
        }
        return jsonResult;
    }


    @RequestMapping("/setMarket")
    @ResponseBody
    public JsonResult setMarket(HttpServletRequest request,
                                @RequestParam(value = "goodsId") @Validated @NotNull Long goodsId,
                                @RequestParam(value = "isMarket") @Validated @NotNull Integer isMarket,
                                @RequestParam(value = "stocknum", required = false) @Validated Integer stocknum) {
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member)) {
            GoodsEntity goods = goodsService.findById(goodsId);
//            Integer version = goods.getVersion();//记录版本号
            //int count = factoryGoodsService.setMarket(version, goodsId, isMarket);

            if (isMarket != null && (isMarket == 0 || isMarket == 1))
                goods.setIsMarket(isMarket);
            else {
                jsonResult = new JsonResult(2, "上下架失败,上下架设置不正确", false);
                return jsonResult;
            }

//            if( goods.getStock()==null){
//            	goods.setStock(0);
//            }

//            if( goods.getVersion()==null){
//            	goods.setVersion(0);
//            	version=0;
//            }
//            
//            goods.setVersion(goods.getVersion()+1);

            if (stocknum != null && stocknum > 0 && isMarket > 0 && goods.getStock() != null)
                stocknum = goods.getStock() + stocknum.intValue();
            else if (stocknum == null || stocknum < 0)
                stocknum = 1;

            int count = factoryGoodsService.setMarket(goodsId, isMarket, stocknum);
            System.out.println(" setMarket : goodsId " + goodsId + " count: " + count);
//            if(count>=0){
            if (isMarket > 0)
                jsonResult = new JsonResult(1, "上架成功", true);
            else
                jsonResult = new JsonResult(1, "下架成功", true);
//            } else {
//                jsonResult = new JsonResult(2, "上下架失败", false);
//            }
        } else {
            jsonResult = new JsonResult(2, "更新失败", false);
        }
        return jsonResult;
    }
}

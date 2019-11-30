package com.zlead.shopmgr.api;

import com.zlead.entity.AclUserEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.GoodsAttrValueDto;
import com.zlead.entity.vo.GoodsAttrValueVo;
import com.zlead.entity.vo.GoodsDetailVo;
import com.zlead.entity.vo.GoodsListVo;
import com.zlead.fplat.entity.BaseWarehouse;
import com.zlead.fplat.service.BaseWarehouseService;
import com.zlead.reception.service.GoodsService;
import com.zlead.shopmgr.service.AclUserService;
import com.zlead.shopmgr.service.FactoryGoodsService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.AppUtil;
import com.zlead.utils.LoginUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Controller
@RequestMapping("/api/shopmgr")
public class FactoryGoodsAppController {

    @Autowired
    private FactoryGoodsService factoryGoodsService;

    @Autowired
    private LoginUtil loginUtil;


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BaseWarehouseService baseWarehouseService;


    /**
     * @param factoryId 工厂id
     * @return 工厂的商铺中所有1级分类所关联的商品信息
     */
    @RequestMapping("/goodslist")
    @ResponseBody
    public JsonResult shopGoodList(HttpServletRequest request, @RequestParam("factoryId") Long factoryId) {
        JsonResult result = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
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
        MemberEntity member = loginUtil.getAppLoginMember(request);
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
        MemberEntity member = loginUtil.getAppLoginMember(request);
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
                                @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                @RequestParam(value = "fullName", required = false) @Validated @NotNull String fullName,
                                @RequestParam(value = "brandId", required = false) @Validated @NotNull Integer brandId,
                                @RequestParam(value = "listId", required = false) @Validated @NotNull Integer listId,
                                @RequestParam(value = "modelId", required = false) @Validated @NotNull Integer modelId,
                                @RequestParam(value = "catId", required = false) @Validated @NotNull Integer catId
    ) {
        JsonResult jsonResult = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
                List<GoodsListVo> shopGoodsList = factoryGoodsService.findGoodsList(member.getId(), fullName,brandId,listId,modelId,catId, new PageBounds(Integer.parseInt(pageNum), Integer.parseInt(pageSize)));
//                AgentFac agentFac = factoryGoodsService.getAgentFactByMemberId(member.getId());
                if (shopGoodsList != null && !shopGoodsList.isEmpty()) {
                    for(GoodsListVo vo : shopGoodsList){
                        if("0".equals(vo.getStockType()) || 0 == vo.getIf_show_stock()){//如果上架库存类型为空，则返回-1前台处理显示为“--”
                            vo.setStock(-1);
                        }
                        if(0 == vo.getIf_show_price()){
                            vo.setMarketPrice(new BigDecimal("-1"));
                        }
                        BigDecimal marketPrice = null;
//                        if (ObjectUtils.isNotEmpty(agentFac)){
//                            switch (agentFac.getAgentDiscountType()){
//                                case "1":
//                                    marketPrice = vo.getBatchPrice().multiply(BigDecimal.valueOf(agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
//                                    break;
//                                case "2":
//                                    marketPrice = vo.getItemPrice().multiply(BigDecimal.valueOf(agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
//                                    break;
//                                case "3":
//                                    marketPrice = vo.getRetailPrice().multiply(BigDecimal.valueOf(agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
//                                    break;
//                                    default:
//                                        marketPrice = vo.getCostPrice();
//                                        break;
//                            }
//                        }
//                        vo.setMarketPrice(marketPrice);
//                        factoryGoodsService.updateGoodsListBySalePrice(vo);
                    }
                    jsonResult = new JsonResult(1, "列表信息", shopGoodsList, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            } else {
                jsonResult = new JsonResult(3, "未登录", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }

        return jsonResult;
    }

    @RequestMapping("/shopPrdInventList")
    @ResponseBody
    public JsonResult shopPrdInventList(HttpServletRequest request,
                                @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                @RequestParam(value = "fullName", required = false) @Validated @NotNull String fullName,
                                @RequestParam(value = "brandId", required = false) @Validated @NotNull Integer brandId,
                                @RequestParam(value = "listId", required = false) @Validated @NotNull Integer listId,
                                @RequestParam(value = "modelId", required = false) @Validated @NotNull Integer modelId,
                                @RequestParam(value = "catId", required = false) @Validated @NotNull Integer catId,
                                        @RequestParam(value = "agentId", required = false) @Validated @NotNull Integer agentId
    ) {
        JsonResult jsonResult = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            //获取登录用
            if (member != null) {
                //找到所有下级的员工
                Set<Integer> userIds = AppUtil.findSubUserByMember(member.getId());
                //找到所有管辖的库房
                Integer sysId = member.getOwnShopid().intValue();
                Set<Integer> whIds = baseWarehouseService.getWhIdByUserIds(userIds, sysId);
                List<GoodsListVo> shopGoodsList = factoryGoodsService.findPrdInventList(member.getId(),agentId, fullName,brandId,listId,modelId,catId,whIds, new PageBounds(Integer.parseInt(pageNum), Integer.parseInt(pageSize)));
                jsonResult = new JsonResult(1, "列表信息", shopGoodsList, true);
            } else {
                jsonResult = new JsonResult(3, "未登录", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }

        return jsonResult;
    }

    @RequestMapping("/shopGoodsDetail")
    @ResponseBody
    public JsonResult shopGoodsDetail(HttpServletRequest request,
                                      @RequestParam(value = "goodsId") @Validated @NotNull Integer goodsId) {
        JsonResult jsonResult = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
                GoodsDetailVo goodsDetail = factoryGoodsService.findShopGoodsDetail(goodsId);
                if (goodsDetail != null) {
                    jsonResult = new JsonResult(1, "列表信息", goodsDetail, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            } else {
                jsonResult = new JsonResult(3, "未登录", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }

    @RequestMapping("/shopPrdInventDetail")
    @ResponseBody
    public JsonResult shopPrdInventDetail(HttpServletRequest request,
                                          @RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                      @RequestParam(value = "goodsId") @Validated @NotNull Integer goodsId) {
        JsonResult jsonResult = new JsonResult();
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
                GoodsDetailVo goodsDetail = factoryGoodsService.findPrdInventDetail(agentId,goodsId,member.getOwnShopid().intValue());
                if (goodsDetail != null) {
                    jsonResult = new JsonResult(1, "列表信息", goodsDetail, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            } else {
                jsonResult = new JsonResult(3, "未登录", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
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
        MemberEntity member = loginUtil.getAppLoginMember(request);
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

    /**
     * 商品库存统计筛选
     * @param request
     * @param keyword 搜索关键字
     * @param warehouseId 库房
     * @param brandId 品牌
     * @param modelId 商品型号
     * @param listId 商品系列
     * @param catagoryId 商品分类
     * @return
     */
    @RequestMapping(value = "/goodsStockSummary")
    @ResponseBody
    public JsonResult goodsStockSummary(HttpServletRequest request,
                                        @RequestParam(value = "keyword", required = false) String keyword,
                                        @RequestParam(value = "warehouseId", required = false) Integer warehouseId,
                                        @RequestParam(value = "brandId", required = false) Integer brandId,
                                        @RequestParam(value = "modelId", required = false) Integer modelId,
                                        @RequestParam(value = "listId", required = false) Integer listId,
                                        @RequestParam(value = "catagoryId" ,required = false) Integer catagoryId){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        }else{
            Map<String, Object> summary = factoryGoodsService.goodsStockSummary(member.getId(), warehouseId, brandId, modelId, listId, catagoryId, keyword);
            if(!summary.isEmpty()){
                jsonResult = new JsonResult(1, "库存统计信息", summary,true);
            }else{
                jsonResult = new JsonResult(1, "无库存信息", null,true);
            }
        }
        return jsonResult;
    }


    @RequestMapping("/selectGoods")
    @ResponseBody
    public JsonResult getGoodsBy(@RequestBody GoodsAttrValueDto goodsAttrValueDto, HttpServletRequest request){
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if(member==null){
            jsonResult = new JsonResult(3,"未登录","",false);
            return jsonResult;
        }
        try {
            GoodsAttrValueVo goodsAttrValVo = goodsService.selectGoods(goodsAttrValueDto.getGoodsAttrValueReponses(),goodsAttrValueDto.getPrdId());
            if(null == goodsAttrValVo){
                return new JsonResult(1,"未查到商品信息!","",true);
            }
            if("0".equals(goodsAttrValVo.getStockType())){
                goodsAttrValVo.setStock(-1);
            }
            jsonResult =  new JsonResult(1,"返回商品详情!",goodsAttrValVo,true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult =  new JsonResult(2,"返回数据失败!","",false);
        }
        return jsonResult;
    }
}

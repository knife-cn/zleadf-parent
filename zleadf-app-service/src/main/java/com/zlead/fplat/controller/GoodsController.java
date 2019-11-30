package com.zlead.fplat.controller;

import com.zlead.common.PageResponse;
import com.zlead.entity.CollectEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OaMarketGoods;
import com.zlead.entity.dto.GoodsDto;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.reception.service.GoodsService;
import com.zlead.reception.service.MemberService;
import com.zlead.service.CollectService;
import com.zlead.service.OaMarketGoodsService;
import com.zlead.service.GoodsBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 前台商品的展示
 *
 * @author fqf
 * @date 2018-07-31 09:55:22
 */
@Controller
@RequestMapping("/zlead/fplat")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @Autowired
    private GoodsBgService goodsBgService;

    @Resource
    private LoginUtil loginUtil;

    @Autowired
    private CollectService collectService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private  OaMarketGoodsService oaMarketGoodsService;

//
//    @Autowired
//    private GoodsAttrValService goodsAttrValService;

    /**
     * 按照传入的商品个数值查询推荐商品
     */
    @RequestMapping("/homeGoodsList")
    @ResponseBody
    public JsonResult homeGoodsList(@RequestParam(name = "showNum", required = true) @Validated @NotNull Integer showNum) {
        JsonResult jsonResult = null;
        //输出的参数
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //查询最新的六个商品
        List<GoodsEntity> goodsList = goodsService.gethomeGoodsList(showNum);
        if (goodsList != null && goodsList.size() > 0) {
            for (GoodsEntity goodsEntity : goodsList) {
                //返回的信息
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", goodsEntity.getId());
                map.put("fullName", goodsEntity.getFullName());
                map.put("sales", goodsEntity.getSalesNum());
                map.put("price", goodsEntity.getPrice());
                map.put("pointPrice", goodsEntity.getPoint());
                map.put("img", goodsEntity.getFirstImg());
                map.put("supplyPrice", goodsEntity.getSupplyPrice());
                map.put("agentPrice", goodsEntity.getAgentPrice());
                map.put("marketPrice", goodsEntity.getMarketPrice());
                // map.put("pointType", goodsEntity.getPointType());//0-积分购 1-积分换 2-共享商城3-代理商城
                map.put("stock", goodsEntity.getStock());//库存
                list.add(map);
            }
            jsonResult = new JsonResult(1, "返回推荐商品", list, true);
        } else {
            jsonResult = new JsonResult(2, "没有查询到商品", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("推荐商品:" + result);
        return jsonResult;
    }

    /**
     * 查询商品列表
     *
     * @param
     * @param page
     * @return
     */
    @RequestMapping("/queryAgentgoodsList")
    @ResponseBody
    public JsonResult queryAgentgoodsList(@RequestParam(name = "page", required = true) @Validated @NotNull Integer page) {
        JsonResult jsonResult = null;
        try {
            //获取信息
            PageList<GoodsEntity> goodslist = goodsService.queryAgentGoodsList(page);
            if (goodslist != null && goodslist.size() > 0) {

                jsonResult = new JsonResult(1, "返回商品列表!", goodslist, true);
            } else {
                jsonResult = new JsonResult(2, "没有查询到商品列表!", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "返回数据失败!", e.getMessage(), false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 查询商品列表
     *
     * @param prodType
     * @param page
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public JsonResult goodsList(@RequestParam(name = "prodType") Integer prodType,
                                @RequestParam(name = "page", required = true) @Validated @NotNull Integer page,
                                @RequestParam(name = "shopId") Integer shopId) {
        JsonResult jsonResult = null;
        try {
            //获取信息
            PageList<GoodsEntity> goodslist = goodsService.getGoodsList(prodType, page, shopId);
            if (goodslist != null && goodslist.size() > 0) {

                jsonResult = new JsonResult(1, "返回商品列表!", goodslist, true);
            } else {
                jsonResult = new JsonResult(2, "没有查询到商品列表!", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "返回数据失败!", e.getMessage(), false);
        }
        return jsonResult;
    }

    /**
     * 商品详情接口
     */
    @RequestMapping("/goodsDetail")
    @ResponseBody
    public JsonResult goodsDetail(@RequestParam(name = "goodsId", required = true) @Validated @NotNull Long goodsId,
                                  @RequestParam(value = "actId", required = false) Long actId,
                                  HttpServletRequest request) {
        //获取商品的ID
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
            return jsonResult;
        }
        try {
            if (goodsId != 0) {

                GoodsEntity goodsEntity = goodsService.queryGoods(goodsId);

                //查询商品详情
                GoodsDto map = null;

                if (null!=goodsEntity){
                    if (goodsEntity.getProdType()==2) {
                        OaMarketGoods oaMarketGoods = new OaMarketGoods();
                        oaMarketGoods.setGoodsId(goodsId.intValue());
                        oaMarketGoods = oaMarketGoodsService.selectByPrimaryKey(oaMarketGoods);
                        map = goodsService.findActGoods(member.getAgentId(), oaMarketGoods.getActId().longValue(), goodsId);
                    }else if (goodsEntity.getProdType()==0){
                        map = goodsService.getGoodsDetail(goodsId, member.getAgentId().intValue());
                    }else{
                        System.out.println("有可能问积分商品。。。目前还没开发");
                    }

                    if(map==null){
                    	jsonResult = new JsonResult(3, "查无此商品的代理权限!", map, false);
                    }else{
                    	redisUtil.saveNaviRedis(request,map);
                        if (map.getGoodsDetailDto().getImgs() == null || "".equals(map.getGoodsDetailDto().getImgs())){
                            map.getGoodsDetailDto().setImgs(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                        }
                        if (map.getGoodsDetailDto().getFirstImag() == null || "".equals(map.getGoodsDetailDto().getFirstImag())){
                            map.getGoodsDetailDto().setFirstImag(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                        }

                    	jsonResult = new JsonResult(1, "返回商品详情!", map, true);
                    }
                }else {
                    jsonResult = new JsonResult(3, "此商品已下架!", map, false);
                }


            } else {
                jsonResult = new JsonResult(2, "传入的商品Id为空!", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jsonResult = new JsonResult(2, "返回数据失败!", "", false);
        }

        return jsonResult;
    }

    /**
     * 商品详情接口
     */
    @RequestMapping("/getAttr")
    @ResponseBody
    public JsonResult getAttr(@RequestParam(name = "goodsId", required = true) @Validated @NotNull Long goodsId, HttpServletRequest request) {
        //获取商品的ID
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
            return jsonResult;
        }
        try {
            if (goodsId != 0) {
                //查询商品详情
                GoodsDto map = goodsService.getAttr(goodsId, member.getAgentId().intValue());
                jsonResult = new JsonResult(1, "返回商品详情!", map, true);
            } else {
                jsonResult = new JsonResult(2, "传入的商品Id为空!", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "返回数据失败!", "", false);
        }

        String result = JsonUtil.getJson(jsonResult);
        //System.out.println("商品详情:" + result);
        return jsonResult;
    }


    /**
     * 查询收藏商品列表
     *
     * @param request
     * @param pageCurrent
     * @param pageSize
     * @return
     */
    @RequestMapping("/goodsCollect/{pageCurrent}/{pageSize}")
    @ResponseBody
    public JsonResult goodsCollectPageList(HttpServletRequest request, @PathVariable("pageCurrent") int pageCurrent,
                                           @PathVariable("pageSize") int pageSize) {
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
            return jsonResult;
        }
        try {
            PageResponse pageResponse = goodsService.pageListCollect(member.getAgentId().intValue(),
                    new PageBounds(null, null),request);
            if(pageResponse!=null&&pageResponse.getCount()>0){
                try {
                    String url = request.getHeader("Referer");
                    redisUtil.saveNaviRedis(request,4,url);
                }catch (Exception e){
                    e.printStackTrace();
                }

                jsonResult = new JsonResult(1, "返回商品详情!", pageResponse, true);
            }else{
                jsonResult = new JsonResult(2, "返回商品详情!", "", false);
            }
        } catch (Exception e) {
            jsonResult = new JsonResult(2, "返回数据失败!", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }




    @RequestMapping("/getGoods/{prdId}")
    @ResponseBody
    public JsonResult getGoodsBy(@RequestBody GoodsAttrValueReponse[] goodsAttrValueResponse, HttpServletRequest request,
                                 @PathVariable("prdId") int prdId) {
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
            return jsonResult;
        }
        try {
            if (goodsAttrValueResponse.length == 0) {
                return new JsonResult(2, "返回数据失败!", "", false);
            }
            GoodsDto goodsDto = goodsService.getByGoodsId(Arrays.asList(goodsAttrValueResponse), member.getAgentId().intValue(), prdId);
            if (null == goodsDto) {
                return new JsonResult(2, "未查到商品信息!", "", true);
            }
            jsonResult = new JsonResult(1, "返回商品详情!", goodsDto, true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jsonResult = new JsonResult(2, "返回数据失败!", "", false);
        }
        return jsonResult;
    }

    @RequestMapping("/getGoodsById/{cartId}")
    @ResponseBody
    public JsonResult getGoodsById(@RequestBody GoodsAttrValueReponse[] goodsAttrValueResponse, HttpServletRequest request,
                                   @PathVariable(name = "cartId", required = true) @Validated @NotNull Long cartId) {
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
            return jsonResult;
        }
        try {
            if (goodsAttrValueResponse.length == 0) {
                return new JsonResult(2, "返回数据失败!", "", false);
            }
            Map<String, Object> map = goodsService.getGoodsById(Arrays.asList(goodsAttrValueResponse), member, cartId);
            if (null == map) {
                return new JsonResult(3, "未查到商品信息!", "", true);
            } else {
                if ("1".equals(map.get("code"))) {
                    jsonResult = new JsonResult(1, "返回商品详情!", map.get("data"), true);
                    return jsonResult;
                } else if ("2".equals(map.get("code"))) {
                    jsonResult = new JsonResult(2, "合并商品!", map.get("data"), true);
                    return jsonResult;
                } else {
                    jsonResult = new JsonResult(3, "返回数据失败!", map.get("data"), true);
                }
            }
        } catch (Exception e) {
            jsonResult = new JsonResult(3, "返回数据失败!", "", false);
            e.printStackTrace();
        }
        return jsonResult;
    }


    /**
     * 添加商品收藏信息
     *
     * @param request
     * @param goodsId
     * @return
     */
    //@PostMapping("/saveCollect/{goodsId}")
    //@RequestMapping(value = "/saveCollect/{goodsId}",params ="goodsId",method ={RequestMethod.POST,RequestMethod.GET})
    @RequestMapping(value = "/saveCollect/{goodsId}",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JsonResult saveCollect(HttpServletRequest request, @PathVariable("goodsId") int goodsId) {
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            return new JsonResult(2, "找不到用户", "", false);
        }
        CollectEntity collectEntity = new CollectEntity();
        collectEntity.setAgentId(member.getAgentId().intValue());
        collectEntity.setMemberId(member.getId().intValue());
        collectEntity.setGoodsId(goodsId);
        return collectService.saveCollect(collectEntity);
    }

    /**
     * 删除商品收藏信息
     *
     * @param request
     * @param goodsId
     * @return
     */
    @PostMapping("/deleteCollect/{goodsId}")
    @ResponseBody
    public JsonResult deleteCollect(HttpServletRequest request, @PathVariable("goodsId") int goodsId) {
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            return new JsonResult(2, "找不到用户", "", false);
        }
        return collectService.deleteCollect(member.getAgentId().intValue(), goodsId);
    }

    /**
     * 删除全部的商品收藏信息
     *
     * @param request
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/deleteAllCollect",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteAllCollect(HttpServletRequest request, @RequestParam(name = "goodsId", required = true) @Validated @NotNull String goodsId) {
        //goodsIds
        List<Long> goodsIds = StrTools.strSpilt(goodsId);
        if (CollectionUtils.isEmpty(goodsIds)){
            return new JsonResult(2, "找不到商品", "", false);
        }
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            return new JsonResult(2, "找不到用户", "", false);
        }
        return collectService.batchDeleteCollect(member.getAgentId().intValue(), goodsIds);
    }



    /**
     * 头部搜索框根据关键字返回
     */
    @RequestMapping(value = "/queryNameKey", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult queryNameKey(HttpServletRequest request, @RequestParam("key") String key, @RequestParam(value = "factId", required = false) Long factId) {
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            return new JsonResult(2, "找不到用户", "", false);
        }
        return goodsBgService.queryNameKey(member.getAgentId(), factId, key);
    }

}

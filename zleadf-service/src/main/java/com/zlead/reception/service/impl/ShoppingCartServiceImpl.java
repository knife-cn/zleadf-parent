package com.zlead.reception.service.impl;
import ch.qos.logback.core.joran.action.ActionConst;
import com.google.common.collect.Lists;

import com.zlead.common.PromptMsg;
import com.zlead.dao.GoodsDao;
import com.zlead.dao.ShoppingCartDao;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.entity.ShoppingCartEntity;
import com.zlead.entity.dto.GoodsDetailDto;
import com.zlead.entity.vo.ShoppingCartVo;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.GoodsattrvalMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.AgentbandList;
import com.zlead.fplat.entity.Goodsattrval;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.service.AgentBandListService;
import com.zlead.fplat.service.CrmPrdModelService;
import com.zlead.reception.service.ShopService;
import com.zlead.reception.service.ShoppingCartService;
import com.zlead.service.GoodsBgService;
import com.zlead.util.CollectionSort;
import com.zlead.util.ObjectUtils;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.constant.Cnst;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsBgService goodsBgService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AgentFacMapper agentFacMapper;
    
    @Autowired
    private OaFactoryInfoMapper facinfoMapper;

    @Autowired
    private GoodsattrvalMapper goodsattrvalMapper;

    @Autowired
    private CrmPrdModelService crmPrdModelService;//型号接口

    @Autowired
    private OaFactoryInfoMapper oaFactoryInfoMapper;

    @Autowired
    private AgentBandListService agentBandListService;

    @Transactional(readOnly = true)
    public PageList<ShoppingCartEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = shoppingCartDao.findPage(params, rowBounds);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCartEntity findById(Long id) {
        return shoppingCartDao.findById(id);
    }

    @Override
    public void save(ShoppingCartEntity entity) {
        shoppingCartDao.insert(entity);
    }

    @Override
    public void update(ShoppingCartEntity entity) {
        shoppingCartDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        shoppingCartDao.delete(id);
    }

    /**
     * 查询购物车的信息
     */
    @Override
    public List<Map<String, Object>> getShoppingCart(MemberEntity memberEntity, ShoppingCartEntity shoppingCartEntity, Integer prodType,HttpServletRequest request){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Long memberId = memberEntity.getId();
        Long agentId = memberEntity.getAgentId();
        if (agentId==null){
            return null;
        }
        System.out.println(" getShoppingCart : 1 agentId "+agentId);
        List<AgentFac> agentFaclist = agentFacMapper.findFacListByAgentId(agentId.intValue());
        for(AgentFac agentFac:agentFaclist){
        if (agentFac==null){
        	return null;
        }
        System.out.println(" getShoppingCart : 2 agentId "+agentId+" factId: "+agentFac.getFactoryId() +" memberId: "+agentFac.getMemberId());
        Double agentDiscount = agentFac.getAgentDiscount();
        BigDecimal agentDiscountBig = new BigDecimal(agentDiscount==null ? 0 : agentDiscount);
        String agentDiscountType = agentFac.getAgentDiscountType();
        Map params = new HashMap();
        params.put("memberId",memberId);
        params.put("type",shoppingCartEntity.getBuyType());
//        params.put("prodType",prodType);
        if(shoppingCartEntity.getChannelType()!=null){
            params.put("channelType",shoppingCartEntity.getChannelType());
        }
        //查询企业购物车的时候需要企业的信息
        //if(shoppingCartEntity.getShopId()!=null){
        OaFactoryInfo fac=null;
        if(agentFac!=null && agentFac.getFactoryId()!=null){
        	fac=facinfoMapper.selectByPrimaryKey(agentFac.getFactoryId());
            //params.put("shopId",shoppingCartEntity.getShopId());a
        	if(fac!=null && fac.getShopId()!=null){
        		params.put("shopId",fac.getShopId());
        		System.out.println(" getShoppingCart : 3 agentId "+agentId+" shopId: "+fac.getShopId());
        	}
        }
        //先根据memberid,type去查询所有的购物车的店铺
        List<ShoppingCartEntity> shopList = shoppingCartDao.shopList(params);
        if(shopList!=null&&shopList.size()>0){
            for (ShoppingCartEntity shoppingCart : shopList) {
            	if(fac!=null && fac.getShopId()!=null){
                //返回的结果的map
                Map<String, Object> map = new HashMap<String, Object>();
                Long shopId = 0L;
                String shopName = "";
                //shopId = shoppingCart.getShopId();
                shopId=fac.getShopId().longValue();
                /*ShopEntity shop = shopService.findById(shopId);
                if(shop!=null){
                    shopName = shop.getShopName();
                }*/
                OaFactoryInfo facByShopId = oaFactoryInfoMapper.findFacByShopId(fac.getShopId());
                if (facByShopId != null){
                    shopName = facByShopId.getFactName();
                }
                map.put("shopId", shopId);
                //查询店铺名称
                map.put("shopName", shopName);
                Map cartParams = new HashMap();
                cartParams.put("memberId",memberId);
                cartParams.put("type",shoppingCartEntity.getBuyType());
//                cartParams.put("prodType",prodType);
                if(shoppingCartEntity.getChannelType()!=null){
                    cartParams.put("channelType",shoppingCartEntity.getChannelType());
                }
                cartParams.put("shopId",shopId);
                    //根据店铺的id查询购物车的信息
                List<ShoppingCartVo> cartInfoList = shoppingCartDao.findShoppingCart(cartParams);
                //输出的商品的list
                List<Map<String, Object>> goodsList = new ArrayList<Map<String, Object>>();
                    Map<String, Object> info =null;


                if(cartInfoList!=null&&cartInfoList.size()>0){
                    for (ShoppingCartVo shoppingCartVo :cartInfoList) {
                        Set<Long> goodsIds = new HashSet<Long>();
                        Long goodsId = shoppingCartVo.getGoodsId();
                        GoodsEntity cartGoods = goodsDao.findById(shoppingCartVo.getGoodsId());
                        if(cartGoods!=null){
                        GoodsDetailDto goodsDetailDto = goodsDao.queryDetailById(goodsId.intValue());
                        info =goodsBgService.findFactoryId(shoppingCartVo.getShopId());
                        //商品的map
                        Map<String, Object> goodsMap = new HashMap<String, Object>();
                        goodsMap.put("id", shoppingCartVo.getId());//购物车id
                        goodsMap.put("goodsId", shoppingCartVo.getGoodsId());//商品id
                        goodsMap.put("modelName", goodsDetailDto.getModelName());//型号名称
                        goodsMap.put("goodsName", shoppingCartVo.getGoodsName());//商品名称
                        goodsMap.put("prodType", cartGoods.getProdType());//是否是活动商品 2：活动商品
                        goodsMap.put("goodsImg", shoppingCartVo.getGoodsImg());//商品图片
                        if (shoppingCartVo.getGoodsImg() == null ||shoppingCartVo.getGoodsImg().equals("")){
                            goodsMap.put("goodsImg", StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                        }

                        AgentbandList agentbandList = null;
                            if (cartGoods.getProdType() == 0){
                            if (agentDiscountType==null || agentDiscountType.equals("1")){
                                if (cartGoods.getAgentPrice().compareTo(BigDecimal.ZERO) == 0 || cartGoods.getAgentPrice().compareTo(BigDecimal.ZERO) == -1){
                                    goodsMap.put("goodsPrice", cartGoods.getSupplyPrice());//成本价
                                }else {
                                    goodsMap.put("goodsPrice", cartGoods.getAgentPrice().multiply(agentDiscountBig));//经销价
                                }
                            } else if (agentDiscountType.equals("2")) {
                                if (cartGoods.getPrice().compareTo(BigDecimal.ZERO) == 0 || cartGoods.getPrice().compareTo(BigDecimal.ZERO) == -1){
                                    goodsMap.put("goodsPrice", cartGoods.getSupplyPrice());//成本价
                                }else {
                                    goodsMap.put("goodsPrice", cartGoods.getPrice().multiply(agentDiscountBig));//批发价
                                }
                            }
                            else if (agentDiscountType.equals("3")) {
                                if (cartGoods.getMarketPrice().compareTo(BigDecimal.ZERO) == 0 || cartGoods.getMarketPrice().compareTo(BigDecimal.ZERO) == -1){
                                    goodsMap.put("goodsPrice", cartGoods.getSupplyPrice());//成本价
                                }else {
                                    goodsMap.put("goodsPrice", cartGoods.getMarketPrice().multiply(agentDiscountBig));//零售价
                                }
                            }
                            /**
                            * 上架库存:0-空、1-自定义  2-实际库存
                            * 同步商品库存数
                            * */
                            Integer realStock=0;
                            if(null!=cartGoods && cartGoods.getId()>0 && cartGoods.getStockType().equals(1)){
                                realStock=goodsBgService.queryGoodsNewestStock(cartGoods);
                                if(null !=realStock){
                                    cartGoods.setStock(realStock);
                                    shoppingCartVo.setStock(realStock);
                                    goodsMap.put("stock", realStock);//商品库存
                                }
                            }
                        }else if (cartGoods.getProdType() == 2){
                            //当价格==0 || <0 的时候使用成本价
                            if (cartGoods.getShowPrice().compareTo(BigDecimal.ZERO) == -1 || cartGoods.getShowPrice().compareTo(BigDecimal.ZERO) == 0){
                                goodsMap.put("goodsPrice", cartGoods.getSupplyPrice());//成本价
                            }else {
                                goodsMap.put("goodsPrice", cartGoods.getShowPrice());//零售价
                            }
                        }
                        //进行四舍五入
                        String goodsPrice = goodsMap.get("goodsPrice").toString();
                        if (StringUtils.isNotBlank(goodsMap.get("goodsPrice").toString())){
                            BigDecimal bigDecimal = new BigDecimal(goodsPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                            goodsMap.put("goodsPrice",bigDecimal);
                        }
                        goodsMap.put("goodsPointPrice", shoppingCartVo.getPointPrice());//商品积分价格
                        goodsMap.put("shopId", shoppingCartVo.getShopId());//店铺id
                        goodsMap.put("facId", oaFactoryInfoMapper.findByShopId(shoppingCartVo.getShopId().intValue()));//店铺id
                        goodsMap.put("shopName", shoppingCartVo.getShopName());//店铺名称
                        goodsMap.put("count", shoppingCartVo.getCount());//商品购买数量
                        goodsMap.put("stock", shoppingCartVo.getStock());//商品库存
                        goodsMap.put("specNames", shoppingCartVo.getSpecNames());//商品规格名称
                        goodsMap.put("isMarketable", shoppingCartVo.getIsMarketable());//商品是否上下架
                            if (cartGoods.getProdType() == 0){
                                //查询当前普通商品是否有代理该品牌，系列
                                //当没有代理品牌，系列的情况下改为下架状态，前端显示为失效商品
                                agentbandList = agentBandListService.queryAgencyList(agentId.intValue(), null, cartGoods.getListId().intValue());
                                if (ObjectUtils.isEmpty(agentbandList)){
                                    goodsMap.put("isMarketable","0");
                                }
                            }
                        goodsMap.put("storeName", shoppingCartVo.getStoreName());//实体店名称
                        goodsMap.put("agentDiscount", agentDiscount);//折扣
                        map.put("facId", info);
                       // List<Goodsattrval> byGoodsId = goodsattrvalMapper.getByGoodsId(goodsId);


                        goodsMap.put("thisGoodsAttr", goodsattrvalMapper.getByGoodsId(goodsId));//

                        goodsIds.add(goodsId);
                        List<Map<String, Object>> byIds = crmPrdModelService.findByIds(goodsIds);//查询商品参数
                        String name = "";
                        if (CollectionUtils.isNotEmpty(byIds)) {
                            for (Map<String, Object> goodsModel : byIds) {
                                name = goodsModel.get("name").toString();
                            }
                        }
                        goodsMap.put("thisGoodsModel", name);
                        goodsList.add(goodsMap);
                        }
                    }
                    //对集合进行排序
                    CollectionSort.listContainMapSort(goodsList, "isMarketable");
                }
                        map.put("goodsList", goodsList);
                    list.add(map);
            	}
            }
            }
        }
        return list;
    }

    //添加购物车
    public String shoppingCartSave(ShoppingCartEntity shoppingCartEntity,MemberEntity memberEntity) {
        //1代表成功2代表失败
        String successType = "1";
        //购物车添加时间
        shoppingCartEntity.setMemberId(memberEntity.getId());
        shoppingCartEntity.setCreateDate(new Date());
        //shoppingCartEntity.setChannelType(0);
        shoppingCartEntity.setAdsId(0L);
        shoppingCartEntity.setIsBuy(0);
        try {
            //判断这个商品是否已经在购物车中有这个信息
            ShoppingCartEntity oldShoppingCart = shoppingCartDao.findByUnique(memberEntity.getId(),
                    shoppingCartEntity.getGoodsId(), shoppingCartEntity.getBuyType(),shoppingCartEntity.getShopId());
            //如果已经有这个信息了先把该信息删除然后重新加数量在生成一条新的信息
            if(oldShoppingCart!=null){
                //加上以前的数量
                shoppingCartEntity.setCount(shoppingCartEntity.getCount()+oldShoppingCart.getCount());
                //删除之前的这条信息
                shoppingCartDao.delete(oldShoppingCart.getId());
            }
            shoppingCartDao.insert(shoppingCartEntity);
            /*if (zxShoppingCart.getType().intValue() == Cnst.ShoppingCartCnst.TYPE_OFFLINE) { // 如果是线下店，只允许出现一家店的购物车，删除其他店的购物车
                zxShoppingCartMapper.deleteOtherStoreOfMember(zxShoppingCart.getStoreId(), zxShoppingCart.getMemberId(), zxShoppingCart.getType());
            }*/
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
            successType = "2";
        }
        return successType;
    }

    //删除购物车
    @Override
    public void delete(String ids) {
        shoppingCartDao.deleteIds(ids);
    }


    //通过memberid和goodsId删除购物车的信息
    @Override
    public void deleteBymemberIdAndGoodsId(Long memberId, Long goodsId){
        //查询购物车的信息
        ShoppingCartEntity shopCart = shoppingCartDao.shopingCartInfo(memberId, goodsId);
        if(shopCart!=null){
            shoppingCartDao.delete(shopCart.getId());
        }
    }
    /**
     * 通过商品ID查询未生成订单的购物车商品
     */
    @Override
    public ShoppingCartEntity shopingCartInfo(Long memberId, Long goodsId){
        //查询购物车的信息
        ShoppingCartEntity shopCart = shoppingCartDao.shopingCartInfo(memberId, goodsId);
        return shopCart;
    }



    /**
     * 判断库存
     * */
    public boolean stockCheck(int buyNum, GoodsEntity goods){
        int stock = goods.getStock();
        if(buyNum <= stock){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 冻结库存
     * @param buyNum 购买数量  goodsId 商品单号
     * */
    public boolean freezeStock(int buyNum, Long goodsId){
        GoodsEntity zxGood = goodsDao.findById(goodsId);

        Integer version = zxGood.getVersion();//记录版本号
        int result = goodsDao.freezeStock(goodsId, buyNum, version);
        System.out.println("result:"+result);
        if(result == 1){
            //更新库存成功
            return true;
        }
        return false;
    }

    /**
     * 解冻库存
     * @param buyNum 购买数量  goodsId 商品单号
     * */
    public boolean unfreezeStock(int buyNum, Long goodsId){
        try {
            GoodsEntity zxGood = goodsDao.findById(goodsId);

            zxGood.setFreezeStock(zxGood.getFreezeStock() - buyNum);
            zxGood.setStock(zxGood.getStock() + buyNum);
            goodsDao.update(zxGood);
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    /**
     * 修改购物车上商品的购买数量
     */
    @Override
    @Transactional
    public Map<String,Object> updateCount(Long cartId,Integer type,Integer count){
        Map<String,Object> map = new HashMap<>();
        try{
            //查询购物车的信息
            ShoppingCartEntity shoppingCartEntity = shoppingCartDao.findById(cartId);
            if(shoppingCartEntity==null){
                map.put("message","没有查询到购物车的信息");
                map.put("success",false);
                return map;
            }
            //查询该购物车的商品信息
            GoodsEntity goodsEntity = goodsDao.findById(shoppingCartEntity.getGoodsId()); 
            
            /**
             * 上架库存:0-空、1-自定义  2-实际库存
             * 同步商品库存数
             */
            Integer realStock=0;
            if(null!=goodsEntity && goodsEntity.getId()>0 && goodsEntity.getStockType().equals(1)){
            	realStock=goodsBgService.queryGoodsNewestStock(goodsEntity);
            	
            	if(null !=realStock)
            		goodsEntity.setStock(realStock);
            }
            if(goodsEntity==null){
                map.put("message","没有查询到商品信息");
                map.put("success",false);
                return map;
            }

            if(type!=1&&type!=2 && type!=3){
                map.put("message","操作失败");
                map.put("success",false);
                return map;
            }
            //修改购物车的数量
            if(type==1){
                //增加数量
                if(goodsEntity.getStock().intValue()<1 || goodsEntity.getStock().intValue()<count+1){
                    //数量不足
                    map.put("message","库存不足");
                    map.put("success",false);
                    return map;
                }
                //购物车增加数量
                shoppingCartEntity.setCount(shoppingCartEntity.getCount()+1);
            }else if(type==2){
                //购物车减少数量
            	if(shoppingCartEntity.getCount()<0){
            		//数量不足
                    map.put("message","购物数量不能为负数");
                    map.put("success",false);
                    return map;
            	}
                shoppingCartEntity.setCount(shoppingCartEntity.getCount()-1);
            }else if (type == 3){
            	//手工输入
                if(goodsEntity.getStock() < count){
                    //库存不足，无法购买
                    map.put("message", PromptMsg.GOODS_STOCK);
                    map.put("success",false);
                    return map;
                }
                shoppingCartEntity.setCount(count);
            }
            if (shoppingCartEntity.getCount()>0){
                shoppingCartDao.update(shoppingCartEntity);
                map.put("message","操作成功");
                map.put("success",true);
            }else {
                map.put("message","操作失败,购物车物品不能小于0");
                map.put("success",false);
            }
        }catch (Exception e) {
            // TODO: handle exception
            map.put("message","操作失败");
            map.put("success",false);
            return map;
        }
        return map;

    }


    /**
     * 修改购物车上商品的id
     */
    @Override
    @Transactional
    public Map<String,Object> updateGoodsId(Long cartId,Long goodsId,List<Long> otherIds){
        Map<String,Object> map = new HashMap<>();
        try{
            //查询购物车的信息
            ShoppingCartEntity shoppingCartEntity = shoppingCartDao.findById(cartId);
            if(shoppingCartEntity==null){
                map.put("message","没有查询到购物车的信息");
                map.put("success",3);
                return map;
            }
            //查询该购物车的商品信息
            GoodsEntity goodsEntity = goodsDao.findById(shoppingCartEntity.getGoodsId());
            if(goodsEntity==null){
                map.put("message","没有查询到商品信息");
                map.put("success",3);
                return map;
            }
            //修改购物车的数量
            if(goodsId!=null){
               for (int i = 0;i<otherIds.size();i++){
                   ShoppingCartEntity cartEntity = shoppingCartDao.findById(otherIds.get(i));
                   if (goodsId.equals(cartEntity.getGoodsId())){
                       Integer count = shoppingCartEntity.getCount();
                       cartEntity.setCount(cartEntity.getCount()+count);
                       map.put("message","商品已经存在购物车中");
                       map.put("success",2);
                }
               }
                shoppingCartEntity.setGoodsId(goodsId);
            }
            shoppingCartDao.update(shoppingCartEntity);
            map.put("message","操作成功");
            map.put("success",1);
        }catch (Exception e) {
        	e.printStackTrace();
            // TODO: handle exception
            map.put("message","操作失败");
            map.put("success",3);
            return map;
        }
        return map;

    }


}

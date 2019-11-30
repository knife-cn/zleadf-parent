package com.zlead.reception.service.impl;

import com.alibaba.fastjson.JSON;
import com.puqian.payment.apipay.commonUtil.StringUtil;
import com.zlead.constant.Cnst;
import com.zlead.dao.*;
import com.zlead.entity.*;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.entity.vo.OrderVo;
import com.zlead.entity.vo.ShoppingCartVo;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.dao.OaAgentUserinfoMapper;
import com.zlead.fplat.entity.*;
import com.zlead.fplat.service.*;
//import com.zlead.reception.service.GoodsService;
import com.zlead.reception.service.MemberAddressService;
import com.zlead.reception.service.OrderService;
import com.zlead.reception.service.ShopService;
import com.zlead.service.*;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.DateTool;
import com.zlead.utils.LoginUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AgentFacService agentFacService;

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    @Autowired
    private OaMarketGoodsService oaMarketGoodsService;//活动商品接口

    @Autowired
    private MemberAddressService memberAddressService;

    @Autowired
    private GoodsBgService goodsService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberAcctDao memberAcctDao;

    @Autowired
    private GoodsAttrValDao goodsAttrValDao;

    @Autowired
    private AccountChangesService accountChangesService;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private OaAgentUserinfoMapper oaAgentUserinfoMapper;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private OperatorlogDao operatorlogDao;

    @Autowired
    private OaFactoryInfoService oaFactoryInfoService;  //工厂接口

    @Autowired
    private CrmPrdModelService crmPrdModelService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private GoodsAttrValService goodsAttrValService;

    @Autowired
    private AgentBandListService agentBandListService;

    @Autowired
    private GoodsBgService goodsBgService;

    @Transactional(readOnly = true)
    @Override
    public PageList<OrderEntity> findPage(Long memberId, Map params, PageBounds rowBounds) {
        PageList list = orderDao.findPage(memberId, params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public PageList<OrderEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = orderDao.getPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderEntity findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public void save(OrderEntity entity) {
        orderDao.insert(entity);
    }

    @Override
    public void update(OrderEntity entity) {
        orderDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderEntity findBySn(String sn) {
        return orderDao.findBySn(sn);
    }

    /**
     * 商城订单列表
     */
    @Override
    public PageList<OrderVo> shopOrderList(boolean isPointOrder, String status, int pageNum, int size, MemberEntity member, Integer orderType, Long shopId) {
        PageList<OrderVo> orderList = null;
        try {
            if (!isPointOrder) {// 普通商品订单
                orderList = commonGoodsOrder(status, pageNum, size, member, orderType, shopId);
            } else {// 积分商品订单
                //orderList = pointGoodsOrder(isPointOrder,status, request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }


    /**
     * 无凭证订单列表
     */
    @Override
    public PageList<OrderEntity> noVoucherOrderList(Long memberId, PageBounds rowBounds) {

        PageList<OrderEntity> orderList = null;
        try {
            orderList = orderDao.findNoVoucherOrder(memberId, rowBounds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;

    }

    /**
     * 无凭证订单列表
     */
    @Override
    public PageList<OrderEntity> getAllnoVoucherOrderList(String memberIds, PageBounds rowBounds) {

        PageList<OrderEntity> orderList = null;
        try {
            orderList = orderDao.getAllnoVoucherOrderList(memberIds, rowBounds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;

    }


    // 普通商品订单
    @Override
    public PageList<OrderVo> commonGoodsOrder(String status, int pageNum, int size, MemberEntity member, Integer orderType, Long shopId) {
        PageList<OrderVo> orderList = null;
        try {
            Map<String, Object> params = new HashMap<>();
            //查看订单信息
            params.put("orderType", orderType);
            params.put("memberId", member.getId());//登录用户      member.getId()
            params.put("isDelete", 0);//未删除
            if (!status.equals("-1")) {
                params.put("status", status);
            }
            //如果是企业订单就查询该企业的订单
            if (shopId != null) {
                params.put("shopId", shopId);
            }
            orderList = orderDao.findPageOrderVo(params, new PageBounds(pageNum, size));
        } catch (RuntimeException e) {
            throw e;
        }
        return orderList;
    }

    /**
     * 立即购买
     */
    @Override
    public Map<String, Object> confirmOrder(String goodsId, String buyNum, MemberEntity memberEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long agentId = memberEntity.getAgentId();
        AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(agentId.intValue(), Integer.parseInt(goodsId));
        BigDecimal agentDiscount = BigDecimal.valueOf(1);
        if (agentFac == null || agentFac.getAgentDiscount() == null) {
            System.out.println(" confirmOrder agentFac.getAgentDiscount() 折扣为null agentId: " + agentId);
        } else {
            agentDiscount = new BigDecimal(agentFac.getAgentDiscount());
        }
        String agentDiscountType = agentFac.getAgentDiscountType();
        try {
            Long memberId = memberEntity.getId();

            int type = Cnst.ShoppingCartCnst.TYPE_POINT;// 类型：0线上购物车，1线下扫码购物车，2积分购物车
            MemberAddressEntity defaultAddress = memberAddressService.getFirstOfMember(memberId); // 默认地址
            Long goodsLong = 0L;
            if (!StringUtil.isNullOrEmpty(goodsId)) {
                goodsLong = Long.parseLong(goodsId);
            }
            GoodsEntity goods = goodsService.findById(goodsLong);
            if (goods.getIsMarket() == 0) {
                map.put("goodsName", goods.getFullName());
                map.put("sotckCheckmsg", goods.getFullName() + "商品已下架");
                map.put("isNotMarket", false);
                return map;
            }
            //库存判断
            boolean stockCheck = stockCheck(Long.parseLong(buyNum), goods);
            if (stockCheck == false) {
                map.put("sotckCheck", false);
                if (goods.getFullName() != null && goods.getFullName().length() > 8)
                    map.put("sotckCheckmsg", goods.getFullName().subSequence(0, 8) + "..商品库存不足,请更改购买数量后再进行结算");
                else if (goods.getFullName() != null)
                    map.put("sotckCheckmsg", goods.getFullName() + "商品库存不足,请更改购买数量后再进行结算");
                else
                    map.put("sotckCheckmsg", "商品库存不足,请更改购买数量后再进行结算");
                return map;
            }
            //获取商品参数名与参数值
            List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValDao.queryByGdsId(goods.getId().intValue());
            map.put("thisGoodsAttr", goodsAttrValDtos);
            //查找店铺名称
            Long shopId = goods.getShopId();
            BigDecimal price = new BigDecimal(0);
            if (goods.getProdType() == 0) {
                if (agentDiscountType == null || agentDiscountType.equals("1")) {
                    price = goods.getAgentPrice().multiply(agentDiscount);//经销价
                } else if (agentDiscountType.equals("2")) {
                    price = goods.getPrice().multiply(agentDiscount);//批发价
                } else if (agentDiscountType.equals("3")) {
                    price = goods.getMarketPrice().multiply(agentDiscount);//零售价
                }
                //判断当前商品是否有代理该品牌，系列
                AgentbandList agentbandList = agentBandListService.queryAgencyList(agentId.intValue(), null, goods.getListId().intValue());
                if (agentbandList == null){
                    //当前商品没有代理该品牌，系列
                    map.put("agentcyScope", "该链接已失效！");
                }
            } else if (goods.getProdType() == 2) {
                price = goods.getShowPrice();//商品展销价与活动价同一字段
            }
            if (price.compareTo(BigDecimal.ZERO) == -1 || price.compareTo(BigDecimal.ZERO) == 0) {
                price = goods.getSupplyPrice();
            }
            ShopEntity shop = shopService.findById(shopId);
            String shopName = "平台自营";
            if (shop != null) {
                shopName = shop.getShopName();
            }
            //标识线上购买商品,商城
            int buyType = Cnst.OrderCnst.BUY_TYPE_1;
            //进行商品单价四舍五入
            price = price.setScale(2,BigDecimal.ROUND_HALF_UP);
            //全部的价格进行四舍五入
            BigDecimal originalPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(price);
            originalPrice = originalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
            //BigDecimal totalPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(price).multiply(agentDiscount); // 总价格
            BigDecimal totalPrice = null;
            if (goods.getProdType() != 2) {
                totalPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(price);
            } else {
                totalPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(price); // 总价格
            }
            //对商品总价进行四舍五入
            totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

            long totalPoint = 0L;
            if (goods.getPoint() != null) {
                totalPoint = Integer.parseInt(buyNum) * goods.getPoint(); // 总积分
            }
            BigDecimal totalPointPrice = new BigDecimal(0.0);
//            if(goods.getPointPrice()!=null){
//                totalPointPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(goods.getPointPrice());
//            }
            CrmPrdModel crmPrdModel = null;
            if (goods != null && goods.getModelId() != null && !goods.getModelId().equals("0")) {
                //获得商品型号
                crmPrdModel = crmPrdModelService.selectByPrimaryKey(goods.getModelId().intValue());
            }

            Map<String, Object> goodInfo = new HashMap<String, Object>();
            goodInfo.put("id", goods.getId());
            goodInfo.put("modelId", crmPrdModel);
            goodInfo.put("fullName", goods.getFullName());
            goodInfo.put("proType", goods.getProdType());
            goodInfo.put("specNames", goods.getSpec());
            goodInfo.put("firstImg", goods.getFirstImg());
            goodInfo.put("count", buyNum);
            goodInfo.put("imgs", goods.getImgs());
            
            String imgs = goods.getImgs();
            if (StringUtils.isBlank(imgs)){                
                goodInfo.put("imgs", "/shopping/img/index/sl3.png");
            }
            if (goods.getFirstImg() == null || "".equals(goods.getFirstImg())){
            	goodInfo.put("firstImg", "/shopping/img/index/sl3.png");
            }
            if (goods.getProdType() == 0) {
//                goodInfo.put("price", price.multiply(agentDiscount));
                goodInfo.put("price", price);
            } else if (goods.getProdType() == 2) {
                goodInfo.put("price", price);
            }
            goodInfo.put("pointPrice", (goods.getPointPrice() != null) ? goods.getPointPrice() : 0);

            map.put("defaultAddress", defaultAddress);
            map.put("memberId", memberEntity.getId());
            map.put("type", type);
            map.put("goods", goodInfo);
            map.put("buyNum", buyNum);
            map.put("totalPointPrice", totalPointPrice);
            map.put("totalPrice", totalPrice);
            map.put("totalPoint", totalPoint);
            map.put("shopName", shopName);
            map.put("shopId", shopId);
            map.put("buyType", Cnst.OrderCnst.BUY_TYPE_1);
            map.put("originalPrice", originalPrice);
            map.put("discount", originalPrice.subtract(totalPrice));
            map.put("trans", 10.00f);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }


    /**
     * 暂时没用
     * 活动商品立即购买
     */
    public Map<String, Object> confirmActOrder(Long actId, String goodsId, String buyNum, MemberEntity memberEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long agentId = memberEntity.getAgentId();
        AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(agentId.intValue(), Integer.parseInt(goodsId));
        BigDecimal agentDiscount = BigDecimal.valueOf(1);
        if (agentFac == null || agentFac.getAgentDiscount() == null) {
            System.out.println(" confirmOrder agentFac.getAgentDiscount() 折扣为null agentId: " + agentId);
        } else {
            agentDiscount = new BigDecimal(agentFac.getAgentDiscount());
        }
        String agentDiscountType = agentFac.getAgentDiscountType();
        try {
            Long memberId = memberEntity.getId();

            int type = Cnst.ShoppingCartCnst.TYPE_POINT;// 类型：0线上购物车，1线下扫码购物车，2积分购物车
            MemberAddressEntity defaultAddress = memberAddressService.getFirstOfMember(memberId); // 默认地址
            Long goodsLong = 0L;
            if (!StringUtil.isNullOrEmpty(goodsId)) {
                goodsLong = Long.parseLong(goodsId);
            }
            GoodsEntity goods = goodsService.findById(goodsLong);
            if (goods.getIsMarket() == 0) {
                map.put("isNotMarket", false);
                return map;
            }
            //查询活动商品
            BigDecimal price = new BigDecimal(0);
            OaMarketGoods oaMarketGoods = null;
            if (actId != null && !actId.equals("0")) {
                oaMarketGoods = new OaMarketGoods();
                oaMarketGoods.setActId(actId.intValue());
                oaMarketGoods.setGoodsId(goods.getId().intValue());
                oaMarketGoods = oaMarketGoodsService.selectByPrimaryKey(oaMarketGoods);//获得活动商品对象
                price = oaMarketGoods.getSalePrice();//获得活动商品价格
            }

            //判断是否是活动商品 ProdType = 2 等于活动商品
            if (goods.getProdType() == 2) {
                boolean stockCheck = false;
                if (Integer.parseInt(buyNum) <= oaMarketGoods.getSaleQty()) {
                    stockCheck = true;
                }
                if (stockCheck == false) {
                    map.put("sotckCheck", false);
                    if (goods.getFullName() != null && goods.getFullName().length() > 8)
                        map.put("sotckCheckmsg", goods.getFullName().subSequence(0, 8) + "..商品库存不足,请更改购买数量后再进行结算");
                    else if (goods.getFullName() != null)
                        map.put("sotckCheckmsg", goods.getFullName() + "商品库存不足,请更改购买数量后再进行结算");
                    else
                        map.put("sotckCheckmsg", "商品库存不足,请更改购买数量后再进行结算");
                    return map;
                }
            } else if (goods.getProdType() == 0) {//普通商品
                //库存判断
                boolean stockCheck = stockCheck(Long.parseLong(buyNum), goods);
                if (stockCheck == false) {
                    map.put("sotckCheck", false);
                    if (goods.getFullName() != null && goods.getFullName().length() > 8)
                        map.put("sotckCheckmsg", goods.getFullName().subSequence(0, 8) + "..商品库存不足,请更改购买数量后再进行结算");
                    else if (goods.getFullName() != null)
                        map.put("sotckCheckmsg", goods.getFullName() + "商品库存不足,请更改购买数量后再进行结算");
                    else
                        map.put("sotckCheckmsg", "商品库存不足,请更改购买数量后再进行结算");
                    return map;
                }
            }
            //获取商品参数名与参数值
            List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValDao.queryByGdsId(goods.getId().intValue());

            /*if(price.doubleValue()<0.1){
                price=goods.getShowPrice();
            }*/
            //查找店铺名称
            Long shopId = goods.getShopId();
            ShopEntity shop = shopService.findById(shopId);
            String shopName = "平台自营";
            if (shop != null) {
                shopName = shop.getShopName();
            }
            //标识线上购买商品,商城
            int buyType = Cnst.OrderCnst.BUY_TYPE_1;
            //全部的价格
            BigDecimal originalPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(price);
            originalPrice = originalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

            BigDecimal totalPrice = null;
            if (goods.getProdType() != 2) {
                totalPrice = totalPrice.add(new BigDecimal(Integer.parseInt(buyNum)).multiply(price).multiply(agentDiscount)); // 总价格
            } else {
                totalPrice = totalPrice.add(new BigDecimal(Integer.parseInt(buyNum)).multiply(price)); // 总价格
            }
            totalPrice = totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

            BigDecimal actTotalPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(price);
            // 活动商品总价格,进行四舍五入
            actTotalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

            long totalPoint = 0L;
            if (goods.getPoint() != null) {
                totalPoint = Integer.parseInt(buyNum) * goods.getPoint(); // 总积分
            }
            BigDecimal totalPointPrice = new BigDecimal(0.0);
            if (goods.getPointPrice() != null) {
                totalPointPrice = new BigDecimal(Integer.parseInt(buyNum)).multiply(goods.getPointPrice());
            }
            //进行四舍五入
            totalPointPrice = totalPointPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
            CrmPrdModel crmPrdModel = null;
            if (goods != null && goods.getModelId() != null && !goods.getModelId().equals("0")) {
                //获得商品型号
                crmPrdModel = crmPrdModelService.selectByPrimaryKey(goods.getModelId().intValue());
            }

            Map<String, Object> goodInfo = new HashMap<String, Object>();
            goodInfo.put("id", goods.getId());
            goodInfo.put("modelId", crmPrdModel);
            goodInfo.put("fullName", goods.getFullName());
            goodInfo.put("specNames", goods.getSpec());
            goodInfo.put("firstImg", goods.getFirstImg());
            goodInfo.put("count", buyNum);
            goodInfo.put("imgs", goods.getImgs());
            goodInfo.put("price", price);//.multiply(agentDiscount)
            goodInfo.put("pointPrice", (goods.getPointPrice() != null) ? goods.getPointPrice() : 0);
            map.put("thisGoodsAttr", goodsAttrValDtos);
            map.put("defaultAddress", defaultAddress);
            map.put("memberId", memberEntity.getId());
            map.put("type", type);
            map.put("goods", goodInfo);
            map.put("buyNum", buyNum);
            map.put("totalPointPrice", totalPointPrice);
            map.put("totalPrice", actTotalPrice);//totalPrice
            map.put("totalPoint", totalPoint);
            map.put("shopName", shopName);
            map.put("shopId", shopId);
            map.put("buyType", Cnst.OrderCnst.BUY_TYPE_1);
            map.put("originalPrice", price);//originalPrice
            map.put("discount", price);//originalPrice.subtract(totalPrice)
            map.put("trans", 10.00f);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    /**
     * 立即购买库存判断
     */
    public boolean stockCheck(Long buyNum, GoodsEntity goodsEntity) {
    	 /**
         *  
         * 上架库存:0-空、1-实际库存 2-自定义
         * 同步商品库存数
         */
        Integer realStock=0;
        if(null!=goodsEntity && goodsEntity.getId()>0 && goodsEntity.getStockType().equals(1)){
        	realStock=goodsService.queryGoodsNewestStock(goodsEntity);
        	
        	if(null !=realStock)
        		goodsEntity.setStock(realStock);
        }
        int stock = goodsEntity.getStock();
        if (buyNum <= stock) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 直接购买提交订单
     */
    @Override
    public Map<String, Object> addOrder(OrderEntity orderEntity, Long addressId, Integer buyNum, Long goodsId, MemberEntity memberEntity, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long agentId = memberEntity.getAgentId();
        //AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(agentId.intValue(),orderEntity.getGoods().getId().intValue());
        AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(agentId.intValue(), goodsId.intValue());
        BigDecimal agentDiscount = new BigDecimal(agentFac.getAgentDiscount());
        orderEntity.setPriceType(agentFac.getAgentDiscountType() == null ? "1" : agentFac.getAgentDiscountType());
        String agentDiscountType = agentFac.getAgentDiscountType();
        try {
            OrderEntity order = null;
            long buyMemberId = memberEntity.getId();
//            long buyMemberId = 143L;
            checkBuyType(orderEntity.getBuyType());//检查购买类型
            checkBuyMemberId(buyMemberId);//检查买方用户id
            if (orderEntity.getOrderType() != 7 && addressId != 0) {
                checkAddressId(addressId, orderEntity.getBuyType());//商家服务订单时不需要提供收获地址
            }
            //查询店铺的信息
            ShopEntity shop = null;
            //代理商的折扣信息
//            BigDecimal disCount = new BigDecimal(0);
            BigDecimal disCount = new BigDecimal(0);
            if (orderEntity.getOrderType() == 10) {
                shop = shopService.findByMemberId(memberEntity.getMemberId());
                if (shop != null) {
                    disCount = new BigDecimal(shop.getDiscount());
                }
            }

            MemberAddressEntity memberAddressEntity = null;
            if (addressId == 0) {
                memberAddressEntity = memberAddressService.getFirstOfMember(memberEntity.getId());
            } else {
                //查询地址信息
                memberAddressEntity = memberAddressService.findById(addressId);
            }
            //查询购买的商品的信息
            GoodsEntity goods = goodsService.findById(goodsId);
            String shopName = "";
            //商品的金额
            BigDecimal goodsAmount = new BigDecimal(0);
            //商品的积分
            Long payPoint = 0L;
            //运单费
            BigDecimal shippingCost = new BigDecimal(0);
            //折扣后的金额
            BigDecimal disAmount = new BigDecimal(0);
            if (goods != null && goods.getId() != null) {
//                //支付的积分
//            	if(goods.getPoint()!=null)
//            		payPoint = goods.getPoint()*buyNum;
//                //商品的价格
//                BigDecimal goodsPrice = new BigDecimal(0);
//                if(orderEntity.getOrderType()==10){
//                    goodsPrice = goods.getAgentPrice();
//                    //代理商订单计算折扣金额
//                    disAmount = goods.getMarketPrice().multiply(disCount).multiply(new BigDecimal(buyNum));
//                }else if(orderEntity.getOrderType()==0||orderEntity.getOrderType()==11){
//                    goodsPrice = goods.getPrice();
//                }
                //当前商品为普通商品的时候，活动商品不需要代理也能给当前代理商看到
                if (goods.getProdType() == 0){
                    //判断当前商品是否有代理该品牌，系列
                    AgentbandList agentbandList = agentBandListService.queryAgencyList(agentId.intValue(), null, goods.getListId().intValue());
                    if (agentbandList == null){
                        map.put("agentcyScope", false);
                        return map;
                    }
                }



                BigDecimal goodsPrice = new BigDecimal(0);
                if (goods.getProdType() == 0) {
                    if (agentDiscountType == null || agentDiscountType.equals("1")) {
                        goodsPrice = goods.getAgentPrice().multiply(agentDiscount);//经销价
                        orderEntity.setPriceType("1");
                    } else if (agentDiscountType.equals("2")) {
                        goodsPrice = goods.getPrice().multiply(agentDiscount);//批发价
                        orderEntity.setPriceType("2");
                    } else if (agentDiscountType.equals("3")) {
                        goodsPrice = goods.getMarketPrice().multiply(agentDiscount);//零售价
                        orderEntity.setPriceType("3");
                    }
                } else if (goods.getProdType() == 2) {
                    goodsPrice = goods.getShowPrice();
                    
                    //活动商品的折扣为1
                    agentDiscount=BigDecimal.valueOf(1);
                    //当时活动商品的时候维护工厂字段。。price_type 价格类型:1-经销价,2-批发价,3-零售价,4-成本价,5-活动价
                    orderEntity.setPriceType("5");
                }
                if (goodsPrice.compareTo(BigDecimal.ZERO) == -1 || goodsPrice.compareTo(BigDecimal.ZERO) == 0) {
                    goodsPrice = goods.getSupplyPrice();
                    
                  //如果是成本价，则设置折扣为1
                    agentDiscount=BigDecimal.valueOf(1);
                    orderEntity.setPriceType("4");
                }
                //对商品单价进行四舍五入
                goodsPrice = goodsPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

                //计算商品的总价
                goodsAmount = goodsPrice.multiply(new BigDecimal(buyNum));
                ShopEntity shopEntity = shopService.findById(goods.getShopId());
                if (shopEntity != null) {
                    shopName = shopEntity.getShopName();
                }
                //对商品总价进行四舍五入
                goodsAmount = goodsAmount.setScale(2,BigDecimal.ROUND_HALF_UP);

                String orderIds = "";
                String orderSn = "";
                if (orderEntity.getBuyType() == Cnst.OrderCnst.BUY_TYPE_1) { // 线上购买商品
                    if (goods.getIsMarket() == 0) {
                        map.put("isNotMarket", false);
                        return map;
                    }
                    //冻结库存
                    boolean freezeStock = freezeStock(buyNum, goodsId);
                    if (!freezeStock) {
                        map.put("sotckCheck", false);
                        return map;
                    }
                    order = addOrderEntity(orderEntity, goods.getShopId(), shopName, memberEntity, memberAddressEntity, goodsAmount, payPoint, agentFac.getUserId());
                    if (order.getId() == null && order.getSn() != null) {
                        order = orderDao.findBySn(order.getSn());
                    }
                    //生成订单
                    //订单商品的信息
                    OrderGoodsEntity orderGoods = new OrderGoodsEntity();
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsName(goods.getFullName());
                    orderGoods.setGoodsImg(goods.getFirstImg());
                    
                    
                    orderGoods.setPrice(goodsPrice);
                    //如果是成本价，则设置折扣为1
                    if(goodsPrice == goods.getSupplyPrice())
                    	agentDiscount=BigDecimal.valueOf(1);
                    orderGoods.setDiscount(agentDiscount);
                    orderGoods.setCount(buyNum);
                    orderGoods.setSpareQty(buyNum);
                    orderGoods.setGoodsPoint(goods.getPoint());
                    orderGoods.setCommentStatus(0);
                    orderGoods.setStatus(0);
                    orderGoods.setCreateDate(new Date());
                    orderGoods.setOrderId(order.getId());
                    BigDecimal bigBuyNum = new BigDecimal(buyNum);
                    orderGoods.setGoodsTotalPrice((goodsPrice.multiply(bigBuyNum)).setScale(2,BigDecimal.ROUND_HALF_UP));
                    Integer insert = orderGoodsDao.insert(orderGoods);
                    if (null != insert && insert > 0) {
                        AgentFac agentFac1 = new AgentFac();
                        agentFac1.setFactoryId(agentFac.getFactoryId());
                        agentFac1.setAgentId(agentId.intValue());
                        agentFac1.setOrderDate(new Date());
                        agentFacService.updateByPrimaryKeySelective(agentFac1);
                    }
                    /**
                     * 生成日志操作数据
                     */
                    OperatorLog operatorLog = new OperatorLog();
                    operatorLog.setMemberId(memberEntity.getId().intValue());

                    List<OaAgentUserinfo> oaAgentUserinfos = oaAgentUserinfoMapper.selectUserInfosByAgentId(memberEntity.getAgentId());
                    for (OaAgentUserinfo oaAgentUserinfo : oaAgentUserinfos) {
                        operatorLog.setUserName(oaAgentUserinfo.getUserName());
                        if ("1".equals(oaAgentUserinfo.getMainUser())) {
                            operatorLog.setUserName(oaAgentUserinfo.getUserName());
                            break;
                        }
                    }
                    if (operatorLog.getUserName() == null) {
                        operatorLog.setUserName(memberEntity.getUsername());
                    }
//                    operatorLog.setUserName(memberEntity.getUsername());
                    operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
                    operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_CREATE);
                    operatorLog.setRemark("商城" + memberEntity.getId() + "创建成功");
                    operatorLog.setSn(orderEntity.getId().toString());
                    operatorLog.setLinkUrl(request.getRequestURI().toString());
                    operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
                    operatorLog.setOperatorParams(JSON.toJSONString(orderGoods));
                    operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
                    operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MALL);
                    operatorLog.setCreateDate(new Date());
                    operatorLog.setAddIp(request.getRemoteAddr());
                    operatorlogDao.insertSelectiveOne(operatorLog);

                    operatorLog.setRemark("商城" + memberEntity.getId() + "支付成功");
                    operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_PAY);
                    operatorlogDao.insertSelectiveOne(operatorLog);


                } else { // 实体店扫码购买商品
                    //orderIds = addOrderOfBuyType2(buyMemberId, orderGoodsList, addressId, shoppingCartList, storeBuyType);
                }
                if (order != null) {
                    //更新信息
                    if (disAmount.compareTo(new BigDecimal(0)) > 0 && disAmount.compareTo(goodsAmount) < 0) {
                        //添加返点的金额
                        order.setRebateAmount(goodsAmount.subtract(disAmount));
                    }
                    order.setDiscount(agentDiscount.floatValue());
                    order.setStatus(1);
                    //取出主要联系人
                    OrderEntity orderEntitys = updateOrderNameAndTel(memberEntity.getAgentId(),goods.getShopId());
                    order.setContactMan(orderEntitys.getContactMan());
                    order.setContactTel(orderEntitys.getContactTel());
                    order.setModifyDate(new Date());
                    order.setPaymentDate(new Date());
                    orderDao.update(order);
                    map.put("orderId", order.getId());
                    map.put("orderSn", order.getSn());
                    map.put("orderType", order.getOrderType());
                }
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                e.printStackTrace();
                throw (RuntimeException) e;
            } else
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return map;

    }

    /**
     * 目前没啥用
     * 直接购买提交活动订单
     */
    public Map<String, Object> addActOrder(Long actId, OrderEntity orderEntity, Long addressId, Integer buyNum, Long goodsId, MemberEntity memberEntity, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long agentId = memberEntity.getAgentId();
        //==============================
        AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(agentId.intValue(), goodsId.intValue());
        BigDecimal agentDiscount = new BigDecimal(agentFac.getAgentDiscount());//获取代理商折扣价
        //String agentDiscountType = agentFac.getAgentDiscountType();//代理商折扣类型 代理商折扣类型(1:经销价;2:批发价;3:零售价)
        //==============================
        try {
            OrderEntity order = null;
            long buyMemberId = memberEntity.getId();
//            long buyMemberId = 143L;
            checkBuyType(orderEntity.getBuyType());//检查购买类型
            checkBuyMemberId(buyMemberId);//检查买方用户id
            if (orderEntity.getOrderType() != 7) {
                checkAddressId(addressId, orderEntity.getBuyType());//商家服务订单时不需要提供收获地址
            }
            //查询店铺的信息
            ShopEntity shop = null;
            //===========================================
            //代理商的折扣信息
            BigDecimal disCount = new BigDecimal(0);
            if (orderEntity.getOrderType() == 10) {
                shop = shopService.findByMemberId(memberEntity.getMemberId());
                if (shop != null) {
                    disCount = new BigDecimal(shop.getDiscount());
                }
            }
            //===========================================
            //查询地址信息
            MemberAddressEntity memberAddressEntity = memberAddressService.findById(addressId);
            //查询购买的商品的信息
            GoodsEntity goods = goodsService.findById(goodsId);
            String shopName = "";
            //商品的金额
            BigDecimal goodsAmount = new BigDecimal(0);
            //商品的积分
            Long payPoint = 0L;
            //运单费
            BigDecimal shippingCost = new BigDecimal(0);
            //折扣后的金额
            BigDecimal disAmount = new BigDecimal(0);
            if (goods != null && goods.getId() != null) {
                OaMarketGoods oaMarketGoods = null;
                BigDecimal goodsPrice = new BigDecimal(0);
                if (actId != null && !actId.equals("0")) {
                    oaMarketGoods = new OaMarketGoods();
                    oaMarketGoods.setActId(actId.intValue());
                    oaMarketGoods.setGoodsId(goods.getId().intValue());
                    oaMarketGoods = oaMarketGoodsService.selectByPrimaryKey(oaMarketGoods);//获得活动商品对象
                    goodsPrice = oaMarketGoods.getSalePrice();//获得活动商品价格
                }
                /*if(goodsPrice.doubleValue()<0.1){
                    goodsPrice=goods.getShowPrice();
                }*/

                //计算商品的总价
                goodsAmount = goodsPrice.multiply(new BigDecimal(buyNum));
                //计算商品的总价,进行四舍五入
                goodsAmount = goodsAmount.setScale(2,BigDecimal.ROUND_HALF_UP);

                ShopEntity shopEntity = shopService.findById(goods.getShopId());//通过shopId获得shop对象
                if (shopEntity != null) {
                    shopName = shopEntity.getShopName();//取得shopName
                }

                String orderIds = "";
                String orderSn = "";
                if (orderEntity.getBuyType() == Cnst.OrderCnst.BUY_TYPE_1) { // 线上购买商品
                    if (goods.getIsMarket() == 0) {
                        map.put("isNotMarket", false);
                        return map;
                    }
                    //冻结库存
                    boolean freezeStock = freezeStock(buyNum, goodsId);
                    if (!freezeStock) {
                        map.put("sotckCheck", false);
                        return null;
                    }
                    order = addOrderEntity(orderEntity, goods.getShopId(), shopName, memberEntity, memberAddressEntity, goodsAmount, payPoint, agentFac.getUserId());
                    if (order.getId() == null && order.getSn() != null) {
                        order = orderDao.findBySn(order.getSn());
                    }
                    //生成订单
                    //订单商品的信息
                    OrderGoodsEntity orderGoods = new OrderGoodsEntity();
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsName(goods.getFullName());
                    orderGoods.setGoodsImg(goods.getFirstImg());
                    orderGoods.setPrice(goodsPrice);
                    
                    //活动商品的折扣为1 
                    //if(goodsPrice == goods.getSupplyPrice())
                    agentDiscount=BigDecimal.valueOf(1);
                    orderGoods.setDiscount(agentDiscount);
                    orderGoods.setCount(buyNum);
                    orderGoods.setSpareQty(buyNum);
                    orderGoods.setGoodsPoint(goods.getPoint());
                    orderGoods.setCommentStatus(0);
                    orderGoods.setStatus(0);
                    orderGoods.setCreateDate(new Date());
                    orderGoods.setOrderId(order.getId());
                    Integer insert = orderGoodsDao.insert(orderGoods);
                    if (null != insert && insert > 0) {
                        AgentFac agentFac1 = new AgentFac();
                        agentFac1.setFactoryId(agentFac.getFactoryId());
                        agentFac1.setAgentId(agentId.intValue());
                        agentFac1.setOrderDate(new Date());
                        agentFacService.updateByPrimaryKeySelective(agentFac1);
                        OaMarketGoods oaMarketGoods1 = new OaMarketGoods();
                        oaMarketGoods1.setActId(actId.intValue());
                        oaMarketGoods1.setGoodsId(goodsId.intValue());
                        int i = oaMarketGoods.getSaleQty() - buyNum;
                        oaMarketGoods1.setSaleQty(i);
                        oaMarketGoodsService.updateByPrimaryKeySelective(oaMarketGoods1);
                    }
                    /**
                     * 生成日志操作数据
                     */
                    OperatorLog operatorLog = new OperatorLog();
                    operatorLog.setMemberId(memberEntity.getId().intValue());
                    List<OaAgentUserinfo> oaAgentUserinfos = oaAgentUserinfoMapper.selectUserInfosByAgentId(memberEntity.getAgentId());
                    for (OaAgentUserinfo oaAgentUserinfo : oaAgentUserinfos) {
                        operatorLog.setUserName(oaAgentUserinfo.getUserName());
                        if ("1".equals(oaAgentUserinfo.getMainUser())) {
                            operatorLog.setUserName(oaAgentUserinfo.getUserName());
                            break;
                        }
                    }
                    if (operatorLog.getUserName() == null) {
                        operatorLog.setUserName(memberEntity.getUsername());
                    }
//                    operatorLog.setUserName(memberEntity.getUsername());
                    operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
                    operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_CREATE);
                    operatorLog.setRemark("商城" + memberEntity.getId() + "创建成功");
                    operatorLog.setSn(orderEntity.getId().toString());
                    operatorLog.setLinkUrl(request.getRequestURI().toString());
                    operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
                    operatorLog.setOperatorParams(JSON.toJSONString(orderGoods));
                    operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
                    operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MALL);
                    operatorLog.setCreateDate(new Date());
                    operatorLog.setAddIp(request.getRemoteAddr());
                    operatorlogDao.insertSelectiveOne(operatorLog);

                    operatorLog.setRemark("商城" + memberEntity.getId() + "支付成功");
                    operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_PAY);
                    operatorlogDao.insertSelectiveOne(operatorLog);


                } else { // 实体店扫码购买商品
                    //orderIds = addOrderOfBuyType2(buyMemberId, orderGoodsList, addressId, shoppingCartList, storeBuyType);
                }
                if (order != null) {
                    //更新信息
                    if (disAmount.compareTo(new BigDecimal(0)) > 0 && disAmount.compareTo(goodsAmount) < 0) {
                        //添加返点的金额
                        order.setRebateAmount(goodsAmount.subtract(disAmount));
                    }
                    order.setDiscount(agentDiscount.floatValue());
                    order.setStatus(1);
                    OrderEntity orderEntitys = updateOrderNameAndTel(memberEntity.getAgentId(),goods.getShopId());
                    order.setContactMan(orderEntitys.getContactMan());
                    order.setContactTel(orderEntitys.getContactTel());
                    orderDao.update(order);
                    map.put("orderId", order.getId());
                    map.put("orderSn", order.getSn());
                    map.put("orderType", order.getOrderType());
                }
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                e.printStackTrace();
                throw (RuntimeException) e;
            } else
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return map;

    }
 
    /**
     * 购物车下单
     */
    @Override
    public Map<String, Map<String, Object>> cartAddOrder(OrderEntity orderEntity, List<Integer> cartIds, List<Long> shopIds, Long addressId, MemberEntity memberEntity, HttpServletRequest request) {
        Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        Long agentId = memberEntity.getAgentId();

        try {
            OrderEntity order = null;
            long buyMemberId = memberEntity.getId();
//            long buyMemberId = 143L;
            checkBuyType(orderEntity.getBuyType());//检查购买类型
            checkBuyMemberId(buyMemberId);//检查买方用户id
            if (orderEntity.getOrderType() != 7) {
                checkAddressId(addressId, orderEntity.getBuyType());//商家服务订单时不需要提供收获地址
            }
            //查询代理商店铺的信息
            ShopEntity agentShop = null;
            //代理商的折扣信息
            BigDecimal disCount = new BigDecimal(0);
            if (orderEntity.getOrderType() == 10) {
                agentShop = shopService.findByMemberId(memberEntity.getMemberId());
                if (agentShop != null) {
                    disCount = new BigDecimal(agentShop.getDiscount());
                }
            }
            //查询地址信息
            MemberAddressEntity memberAddressEntity = memberAddressService.findById(addressId);
            //查询购买的商品的信息(通过购物车获取)
            List<ShoppingCartEntity> shoppingCartEntities = shoppingCartDao.cartByids(cartIds);
            String shopName = "";
            //商品的积分
            Long payPoint = 0L;
            //代理商折扣价格
            BigDecimal rebateAmount = new BigDecimal(0);
            //运单费
            BigDecimal shippingCost = new BigDecimal(0);
            System.out.println(" add order: 1 " + shopIds.size());
            shopIds = removeDuplicate(shopIds);
            System.out.println(" add order: 2 " + shopIds.size());
            if (shoppingCartEntities != null && shoppingCartEntities.size() > 0 && shopIds != null) {
                //购买的商品的店铺信息
                for (int i = 0; i < shopIds.size(); i++) {
                    for (ShoppingCartEntity cartEntity : shoppingCartEntities) {
                        GoodsEntity goods = goodsDao.findById(cartEntity.getGoodsId());
                        if (goods != null) {
                            if (goods.getIsMarket() == 0) {
                                Map<String, Object> isNotMarketMap = new HashMap<String, Object>();
                                isNotMarketMap.put("isNotMarket", false);
                                map.put("isNotMarket", isNotMarketMap);
                                return map;
                            }
                            boolean stockCheck = stockCheck(Long.valueOf(cartEntity.getCount()), goods);
                            if (stockCheck == false) {
                                Map<String, Object> sotckCheckMap = new HashMap<String, Object>();
                                sotckCheckMap.put("sotckCheck", false);
                                if (goods.getFullName() != null && goods.getFullName().length() > 8)
                                    sotckCheckMap.put("sotckCheckmsg", goods.getFullName().subSequence(0, 8) + "..商品库存不足,请更改购买数量后再进行结算");
                                else if (goods.getFullName() != null) {
                                    sotckCheckMap.put("sotckCheckmsg", goods.getFullName() + "商品库存不足,请更改购买数量后再进行结算");
                                } else {
                                    sotckCheckMap.put("sotckCheckmsg", "商品库存不足,请更改购买数量后再进行结算");
                                }
                                map.put("sotckCheck", sotckCheckMap);
                                return map;
                            }
                            if (goods.getProdType() == 0 ){
                                AgentbandList agentbandList = agentBandListService.queryAgencyList(agentId.intValue(), null, goods.getListId().intValue());
                                if (agentbandList == null){
                                    Map<String, Object> sotckCheckMap = new HashMap<String, Object>();
                                    sotckCheckMap.put("agentcyScope",false);
                                    map.put("agentcyScope", sotckCheckMap);
                                    return map;
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < shopIds.size(); i++) {
                    //获得关联工厂信息
                    AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(agentId.intValue(), shopIds.get(i).intValue());
                    BigDecimal agentDiscount = new BigDecimal(agentFac.getAgentDiscount());
                    orderEntity.setPriceType(agentFac.getAgentDiscountType() == null ? "1" : agentFac.getAgentDiscountType());
                    String agentDiscountType = agentFac.getAgentDiscountType();
                    //商品的金额
                    BigDecimal goodsAmount = new BigDecimal(0);
                    Map<String, Object> orderMap = new HashMap<String, Object>();
                    OrderEntity tempOrderEntity = new OrderEntity();
                    BeanUtils.copyProperties(orderEntity, tempOrderEntity);
                    //获取店铺信息
                    ShopEntity shopEntity = shopService.findById(shopIds.get(i));
                    if (shopEntity != null) {
                        shopName = shopEntity.getShopName();
                    }
                    if (tempOrderEntity.getBuyType() == Cnst.OrderCnst.BUY_TYPE_1) { // 线上购买商品
                        //生成订单
                        System.out.println(" add order: 生成订单 1 " + i + " shopId: " + shopIds.get(i) + " orderId: " + tempOrderEntity.getId());
                        order =  addOrderEntity(tempOrderEntity, shopIds.get(i), shopName,
                                memberEntity, memberAddressEntity, goodsAmount, payPoint, agentFac.getUserId());
                        System.out.println(" add order: 生成订单 2 " + i + " shopId: " + shopIds.get(i) + " orderId: " + order.getId());
                    } else { // 实体店扫码购买商品
                        //orderIds = addOrderOfBuyType2(buyMemberId, orderGoodsList, addressId, shoppingCartList, storeBuyType);
                    }

                    //查询商品的的信息
                    if (order != null) {
                        //折扣的金额
                        BigDecimal disAmount = new BigDecimal(0);
                        boolean flag = false; //假设没有活动商品
                        Set<Integer> prodType = new HashSet<Integer>();
                        for (ShoppingCartEntity cartEntity : shoppingCartEntities) {
                            GoodsEntity goods = goodsDao.findById(cartEntity.getGoodsId());
                            if (goods != null) {
                                if (shopIds.get(i).equals(goods.getShopId())) {
                                    //商品的价格
                                    BigDecimal goodsPrice = new BigDecimal(0);
                                    if (goods.getProdType() == 0) {
                                        if (agentDiscountType.equals("1") && goods.getSupplyPrice() != null) {
                                            goodsPrice = goods.getAgentPrice().multiply(agentDiscount);//经销价
                                            orderEntity.setPriceType("1");
                                        } else if (agentDiscountType.equals("2") && goods.getAgentPrice() != null) {
                                            goodsPrice = goods.getPrice().multiply(agentDiscount);//批发价
                                            orderEntity.setPriceType("2");
                                        } else if (agentDiscountType.equals("3") && goods.getMarketPrice() != null) {
                                            goodsPrice = goods.getMarketPrice().multiply(agentDiscount);//零售价
                                        }
                                    } else if (goods.getProdType() == 2) {
                                        goodsPrice = goods.getShowPrice();
                                    	//活动价格的折扣为1
                                        agentDiscount=BigDecimal.valueOf(1);
                                        
                                      //当时活动商品的时候维护工厂字段。。price_type 价格类型:1-经销价,2-批发价,3-零售价,4-成本价,5-活动价
                                        orderEntity.setPriceType("5");
                                    }
                                    //当价格==0 || <0 的时候使用成本价
                                    if (goodsPrice.compareTo(BigDecimal.ZERO) == -1 || goodsPrice.compareTo(BigDecimal.ZERO) == 0) {
                                        goodsPrice = goods.getSupplyPrice();
                                        
                                        //成本价的折扣为1
                                        agentDiscount=BigDecimal.valueOf(1);
                                        orderEntity.setPriceType("4");//当没有设置起代理价的时候使用成本价
                                    }
                                    //对商品单价进行四舍五入
                                    goodsPrice = goodsPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

                                    //计算商品的总价
                                    goodsAmount = goodsAmount.add(goodsPrice.multiply(new BigDecimal(cartEntity.getCount())));
                                    //计算商品的总价，进行四舍五入
                                    goodsAmount = goodsAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
                                    //支付的积分
                                    payPoint += goods.getPoint() * cartEntity.getCount();
                                    goods.setPrice(goodsPrice);
                                    goods.setDiscount(agentDiscount);
                                    //冻结库存
                                    boolean freezeStock = freezeStock(cartEntity.getCount(), goods.getId());
                                    if (!freezeStock) {
                                        return null;
                                    }
                                    //订单商品的信息
                                    addOrderGoods(goods, order, cartEntity.getCount());
                                    //获取所有商品的prodType，如果都为2下面会设置为活动价
                                    prodType.add(goods.getProdType());
                                }
                                //设置购物车信息为已购买
                                cartEntity.setIsBuy(1);
                                shoppingCartDao.update(cartEntity);
                            }
                        }
                        //更新信息
                        if (disAmount.compareTo(new BigDecimal(0)) > 0 && disAmount.compareTo(goodsAmount) < 0) {
                            //添加返点的金额
                            order.setRebateAmount(goodsAmount.subtract(disAmount));
                        }
                        //判断是否设置为活动价
                        for (Integer ii: prodType) {
                            if (ii == 2){//当订单中的商品都为活动商品的时候，priceType为活动价
                                flag = true;
                            }else {
                                flag = false;
                                break;
                            }
                        }
                        if (flag){
                            //价格类型:1-经销价,2-批发价,3-零售价,4-成本价,5-活动价
                            order.setPriceType("5");
                        }
                        //添加金额
                        order.setShippingCost(shippingCost);
                        order.setGoodsAmount(goodsAmount);
                        //order.setPayPoint(payPoint);
                        order.setStatus(1);
                        order.setDiscount(agentDiscount.floatValue());
                        order.setPayableAmount(goodsAmount.add(shippingCost));
                        order.setActualPayments(goodsAmount.add(shippingCost));
                        OrderEntity orderEntitys = updateOrderNameAndTel(memberEntity.getAgentId(),shopIds.get(i));
                        order.setContactMan(orderEntitys.getContactMan());
                        order.setContactTel(orderEntitys.getContactTel());
                        order.setModifyDate(new Date());
                        order.setPaymentDate(new Date());
                        orderDao.update(order);
                    }
                    orderMap.put("orderId", tempOrderEntity.getId());
                    orderMap.put("orderSn", tempOrderEntity.getSn());
                    orderMap.put("orderType", tempOrderEntity.getOrderType());
                    System.out.println(" add order: " + i + " shopId: " + shopIds.get(i) + " orderId: " + tempOrderEntity.getId());
                    map.put("order" + i, orderMap);

                    /**
                     * 生成日志操作数据
                     */
                    OperatorLog operatorLog = new OperatorLog();
                    operatorLog.setMemberId(memberEntity.getId().intValue());
                    List<OaAgentUserinfo> oaAgentUserinfos = oaAgentUserinfoMapper.selectUserInfosByAgentId(memberEntity.getAgentId());
                    for (OaAgentUserinfo oaAgentUserinfo : oaAgentUserinfos) {
                        operatorLog.setUserName(oaAgentUserinfo.getUserName());
                        if ("1".equals(oaAgentUserinfo.getMainUser())) {
                            operatorLog.setUserName(oaAgentUserinfo.getUserName());
                            break;
                        }
                    }
                    if (operatorLog.getUserName() == null) {
                        operatorLog.setUserName(memberEntity.getUsername());
                    }
//                    operatorLog.setUserName(memberEntity.getUsername());
                    operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
                    operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_CREATE);
                    operatorLog.setRemark("商城" + memberEntity.getId() + "创建成功");
                    operatorLog.setSn(order.getId().toString());
                    operatorLog.setLinkUrl(request.getRequestURI().toString());
                    operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
                    operatorLog.setOperatorParams(JSON.toJSONString(order));
                    operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
                    operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MALL);
                    operatorLog.setCreateDate(new Date());
                    operatorLog.setAddIp(request.getRemoteAddr());
                    operatorlogDao.insertSelectiveOne(operatorLog);

                    operatorLog.setRemark("商城" + memberEntity.getId() + "支付成功");
                    operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_PAY);
                    operatorlogDao.insertSelectiveOne(operatorLog);
                }
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                e.printStackTrace();
                throw (RuntimeException) e;
            } else
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return map;
    }

    // 检查购买类型
    private void checkBuyType(int buyType) throws Exception {
        if (buyType == Cnst.OrderCnst.BUY_TYPE_2) {
            throw new Exception("暂不支持小程序下单");
        }
        if (buyType != Cnst.OrderCnst.BUY_TYPE_1 && buyType != Cnst.OrderCnst.BUY_TYPE_3) {
            throw new Exception("订单来源不正确");
        }
    }

    // 检查买方用户ID
    private void checkBuyMemberId(long buyMemberId) throws Exception {
        if (buyMemberId <= 0) {
            throw new Exception("买方用户ID不能为空");
        }
    }

    // 检查地址
    private void checkAddressId(Long addressId, int buyType) throws Exception {
        if (buyType == Cnst.OrderCnst.BUY_TYPE_1) {
            if (addressId == null || addressId <= 0) {
                throw new Exception("地址不能为空");
            }
        }
        if (buyType == Cnst.OrderCnst.BUY_TYPE_2) {
            if (addressId == null || addressId.longValue() <= 0) {
                throw new Exception("地址不能为空");
            }
        }
    }


    /**
     * 冻结库存
     *
     * @param buyNum 购买数量  goodsId 商品单号
     */
    public boolean freezeStock(int buyNum, Long goodsId) {
        GoodsEntity goodsEntity = goodsService.findById(goodsId);

    	 /**
         * 上架库存:0-空、1-自定义  2-实际库存
         * 同步商品库存数
         */
        Integer realStock=0;
        if(null!=goodsEntity && goodsEntity.getId()>0 && goodsEntity.getStockType().equals(1)){
        	realStock=goodsService.queryGoodsNewestStock(goodsEntity);
        	
        	if(null !=realStock)
        		goodsEntity.setStock(realStock);
        }
        
        Integer stock = goodsEntity.getStock();//库存
        if (stock < buyNum) {
            return false;//库存不足
        }
        int i = 0;
        //while(true){
        goodsEntity = goodsService.findById(goodsId);
        Integer version = goodsEntity.getVersion();//记录版本号
        int result = goodsDao.freezeStock(goodsId, buyNum, version);
        System.out.println("修改库存是否成功 goodsId：" + goodsId + " result:" + result);
        if (result > 0) {
        	// 修改库存数
        	if(goodsEntity.getStockType()==1 && goodsEntity.getProdType() == 0){
                goodsService.updateGoodsRealStock(buyNum, 1, goodsEntity);
            }
//            zxGood = goodsService.findById(goodsId);
            /**
             * 下架条件 1 不自动下架 2 以日期 3 以库存'
             */
            if (goodsEntity.getMarketConfig() == 3){
                //当对库存进行改动后再次查询当前商品，获取最新库存
                goodsEntity = goodsService.findById(goodsId);
                setMarketOffByStock(goodsEntity);
            }
            return true;
        } else
            return false;
        /**
         if(result == 1){
         //更新库存成功
         return true;
         }else{
         //更新库存失败
         i++;
         if(i > 5){
         throw new RuntimeException();
         }
         // continue;
         }**/
    }
    //  }


    /**
     * 扣减冻结库存
     */
    public void minusFreezeStock(int buyNum, Long goodsId) {
        GoodsEntity goodsEntity = goodsDao.findById(goodsId);
        Integer sales = goodsEntity.getSalesNum();//售出数量
        Integer freezeStock = goodsEntity.getFreezeStock();//冻结库存
        goodsEntity.setFreezeStock(freezeStock - buyNum);
        goodsEntity.setSalesNum(sales + buyNum);
        goodsDao.update(goodsEntity);
    }

    /**
     * 恢复冻结库存
     */
    @Override
    public void releaseFreezeStock(int buyNum, Long goodsId) {
        GoodsEntity goodsEntity = goodsDao.findById(goodsId);
        Integer freezeStock = goodsEntity.getFreezeStock();//冻结库存
        Integer stock = goodsEntity.getStock();//库存
        goodsEntity.setFreezeStock(freezeStock - buyNum);
        goodsEntity.setStock(stock + buyNum);
        goodsDao.update(goodsEntity);
    }


    public OrderEntity addOrderEntity(OrderEntity orderEntity, Long shopId, String shopName, MemberEntity memberEntity,
                                      MemberAddressEntity memberAddressEntity, BigDecimal goodsAmount, Long payPoint, Integer userId) {
        BigDecimal shippingCost = new BigDecimal(0);

        String orderNumber = Cnst.SDF_SHORT.format(new Date()) + Cnst.getFixLengthString(4);
        /*Integer integer = orderService.queryOrderNumber(orderNumber);
        if (integer > 0){

        }*/

        //生成订单
        orderEntity.setUserId(userId);//业务员编号
        orderEntity.setSn(orderNumber);
        orderEntity.setShopId(shopId);
        orderEntity.setShippingType(1);
        orderEntity.setShopName(shopName);
        orderEntity.setPayType(1);
        orderEntity.setMemberId(memberEntity.getId());
        orderEntity.setMemberName(memberEntity.getRealName());
        if (memberAddressEntity != null) {
            orderEntity.setConsignee(memberAddressEntity.getMemberName());
            //获得省
            String province = regionService.getRegionName(memberAddressEntity.getProvinceId().toString());
            //获得市
            String city = regionService.getRegionName(memberAddressEntity.getCityId().toString());
            //获得区
            String area = regionService.getRegionName(memberAddressEntity.getRegionId().toString());
            orderEntity.setAddress(province +
                    city +
                    area +
                    memberAddressEntity.getAddress());
            orderEntity.setPostCode(memberAddressEntity.getPostcode());
            orderEntity.setPhone(memberAddressEntity.getPhone());
            orderEntity.setEmail(memberAddressEntity.getEmail());
        }
        orderEntity.setNeedInvoide(0);
        orderEntity.setCreateDate(new Date());
        orderEntity.setIsExpire(0);//是否过期
        orderEntity.setIsDelete(0);
        orderEntity.setStatus(0);

        //添加金额
        orderEntity.setShippingCost(shippingCost);
        orderEntity.setGoodsAmount(goodsAmount);
        orderEntity.setPayPoint(payPoint);
        orderEntity.setPayableAmount(goodsAmount.add(shippingCost));
        orderEntity.setActualPayments(goodsAmount.add(shippingCost));
        orderEntity.setShippingCost(new BigDecimal(0));
        try {
            //保存订单信息
            orderDao.insert(orderEntity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("有可能出现订单号重复的情况下。。。目前订单号是由年月日+4为随机数");
        }

        System.out.println(orderEntity.getId());
        return orderEntity;
    }

    /**
     * 添加订单商品信息
     */
    public void addOrderGoods(GoodsEntity goods, OrderEntity order, int buyNum) {
        if (order != null) {
            BigDecimal bigBuyNum = new BigDecimal(buyNum);
            //订单商品的信息
            OrderGoodsEntity orderGoods = new OrderGoodsEntity();
            orderGoods.setGoodsId(goods.getId());
            orderGoods.setGoodsName(goods.getFullName());
            orderGoods.setGoodsImg(goods.getFirstImg());
            orderGoods.setPrice(goods.getPrice());
            orderGoods.setCount(buyNum);
            orderGoods.setSpareQty(buyNum);
            orderGoods.setGoodsPoint(goods.getPoint());
            orderGoods.setDiscount(goods.getDiscount()); 
            orderGoods.setCommentStatus(0);
            orderGoods.setStatus(0);
            orderGoods.setCreateDate(new Date());
            orderGoods.setGoodsTotalPrice((goods.getPrice().multiply(bigBuyNum)).setScale(2,BigDecimal.ROUND_HALF_UP));
            orderGoods.setOrderId(order.getId());
            orderGoodsDao.insert(orderGoods);
            
            //修改商品的实际库存     在冻结库存处已处理 
//            if(goods.getStockType()==2)
//            goodsService.updateGoodsRealStock(buyNum, 1, goods);
        }
    }

    /**
     * 销售订单
     */
    @Override
    public PageList<OrderEntity> sellOrderList(Long memberId, Integer pageNum, Integer size, Integer status) {
        Map<String, Object> param = new HashMap<>();
        PageBounds pageBounds = new PageBounds(pageNum, size);
        param.put("pageBounds", pageBounds);
        param.put("status", status);
        param.put("memberId", memberId);
        return orderDao.findSellOrderList(param);
    }

    /**
     * 根据订单id发货
     *
     * @param order
     * @return
     */

    @Override
    public Boolean sendGoodsOrder(OrderEntity order) {

        OrderEntity orderEntity = orderDao.findById(order.getId());
        if (orderEntity != null) {
            orderEntity.setStatus(2);//订单状态变为带收货
            orderDao.update(orderEntity);
            return true;
        }
        return false;
    }


    /**
     * 支付回调
     *
     * @param orderSn    订单编号
     * @param paySn      支付流水号
     * @param payChannel 支付渠道 1支付宝，2银联，3微信,4线下现金支付，5线下对公转账支 7拉卡拉支付  ,propagation=Propagation.REQUIRES_NEW REQUIRES_NEW
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public synchronized void payCallBack(String orderSn, String paySn, int payChannel, String systemId, String total_amount) {
        try {
            OrderEntity orderEntity = orderDao.findBySn(orderSn);
//            ZxShop zxShop = null;

            if (orderEntity != null) {//&& zxOrder.getOrderType().intValue() == Cnst.OrderCnst.TYPE_GOODS
                if (orderEntity.getStatus().intValue() == Cnst.OrderCnst.STATUS_WAIT_PAY) {//待付款状态

                    //获得日志需要字段
                    Long memberId = orderEntity.getMemberId();
                    MemberEntity member = memberDao.findById(memberId);

                    //处理订单信息
                    orderEntity.setActualPayments(new BigDecimal(total_amount));
                    updateOrderInfo(orderEntity, payChannel);
                    //查询店铺的信息
                    ShopEntity shopEntity = shopService.findById(orderEntity.getShopId());
                    if (orderEntity.getOrderType() == 0) {
                        updateGoodsInfo(orderEntity);
                        //给平台加钱
                        accountChangesService.addAmount("1", 1, orderEntity.getPayableAmount(),
                                "", "1", "商品订单加钱,平台加钱");
                        //冻结
                        accountChangesService.freezeAmount("1", 1, orderEntity.getPayableAmount(),
                                "", "1", "商品订单冻结,平台冻结");
                    } else if (orderEntity.getOrderType() == 10) {
                        //代理商订单
                        updateGoodsInfo(orderEntity);
                        //给平台加钱
                        accountChangesService.addAmount("1", 1, orderEntity.getPayableAmount(),
                                "", "1", "代理商订单加钱,平台加钱");
                        //冻结
                        accountChangesService.freezeAmount("1", 1, orderEntity.getPayableAmount(),
                                "", "1", "代理商订单冻结,平台冻结");

                        //给代理商加钱
                        accountChangesService.addAmount(member.getMemberId(), 1, orderEntity.getRebateAmount(),
                                "", "1", "代理商订单加钱,代理商加钱");
                        //冻结
                        accountChangesService.freezeAmount(member.getMemberId(), 1, orderEntity.getRebateAmount(),
                                "", "1", "代理商订单冻结,代理商冻结");
                    } else if (orderEntity.getOrderType() == 11) {
                        //给企业账户加钱
                        //给代理商加钱
                        accountChangesService.addAmount(shopEntity.getMemberid(), 1, orderEntity.getRebateAmount(),
                                "", "1", "会员企业订单加钱,企业加钱");
                        //冻结
                        accountChangesService.freezeAmount(shopEntity.getMemberid(), 1, orderEntity.getRebateAmount(),
                                "", "1", "会员企业订单冻结,企业冻结");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    /**
     * 修改订单状态
     */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    void updateOrderInfo(OrderEntity orderEntity, int payChannel) {
        //更新订单及相关会员卡信息
        List<OrderGoodsEntity> orderGoodses = orderGoodsDao.findListByOrderId(orderEntity.getId());
        int status = Cnst.OrderCnst.STATUS_SUCCESS; //已完成
        if (orderEntity.getBuyType().intValue() == Cnst.OrderCnst.BUY_TYPE_3 || orderEntity.getBuyType().intValue() == Cnst.OrderCnst.BUY_TYPE_4 ||
                orderEntity.getBuyType().intValue() == Cnst.OrderCnst.BUY_TYPE_1) {
            status = Cnst.OrderCnst.STATUS_WAIT_SEND; //待发货
        }
        for (OrderGoodsEntity orderGoods : orderGoodses) {
            orderGoods.setStatus(status);
            orderGoodsDao.update(orderGoods);
        }
        if (payChannel < 4) {
            orderEntity.setPayType(Cnst.OrderCnst.PAY_TYPE_ONLINE);
        }
        orderEntity.setStatus(1);
        orderEntity.setPaymentDate(new Date());//设置完成付款时间
        orderDao.update(orderEntity);
    }

    /**
     * 支付完成修改商品库存、销售量信息
     */

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    void updateGoodsInfo(OrderEntity zxOrder) {
        try {
            Long orderId = zxOrder.getId();
            Long orderCreateTime = zxOrder.getCreateDate().getTime();
            String time = Cnst.getFormatDate(orderCreateTime);
            Long time1 = Cnst.getFormatDate(OrderEntity.FREEZE_ORDER_VALID_TIME);//生效时间
            Long time2 = Cnst.getFormatDate(time);

            List<OrderGoodsEntity> orderGoodsList = orderGoodsDao.findListByOrderId(orderId);
            if (orderGoodsList != null && orderGoodsList.size() > 0) {
                for (OrderGoodsEntity orderGoodsEntity : orderGoodsList) {
                    Long goodsId = orderGoodsEntity.getGoodsId();//商品id
                    Integer goodsCount = orderGoodsEntity.getCount();//商品购买数量
                    GoodsEntity goods = goodsDao.findById(goodsId);
                    if (time1 < time2) {//根据时间判断订单是否处理冻结的库存
                        minusFreezeStock(goodsCount, goodsId);//扣减冻结库存
                    } else {
                        goods.setStock(goods.getStock() - goodsCount);
                        goods.setSalesNum(goods.getSalesNum() + goodsCount);
                        goodsDao.update(goods);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 购物车立即购买
     *
     * @param
     */
    @Override
    public List<Map<String, Object>> cartConfirmOrder(MemberEntity memberEntity, List<Integer> cartIds) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Long agentId = memberEntity.getAgentId();
        //List<AgentFac> agentFaclist = agentFacMapper.findFacListByAgentId(agentId.intValue());

        //for(AgentFac agentFac:agentFaclist){

        int type = Cnst.ShoppingCartCnst.TYPE_POINT;// 类型：0线上购物车，1线下扫码购物车，2积分购物车
        MemberAddressEntity defaultAddress = memberAddressService.getFirstOfMember(memberEntity.getId()); // 默认地址
//        MemberAddressEntity defaultAddress = memberAddressService.getFirstOfMember(memberEntity.getId()); // 默认地址
        try {
            //先通过这些数据找到所有的购物车信息
            List<ShoppingCartEntity> shopCartList = shoppingCartDao.shopListByids(cartIds);
            if (shopCartList != null && shopCartList.size() > 0) {
                for (ShoppingCartEntity shoppcart : shopCartList) {
                    //返回的结果的map
                    Map<String, Object> map = new HashMap<String, Object>();
                    Long shopId = 0L;
                    String shopName = "";
                    shopId = shoppcart.getShopId();
                    ShopEntity shop = shopService.findById(shopId);
                    if (shop != null) {
                        shopName = shop.getCompanyName();
                    }
                    map.put("shopId", shopId);
                    //查询店铺名称
                    map.put("shopName", shopName);
                    //该店商品的总价
                    BigDecimal totalPrice = new BigDecimal(0);
                    BigDecimal originalPrice = new BigDecimal(0);
                    BigDecimal totalPointPrice = new BigDecimal(0.0);

                    AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(agentId.intValue(), shopId.intValue());
                    if (agentFac.getAgentDiscount() == null) {
                        agentFac.setAgentDiscount(1d);
                    }
                    BigDecimal agentDiscount =  BigDecimal.valueOf(1.0);
                    if(null!=agentFac && agentFac.getAgentDiscount()!=null)
                    	agentDiscount = new BigDecimal(agentFac.getAgentDiscount()).setScale(2, RoundingMode.HALF_UP);
                    if (agentDiscount == null) agentDiscount = BigDecimal.valueOf(1.0);
                    //根据店铺的id查询购物车的信息
                    List<ShoppingCartVo> voList = shoppingCartDao.shoppingCartByids(cartIds, shopId);
                    //输出的商品的list
                    List<Map<String, Object>> goodsList = new ArrayList<Map<String, Object>>();
                    if (voList != null && voList.size() > 0) {
                        for (ShoppingCartVo vo : voList) {
                            //商品的map
                            GoodsEntity goods = goodsService.findById(vo.getGoodsId());
                            BigDecimal goodsPrice = new BigDecimal(0);
                            if (goods.getIsMarket() == 0) {
                                map.put("isNotMarket", false);
                                map.put("goodsName", goods.getFullName());
                                map.put("sotckCheckmsg", goods.getFullName() + "商品以下架");
                                list.add(map);
                                return list;
                            }
                            boolean sotckCheck = stockCheck(Long.valueOf(vo.getCount()), goods);
                            if (sotckCheck == false) {
                                map.put("goodsName", vo.getGoodsName());
                                map.put("sotckCheck", false);
                                map.put("sotckCheck", goods.getFullName() + "商品库存不足,请更改购买数量后再进行结算");
                                list.add(map);
                                return list;
                            }
                            //当前商品为普通商品
                            if (goods.getProdType() == 0){
                                //判断当前商品是否有代理该品牌，系列
                                AgentbandList agentbandList = agentBandListService.queryAgencyList(agentId.intValue(), null, goods.getListId().intValue());
                                if (agentbandList == null){
                                    map.put("agentcyScope", false);
                                    list.add(map);
                                    return list;
                                }
                            }
                            System.out.println("agentDiscount: " + agentDiscount);

                            String agentDiscountType = agentFac.getAgentDiscountType();
                            if (agentDiscountType == null) {
                                System.out.println(" cartConfirmOrder 代理关系数据错误 agentId: " + agentFac.getAgentId() + " fac:" + agentFac.getFactoryId());
                                agentDiscountType = "1";
                            }
                            if (goods.getProdType() == 0) {
                            	//普通商品的折扣取代理关系表
                            	if(null!=agentFac && agentFac.getAgentDiscount()!=null)
                            		agentDiscount= BigDecimal.valueOf(agentFac.getAgentDiscount());
                                if (agentDiscountType == null || agentDiscountType.equals("1")) {
                                    goodsPrice = goods.getAgentPrice().multiply(agentDiscount);//经销价
                                } else if (agentDiscountType.equals("2")) {
                                    goodsPrice = goods.getPrice().multiply(agentDiscount);//批发价
                                } else if (agentDiscountType.equals("3")) {
                                    goodsPrice = goods.getMarketPrice().multiply(agentDiscount);//零售价
                                }
                            } else if (goods.getProdType() == 2) {
                                goodsPrice = goods.getShowPrice();
                                
                              //活动价格的折扣为1
                                agentDiscount=BigDecimal.valueOf(1);
                                
                              //当时活动商品的时候维护工厂字段。。price_type 价格类型:1-经销价,2-批发价,3-零售价,4-成本价,5-活动价
                              //  orderEntity.setPriceType("5");
                            }
                            //当价格==0 || <0 的时候使用成本价
                            if (goodsPrice.compareTo(BigDecimal.ZERO) == -1 || goodsPrice.compareTo(BigDecimal.ZERO) == 0) {
                                goodsPrice = goods.getSupplyPrice();
                                
                                //
                                agentDiscount=BigDecimal.valueOf(1);
                            }

                            if (goodsPrice == null) goodsPrice = BigDecimal.valueOf(0.0);

                            //对商品单价进行四舍五入
                            goodsPrice = goodsPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

                            Map<String, Object> goodsMap = new HashMap<String, Object>();
                            goodsMap.put("goodsId", vo.getGoodsId());//商品id
                            goodsMap.put("goodsName", vo.getGoodsName());//商品名称
                            goodsMap.put("proType", goods.getProdType());//商品类别  是否是活动商品  2是活动商品
                            goodsMap.put("agentDiscount", agentDiscount);//代理商折扣
                            if (vo.getGoodsImg() != null)
                                goodsMap.put("goodsImg", vo.getGoodsImg());//商品图片
                            if (goods.getProdType() != 2) {
                                goodsMap.put("goodsPrice", goodsPrice);//商品价格  非活动商品乘折扣
                            } else {
                                goodsMap.put("goodsPrice", goodsPrice);//商品价格 活动商品不乘折扣
                            }
                            goodsMap.put("goodsPointPrice", vo.getPointPrice());//商品积分价格
                            goodsMap.put("shopId", vo.getShopId());//店铺id
                            goodsMap.put("shopName", vo.getShopName());//店铺名称
                            goodsMap.put("count", vo.getCount());//商品购买数量
                            goodsMap.put("stock", vo.getStock());//商品库存
                            goodsMap.put("specNames", vo.getSpecNames());//商品规格名称
                            goodsMap.put("isMarketable", vo.getIsMarketable());//商品是否上下架
                            goodsMap.put("storeName", vo.getStoreName());//实体店名称
                            List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValDao.queryByGdsId(vo.getGoodsId().intValue());
                            //==========拼接商品参数=============
                            StringBuffer attrJoint = new StringBuffer();
                            for (int i = 0; i < goodsAttrValDtos.size(); i++) {
                                String attrValue = goodsAttrValDtos.get(i).getAttrValue();
                                attrJoint.append(attrValue);
                                if (i != goodsAttrValDtos.size() - 1) {
                                    attrJoint.append(",");
                                }
                            }
                            //===============================
                            goodsMap.put("goodsAttr", attrJoint.toString());
                            goodsMap.put("modelName", goodsDao.queryDetailById(vo.getGoodsId().intValue()).getModelName());//型号名称
                            goodsList.add(goodsMap);
                            //该店铺商品的总价价格

                            //totalPrice =totalPrice.add(new BigDecimal(vo.getCount()).multiply(goodsPrice).multiply(agentDiscount)); // 总价格
                            if (goods.getProdType() != 2) {
                                totalPrice = totalPrice.add(new BigDecimal(vo.getCount()).multiply(goodsPrice)); // 总价格
                            } else {
                                totalPrice = totalPrice.add(new BigDecimal(vo.getCount()).multiply(goodsPrice)); // 总价格
                            }

                            //对商品总价进行四舍五入
                            totalPrice = totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
                            //进行四舍五入
                            originalPrice = originalPrice.add((new BigDecimal(vo.getCount()).multiply(goodsPrice)).setScale(2,BigDecimal.ROUND_HALF_UP));

                           /* long totalPoint = 0L;
                            if(goods.getPoint()!=null){
                                totalPoint = Integer.parseInt(buyNum)*goods.getPoint(); // 总积分
                            }*/
//                            if(vo.getPointPrice()!=null){
//                                totalPointPrice = totalPointPrice.add(new BigDecimal(vo.getCount()).multiply(vo.getPointPrice()));
//                            }
                        }
                    }
                    //设置默认图片
                    for (Map<String, Object> g :goodsList ) {
                         
                        if (g.get("goodsImg") == null || ("".equals(g.get("goodsImg")))){
                            g.put("goodsImg","/shopping/img/index/sl3.png");
                        }
                    }
                    map.put("goodsList", goodsList);
                    map.put("totalPointPrice", totalPointPrice);
                    map.put("totalPrice", totalPrice);
                    map.put("originalPrice", originalPrice);
                    map.put("discount", agentDiscount);
                    //map.put("discount", originalPrice.subtract(totalPrice));
                    map.put("defaultAddress", defaultAddress);
                    map.put("trans", 0.00f);
                    map.put("type", type);
                    list.add(map);


                }
            }

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
//        }
        }
        return list;
    }

    /**
     * 取消订单
     */
    @Override
    public boolean cancelOrder(Long orderId, MemberEntity memberEntity) {
        boolean r = false;
        try {
            //查询该订单信息
            OrderEntity order = orderDao.findById(orderId);
            if (order != null) {
                //更新订单状态为取消
                order.setStatus(4);
                orderDao.update(order);
                //int orderType = order.getOrderType();
//                List<OrderGoodsEntity> orderGoodses = orderGoodsDao.findListByOrderId(order.getId());
//                for (OrderGoodsEntity orderGoods : orderGoodses) {
//                    //更新订单从表的状态为取消
//                    orderGoods.setStatus(4);
//                    orderGoodsDao.update(orderGoods);
//                    releaseFreezeStock(orderGoods.getCount(), orderGoods.getGoodsId());
//                }
                //插入系统消息
                sysMessageService.insertOrderSysMsg(order, memberEntity, 1);
                r = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 订单付款
     */
    @Override
    public boolean payOrder(Long orderId, HttpServletRequest request) {
        boolean r = true;
        try {
            //查询该订单信息
            OrderEntity order = orderDao.findById(orderId);
            MemberEntity memberEntity = loginUtil.getLoginMember(request);
            if (order != null) {
                //更新订单状态为已支付代发货
                order.setStatus(1);
                //线下支付
                order.setPayType(1);
                r = orderDao.update(order);
                /**
                 * 生成日志操作数据
                 */
                OperatorLog operatorLog = new OperatorLog();
                operatorLog.setMemberId(order.getMemberId().intValue());
                List<OaAgentUserinfo> oaAgentUserinfos = oaAgentUserinfoMapper.selectUserInfosByAgentId(memberEntity.getAgentId());
                for (OaAgentUserinfo oaAgentUserinfo : oaAgentUserinfos) {
                    operatorLog.setUserName(oaAgentUserinfo.getUserName());
                    if ("1".equals(oaAgentUserinfo.getMainUser())) {
                        operatorLog.setUserName(oaAgentUserinfo.getUserName());
                        break;
                    }
                }
                if (operatorLog.getUserName() == null) {
                    operatorLog.setUserName(memberEntity.getUsername());
                }

//                operatorLog.setUserName(memberEntity.getUsername());
                operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
                operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_PAY);
                operatorLog.setRemark("商城" + order.getMemberId() + "支付成功");
                operatorLog.setSn(orderId + "");
                operatorLog.setLinkUrl(request.getRequestURI().toString());
                operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
                operatorLog.setOperatorParams(JSON.toJSONString(orderId));
                operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
                operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MALL);
                operatorLog.setCreateDate(new Date());
                operatorLog.setAddIp(request.getRemoteAddr());
                operatorlogDao.insertSelectiveOne(operatorLog);

                //int orderType = order.getOrderType();

//                List<OrderGoodsEntity> orderGoodses = orderGoodsDao.findListByOrderId(order.getId());
//                for (OrderGoodsEntity orderGoods : orderGoodses) {
//                    //更新订单从表的状态为取消
//                    orderGoods.setStatus(4);
//                    orderGoodsDao.update(orderGoods);
//
//
//                    releaseFreezeStock(orderGoods.getCount(), orderGoods.getGoodsId());
//
//                }

//                r = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 订单发货
     */
    @Override
    public boolean sendOrder(Long orderId) {
        boolean r = true;
        try {
            //查询该订单信息
            OrderEntity order = orderDao.findById(orderId);
            if (order != null) {
                //更新订单状态为已支付代发货
                order.setStatus(2);

                //线下支付
//                order.setPayType(1);
                order.setModifyDate(new Date());
                order.setPaymentDate(new Date());
                r = orderDao.update(order);
                //int orderType = order.getOrderType();

//                List<OrderGoodsEntity> orderGoodses = orderGoodsDao.findListByOrderId(order.getId());
//                for (OrderGoodsEntity orderGoods : orderGoodses) {
//                    //更新订单从表的状态为取消
//                    orderGoods.setStatus(4);
//                    orderGoodsDao.update(orderGoods);
//
//
//                    releaseFreezeStock(orderGoods.getCount(), orderGoods.getGoodsId());
//
//                }

//                r = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    /**
     * 订单收货
     */
    @Override
    public boolean recevOrder(Long orderId, MemberEntity memberEntity) {
        boolean r = true;
        try {
            //查询该订单信息
            OrderEntity order = orderDao.findById(orderId);
            if (order != null) {
                //更新订单状态为已支付代发货
                order.setStatus(3);
                //线下支付
//              order.setPayType(1);
                r = orderDao.update(order);
                //int orderType = order.getOrderType();
//                List<OrderGoodsEntity> orderGoodses = orderGoodsDao.findListByOrderId(order.getId());
//                for (OrderGoodsEntity orderGoods : orderGoodses) {
//                    //更新订单从表的状态为取消
//                    orderGoods.setStatus(4);
//                    orderGoodsDao.update(orderGoods);
//                    releaseFreezeStock(orderGoods.getCount(), orderGoods.getGoodsId());
//                }
                //插入系统消息
                sysMessageService.insertOrderSysMsg(order, memberEntity, 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 删除订单
     */
    @Override
    public int deleteorder(Long orderId) {
        int i = 0;
        try {
            //查询该订单信息
            OrderEntity order = orderDao.findById(orderId);
            if (order != null) {
                //更新订单状态为逻辑删除
//                order.setId(orderId);
                order.setIsDelete(1);
                order.setStatus(4);
                System.out.println("22" + orderDao.updateorder(order));
                int is = orderDao.updateorder(order);
                if (is != 0) {
                    System.out.println("11" + orderDao.updateorder(order));
                    i = 1;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public void paySuccess(String sn) {
        OrderEntity order = orderDao.findBySn(sn);
        updateGoodsInfo(order);
    }

    @Override
    public List<OrderEntity> getOrderInfoCus(Long memberId, PageBounds pageBounds, Integer status) {
        Map params = new HashMap();
        params.put("memberId", memberId);
        if (status != null)
            params.put("status", status);
        return orderDao.findOrderList(params, pageBounds);
        //return orderDao.findOrderList(memberId);
    }

    @Override
    public List<OrderEntity> getShopOrderList(Long shopId, PageBounds pageBounds, Integer status) {
        Map params = new HashMap();
        params.put("shopId", shopId);
        if (status != null)
            params.put("status", status);
        return orderDao.findOrderList(params, pageBounds);
        //return orderDao.findOrderList(memberId);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public Map<String,Object> getOrderInfoId(HttpServletRequest request,Long orderId){
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            OrderEntity orderEntity = orderDao.getOrderInfoId(orderId);
            if (null!=orderEntity&&null==orderEntity.getShippingCost()){
                orderEntity.setShippingCost(BigDecimal.valueOf(0));
            }

            List<OrderGoodsEntity> orderGoods = orderGoodsDao.findListByOrderId(orderId);
            for (OrderGoodsEntity orderGood : orderGoods) {
                StringBuffer stringBuffer = new StringBuffer();
                Long goodsId = orderGood.getGoodsId();
                Long agentId = memberEntity.getAgentId();
                if (goodsId != null && goodsId != 0) {
                    //获取品牌名，商品名，商品型号，商品系列 2426 804
                    Map<String, Object> maps = goodsBgService.goodsFull(goodsId, agentId);
                    String bandName = "";
                    String listDesc = "";
                    String modelName = "";
                    if (null != maps) {
                        if (maps.get("bandName") != null)
                            bandName = maps.get("bandName").toString();//品牌名
                        if (maps.get("listDesc") != null)
                            listDesc = maps.get("listDesc").toString();//系列名
                        if (maps.get("modelName") != null)
                            modelName = maps.get("modelName").toString();//型号名
                    }
                    if (StringUtils.isBlank(orderGood.getGoodsImg())){         
                        orderGood.setGoodsImg("/shopping/img/index/sl3.png");
                    } 
                    
                    //拼接商品参数
                    List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValService.queryByGdsId(goodsId.intValue());
                    for (GoodsAttrValDto byGdsId : goodsAttrValDtos) {
                        String attrValue = byGdsId.getAttrValue();
                        stringBuffer.append(attrValue + " ");
                    }//商品名，品牌名，系列名，型号名，参数名
                    orderGood.setGoodsName(bandName + " " + listDesc + " " + modelName + " " + orderGood.getGoodsName() + " " + stringBuffer.toString());
                    //设置属性值
                    orderGood.setGoodsAttriValue(stringBuffer.toString());
                }
            }
            List<OperatorLog> listOpLogs = operatorlogDao.getListOpLogs(orderId);
//            String CreateTime="";
//            String PayTime="";
//            String SendTime="";
//            String ReceiveTime="";
            HashMap opTime =new HashMap();
            opTime.put("RECEIVE", null);
            opTime.put("PAY",  null);
            opTime.put("SEND", null);
            opTime.put("RECEIVE", null);
            opTime.put("status", 0);
            for(OperatorLog opLog:listOpLogs){
            	if(null!=opLog && opLog.getTitle().equals("CREATE") && null!=opLog.getCreateDate()){ 
            		opTime.put("CREATE", opLog.getCreateDate());
//            		opTime.put("CREATE", DateTool.parseDateTime(opLog.getCreateDate().toString()).toString());
            	}
            	if(null!=opLog && opLog.getTitle().equals("PAY") && null!=opLog.getCreateDate()){ 
            		opTime.put("PAY", opLog.getCreateDate());
            	}
            	if(null!=opLog && opLog.getTitle().equals("SEND") && null!=opLog.getCreateDate()){ 
            		opTime.put("SEND", opLog.getCreateDate());
            	}
            	if(null!=opLog && opLog.getTitle().equals("RECEIVE") && null!=opLog.getCreateDate()){ 
            		opTime.put("RECEIVE", opLog.getCreateDate());
            	}            	
            }
            if(null!=orderEntity && null!=orderEntity.getStatus()){ 
        		opTime.put("status", orderEntity.getStatus().toString());
        	}
            int b = orderId.intValue();
            map.put("orderEntity", orderEntity);
            map.put("orderGoods", orderGoods);
            map.put("listOpLogs", listOpLogs);
            map.put("opTime", opTime);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }


    @Override
    public List<OrderEntity> getOrderInfoSta(Long memberId, int status) {
        return orderDao.getOrderInfoSta(memberId, status);
    }

    //状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消)
    @Override
    public PageList<OrderEntity> findPageSt(Integer status, Long memberId, Map params, PageBounds rowBounds) {
        PageList list = orderDao.findPageSt(status, memberId, params, rowBounds);
        return list;
    }

    @Override
    public PageList<OrderEntity> findPageSt2(Integer status, Long memberId, Map params, PageBounds rowBounds) {
        PageList<OrderEntity> list = new PageList<>();
        if (null == status) {
            list = orderDao.findPageSt2(status, memberId, params, rowBounds);
        } else {
            list = orderDao.findOderListByStatus(status, memberId, params, rowBounds);
        }

        List<Long> orderIdList = new ArrayList<>();
        if (null!=list&&list.size()>0){
            for (OrderEntity entity : list) {
                orderIdList.add(entity.getId());
            }
            List<OrderGoodsEntity> orderGoodsList = orderDao.findOrderGoods(orderIdList);

            for (OrderEntity entity : list) {
                List<OrderGoodsEntity> entityList = new ArrayList<>();
                if (null!=orderGoodsList&&orderGoodsList.size()>0){
                    for (OrderGoodsEntity good : orderGoodsList) {
                        if (entity.getId().equals(good.getOrderId())) {
                            OrderGoodsEntity entityGood = new OrderGoodsEntity();
                            BeanUtils.copyProperties(good, entityGood);
                            entityList.add(entityGood);
                        }
                    }
                }

                entity.setOrderGoods(entityList);
            }
        }

        return list;
    }

    /**
     * 立即支付，选择支付方式
     */
    @Override
    public void payType(Long orderId, Integer payType) {
        OrderEntity orderEntity = orderDao.findById(orderId);
        orderEntity.setPayType(payType);
        orderDao.update(orderEntity);
    }

    @Override
    public int findSize(Long memberId, Integer status) {

        return orderDao.findSize(memberId, status);
    }

    @Override
    public int findTotalOrderSize(Long memberId) {

        return orderDao.findTotalSize(memberId);
    }
    @Override
    public OrderEntity updateOrderNameAndTel(Long agentId, Long shopId) {
        List<OaAgentUserinfo> oaAgentUserinfo = oaAgentUserinfoMapper.getUserInfoByAgentIdAndSysId(agentId,shopId);
        OrderEntity orderEntity = new OrderEntity();
        int i=0;
        if(oaAgentUserinfo != null && oaAgentUserinfo.size() > 0){
            for (OaAgentUserinfo agentUserInfo: oaAgentUserinfo) {

            	//逻辑错误
                if(agentUserInfo.getMainUser() == "1"){
                    orderEntity.setContactMan(oaAgentUserinfo.get(i).getUserName());
                    orderEntity.setContactTel(oaAgentUserinfo.get(i).getLinkTel());
                    break;
                }else{
                    orderEntity.setContactMan(oaAgentUserinfo.get(i).getUserName());
                    orderEntity.setContactTel(oaAgentUserinfo.get(i).getLinkTel());
                    break;
                }
            }
        }
        return orderEntity;
    }
    public static List removeDuplicate(List list) {
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).equals(list.get(i))) {
                        list.remove(j);
                    }
                }
            }
            return list;
        }
        return null;
    }

    private void setMarketOffByStock(GoodsEntity goods) {
        if (goods.getStock() == 0) {
            goodsDao.setMarketByStock(goods.getId(), 0);
            goodsDao.deleteItemState(goods.getId());
        }
    }

    /**
     * 查询订单编号是否重复，大于0表示重复
     *
     * @param
     * @return
     * @author
     * @date
     */
    public Integer queryOrderNumber(@Param("sn") String sn) {
        return orderDao.queryOrderNumber(sn);
    }
}

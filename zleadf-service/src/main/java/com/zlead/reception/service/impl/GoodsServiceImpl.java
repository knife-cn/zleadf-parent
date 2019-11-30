package com.zlead.reception.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zlead.common.PageResponse;
import com.zlead.constant.Cnst;
import com.zlead.dao.*;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OaMarketGoods;
import com.zlead.entity.ShoppingCartEntity;
import com.zlead.entity.dto.*;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.entity.vo.GoodsAttrValueVo;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.GoodsattrvalMapper;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.AgentbandList;
import com.zlead.fplat.service.AgentBandListService;
import com.zlead.reception.service.GoodsService;
import com.zlead.service.GoodsAttrValService;
import com.zlead.service.GoodsBgService;
import com.zlead.util.ObjectUtils;
import com.zlead.util.StrTools;
import com.zlead.util.page.Order;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private GoodsAttrValDao goodsAttrValDao;

    @Autowired
    private CollectDao collectDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private GoodsattrvalMapper goodsattrvalMapper;

    @Autowired
    private OaMarketGoodsDao oaMarketGoodsDao;

    @Autowired
    private GoodsAttrValService goodsAttrValService;

    @Autowired
    private AgentBandListService agentBandListService;

    @Autowired
    private GoodsBgService goodsBgService;

    @Transactional(readOnly = true)
    public PageList<GoodsEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = goodsDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public GoodsEntity findById(Long id) {
        return goodsDao.findById(id);
    }

    public void save(GoodsEntity entity) {
        goodsDao.insert(entity);
    }

    public int update(GoodsEntity entity) {
        return goodsDao.update(entity);
    }

    public void delete(Long id) {
        goodsDao.delete(id);
    }

    //查询首页的推荐商品（最新的六个商品）
    public List<GoodsEntity> gethomeGoodsList(Integer showNum) {
        //查询最新的六个商品
        Map params = new HashMap();
        params.put("showNum", showNum);
        params.put("prodType", 0);
        List<GoodsEntity> list = goodsDao.findHomeGoods(params);
        return list;
    }

    /**
     * 商品列表
     */
    public PageList<GoodsEntity> getGoodsList(Integer prodType, Integer page, Integer shopId) {
        //传入的判断数据
        Map params = new HashMap();
        //页数
        PageBounds pageBounds = new PageBounds(page, Cnst.GOODS_PAGE_SIZE);
        pageBounds.setOrders(Order.formString("update_date.DESC"));
        params.put("isMarketable", 1);
        params.put("isAudit", 1);
        params.put("shopId", shopId);
        if (prodType != null) {
            params.put("prodType", prodType);
        }
        //获取信息
        PageList<GoodsEntity> goodslist = goodsDao.findPage(params, pageBounds);
        return goodslist;
    }

    public PageList<GoodsEntity> queryAgentGoodsList(Integer page) {
        //传入的判断数据
        Map params = new HashMap();
        //页数
        PageBounds pageBounds = new PageBounds(page, Cnst.GOODS_PAGE_SIZE);
        pageBounds.setOrders(Order.formString("update_date.DESC"));
        params.put("isMarketable", 1);
        params.put("isAudit", 1);
        params.put("isHome", 1);
//        if(prodType!=null){
        params.put("prodType", 0);
//        }
        //获取信息
        PageList<GoodsEntity> goodslist = goodsDao.findPage(params, pageBounds);
        return goodslist;
    }


    @Override
    public GoodsDto getByGoodsId(List<GoodsAttrValueReponse> goodsAttrValueResponse, int agentId, int prdId) {
        List<Map> l = goodsDao.getByGoodsId(goodsAttrValueResponse, prdId);
        if (l.size() == 1) {
            return getGoodsDetail(Long.valueOf(l.get(0).get("goodsId").toString()), agentId);
        } else if (l.size() > 1) {
            return getGoodsDetail(Long.valueOf(l.get(0).get("goodsId").toString()), agentId);
        }
        return null;
    }


    /**
     * 购物车更改商品
     */
    @Override
    public Map<String, Object> getGoodsById(List<GoodsAttrValueReponse> goodsAttrValueResponse, MemberEntity member, Long cartId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (CollectionUtils.isEmpty(goodsAttrValueResponse) || member == null || cartId == null || cartId.equals("0")) {
            map.put("code", "3");
            map.put("data", null);
            return map;
        }
        ShoppingCartEntity cartEntity = shoppingCartDao.findById(cartId);
        Long goodsId = cartEntity.getGoodsId();//2426
        //通过当前商品获取代理商id
        GoodsEntity goodsEntity = goodsDao.findById(goodsId);
        Long shopId = cartEntity.getShopId();

        Long shopid = cartEntity.getShopId();
        AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(member.getAgentId().intValue(), shopid.intValue());
        String agentDiscountType = agentFac.getAgentDiscountType();
        //获取当前代理商的购物车中之前的所有商品ID，除了选中的这条以外
        List<Long> cartIds = shoppingCartDao.shopCartIdList(shopId, goodsId, member.getId());
        //前端输入的时候没有重复值不需要去重
        /*List<GoodsAttrValueReponse> collect = goodsAttrValueResponse.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<GoodsAttrValueReponse>(comparing(GoodsAttrValueReponse::getValue))), ArrayList::new)
        );*/
        //查询之前商品在购物车中的数量
        //ShoppingCartEntity shoppingCartEntity = shoppingCartDao.shopingCartInfo(member.getId(), goodsId);
        //Integer count = shoppingCartEntity.getCount();
        //通过前端传入的参数，然后查询修改前的商品id
        //List<Map> l = goodsDao.getByGoodsId(goodsAttrValueResponse, goodsEntity.getProdId().intValue());
        Integer attrGoodsId = null;
        for (GoodsAttrValueReponse attr: goodsAttrValueResponse) {
            if (attr.getGoodsId() != null && attr.getGoodsId() != 0){
                attrGoodsId = attr.getGoodsId();
            }
        }
        GoodsDto goodsDto = new GoodsDto();
        if (attrGoodsId != null && attrGoodsId != 0) {
            goodsDto = getGoodsDetail(Long.valueOf(attrGoodsId), member.getAgentId().intValue());
            Long newGoodsId = goodsDto.getGoodsDetailDto().getId();
            boolean hasGoods = false;
            for (int i = 0; i < cartIds.size(); i++) {
                ShoppingCartEntity otherCartEntity = shoppingCartDao.findById(cartIds.get(i));
                if (newGoodsId.equals(otherCartEntity.getGoodsId()) && otherCartEntity.getIsBuy() == 0) {
                    Integer count = cartEntity.getCount();
                    otherCartEntity.setCount(otherCartEntity.getCount() + count);
                    System.out.println(count);
                    System.out.println(otherCartEntity.getCount());
                    System.out.println(otherCartEntity.getCount() + count);
                    map.put("code", "2");
                    map.put("data", otherCartEntity.getId());
                    hasGoods = true;
                    shoppingCartDao.update(otherCartEntity);
                    shoppingCartDao.delete(cartEntity.getId());
                    return map;
                }
            }
            if (hasGoods == false) {
                //获取价格 根据membeid和shopid
                GoodsEntity newGoodsEntity = goodsDao.findById(newGoodsId);
                //当前采用的是代理商价，有可能有问题
                // cartEntity.setPrice(newGoodsEntity.getAgentPrice());
                if (newGoodsEntity.getProdType() == 0) {
                    if (agentDiscountType == null || agentDiscountType.equals("1")) {
                        cartEntity.setPrice(newGoodsEntity.getAgentPrice());//经销价
                        goodsDto.getGoodsDetailDto().setPrice(newGoodsEntity.getAgentPrice());
                    } else if (agentDiscountType.equals("2")) {
                        cartEntity.setPrice(newGoodsEntity.getPrice());//批发价
                        goodsDto.getGoodsDetailDto().setPrice(newGoodsEntity.getPrice());
                    } else if (agentDiscountType.equals("3")) {
                        cartEntity.setPrice(newGoodsEntity.getMarketPrice());//零售价
                        goodsDto.getGoodsDetailDto().setPrice(newGoodsEntity.getMarketPrice());
                    }
                } else if (newGoodsEntity.getProdType() == 2) {
                    cartEntity.setPrice(newGoodsEntity.getShowPrice());//活动价
                    goodsDto.getGoodsDetailDto().setPrice(newGoodsEntity.getShowPrice());
                }
                //当价格==0 || <0 的时候使用成本价
                if (cartEntity.getPrice().compareTo(BigDecimal.ZERO) == -1 || cartEntity.getPrice().compareTo(BigDecimal.ZERO) == 0) {
                    cartEntity.setPrice(newGoodsEntity.getSupplyPrice());
                }
                if (goodsDto.getGoodsDetailDto().getPrice().compareTo(BigDecimal.ZERO) == -1 || goodsDto.getGoodsDetailDto().getPrice().compareTo(BigDecimal.ZERO) == 0) {
                    goodsDto.getGoodsDetailDto().setPrice(goodsDto.getGoodsDetailDto().getSupplyPrice());
                }
                goodsDto.setDiscount(agentFac.getAgentDiscount());
                // cartEntity.setPrice(goodsDto.getGoodsDetailDto().getPrice());
//                goodsMap.put("isMarketable", shoppingCartVo.getIsMarketable());//商品是否上下架
//                goodsMap.put("storeName", shoppingCartVo.getStoreName());//实体店名称
//                goodsMap.put("agentDiscount", agentDiscount);//折扣
            }
            map.put("code", "1");
            map.put("data", goodsDto);
            cartEntity.setGoodsId(newGoodsId);
            shoppingCartDao.update(cartEntity);
            return map;
        }
        map.put("code", "3");
        map.put("data", null);
        map.put("message", "");
        return map;
    }


    /**
     * 商品详情  单纯的展示商城的商品，小程序的商品参数没有查询
     */
    public GoodsDto getGoodsDetail(Long goodsId, int agentId) {
        //查询商品详情
        GoodsDetailDto goodsDetailDto = goodsDao.queryDetailById(goodsId.intValue());
        if (goodsDetailDto == null) {
            return null;
        }
        //获取代理商的代理商折扣类型
        AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(agentId, goodsId.intValue());
        if (agentFac != null) {
            String agentDiscountType = agentFac.getAgentDiscountType();
            if (agentDiscountType == null || agentDiscountType.equals("1")) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getAgentPrice());//经销价
            } else if (agentDiscountType.equals("2")) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getPrice());//批发价
            } else if (agentDiscountType.equals("3")) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getMarketPrice());//零售价
            }
            if (goodsDetailDto.getResultPrice().compareTo(BigDecimal.ZERO) == 0 || goodsDetailDto.getResultPrice().compareTo(BigDecimal.ZERO) == -1) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getSupplyPrice());//成本价
            }
        }
        //产品参数
        List<GoodsAttrValDto> list = null;
        List<GoodsAttrValDto> listg = goodsAttrValDao.queryByGdsId((int) goodsDetailDto.getId());
        List<Map<String, Object>> attrVals = null;
        if (goodsDetailDto.getProdType() == 0) {
        	//查询此商品是否有代理权限
            GoodsDetailDto goodsDetailDto2=goodsDao.queryGoodsWithAgent(goodsId, agentId);
        	 if (goodsDetailDto2 == null ||  goodsDetailDto2.getProdId()==0) {
                 return null;
             }else{
            list = goodsAttrValDao.queryByPid(goodsDetailDto.getProdId(), agentId);
                attrVals = goodsAttrValDao.findGoodsAttrVal(goodsDetailDto.getProdId(), agentId);
             }
                 /**
                  * 上架库存:0-空、1-自定义  2-实际库存
                  * 同步商品库存数
                  */
                 Integer realStock=0;
                 if(null!=goodsDetailDto && goodsDetailDto.getId()>0 && goodsDetailDto.getStockType().equals(1)){
                     GoodsEntity goodsEntity = new GoodsEntity();
                     goodsEntity.setId(goodsDetailDto.getId());
                     goodsEntity.setStockType(goodsDetailDto.getStockType());
                     goodsEntity.setStock(Integer.parseInt(goodsDetailDto.getStock()+""));
                     goodsEntity.setProdType(goodsDetailDto.getProdType());
                     realStock=goodsBgService.queryGoodsNewestStock(goodsEntity);
                     if(null !=realStock){
                         goodsDetailDto.setStock(realStock);
                     }
                 }
        } else if (goodsDetailDto.getProdType() == 2) {//仅仅只是用来判断是否是活动商品的问题
            OaMarketGoods oaMarketGoods = new OaMarketGoods();
            Long id = goodsDetailDto.getId();
            oaMarketGoods.setGoodsId(id.intValue());
            oaMarketGoods = oaMarketGoodsDao.selectByPrimaryKey(oaMarketGoods);
            Integer prodId = goodsDetailDto.getProdId();
            list = goodsAttrValDao.findActGoodsAttrs(oaMarketGoods.getActId().longValue(), prodId.longValue());
            attrVals = goodsAttrValDao.findActGoodsAttrVal(oaMarketGoods.getActId().longValue(), goodsDetailDto.getProdId());
        }
        if (CollectionUtils.isNotEmpty(attrVals)){
            //去重复参数处理
            for (Map<String, Object> attrVal : attrVals) {
                String fullName = StrTools.strDistinctJoinWire(attrVal.get("full_name").toString());
                String attr_val = StrTools.strDistinctJoinWire(attrVal.get("attr_val").toString());
                attrVal.put("full_name",fullName);
                attrVal.put("attr_val",attr_val);
                GoodsDetailDto attrGoods = goodsDao.queryDetailById(Integer.parseInt(attrVal.get("goods_id").toString()));
                if (attrGoods != null && attrGoods.getProdType() == 0){
                    /**
                     * 上架库存:0-空、1-自定义  2-实际库存
                     * 同步商品库存数
                     */
                    Integer realStock=0;
                    if(null!=attrGoods && attrGoods.getId()>0 && attrGoods.getStockType().equals(1)){
                        GoodsEntity goodsEntity = new GoodsEntity();
                        goodsEntity.setId(attrGoods.getId());
                        goodsEntity.setStockType(attrGoods.getStockType());
                        goodsEntity.setStock(Integer.parseInt(attrGoods.getStock()+""));
                        goodsEntity.setProdType(attrGoods.getProdType());
                        realStock=goodsBgService.queryGoodsNewestStock(goodsEntity);
                        if(null !=realStock){
                            attrVal.put("stock",realStock);
                        }
                    }
                }

            }
        }
        List<LinkedHashMap<String, List>> mapList = new ArrayList<>();
        LinkedHashSet<String> listAttr = new LinkedHashSet<>();
        //当商品为下架状态的时候只查询产品的商品参数，不设置选中状态
        if (goodsDetailDto.getIsMarket() != null && goodsDetailDto.getIsMarket().equals("0")) {
            listg.forEach(m -> listAttr.add(m.getName()));
            for (String str2 : listAttr) {
                if (str2 != null) {
                    LinkedHashMap<String, List> mapMap = new LinkedHashMap<>();
                    List arrayList = new ArrayList();
                    listg.forEach(m -> {
                        if (str2.equals(m.getName())) {
                            AttrValShowDto attrValShowDto = new AttrValShowDto();
                            attrValShowDto.setContent(m.getAttrValue());
                            arrayList.add(attrValShowDto);
                        }
                    });
                    mapMap.put(str2, arrayList);
                    mapList.add(mapMap);
                }
            }
        } else {
            list.forEach(m -> listAttr.add(m.getName()));
            for (String str : listAttr) {
                if (str != null) {
                    LinkedHashMap<String, List> mapMap = new LinkedHashMap<>();
                    List arrayList = new ArrayList();
                    list.forEach(m -> {
                        if (str.equals(m.getName())) {
                            AttrValShowDto attrValShowDto = new AttrValShowDto();
                            for (GoodsAttrValDto goodsAttrValDto : listg) {
                            	//判断商品的有效参数和数值是否存在
                                if (m.getName().equals(goodsAttrValDto.getName()) && m.getAttrValue().equals(goodsAttrValDto.getAttrValue())) {
                                    attrValShowDto.setShow(1);
                                }
                            }
                            if (m != null && m.getAttrValue() != null ){
                                attrValShowDto.setContent(m.getAttrValue().trim());
                            }
                            arrayList.add(attrValShowDto);
                        }
                    });
                    mapMap.put(str, arrayList);
                    mapList.add(mapMap);
                }
            }
        }
        int count = collectDao.countByGidAndAid(agentId, goodsId.intValue());
        GoodsDto goodsDto = new GoodsDto();
        goodsDetailDto.setIsCollect(count == 0 ? 0 : 1);
        goodsDto.setGoodsDetailDto(goodsDetailDto);
        goodsDto.setProdAttr(mapList);
        goodsDto.setAttrVals(attrVals);
        return goodsDto;
    }

    /**
     * 购物车获得商品详情
     */
    public GoodsDto getAttr(Long goodsId, int agentId) {

        //查询商品详情
        GoodsDetailDto goodsDetailDto = goodsDao.queryDetailById(goodsId.intValue());
        if (goodsDetailDto == null) {
            return null;
        }
        //产品参数
        List<GoodsAttrValDto> list = new ArrayList<GoodsAttrValDto>();
        List<GoodsAttrValDto> listg = goodsAttrValDao.queryByGdsId((int) goodsDetailDto.getId());
        List<Map<String, Object>> attrVals = null;
        if (goodsDetailDto.getProdType() == 0) {
            list = goodsAttrValDao.queryByPid(goodsDetailDto.getProdId(), agentId);
            attrVals = goodsAttrValDao.findGoodsAttrVal(goodsDetailDto.getProdId(), agentId);
        } else if (goodsDetailDto.getProdType() == 2) {//2代表是活动商品
            OaMarketGoods oaMarketGoods = new OaMarketGoods();
            Long id = goodsDetailDto.getId();
            oaMarketGoods.setGoodsId(id.intValue());
            oaMarketGoods = oaMarketGoodsDao.selectByPrimaryKey(oaMarketGoods);
            Integer prodId = goodsDetailDto.getProdId();
            list = goodsAttrValDao.findActGoodsAttrs(oaMarketGoods.getActId().longValue(), prodId.longValue());
            attrVals = goodsAttrValDao.findActGoodsAttrVal(oaMarketGoods.getActId().longValue(), goodsDetailDto.getProdId());
        }
        if (CollectionUtils.isNotEmpty(attrVals)){
            //去重复参数处理
            for (Map<String, Object> attrVal : attrVals) {
                String fullName = StrTools.strDistinctJoinWire(attrVal.get("full_name").toString());
                String attr_val = StrTools.strDistinctJoinWire(attrVal.get("attr_val").toString());
                attrVal.put("full_name",fullName);
                attrVal.put("attr_val",attr_val);
            }
        }
        List<LinkedHashMap<String, List>> mapList = new ArrayList<>();
        LinkedHashSet<String> listAttr = new LinkedHashSet<>();
        list.forEach(m -> listAttr.add(m.getName()));
        for (String str : listAttr) {
            LinkedHashMap<String, List> mapMap = new LinkedHashMap<>();
            List arrayList = new ArrayList();
            list.forEach(m -> {
                if (str.equals(m.getName())) {
                    AttrValShowDto attrValShowDto = new AttrValShowDto();
                    for (GoodsAttrValDto goodsAttrValDto : listg) {
                        if (m.getAttrValue().equals(goodsAttrValDto.getAttrValue()) && m.getName().equals(goodsAttrValDto.getName())) {
                            attrValShowDto.setShow(1);
                        }
                    }
                    attrValShowDto.setContent(m.getAttrValue());
                    arrayList.add(attrValShowDto);
                }
            });
            mapMap.put(str, arrayList);
            mapList.add(mapMap);
        }
        int count = collectDao.countByGidAndAid(agentId, goodsId.intValue());
        List<String> thisGoodsAttr = goodsattrvalMapper.getByGoodsId(goodsId);

        AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(agentId, goodsDetailDto.getShopId());
        GoodsDto goodsDto = new GoodsDto();
        goodsDetailDto.setIsCollect(count == 0 ? 0 : 1);

        BigDecimal goodsPrice = new BigDecimal(0);
        if (agentFac.getAgentDiscountType().equals("1") || agentFac.getAgentDiscountType() == null) {
            goodsPrice = goodsDetailDto.getAgentPrice();//经销价
        } else if (agentFac.getAgentDiscountType().equals("2")) {
            goodsPrice = goodsDetailDto.getPrice();//批发价
        } else if (agentFac.getAgentDiscountType().equals("3")) {
            goodsPrice = goodsDetailDto.getMarketPrice();//零售价
        }
        //代理价
        if (goodsPrice != null && goodsPrice.compareTo(BigDecimal.ZERO) == 1) {
            goodsDetailDto.setPrice(goodsPrice);
        } else {
            goodsDetailDto.setPrice(goodsDetailDto.getSupplyPrice());
        }
        goodsDto.setGoodsDetailDto(goodsDetailDto);
        goodsDto.setProdAttr(mapList);
        goodsDto.setThisGoodsAttr(thisGoodsAttr);
        goodsDto.setDiscount(agentFac.getAgentDiscount());
        goodsDto.setAttrVals(attrVals);
        return goodsDto;
    }

    @Override
    public GoodsAttrValueVo selectGoods(List<GoodsAttrValueReponse> goodsAttrValueResponse, Long prdId) {
        GoodsAttrValueVo goodsAttrValVo = goodsAttrValDao.selectGoods(goodsAttrValueResponse, prdId);
        return goodsAttrValVo;
    }


    @Override
    public PageResponse pageListCollect(Integer agentId, PageBounds rowBounds, HttpServletRequest request) {
        PageResponse pageResponse = new PageResponse();
        Map map = new HashMap();
        map.put("agentId", agentId);
        Page page = PageHelper.startPage(rowBounds.getPageNumber(), rowBounds.getPageSize(), true);
        PageList<GoodsPageDto> list = goodsDao.findByPageByCollect(map, rowBounds);


        if (null!=list&&list.size()>0) {

            for (GoodsPageDto dto : list) {

                Long goodsId = Long.valueOf(dto.getId());
                StringBuffer stringBuffer = new StringBuffer();
                if (goodsId != null && goodsId != 0) {
                    //获取品牌名，商品名，商品型号，商品系列 2426 804
                    Map<String, Object> maps = goodsBgService.goodsFull(goodsId, agentId.longValue());
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
                    //拼接商品参数
                    List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValService.queryByGdsId(goodsId.intValue());
                    for (GoodsAttrValDto byGdsId : goodsAttrValDtos) {
                        String attrValue = byGdsId.getAttrValue();
                        stringBuffer.append(attrValue + " ");
                    }//商品名，品牌名，系列名，型号名，参数名
                    dto.setGoodsName(dto.getGoodsName() + " "+bandName + " " + listDesc + " " + modelName + " "  + stringBuffer.toString());
                    if (dto.getImage() == null || "".equals(dto.getImage())){
                        dto.setImage(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                    }
                }
                /*if (dto.getProdType() == 0){
                    //当前收藏商品没有代理品牌，系列的时候设置为下架商品，前端判断为失效商品
                    AgentbandList agentbandList = agentBandListService.queryAgencyList(agentId.intValue(), null, dto.getListId());
                    if (ObjectUtils.isEmpty(agentbandList)){
                        dto.setIsMarket(0);
                    }
                }*/

            }
        }
        pageResponse.setPage(rowBounds);
        pageResponse.setCount(page.getTotal());
        pageResponse.setData(list);
        return pageResponse;
    }

    @Override
    public PageResponse findByProdTypePage(PageBounds pageBounds) {
        PageResponse pageResponse = new PageResponse();
        Page page = PageHelper.startPage(pageBounds.getPageNumber(), pageBounds.getPageSize(), true);
        PageList<GoodsPageDto> pageDto = goodsDao.findByProdTypePage(2);
        pageResponse.setData(pageDto);
        pageResponse.setCount(page.getTotal());
        pageResponse.setPage(pageBounds);
        return pageResponse;
    }


    public GoodsDto findActGoods(Long agentId, Long actId, Long goodId) {
        //先判断是不是活动商品，不需要actId，当你从搜索也进来的时候是没有actId的只能通过prodType去判断
        GoodsDetailDto goodsDetailDto = goodsDao.queryDetailById(goodId.intValue());
        if (goodsDetailDto == null) {
            return null;
        }
        //获取代理商的代理商折扣类型
        AgentFac agentFac = agentFacMapper.findByAgentIdAndGoodsId(Integer.parseInt(agentId.toString()), goodId.intValue());
        List<Map<String, Object>> attrVals = new ArrayList<>();
        //查找该商品发布活动的参数
        List<GoodsAttrValDto> list = goodsAttrValDao.findActGoodsAttrs(actId, (long) goodsDetailDto.getProdId());
        //查询这个活动商品的参数
        List<GoodsAttrValDto> listg = goodsAttrValDao.queryByGdsId((int) goodsDetailDto.getId());
        /*if (CollectionUtils.isNotEmpty(listg)) {
            Set<Long> goodsIds = new HashSet<>();
            for (GoodsAttrValDto goodsAttrValDto : listg) {
                goodsIds.add((long) goodsAttrValDto.getGoodsId());

            }
            attrVals = goodsAttrValDao.findActGoodsAttrVal(goodsIds,actId);
        }*/
        if (goodsDetailDto.getProdType() == 0) {
            attrVals = goodsAttrValDao.findGoodsAttrVal(goodsDetailDto.getProdId(), agentId.intValue());
        } else if (goodsDetailDto.getProdType() == 2) {
            attrVals = goodsAttrValDao.findActGoodsAttrVal(actId, goodsDetailDto.getProdId());
            goodsDetailDto.setIfShowPrice(1);
            goodsDetailDto.setIfShowStock(1);
        }

        if (agentFac != null) {
            //活动商品对比价取成本价
            goodsDetailDto.setResultPrice(goodsDetailDto.getSupplyPrice());
            for (Map<String, Object> attrValsMap : attrVals) {
                BigDecimal supplyPrice = null;
                if (ObjectUtils.isNotEmpty(attrValsMap.get("supplyPrice"))){
                    supplyPrice = new BigDecimal(attrValsMap.get("supplyPrice").toString());
                }
                attrValsMap.put("resultPrice", supplyPrice == null ? 0 : supplyPrice);
            }
        	/** begin 活动商品原价根据代理规则 ,计算商品的代理价  comment by qj at 20190415
            BigDecimal agentDiscount = new BigDecimal(agentFac.getAgentDiscount());
            String agentDiscountType = agentFac.getAgentDiscountType();
            if (agentDiscountType == null || agentDiscountType.equals("1")) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getAgentPrice());//经销价.multiply(agentDiscount)
            } else if (agentDiscountType.equals("2")) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getPrice());//批发价.multiply(agentDiscount)
            } else if (agentDiscountType.equals("3")) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getMarketPrice());//零售价.multiply(agentDiscount)
            }
            if (goodsDetailDto.getResultPrice().compareTo(BigDecimal.ZERO) == 0 || goodsDetailDto.getResultPrice().compareTo(BigDecimal.ZERO) == -1) {
                goodsDetailDto.setResultPrice(goodsDetailDto.getSupplyPrice());//成本价
            }             
            for (Map<String, Object> attrValsMap : attrVals) { 
                BigDecimal supplyPrice = new BigDecimal(attrValsMap.get("supplyPrice").toString());
                if (agentDiscountType == null || agentDiscountType.equals("1")) {
                    BigDecimal agentPrice = new BigDecimal(attrValsMap.get("agentPrice").toString());
                    if (agentPrice.compareTo(BigDecimal.ZERO) == 0 || agentPrice.compareTo(BigDecimal.ZERO) == -1) {
                        attrValsMap.put("resultPrice", supplyPrice);//成本价不需要计算折扣
                    } else {
                        attrValsMap.put("resultPrice", agentPrice);//经销价 .multiply(agentDiscount)
                    }
                } else if (agentDiscountType.equals("2")) {
                    BigDecimal price = new BigDecimal(attrValsMap.get("price").toString());
                    if (price.compareTo(BigDecimal.ZERO) == 0 || price.compareTo(BigDecimal.ZERO) == -1) {
                        attrValsMap.put("resultPrice", supplyPrice);//成本价不需要计算折扣
                    } else {
                        attrValsMap.put("resultPrice", price);//批发价.multiply(agentDiscount)
                    }
                } else if (agentDiscountType.equals("3")) {
                    BigDecimal marketPrice = new BigDecimal(attrValsMap.get("marketPrice").toString());
                    if (marketPrice.compareTo(BigDecimal.ZERO) == 0 || marketPrice.compareTo(BigDecimal.ZERO) == -1) {
                        attrValsMap.put("resultPrice", supplyPrice);//成本价不需要计算折扣
                    } else {
                        attrValsMap.put("resultPrice", marketPrice);//零售价 .multiply(agentDiscount)
                    }
                }  
            	          
            }  end 活动商品原价根据代理规则 ,计算商品的代理价  comment by qj at 20190415 **/
            if (CollectionUtils.isNotEmpty(attrVals)){
                //去重复参数处理
                for (Map<String, Object> attrVal : attrVals) {
                    String fullName = StrTools.strDistinctJoinWire(attrVal.get("full_name").toString());
                    String attr_val = StrTools.strDistinctJoinWire(attrVal.get("attr_val").toString());
                    attrVal.put("full_name",fullName);
                    attrVal.put("attr_val",attr_val);
                }
            }
        }

        List<LinkedHashMap<String, List>> mapList = new ArrayList<>();
        Set<String> listAttr = new HashSet<>();
        list.forEach(m -> listAttr.add(m.getName()));
        for (String str : listAttr) {
            if (str != null) {
                LinkedHashMap<String, List> mapMap = new LinkedHashMap<>();
                List arrayList = new ArrayList();
                list.forEach(m -> {
                    if (str.equals(m.getName())) {
                        AttrValShowDto attrValShowDto = new AttrValShowDto();
                        for (GoodsAttrValDto goodsAttrValDto : listg) {
                            if (m.getAttrValue().equals(goodsAttrValDto.getAttrValue())) {
                                attrValShowDto.setShow(1);
                            }
                        }
                        //需要用replace把空格替换掉
                        attrValShowDto.setContent(m.getAttrValue().trim());
                        arrayList.add(attrValShowDto);
                    }
                });
                mapMap.put(str, arrayList);
                mapList.add(mapMap);
            }
        }
        int count = collectDao.countByGidAndAid(agentId.intValue(), goodId.intValue());
        GoodsDto goodsDto = new GoodsDto();
        goodsDetailDto.setIsCollect(count == 0 ? 0 : 1);
        goodsDto.setGoodsDetailDto(goodsDetailDto);
        goodsDto.setProdAttr(mapList);
        goodsDto.setAttrVals(attrVals);
        return goodsDto;
    }

    /**
     * 通过商品Id查询有效的商品
     * @param goodsId
     * @return
     */
    public GoodsEntity queryGoods(Long goodsId){
        return goodsDao.queryGoods(goodsId);
    }


}

package com.zlead.shopmgr.service.impl;

import com.zlead.dao.AclUserDao;
import com.zlead.dao.GoodsAttrValDao;
import com.zlead.dao.GoodsDao;
import com.zlead.dao.MemberDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.GoodsStockSummaryDTO;
import com.zlead.entity.vo.GoodsAttrValVo;
import com.zlead.entity.vo.GoodsDetailVo;
import com.zlead.entity.vo.GoodsListVo;
import com.zlead.entity.vo.GoodsStockSummaryListVO;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.PrdCustPrice;
import com.zlead.fplat.service.BaseWarehouseService;
import com.zlead.fplat.service.CrmPrdCatService;
import com.zlead.shopmgr.service.FactoryGoodsService;
import com.zlead.util.ObjectUtils;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.AppUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Service
public class FactoryGoodsServiceImpl implements FactoryGoodsService {
    @Autowired
    private OaFactoryInfoMapper mapper;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsAttrValDao goodsAttrValDao;
    @Autowired
    private CrmPrdCatService crmPrdCatService;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private BaseWarehouseService baseWarehouseService;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private PrdCustPriceMapper prdCustPriceMapper;

    @Autowired
    private BaseWarehouseMapper baseWarehouseMapper;

    @Autowired
    private AclUserDao aclUserDao;

    @Override
    public List<Map<String, Object>> findAllCatsByFactoryId(Long factoryId) {
        return mapper.findAllCatsByFactoryId(factoryId);
    }

    @Override
    public List<Map<String, Object>> findChildCatsByPactId(Long factoryId, Long pcatId) {
        return mapper.findChildCatsByPactId(factoryId, pcatId);
    }

    @Override
    public List<Map<String, Object>> findBrandsByFactoryIdAndAgentId(Long factoryId, Long agentId) {
        return mapper.findBrandsByFactoryIdAndAgentId(factoryId, agentId);
    }

    @Override
    public List<Map<String, Object>> findShopGoodsByCatIds(Long factoryId, Long agentId, Set<Long> brandIds, Set<Long> listIds, Set<Long> catIds) {
        return mapper.findShopGoodsByCatIds(factoryId, agentId, brandIds, listIds, catIds);
    }

    @Override
    public Integer findShopStock(Long shopId) {
        return goodsDao.findShopStock(shopId);
    }

    @Override
    public BigDecimal findShopGoodsTotalPrice(Long shopId) {
        return goodsDao.findShopGoodsTotalPrice(shopId);
    }

    @Override
    public PageList<GoodsEntity> findShopGoodsInfo(Long shopId, PageBounds rowBounds) {
        return goodsDao.findShopGoodsInfo(shopId, rowBounds);
    }

    @Override
    public GoodsEntity findGoodsDetail(Long shopId, Long goodsId) {
        return goodsDao.findGoodsDetail(shopId, goodsId);
    }

    @Override
    public int setMarket(Long goodsId, Integer isMarket, Integer stocknum) {
        return goodsDao.setMarket(isMarket, goodsId, stocknum);
    }



    @Override
    public List<GoodsListVo> findGoodsList(Long memberId, String fullName,Integer brandId,Integer listId,Integer modelId,Integer catId, PageBounds rowBounds) {
        //分类应该可以搜索其下级分类
        Set<Integer> catIds = new HashSet<>();
        if(catId != null){
            catIds.add(catId);
            List<CrmPrdCat> dataList = this.crmPrdCatService.findChildByCatId(catId,null);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(CrmPrdCat crmPrdCat: dataList){
                    catIds.add(crmPrdCat.getCatId());
                }
            }
        }
        return goodsDao.findGoodsList(memberId, fullName,brandId,listId,modelId,catIds, rowBounds);
    }

    @Override
    public List<GoodsListVo> findPrdInventList(Long memberId, Integer agentId,String fullName, Integer brandId, Integer listId, Integer modelId, Integer catId, Set<Integer> whIds, PageBounds rowBounds) {
        //分类应该可以搜索其下级分类
        Set<Integer> catIds = new HashSet<>();
        if(catId != null){
            catIds.add(catId);
            List<CrmPrdCat> dataList = this.crmPrdCatService.findChildByCatId(catId,null);
            if(CollectionUtils.isNotEmpty(dataList)){
                for(CrmPrdCat crmPrdCat: dataList){
                    catIds.add(crmPrdCat.getCatId());
                }
            }
        }

        Long sysId = memberDao.getSysIdByAgentId(memberId);
        AgentFac agentFac = new AgentFac();
        agentFac = agentFacMapper.findByAgentIdAndShopId(agentId,Integer.valueOf(sysId.intValue()));

        //此处需要获取当前用户的上下级权限并检索是否为库房管理员进行条件筛选
        List<GoodsListVo> resList = goodsDao.findPrdInventList(memberId, fullName,brandId,listId,modelId,catIds,null, rowBounds);

        if (ObjectUtils.isNotEmpty(agentFac)){

            if (null!=resList&&resList.size()>0){
                for (GoodsListVo vo:resList){
                    vo.setMarketPrice(countPrice(agentFac,vo));
                }
            }
        }
        return resList;
    }

    /**
     * 计算商品折扣后的价格
     * 计算方法：根据代理商的折扣类型 得到代理商的价格，然后 x 代理商的折扣
     * @param agentFac
     * @param vo
     * @return
     */
    private BigDecimal countPrice(AgentFac agentFac,GoodsListVo vo){
        BigDecimal marketPrice = null;
        String type = agentFac.getAgentDiscountType();
        if (StringUtils.isNotBlank(type)) {
            switch (type) {
                case "1":
                    if (null == vo.getBatchPrice()) {
                        marketPrice = vo.getCostPrice();
                    } else {
                        marketPrice = vo.getBatchPrice().multiply(BigDecimal.valueOf(agentFac.getAgentDiscount() == null ? 1D : agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
                    }
                    break;
                case "2":
                    if (null == vo.getItemPrice()) {
                        marketPrice = vo.getCostPrice();
                    } else {
                        marketPrice = vo.getItemPrice().multiply(BigDecimal.valueOf(agentFac.getAgentDiscount() == null ? 1D : agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
                    }
                    break;
                case "3":
                    if (null == vo.getRetailPrice()) {
                        marketPrice = vo.getCostPrice();
                    } else {
                        marketPrice = vo.getRetailPrice().multiply(BigDecimal.valueOf(agentFac.getAgentDiscount() == null ? 1D : agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
                    }
                    break;
                default:
                    marketPrice = vo.getCostPrice();
                    break;
            }
        }else {
            marketPrice = vo.getCostPrice();
        }

        return marketPrice;

    }

    @Override
    public GoodsDetailVo findShopGoodsDetail(Integer goodsId) {
        Integer prodId = goodsDao.findProdId(goodsId);
        GoodsDetailVo goodsDetailVo = goodsDao.findShopGoodsDetail(goodsId);
        Integer prodcnt = goodsDao.findProdCntWithBrand(goodsId);
        GoodsDetailVo goodsDetail = null;
        if (goodsDetailVo != null) {
            goodsDetail = new GoodsDetailVo();
            List<GoodsAttrValVo> list = new ArrayList<>();
            //List<GoodsAttrValEntity> goodsAttrVals = goodsAttrValDao.findByGdsId(prodId);
            List<GoodsAttrValEntity> goodsAttrVals = goodsAttrValDao.findGoodsAttrByProdId(prodId);
            for(GoodsAttrValEntity goodsAttrVal:goodsAttrVals){
                GoodsAttrValVo goodsAttrValVo = new GoodsAttrValVo();
                goodsAttrValVo.setAttrId(goodsAttrVal.getAttrId());
                goodsAttrValVo.setAttrName(goodsAttrVal.getAttrName());
                if(goodsAttrVal.getAttrValue().contains(",")){
                    goodsAttrValVo.setAttrValues(goodsAttrVal.getAttrValue().split(","));
                }else{
                    goodsAttrValVo.setAttrValues((String[])(Arrays.asList(goodsAttrVal.getAttrValue()).toArray()));
                }
                list.add(goodsAttrValVo);
            }
            goodsDetail.setGoodsId(goodsDetailVo.getGoodsId());
            goodsDetail.setProdId(prodId);
            goodsDetail.setBrandName(goodsDetailVo.getBrandName());
            goodsDetail.setFullName(goodsDetailVo.getFullName());
            goodsDetail.setImgs(goodsDetailVo.getImgs());
//            String intro = goodsDetailVo.getIntro();
//            if(intro!=null){
//                if(intro.contains("http://")){
//                    intro = intro.substring(intro.indexOf("http://"),intro.indexOf(".png")+4);
//                }else if(intro.contains("https://")){
//                    intro = intro.substring(intro.indexOf("https://"),intro.indexOf(".jpg")+4);
//                }
//            }else{
//                intro="";
//            }
//            goodsDetail.setIntro(intro);
            goodsDetail.setRemark(goodsDetailVo.getRemark()!=null?goodsDetailVo.getRemark():"");
            String imgs = goodsDetailVo.getImgs();
            if (imgs != null) {
                if (imgs.contains(",")) {
                    String[] goodsImgs = imgs.split(",");
                    goodsDetail.setGoodsImgs(goodsImgs);
                } else {
                    goodsDetail.setGoodsImgs(new String[]{imgs});
                }
            }
            if(0 == goodsDetailVo.getIf_show_price()){
                goodsDetail.setShowPrice(new BigDecimal("-1"));
            }else {
                goodsDetail.setShowPrice(goodsDetailVo.getShowPrice());
            }
            goodsDetail.setStock(goodsDetailVo.getStock());
            if("0".equals(goodsDetailVo.getStockType()) || 0 == goodsDetailVo.getIf_show_stock()){
                goodsDetail.setStock(-1);
            }
            goodsDetail.setProdcnt(prodcnt);
            goodsDetail.setGoodsAttrValVos(list);
        }
        return goodsDetail;
    }

    @Override
    public Map<String, Object> goodsStockSummary(Long memberId, Integer warehouseId, Integer brandId, Integer modelId, Integer listId, Integer catagoryId, String keyword) {
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        MemberEntity memberEntity = memberDao.findById(memberId);
        Set<Integer> whIds = baseWarehouseService.getWhIdByUserIds(userIds, memberEntity.getOwnShopid().intValue());
        GoodsStockSummaryDTO goodsStockSummaryDTO = new GoodsStockSummaryDTO();
        goodsStockSummaryDTO.setMemberId(memberId);
        goodsStockSummaryDTO.setWarehouseId(warehouseId);
        goodsStockSummaryDTO.setBrandId(brandId);
        goodsStockSummaryDTO.setModelId(modelId);
        goodsStockSummaryDTO.setListId(listId);
        goodsStockSummaryDTO.setCatagoryId(catagoryId);
        goodsStockSummaryDTO.setKeyword(keyword);
        goodsStockSummaryDTO.setWhIds(whIds);
        //先按筛选条件统计商品类型总数
        Integer goodsTotalNumber = goodsDao.countTotalNumber(goodsStockSummaryDTO);
        Map<String, Object> summary = null;
            summary = new HashMap<>();
            //统计商品库存总成本
         //   BigDecimal totalGoodsSupplyAmount = goodsDao.sumGoodsSupplyPrice(goodsStockSummaryDTO);
            //统计商品库存列表信息
            List<GoodsStockSummaryListVO> goodsStockSummaryList = goodsDao.getGoodsStockSummaryList(goodsStockSummaryDTO);
            BigDecimal totalGoodsSupplyAmount = new BigDecimal(
            goodsStockSummaryList.stream().mapToDouble(t->new BigDecimal(t.getStock().toString()).multiply(t.getSupplyPrice()).doubleValue()).sum());
            summary.put("goodsTotalNumber", goodsTotalNumber);
            summary.put("totalGoodsSupplyAmount", totalGoodsSupplyAmount.setScale(2, RoundingMode.HALF_UP));
            summary.put("goodsStockSummaryList", goodsStockSummaryList);

        return summary;
    }

    @Override
    public AgentFac getAgentFactByMemberId(Long id) {
        return null;
    }

    @Override
    public void updateGoodsListBySalePrice(GoodsListVo vo) {

    }

    @Override
    public GoodsDetailVo findPrdInventDetail(Integer agentId, Integer goodsId,Integer shopId) {

        Integer prodId = goodsDao.findProdId(goodsId);
        GoodsDetailVo goodsDetailVo = goodsDao.findShopGoodsDetail(goodsId);
        Integer prodcnt = goodsDao.findProdCntWithBrand(goodsId);
        GoodsDetailVo goodsDetail = null;
        if (goodsDetailVo != null) {
            goodsDetail = new GoodsDetailVo();
            List<GoodsAttrValVo> list = new ArrayList<>();
            //List<GoodsAttrValEntity> goodsAttrVals = goodsAttrValDao.findByGdsId(prodId);
            List<GoodsAttrValEntity> goodsAttrVals = goodsAttrValDao.findGoodsAttrByProdId(prodId);
            for(GoodsAttrValEntity goodsAttrVal:goodsAttrVals){
                GoodsAttrValVo goodsAttrValVo = new GoodsAttrValVo();
                goodsAttrValVo.setAttrId(goodsAttrVal.getAttrId());
                goodsAttrValVo.setAttrName(goodsAttrVal.getAttrName());
                if(goodsAttrVal.getAttrValue().contains(",")){
                    goodsAttrValVo.setAttrValues(goodsAttrVal.getAttrValue().split(","));
                }else{
                    goodsAttrValVo.setAttrValues((String[])(Arrays.asList(goodsAttrVal.getAttrValue()).toArray()));
                }
                list.add(goodsAttrValVo);
            }
            goodsDetail.setGoodsId(goodsDetailVo.getGoodsId());
            goodsDetail.setProdId(prodId);
            goodsDetail.setBrandName(goodsDetailVo.getBrandName());
            goodsDetail.setFullName(goodsDetailVo.getFullName());
            goodsDetail.setImgs(goodsDetailVo.getImgs());
//            String intro = goodsDetailVo.getIntro();
//            if(intro!=null){
//                if(intro.contains("http://")){
//                    intro = intro.substring(intro.indexOf("http://"),intro.indexOf(".png")+4);
//                }else if(intro.contains("https://")){
//                    intro = intro.substring(intro.indexOf("https://"),intro.indexOf(".jpg")+4);
//                }
//            }else{
//                intro="";
//            }
//            goodsDetail.setIntro(intro);
            goodsDetail.setRemark(goodsDetailVo.getRemark()!=null?goodsDetailVo.getRemark():"");
            String imgs = goodsDetailVo.getImgs();
            if (imgs != null) {
                if (imgs.contains(",")) {
                    String[] goodsImgs = imgs.split(",");
                    goodsDetail.setGoodsImgs(goodsImgs);
                } else {
                    goodsDetail.setGoodsImgs(new String[]{imgs});
                }
            }
            //此时showPrice根据代理商折扣去计算
            PrdCustPrice prdCustPrice = prdCustPriceMapper.selectByPrimaryKey(goodsId);
            //再查询代理商的折扣类型
            AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(agentId, shopId);
            if(agentFac!=null&&agentFac.getAgentDiscountType()!=null&&agentFac.getAgentDiscount()!=null){
                if("1".equals(agentFac.getAgentDiscountType())){
                    if (prdCustPrice.getBatchPrice() != null) {
                        goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getBatchPrice() * agentFac.getAgentDiscount()) );
                    } else {
                        goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getCostPrice()));
                    }
                }
                else if("2".equals(agentFac.getAgentDiscountType())){
                    if (prdCustPrice.getItemPrice() != null) {
                        goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getItemPrice() * agentFac.getAgentDiscount()) );
                    } else {
                        goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getCostPrice()));
                    }
                }
                else if("3".equals(agentFac.getAgentDiscountType())){
                    if (prdCustPrice.getRetailPrice() != null) {
                        goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getRetailPrice() * agentFac.getAgentDiscount()) );
                    } else {
                        goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getCostPrice()));
                    }
                }
            } else {
                goodsDetail.setShowPrice(new BigDecimal(prdCustPrice.getCostPrice()));
            }
            goodsDetail.setStock(goodsDetailVo.getStock());
            if("0".equals(goodsDetailVo.getStockType())){
                goodsDetail.setStock(-1);
            }
            goodsDetail.setProdcnt(prodcnt);
            goodsDetail.setGoodsAttrValVos(list);
        }
        return goodsDetail;
    }

}

package com.zlead.shopmgr.service.impl;

import com.zlead.dao.GoodsAttrValDao;
import com.zlead.dao.GoodsDao;
import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.dto.GoodsStockSummaryDTO;
import com.zlead.entity.vo.GoodsAttrValVo;
import com.zlead.entity.vo.GoodsDetailVo;
import com.zlead.entity.vo.GoodsListVo;
import com.zlead.entity.vo.GoodsStockSummaryListVO;
import com.zlead.fplat.dao.CrmPrdCatMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.service.CrmPrdCatService;
import com.zlead.shopmgr.service.FactoryGoodsService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.commons.collections.CollectionUtils;
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
            goodsDetail.setShowPrice(goodsDetailVo.getShowPrice());
            goodsDetail.setStock(goodsDetailVo.getStock());
            goodsDetail.setProdcnt(prodcnt);
            goodsDetail.setGoodsAttrValVos(list);
        }
        return goodsDetail;
    }

    @Override
    public Map<String, Object> goodsStockSummary(Long memberId, Integer warehouseId, Integer brandId, Integer modelId, Integer listId, Integer catagoryId, String keyword) {
        GoodsStockSummaryDTO goodsStockSummaryDTO = new GoodsStockSummaryDTO();
        goodsStockSummaryDTO.setMemberId(memberId);
        goodsStockSummaryDTO.setWarehouseId(warehouseId);
        goodsStockSummaryDTO.setBrandId(brandId);
        goodsStockSummaryDTO.setModelId(modelId);
        goodsStockSummaryDTO.setListId(listId);
        goodsStockSummaryDTO.setCatagoryId(catagoryId);
        goodsStockSummaryDTO.setKeyword(keyword);
        //先按筛选条件统计商品类型总数
        Integer goodsTotalNumber = goodsDao.countTotalNumber(goodsStockSummaryDTO);
        Map<String, Object> summary = null;
        if(goodsTotalNumber.intValue() > 0){
            summary = new HashMap<>();
            //统计商品库存总成本
            BigDecimal totalGoodsSupplyAmount = goodsDao.sumGoodsSupplyPrice(goodsStockSummaryDTO);
            //统计商品库存列表信息
            List<GoodsStockSummaryListVO> goodsStockSummaryList = goodsDao.getGoodsStockSummaryList(goodsStockSummaryDTO);
            summary.put("goodsTotalNumber", goodsTotalNumber);
            summary.put("totalGoodsSupplyAmount", totalGoodsSupplyAmount.setScale(2, RoundingMode.HALF_UP));
            summary.put("goodsStockSummaryList", goodsStockSummaryList);
        }else{
            //返回空Map
            summary = Collections.emptyMap();
        }
        return summary;
    }

}

package com.zlead.service.impl;

import com.zlead.dao.GoodsDao;
import com.zlead.dao.ProdImgDao;
import com.zlead.dao.ProductDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.*;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.*;
import com.zlead.fplat.entity.vo.AgentBlmcVO;
import com.zlead.fplat.service.CrmPrdCatService;
import com.zlead.fplat.service.OaFactoryInfoService;
import com.zlead.service.GoodsBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.ObjectUtils;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.util.page.Pagination;
import com.zlead.utils.LoginUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

import com.zlead.fplat.service.AgentBandListService;


@Transactional
@Service
public class GoodsBgServiceImpl implements GoodsBgService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsBgServiceImpl.class);

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProdImgDao prodImgDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private GoodsattrvalMapper goodsattrvalMapper;

    @Autowired
    private GoodscatMapper goodscatMapper;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private CrmPrdCatService crmPrdCatService;

    @Autowired
    private OaFactoryInfoService oaFactoryInfoService;

    @Autowired
    private AgentBandListService agentBandListService;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private AgentbandMapper agentbandMapper;

    @Autowired
    private CrmPrdCatMapper crmPrdCatMapper;

    @Autowired
    private CrmPrdListMapper crmPrdListMapper;


    @Autowired
    private ProdmodelMapper prodmodelMapper;

    @Autowired
    private CrmPrdModelMapper crmPrdModelMapper;
    @Autowired
    private CustbandMapper custbandMapper;
    
    @Autowired
    private PrdCustPriceMapper prdCustPriceMapper;

    @Autowired
    private LoginUtil loginUtil;


    @Override
    @Transactional(readOnly = true)
    public PageList<GoodsEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = goodsDao.findPage(params, rowBounds);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public GoodsEntity findById(Long id) {
        return goodsDao.findById(id);
    }

    @Override
    public void save(GoodsEntity entity) {
        goodsDao.insert(entity);
    }

    @Override
    public void update(GoodsEntity entity) {
        goodsDao.update(entity);
    }


    @Override
    public void delete(Long id) {
        goodsDao.delete(id);
    }

    /**
     * 后台用户添加商品
     */
    @Override
    public boolean sysGoodsSave(GoodsEntity goodsEntity, SysUserEntity userEntity) {
        boolean b = true;
        //查询该登录的供应商店铺信息
        try {
            ShopEntity shopEntity = shopDao.findByMgrUserId(userEntity.getId());
            if (shopEntity == null) {
                return false;
            }
            goodsSave(goodsEntity, userEntity.getId(), shopEntity);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 商品修改
     */
    @Override
    public boolean goodsUpdate(GoodsEntity goodsEntity) {
        boolean b = true;
        try {
            //查询商品的信息
            GoodsEntity goods = goodsDao.findById(goodsEntity.getId());
            if (goods == null) {
                return false;
            }
            //查询产品信息
            ProductEntity productEntity = productDao.findById(goods.getProdId());
            if (productEntity == null) {
                return false;
            }
            goods.setPrice(goodsEntity.getPrice());
            goods.setStock(goodsEntity.getStock());
            goods.setSalesNum(goodsEntity.getSalesNum());
            goods.setClickNum(goodsEntity.getClickNum());
            goods.setIsMarket(goodsEntity.getIsMarket());
            goods.setIsHome(goodsEntity.getIsHome());
            goods.setUpdateDate(new Date());
            goodsDao.update(goods);
            //修改产品的信息
            productEntity.setIsHome(goods.getIsHome());
            productEntity.setIsMarket(goods.getIsMarket());
            productEntity.setUpdateDate(new Date());
            productDao.update(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public boolean goodUpdate(GoodsEntity goodsEntity) {
        boolean b = true;
        try {

            //查询商品的信息
            GoodsEntity goods = goodsDao.findById(goodsEntity.getId());

            if (goods == null) {
                return false;
            }
            goods.setFullName(goodsEntity.getFullName());
            goods.setImgs(goodsEntity.getImgs());
            goods.setProdType(goodsEntity.getProdType());
            goods.setPointPrice(goodsEntity.getPointPrice());
            goods.setAgentPrice(goodsEntity.getAgentPrice());
            goods.setSolrName(goodsEntity.getSolrName());
            goods.setClickNum(goodsEntity.getClickNum());
            goods.setIsHome(goodsEntity.getIsHome());
            goods.setDiscount(goodsEntity.getDiscount());
            goods.setPoint(goodsEntity.getPoint());
            goods.setChannel(goodsEntity.getChannel());
            goods.setRemark(goodsEntity.getRemark());
            goods.setIntro(goodsEntity.getIntro());
            goods.setIsMarket(goodsEntity.getIsMarket());
            goods.setMarketPrice(goodsEntity.getMarketPrice());
            goods.setSupplyPrice(goodsEntity.getSupplyPrice());
            goods.setPrice(goodsEntity.getPrice());
            goods.setStock(goodsEntity.getStock());
            goods.setSalesNum(goodsEntity.getSalesNum());
            goods.setClickNum(goodsEntity.getClickNum());
            goods.setIsMarket(goodsEntity.getIsMarket());
            goods.setIsHome(goodsEntity.getIsHome());
            goods.setUpdateDate(new Date());
            goods.setPrice(goodsEntity.getPrice());
            goodsDao.update(goods);

        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 商品添加
     */
    @Override
    public boolean goodsSave(GoodsEntity goodsEntity, Long userId, ShopEntity shopEntity) {
        boolean b = true;
        try {
            goodsEntity.setIsAudit(1);

            goodsEntity.setProdId((long) 0);//设置proid
            //先添加prod信息
            ProductEntity productEntity = new ProductEntity();
            productEntity.setCatagoryId(goodsEntity.getCatagoryId());
            productEntity.setProductName(goodsEntity.getFullName());
            productEntity.setIntro(goodsEntity.getIntro());
            productEntity.setSupplierShopId(shopEntity.getId());
            productEntity.setAgentPrice(goodsEntity.getAgentPrice());
            productEntity.setSupplyPrice(goodsEntity.getSupplyPrice());
            productEntity.setMarketPrice(goodsEntity.getMarketPrice());
            productEntity.setIsMarket(goodsEntity.getIsMarket());
            productEntity.setIsSpec(0);
            productEntity.setIsHome(goodsEntity.getIsHome());
            productEntity.setIsAudit(goodsEntity.getIsAudit());
            productEntity.setUserId(userId);
            productEntity.setCreateDate(new Date());


            productDao.insert(productEntity);
            //添加图片信息
            //拿到图片的信息
            String imgs = goodsEntity.getImgs();
            String[] split = imgs.split(",");
            //获取第一张图片
            String firstImg = split[0];
            ProdImgEntity prodImgEntity = new ProdImgEntity();

            prodImgEntity.setProdId((long) 0);//设置proid为0

            prodImgEntity.setProdId(productEntity.getId());
            prodImgEntity.setFirstImg(firstImg);
            prodImgEntity.setImgs(imgs);
            prodImgDao.insert(prodImgEntity);
            //保存goods信息
            goodsEntity.setProdId(productEntity.getId());
            goodsEntity.setProdType(0);
            goodsEntity.setFirstImg(firstImg);
            goodsEntity.setShopId(shopEntity.getId());
            goodsEntity.setSupplierShopId(shopEntity.getId());
            goodsEntity.setCreateDate(new Date());
            goodsEntity.setVersion(1);
            goodsDao.insert(goodsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 商品添加
     */
    @Override
    public boolean goodSave(GoodsEntity goodsEntity, Long userId, ShopEntity shopEntity) {
        boolean b = true;
        try {
            goodsEntity.setIsAudit(1);

            goodsEntity.setProdId((long) 0);//设置proid
            //先添加prod信息

            //添加图片信息
            //拿到图片的信息
            String imgs = goodsEntity.getImgs();

            //获取第一张图片
            String firstImg = "";

            if (imgs != null && imgs.indexOf(",") > 0) {
                String[] split = imgs.split(",");
                firstImg = split[0];
                goodsEntity.setFirstImg(firstImg);
            }

            if (goodsEntity.getCatagoryId() == null) {
                goodsEntity.setCatagoryId(0L);
            }

            if (goodsEntity.getIsAudit() == null) {
                goodsEntity.setIsAudit(1);
            }

            if (goodsEntity.getIsMarket() == null) {
                goodsEntity.setIsMarket(1);
            }
            if (goodsEntity.getPoint() == null) {
                goodsEntity.setPoint(0L);
            }
            //保存goods信息
            //goodsEntity.setProdId(productEntity.getId());
            goodsEntity.setProdType(0);
            goodsEntity.setShopId(shopEntity.getId());
            goodsEntity.setSupplierShopId(shopEntity.getId());
            goodsEntity.setFreezeStock(0);
            goodsEntity.setChannel(5);
            goodsEntity.setCreateDate(new Date());
            goodsEntity.setVersion(1);
            goodsEntity.setSalesNum(101);
            goodsDao.insert(goodsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public int queryCountByTerm(Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key) {
        return goodsDao.queryCountByTerm(bids, lids, mids, cids, key);
    }

    @Override
    public List<Map<String, Object>> queryListByTerm(Long factoryId, Long agentId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key, Integer start, Integer end) {
        return goodsDao.queryListByTerm(factoryId, agentId, bids, lids, mids, cids, key, start, end);
    }

    @Override
    public List<Map<String, Object>> queryListByTermAPP(Long factoryId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key, Integer start, Integer end) {
        return goodsDao.queryListByTermAPP(factoryId, bids, lids, mids, cids, key, start, end);
    }


    @Override
    public void insert(GoodsEntity goodsEntity) {
        goodsDao.insert(goodsEntity);
        if (goodsEntity.getId() != null) {
            if (CollectionUtils.isNotEmpty(goodsEntity.getGoodsattrvals())) {
                for (Goodsattrval goodsattrval : goodsEntity.getGoodsattrvals()) {
                    goodsattrval.setGoodsId(goodsEntity.getId().intValue());
                    goodsattrvalMapper.insert(goodsattrval);
                }
            }
            if (CollectionUtils.isNotEmpty(goodsEntity.getGoodscats())) {
                for (Goodscat goodscat : goodsEntity.getGoodscats()) {
                    goodscat.setGoodsId(goodsEntity.getId());
                    goodscatMapper.insert(goodscat);
                }
            }
        }
    }

    @Override
    public List<Map<String, Object>> queryAllListByTerm(Long factoryId, Long agentId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key) {
        return goodsDao.queryAllListByTerm(factoryId, agentId, bids, lids, mids, cids, key);
    }

    @Override
    public List<Map<String, Object>> queryAllListByTermAPP(Long factoryId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key) {
        return goodsDao.queryAllListByTermAPP(factoryId, bids, lids, mids, cids, key);
    }

    /**
     * 删除商品
     *
     * @return
     */
    public boolean goodsDelete(Long id) {
        boolean b = true;
        try {
            //查询商品信息
            GoodsEntity goodsEntity = goodsDao.findById(id);
            if (goodsEntity == null) {
                return false;
            }
            //查询产品信息
            ProductEntity productEntity = productDao.findById(goodsEntity.getProdId());
            if (productEntity != null) {
                ProdImgEntity imgEntity = prodImgDao.findByProdId(productEntity.getId());
                if (imgEntity != null) {
                    prodImgDao.delete(imgEntity.getId());
                }
            }
            //删除该产品
            productDao.delete(productEntity.getId());
            //删除该商品
            goodsDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    public List<GoodsEntity> Hgoods() {

        return goodsDao.Hgoods();
    }

    @Override
    public List<GoodsAttrValEntity> goodsCanvs(@Param("List") List<Integer> goodsId) {
        return goodsDao.goodsCanvs(goodsId);
    }


    @Override
    public JsonResult query(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize) {
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Set<Long> cids = new HashSet<>();//分类id
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        List<Map<String, Object>> blist = new ArrayList<>();//品牌
        List<Map<String, Object>> llist = new ArrayList<>();//系列
        List<Map<String, Object>> mlist = new ArrayList<>();//型号
        List<Map<String, Object>> clist = new ArrayList<>();//分类
        int count = 0;
        List<Map<String, Object>> goods = new ArrayList<>();
        Map<String, Object> term = new HashMap<>();
        //查出来代理商关联的品牌、系列、型号、分类
        List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factoryId, bKey, lKey, mKey, cKey);

        List<Long> factoryIds = new ArrayList<Long>();
        if (factoryId != null) {
            factoryIds.add(factoryId);
        }
        //add by ykf查出代理商参与的活动商品的品牌、系列、型号、分类
        List<AgentBlmcVO> activicityBlmcList = oaAgentMasMapper.findActivityBLMC(agentId, factoryIds, bKey, lKey, mKey, cKey);

        if (CollectionUtils.isEmpty(blmcList) && CollectionUtils.isEmpty(activicityBlmcList)) {
            term.put("blist", blist);
            term.put("llist", llist);
            term.put("mlist", mlist);
            term.put("clist", clist);
            dataMap.put("count", count);
            dataMap.put("goods", goods);
            dataMap.put("term", term);
            result.setCode(2);
            result.setSuccess(false);
            return result;
        }
        for (AgentBlmcVO agentBlmcVO : blmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cids.add(agentBlmcVO.getCatId());
            }
        }

        //add by ykf添加代理商参与的活动商品的品牌、系列、型号、分类商品查询参数
        Set<Long> cActIds = new HashSet<>();//分类id
        Map<Long, String> bActMap = new HashMap<>();//品牌
        Map<Long, String> lActMap = new HashMap<>();//系列
        Map<Long, String> mActMap = new HashMap<>();//型号
        for (AgentBlmcVO agentBlmcVO : activicityBlmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bActMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lActMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mActMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cActIds.add(agentBlmcVO.getCatId());
            }
        }

        if ("c".equals(x)) {
            cids.add(-1L);
        }
        boolean catlf = true;//用来判断关联分类的时候采用左连接还是内连接
        if (StringUtils.isNotBlank(cKey)) {
            catlf = false;
        }
        String queryFlag = "ALL";//判断查询条件
        if (CollectionUtils.isEmpty(blmcList)) {
            queryFlag = "ACT";
        }
        if (CollectionUtils.isEmpty(activicityBlmcList)) {
            queryFlag = "PT";
        }

        //List<Map<String, Object>> goodsBLMC = goodsDao.findAllBLMC(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf);
        //add by ykf查出代理商参与的活动商品的品牌、系列、型号、分类
        List<Map<String, Object>> goodsBLMC = goodsDao.findAllAndActivityBLMC(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf,
                bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag);
        if (CollectionUtils.isNotEmpty(goodsBLMC)) {
            count = goodsBLMC.size();
            //goods = goodsDao.findList(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf, (page - 1) * paseSize, paseSize);
            //add by ykf查出代理商参与的活动商品的品牌、系列、型号、分类
            goods = goodsDao.findAllList(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf,
                    bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag, (page - 1) * paseSize, paseSize);
            if (CollectionUtils.isNotEmpty(goods)) {
                cids.clear();
                //add by ykf添加活动商品查询出来的品牌、系列、型号、分类
                for (AgentBlmcVO agentBlmcVO : activicityBlmcList) {
                    if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                        bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
                    }
                    if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                        lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
                    }
                    if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                        mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
                    }
                    if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                        cids.add(agentBlmcVO.getCatId());
                    }
                }
                for (Map<String, Object> tMap : goodsBLMC) {
                    if (tMap.get("brand_id") != null) {
                        Long bid = Long.parseLong(tMap.get("brand_id").toString());
                        Map<String, Object> map = new HashMap<>();
                        map.put("band_id", bid);
                        map.put("band_name", bMap.get(bid));
                        if (!blist.contains(map)) {
                            blist.add(map);
                        }
                    }
                    if (tMap.get("list_id") != null) {
                        Long lid = Long.parseLong(tMap.get("list_id").toString());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", lid);
                        map.put("name", lMap.get(lid));
                        if (!llist.contains(map)) {
                            llist.add(map);
                        }
                    }
                    if (tMap.get("model_id") != null) {
                        Long mid = Long.parseLong(tMap.get("model_id").toString());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", mid);
                        map.put("name", mMap.get(mid));
                        if (!mlist.contains(map)) {
                            mlist.add(map);
                        }
                    }
                    if (tMap.get("cat_ids") != null) {
                        String cidstr = tMap.get("cat_ids").toString();
                        String[] cidsArray = cidstr.split(",");
                        for (String cid : cidsArray) {
                            cids.add(Long.parseLong(cid));
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(cids)) {
            //获取分类
            List<CrmPrdCat> cats = crmPrdCatService.findListByIds(cids);
            if (CollectionUtils.isNotEmpty(cats)) {
                for (CrmPrdCat cat : cats) {
                    if (cat.getPcatId() == null || cat.getIsFac() == null) {
                        continue;
                    }
                    CrmPrdCat crmPrdCat = cat;
                    if (0 != cat.getPcatId()) {
                        crmPrdCat = getParentCat(cat.getPcatId());
                    }
                    if (1 == crmPrdCat.getIsFac() || (factoryId != null && 0 == crmPrdCat.getIsFac())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", crmPrdCat.getCatId());
                        map.put("name", crmPrdCat.getCatName());
                        if (!clist.contains(map)) {
                            clist.add(map);
                        }
                    }
                }
            }
        }
        term.put("blist", blist);
        term.put("llist", llist);
        term.put("mlist", mlist);
        term.put("clist", clist);
        dataMap.put("count", count);
        dataMap.put("goods", goods);
        dataMap.put("term", term);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);
        return result;
    }

    @Override
    public JsonResult queryGoods(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize) {
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Set<Long> cids = new HashSet<>();//分类id
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        List<Map<String, Object>> blist = new ArrayList<>();//品牌
        List<Map<String, Object>> llist = new ArrayList<>();//系列
        List<Map<String, Object>> mlist = new ArrayList<>();//型号
        List<Map<String, Object>> clist = new ArrayList<>();//分类
        int count = 0;
        List<Map<String, Object>> goods = new ArrayList<>();
        Map<String, Object> term = new HashMap<>();
        //查出来代理商关联的品牌、系列、型号、分类
        List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factoryId, bKey, lKey, mKey, cKey);

        List<Long> factoryIds = new ArrayList<Long>();
        if (factoryId != null) {
            factoryIds.add(factoryId);
        }
        //add by ykf查出代理商参与的活动商品的品牌、系列、型号、分类
        List<AgentBlmcVO> activicityBlmcList = oaAgentMasMapper.findActivityBLMC(agentId, factoryIds, bKey, lKey, mKey, cKey);

        if (CollectionUtils.isEmpty(blmcList) && CollectionUtils.isEmpty(activicityBlmcList)) {
            term.put("blist", blist);
            term.put("llist", llist);
            term.put("mlist", mlist);
            term.put("clist", clist);
            dataMap.put("count", count);
            dataMap.put("goods", goods);
            dataMap.put("term", term);
            result.setCode(2);
            result.setSuccess(false);
            return result;
        }
        for (AgentBlmcVO agentBlmcVO : blmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cids.add(agentBlmcVO.getCatId());
            }
        }

        //add by ykf添加代理商参与的活动商品的品牌、系列、型号、分类商品查询参数
        Set<Long> cActIds = new HashSet<>();//分类id
        Map<Long, String> bActMap = new HashMap<>();//品牌
        Map<Long, String> lActMap = new HashMap<>();//系列
        Map<Long, String> mActMap = new HashMap<>();//型号
        for (AgentBlmcVO agentBlmcVO : activicityBlmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bActMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lActMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mActMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cActIds.add(agentBlmcVO.getCatId());
            }
        }

        if ("c".equals(x)) {
            cids.add(-1L);
        }
        boolean catlf = true;//用来判断关联分类的时候采用左连接还是内连接
        if (StringUtils.isNotBlank(cKey)) {
            catlf = false;
        }
        String queryFlag = "ALL";//判断查询条件
        if (CollectionUtils.isEmpty(blmcList)) {
            queryFlag = "ACT";
        }
        if (CollectionUtils.isEmpty(activicityBlmcList)) {
            queryFlag = "PT";
        }

        //List<Map<String, Object>> goodsBLMC = goodsDao.findAllBLMC(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf);
        //add by ykf查出代理商参与的活动商品的品牌、系列、型号、分类
        List<Map<String, Object>> goodsBLMC = goodsDao.findAllAndActivityBLMC(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf,
                bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag);
        if (CollectionUtils.isNotEmpty(goodsBLMC)) {
            count = goodsBLMC.size();
            //goods = goodsDao.findList(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf, (page - 1) * paseSize, paseSize);
            //add by ykf查出代理商参与的活动商品的品牌、系列、型号、分类
            goods = goodsDao.findAllList(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf,
                    bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag, (page - 1) * paseSize, paseSize);
            if (CollectionUtils.isNotEmpty(goods)) {
                cids.clear();
                //add by ykf添加活动商品查询出来的品牌、系列、型号、分类
                for (AgentBlmcVO agentBlmcVO : activicityBlmcList) {
                    if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                        bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
                    }
                    if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                        lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
                    }
                    if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                        mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
                    }
                    if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                        cids.add(agentBlmcVO.getCatId());
                    }
                }
                for (Map<String, Object> tMap : goodsBLMC) {
                    if (tMap.get("brand_id") != null) {
                        Long bid = Long.parseLong(tMap.get("brand_id").toString());
                        Map<String, Object> map = new HashMap<>();
                        map.put("band_id", bid);
                        //map.put("band_name", bMap.get(bid));
                        String brandName = bMap.get(bid);
                        map.put("band_name", brandName);
                        if (blist.size() == 0) {
                            blist.add(map);
                        } else {
                            boolean add = true;
                            for (Map<String, Object> brand : blist) {
                                if (brand.containsValue(brandName)) {
                                    add = false;
                                    break;
                                }
                            }
                            if (add) {
                                blist.add(map);
                            }
                        }
                    }
                    if (tMap.get("list_id") != null) {
                        Long lid = Long.parseLong(tMap.get("list_id").toString());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", lid);
                        //map.put("name", lMap.get(lid));
                        String listName = lMap.get(lid);
                        map.put("name", listName);
                        if (llist.size() == 0) {
                            llist.add(map);
                        } else {
                            boolean add = true;
                            for (Map<String, Object> list : llist) {
                                if (list.containsValue(listName)) {
                                    add = false;
                                    break;
                                }
                            }
                            if (add) {
                                llist.add(map);
                            }
                        }
                    }
                    if (tMap.get("model_id") != null) {
                        Long mid = Long.parseLong(tMap.get("model_id").toString());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", mid);
                        //map.put("name", mMap.get(mid));
                        String modelName = mMap.get(mid);
                        map.put("name", modelName);
                        if (mlist.size() == 0) {
                            mlist.add(map);
                        } else {
                            boolean add = true;
                            for (Map<String, Object> model : mlist) {
                                if (model.containsValue(modelName)) {
                                    add = false;
                                    break;
                                }
                            }
                            if (add) {
                                mlist.add(map);
                            }
                        }
                    }
                    if (tMap.get("cat_ids") != null) {
                        String cidstr = tMap.get("cat_ids").toString();
                        String[] cidsArray = cidstr.split(",");
                        for (String cid : cidsArray) {
                            cids.add(Long.parseLong(cid));
                        }
                    }

                }
            }
        }
        if (CollectionUtils.isNotEmpty(cids)) {
            //获取分类
//            List<Integer> catIdss = crmPrdCatMapper.queryCatIdsList(agentId);
//            List<CrmPrdCat> crmPrdCatList = crmPrdCatMapper.selectByIds(catIdss);
            List<CrmPrdCat> cats = crmPrdCatService.findListByIds(cids);

            if (CollectionUtils.isNotEmpty(cats)) {
                for (CrmPrdCat cat : cats) {
                    if (cat.getPcatId() == null || cat.getIsFac() == null) {
                        continue;
                    }
                    CrmPrdCat crmPrdCat = cat;
                    if (0 != cat.getPcatId()) {
                        crmPrdCat = getParentCat(cat.getPcatId());
                    }
                    if (1 == crmPrdCat.getIsFac() || (factoryId != null && 0 == crmPrdCat.getIsFac())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", crmPrdCat.getCatId());
//                        map.put("name", crmPrdCat.getCatName());
//                        if (!clist.contains(map)) {
//                            clist.add(map);
//                        }
                        String catName = crmPrdCat.getCatName();
                        map.put("name", catName);
                        if (clist.size() == 0) {
                            clist.add(map);
                        } else {
                            boolean add = true;
                            for (Map<String, Object> catList : clist) {
                                if (catList.containsValue(catName)) {
                                    add = false;
                                    break;
                                }
                            }
                            if (add) {
                                clist.add(map);
                            }
                        }
                    }
                }
            }
        }
        term.put("blist", blist);
        term.put("llist", llist);
        term.put("mlist", mlist);
        term.put("clist", clist);
        dataMap.put("count", count);
        dataMap.put("goods", goods);
        dataMap.put("term", term);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);
        return result;
    }


    //@Override
    private JsonResult oldqueryGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Set<Long> cids = new HashSet<>();//分类id
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        List<Map<String, Object>> blist = new ArrayList<>();//品牌
        List<Map<String, Object>> llist = new ArrayList<>();//系列
        List<Map<String, Object>> mlist = new ArrayList<>();//型号
        List<Map<String, Object>> clist = new ArrayList<>();//分类
        int count = 0;
        List<Map<String, Object>> goods = new ArrayList<>();
        Map<String, Object> term = new HashMap<>();
        //1、根据查询条件查询代理商关联的品牌、系列、类型、分类用于页面展示（页面展示的都是有商品的数据）
       /* List<AgentBlmcVO> blmcList = oaAgentMasMapper.findNewBLMC(agentId, factoryId, bKey, lKey, mKey, cKey);

        List<Long> factoryIds = new ArrayList<Long>();
        if(factoryId != null){
            factoryIds.add(factoryId);
        }
        //2、根据查询条件查询代理商参与的活动品牌、系列、类型、分类用于页面展示（页面展示的都是有商品的数据）
        List<AgentBlmcVO> actBlmcList = oaAgentMasMapper.findActivityBLMC(agentId, factoryIds, bKey, lKey, mKey, cKey);

        if (CollectionUtils.isEmpty(blmcList) && CollectionUtils.isEmpty(actBlmcList)) {
            term.put("blist", blist);
            term.put("llist", llist);
            term.put("mlist", mlist);
            term.put("clist", clist);
            dataMap.put("count", count);
            dataMap.put("goods", goods);
            dataMap.put("term", term);
            result.setCode(2);
            result.setSuccess(false);
            return result;
        }
        for (AgentBlmcVO agentBlmcVO : blmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cids.add(agentBlmcVO.getCatId());
            }
        }

        //add by ykf添加代理商参与的活动商品的品牌、系列、型号、分类商品查询参数
        Set<Long> cActIds = new HashSet<>();//分类id
        Map<Long, String> bActMap = new HashMap<>();//品牌
        Map<Long, String> lActMap = new HashMap<>();//系列
        Map<Long, String> mActMap = new HashMap<>();//型号
        for (AgentBlmcVO agentBlmcVO : actBlmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bActMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lActMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mActMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cActIds.add(agentBlmcVO.getCatId());
            }
        }
*/
      /*  List<Agentband> agentbands = agentbandMapper.selectByAgentFacIds(agentId.intValue(),facIds);


            term.put("blist", agentbands);
            term.put("llist", llist);
            term.put("mlist", mlist);
            term.put("clist", clist);
            dataMap.put("count", count);
            dataMap.put("goods", goods);
            dataMap.put("term", term);
            result.setCode(2);
            result.setSuccess(false);


        if ("c".equals(x)) {
            cids.add(-1L);
        }
        boolean catlf = true;//用来判断关联分类的时候采用左连接还是内连接
        if (StringUtils.isNotBlank(cKey)) {
            catlf = false;
        }
        String queryFlag = "ALL";//判断查询条件
        if(CollectionUtils.isEmpty(blmcList) && CollectionUtils.isNotEmpty(actBlmcList)){
            queryFlag = "ACT";
        }
        if(CollectionUtils.isEmpty(actBlmcList) && CollectionUtils.isNotEmpty(blmcList)){
            queryFlag = "PT";
        }

        //根据名称查询出来的品牌、系列、型号、分类ID分页查询商品数据
        count = goodsDao.findAllListCount(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf,
                bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds ,queryFlag);
        if(count > 0){
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            goods =  goodsDao.findAllListByPage(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key, catlf,
                    bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds ,queryFlag,pageBounds);
            for (Map<String, Object> newGoods:goods ) {
                String full_name = StrTools.strDistinct(newGoods.get("full_name").toString());
                newGoods.put("full_name",full_name);
            }
        }
        //封装前台显示的品牌、系列、类型、分类（重名的只显示一个）
        //代理商代理的
        if(CollectionUtils.isNotEmpty(blmcList)){
            for (AgentBlmcVO agentBlmcVO : blmcList) {
                Long bandId  = agentBlmcVO.getBandId();
                String bandName = agentBlmcVO.getBandName();

                Long listId = agentBlmcVO.getListId();
                String listName = agentBlmcVO.getListName();

                Long modelId = agentBlmcVO.getModelId();
                String modelName = agentBlmcVO.getModelName();

                Long catId = agentBlmcVO.getCatId();
                String catName = agentBlmcVO.getCatName();
                //添加品牌
                if (bandId != null && StringUtils.isNotBlank(bandName)) {
                    boolean addFlag = true;
                    for(Map<String,Object> map : blist){
                        if(bandName.equals(map.get("band_name"))){
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("band_id",bandId);
                        map.put("band_name",bandName);
                        blist.add(map);
                    }
                }
                //添加系列
                if (listId != null && StringUtils.isNotBlank(listName)) {
                    boolean addFlag = true;
                    for(Map<String,Object> map : llist){
                        if(listName.equals(map.get("name"))){
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("id",listId);
                        map.put("name",listName);
                        llist.add(map);
                    }
                }
                //添加类型
                if (modelId != null && StringUtils.isNotBlank(modelName)) {
                    boolean addFlag = true;
                    for(Map<String,Object> map : mlist){
                        if(modelName.equals(map.get("name"))){
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("id",modelId);
                        map.put("name",modelName);
                        mlist.add(map);
                    }
                }
                //添加分类
                if (catId != null && StringUtils.isNotBlank(catName)) {
                    cids.add(catId);
                }
            }
        }

        //活动商品的
        if(CollectionUtils.isNotEmpty(actBlmcList)){
            for (AgentBlmcVO agentBlmcVO : actBlmcList) {
                Long bandId  = agentBlmcVO.getBandId();
                String bandName = agentBlmcVO.getBandName();

                Long listId = agentBlmcVO.getListId();
                String listName = agentBlmcVO.getListName();

                Long modelId = agentBlmcVO.getModelId();
                String modelName = agentBlmcVO.getModelName();

                Long catId = agentBlmcVO.getCatId();
                String catName = agentBlmcVO.getCatName();
                //添加品牌
                if (bandId != null && StringUtils.isNotBlank(bandName)) {
                    boolean addFlag = true;
                    for(Map<String,Object> map : blist){
                        if(bandName.equals(map.get("band_name"))){
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("band_id",bandId);
                        map.put("band_name",bandName);
                        blist.add(map);
                    }
                }
                //添加系列
                if (listId != null && StringUtils.isNotBlank(listName)) {
                    boolean addFlag = true;
                    for(Map<String,Object> map : llist){
                        if(listName.equals(map.get("name"))){
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("id",listId);
                        map.put("name",listName);
                        llist.add(map);
                    }
                }
                //添加类型
                if (modelId != null && StringUtils.isNotBlank(modelName)) {
                    boolean addFlag = true;
                    for(Map<String,Object> map : mlist){
                        if(modelName.equals(map.get("name"))){
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("id",modelId);
                        map.put("name",modelName);
                        mlist.add(map);
                    }
                }
                //添加分类
                if (catId != null && StringUtils.isNotBlank(catName)) {
                    cids.add(catId);
                }
            }
        }

        //二级分类显示分类名称还是分类备注（当选择的工厂的时候显示的是分类备注）
        boolean showCatNameFlag = true;
        if(factoryId != null){
            showCatNameFlag = false;
        }

        if (CollectionUtils.isNotEmpty(cids)) {
            //获取分类
            List<CrmPrdCat> cats = crmPrdCatService.findListByIds(cids);

            if (CollectionUtils.isNotEmpty(cats)) {
                for (CrmPrdCat cat : cats) {
                    if (cat.getPcatId() == null || cat.getIsFac() == null) {
                        continue;
                    }
                    CrmPrdCat crmPrdCat = cat;
                    if (0 != cat.getPcatId()) {
                        crmPrdCat = getParentCat(cat.getPcatId());
                    }
                    if (1 == crmPrdCat.getIsFac() || (factoryId != null && 0 == crmPrdCat.getIsFac())) {
                        Integer catId = crmPrdCat.getCatId();
                        String catName = null;
                        if(showCatNameFlag){
                            catName = crmPrdCat.getCatName();
                        } else{
                            //二级店铺页，平台分类显示备注名称，自定义分类显示分类名称
                            if(1 == crmPrdCat.getIsFac()){
                                catName = crmPrdCat.getCatDesc();
                            }else{
                                catName = crmPrdCat.getCatName();
                            }
                        }
                        if (catId != null && StringUtils.isNotBlank(catName)) {
                            boolean addFlag = true;
                            for(Map<String,Object> map : clist){
                                if(catName.equals(map.get("name"))){
                                    addFlag = false;
                                    break;
                                }
                            }
                            if(addFlag){
                                Map<String,Object> map = new HashMap<String,Object>();
                                map.put("id",catId);
                                map.put("name",catName);
                                clist.add(map);
                            }
                        }
                    }
                }
            }
        }
        term.put("blist", blist);
        term.put("llist", llist);
        term.put("mlist", mlist);
        term.put("clist", clist);
        dataMap.put("count", count);
        dataMap.put("goods", goods);
        dataMap.put("term", term);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);*/
        return result;
    }

    public JsonResult queryGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize) {//,String brandids,String listids,String catids
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Set<Long> cids = new HashSet<>();//分类id
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        List<Map<String, Object>> blist = new ArrayList<>();//品牌
        List<Map<String, Object>> llist = new ArrayList<>();//系列
        List<Map<String, Object>> mlist = new ArrayList<>();//型号
        List<Map<String, Object>> clist = new ArrayList<>();//分类
        int count = 0;
        List<Map<String, Object>> goods = new ArrayList<>();
        Map<String, Object> term = new HashMap<>();
        //1、根据查询条件查询代理商关联的品牌、系列、类型、分类用于页面展示（页面展示的都是有商品的数据）
        List<AgentBlmcVO> blmcList = oaAgentMasMapper.findNewBLMC(agentId, factoryId, bKey, lKey, mKey, cKey);

        List<Long> factoryIds = new ArrayList<Long>();
        if (factoryId != null) {
            factoryIds.add(factoryId);
        }
        //2、根据查询条件查询代理商参与的活动品牌、系列、类型、分类用于页面展示（页面展示的都是有商品的数据）
        List<AgentBlmcVO> actBlmcList = oaAgentMasMapper.findActivityBLMC(agentId, factoryIds, bKey, lKey, mKey, cKey);

        if (CollectionUtils.isEmpty(blmcList) && CollectionUtils.isEmpty(actBlmcList)) {
            term.put("blist", blist);
            term.put("llist", llist);
            term.put("mlist", mlist);
            term.put("clist", clist);
            dataMap.put("count", count);
            dataMap.put("goods", goods);
            dataMap.put("term", term);
            result.setCode(2);
            result.setSuccess(false);
            return result;
        }
        for (AgentBlmcVO agentBlmcVO : blmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cids.add(agentBlmcVO.getCatId());
            }
        }

        //add by ykf添加代理商参与的活动商品的品牌、系列、型号、分类商品查询参数
        Set<Long> cActIds = new HashSet<>();//分类id
        Map<Long, String> bActMap = new HashMap<>();//品牌
        Map<Long, String> lActMap = new HashMap<>();//系列
        Map<Long, String> mActMap = new HashMap<>();//型号
        for (AgentBlmcVO agentBlmcVO : actBlmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bActMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lActMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mActMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cActIds.add(agentBlmcVO.getCatId());
            }
        }
        if ("c".equals(x)) {
            cids.add(-1L);
        }
        boolean catlf = true;//用来判断关联分类的时候采用左连接还是内连接
        if (StringUtils.isNotBlank(cKey)) {
            catlf = false;
        }
        String queryFlag = "ALL";//判断查询条件
        if (CollectionUtils.isEmpty(blmcList) && CollectionUtils.isNotEmpty(actBlmcList)) {
            queryFlag = "ACT";
        }
        if (CollectionUtils.isEmpty(actBlmcList) && CollectionUtils.isNotEmpty(blmcList)) {
            queryFlag = "PT";
        }
        //分类单独查询
        Set<Long> catIds = new HashSet<>();
        List<CrmPrdCat> crmPrdCatList = crmPrdCatService.findChildAndOwnByCatName(agentId, factoryId, cKey);
        if (CollectionUtils.isNotEmpty(crmPrdCatList)) {
            for (CrmPrdCat crmPrdCat : crmPrdCatList) {
                catIds.add(crmPrdCat.getCatId().longValue());
            }
        }

        //根据名称查询出来的品牌、系列、型号、分类ID分页查询商品数据
        count = goodsDao.findAllListCount(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), catIds, key, catlf,
                bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag);
        if (count > 0) {
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            goods = goodsDao.findAllListByPage(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), catIds, key, catlf,
                    bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag, pageBounds);
            for (Map<String, Object> newGoods : goods) {
                String full_name = StrTools.strDistinct(newGoods.get("full_name").toString());
                newGoods.put("full_name", full_name);
            }
        }
        //封装前台显示的品牌、系列、类型、分类（重名的只显示一个）
        //代理商代理的
        if (CollectionUtils.isNotEmpty(blmcList)) {
            for (AgentBlmcVO agentBlmcVO : blmcList) {
                Long bandId = agentBlmcVO.getBandId();
                String bandName = agentBlmcVO.getBandName();

                Long listId = agentBlmcVO.getListId();
                String listName = agentBlmcVO.getListName();

                Long modelId = agentBlmcVO.getModelId();
                String modelName = agentBlmcVO.getModelName();

                Long catId = agentBlmcVO.getCatId();
                String catName = agentBlmcVO.getCatName();
                //添加品牌
                if (bandId != null && StringUtils.isNotBlank(bandName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : blist) {
                        if (bandName.equals(map.get("band_name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("band_id", bandId);
                        map.put("band_name", bandName);
                        blist.add(map);
                    }
                }
                //添加系列
                if (listId != null && StringUtils.isNotBlank(listName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : llist) {
                        if (listName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", listId);
                        map.put("name", listName);
                        llist.add(map);
                    }
                }
                //添加类型
                if (modelId != null && StringUtils.isNotBlank(modelName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : mlist) {
                        if (modelName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", modelId);
                        map.put("name", modelName);
                        mlist.add(map);
                    }
                }
                //添加分类
                if (catId != null && StringUtils.isNotBlank(catName)) {
                    cids.add(catId);
                }
            }
        }

        //活动商品的
        if (CollectionUtils.isNotEmpty(actBlmcList)) {
            for (AgentBlmcVO agentBlmcVO : actBlmcList) {
                Long bandId = agentBlmcVO.getBandId();
                String bandName = agentBlmcVO.getBandName();

                Long listId = agentBlmcVO.getListId();
                String listName = agentBlmcVO.getListName();

                Long modelId = agentBlmcVO.getModelId();
                String modelName = agentBlmcVO.getModelName();

                Long catId = agentBlmcVO.getCatId();
                String catName = agentBlmcVO.getCatName();
                //添加品牌
                if (bandId != null && StringUtils.isNotBlank(bandName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : blist) {
                        if (bandName.equals(map.get("band_name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("band_id", bandId);
                        map.put("band_name", bandName);
                        blist.add(map);
                    }
                }
                //添加系列
                if (listId != null && StringUtils.isNotBlank(listName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : llist) {
                        if (listName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", listId);
                        map.put("name", listName);
                        llist.add(map);
                    }
                }
                //添加类型
                if (modelId != null && StringUtils.isNotBlank(modelName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : mlist) {
                        if (modelName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", modelId);
                        map.put("name", modelName);
                        mlist.add(map);
                    }
                }
                //添加分类
                if (catId != null && StringUtils.isNotBlank(catName)) {
                    cids.add(catId);
                }
            }
        }

        //二级分类显示分类名称还是分类备注（当选择的工厂的时候显示的是分类备注）
        boolean showCatNameFlag = true;
        if (factoryId != null) {
            showCatNameFlag = false;
        }

        if (CollectionUtils.isNotEmpty(cids)) {
            //获取分类
            List<CrmPrdCat> cats = crmPrdCatService.findListByIds(cids);

            if (CollectionUtils.isNotEmpty(cats)) {
                for (CrmPrdCat cat : cats) {
                    if (cat.getPcatId() == null || cat.getIsFac() == null) {
                        continue;
                    }
                    CrmPrdCat crmPrdCat = cat;
                    if (0 != cat.getPcatId()) {
                        crmPrdCat = getParentCat(cat.getPcatId());
                    }
                    if (1 == crmPrdCat.getIsFac() || (factoryId != null && 0 == crmPrdCat.getIsFac())) {
                        Integer catId = crmPrdCat.getCatId();
                        String catName = null;
                        if (showCatNameFlag) {
                            catName = crmPrdCat.getCatName();
                        } else {
                            catName = crmPrdCat.getCatDesc();
                        }
                        if (catId != null && StringUtils.isNotBlank(catName)) {
                            boolean addFlag = true;
                            for (Map<String, Object> map : clist) {
                                if (catName.equals(map.get("name"))) {
                                    addFlag = false;
                                    break;
                                }
                            }
                            if (addFlag) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("id", catId);
                                map.put("name", catName);
                                clist.add(map);
                            }
                        }
                    }
                }
            }
        }
        term.put("blist", blist);
        term.put("llist", llist);
        term.put("mlist", mlist);
        term.put("clist", clist);
        dataMap.put("count", count);
        dataMap.put("goods", goods);
        dataMap.put("term", term);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);
        return result;
    }

    public JsonResult queryAgentGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> term = new HashMap<>();
        term = agentBandListService.queryBandListOrModelList(factoryId, agentId, bKey, lKey, mKey, cKey, key, x);

        //查询商品的总数
        int count = 0;

        //查询商品的记录         
        List<Map<String, Object>> goods = new ArrayList<>();

        dataMap.put("term", term);
        dataMap.put("count", count);
        dataMap.put("goods", goods);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);
        return result;
    }

    @Override
    public JsonResult queryGoodsByPageNew(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Set<Long> cids = new HashSet<>();//分类id
        List<Map<String, Object>> blist = new ArrayList<>();//品牌
        List<Map<String, Object>> llist = new ArrayList<>();//系列
        List<Map<String, Object>> mlist = new ArrayList<>();//型号
        List<Map<String, Object>> clist = new ArrayList<>();//分类

        Set<Long> bSet = new HashSet<>();//品牌
        Set<Long> lSet = new HashSet<>();//序列
        Set<Long> mSet = new HashSet<>();//型号
        Set<Long> cSet = new HashSet<>();//分类

        int count = 0;
        List<Map<String, Object>> goods = new ArrayList<>();
        Map<String, Object> term = new HashMap<>();

        List<Integer> facIds = new ArrayList<Integer>();
        if (factoryId != null) {
            facIds.add(factoryId.intValue());
        }
        //1、根据代理商查询该代理商下有商品的品牌（全部）
        List<Agentband> agentbandList = agentbandMapper.selectByAgentFacIds(agentId.intValue(), facIds);

        List<Integer> listIds = new ArrayList<>();
        //根据前端传的品牌名称过滤
        boolean filterBandFlag = false;
        if (StringUtils.isNotBlank(bKey)) {
            filterBandFlag = true;
        }
        if (CollectionUtils.isNotEmpty(agentbandList)) {
            for (int i = 0; i < agentbandList.size(); i++) {
                Agentband agentband = agentbandList.get(i);
                String name = agentband.getBandName();
                if (StringUtils.isNotBlank(name)) {
                    if (filterBandFlag) {
                        if (name.contains(bKey)) {
                            bSet.addAll(this.splitString(agentband.getBandIds()));
                            //当只选中品牌的时候，应该根据品牌名称去查询分类
                            /*CrmPrdCat crmPrdCat = crmPrdCatMapper.brandQueryCat(agentId, name);
                            bSet.addAll(this.splitString(agentband.getBandIds()));
                            cKey = crmPrdCat.getCatName();*/
                            continue;
                        } else {
                            agentbandList.remove(i);
                            i--;
                        }
                    } else {
                        bSet.addAll(this.splitString(agentband.getBandIds()));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(agentbandList)) {
            for (Agentband agentband : agentbandList) {
                String str = agentband.getListIds();
                if (StringUtils.isNotBlank(str)) {
                    String[] list = str.split(",");
                    if (list.length > 0) {
                        for (String s : list) {
                            if (null != s && "" != s && !s.isEmpty() && s.length() > 0) {
                                listIds.add(Integer.parseInt(s));
                            }
                        }
                    }
                }
            }
        }
        //2、根据代理商查询该代理商下有商品的序列（全部）
        List<CrmPrdList> crmPrdListList = crmPrdListMapper.selectByListIds(listIds.stream().distinct().collect(Collectors.toList()), facIds, agentId);
        //根据前端传的序列名称过滤
        List<Integer> crmPrdListIds = new ArrayList<>();
        boolean filterListFlag = false;
        if (StringUtils.isNotBlank(lKey)) {
            filterListFlag = true;
        }
        if (CollectionUtils.isNotEmpty(crmPrdListList)) {
            for (int i = 0; i < crmPrdListList.size(); i++) {
                CrmPrdList crmPrdList = crmPrdListList.get(i);
                String name = crmPrdList.getListName();
                if (StringUtils.isNotBlank(name)) {
                    if (filterListFlag) {
                        if (name.contains(lKey)) {
                            lSet.addAll(this.splitString(crmPrdList.getListIds()));
                            continue;
                        } else {
                            crmPrdListList.remove(i);
                            i--;
                        }
                    } else {
                        lSet.addAll(this.splitString(crmPrdList.getListIds()));
                        //当选中系列的时候，应该通过系列去查找品牌，型号及分类
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(crmPrdListList)) {
            for (CrmPrdList crmPrdList : crmPrdListList) {
                if (crmPrdList.getListId() != null) {
                    crmPrdListIds.add(crmPrdList.getListId());
                }
            }
        }
        //3、根据代理商查询该代理商下有商品的所有型号
        List<CrmPrdModel> crmPrdModelList = crmPrdModelMapper.findExistsGoodsByAgentAndListId(crmPrdListIds.stream().distinct().collect(Collectors.toList()), facIds, agentId);
        //根据前台传的类型名称过滤
        if (CollectionUtils.isNotEmpty(crmPrdModelList)) {
            //根据前端传的序列名称过滤
            boolean filterModelFlag = false;
            if (StringUtils.isNotBlank(mKey)) {
                filterModelFlag = true;
            }
            for (int i = 0; i < crmPrdModelList.size(); i++) {
                CrmPrdModel crmPrdModel = crmPrdModelList.get(i);
                String name = crmPrdModel.getModelName();
                if (StringUtils.isNotBlank(name)) {
                    if (filterModelFlag) {
                        if (name.contains(mKey)) {
                            mSet.addAll(this.splitString(crmPrdModel.getModelIds()));
                            continue;
                        } else {
                            crmPrdModelList.remove(i);
                            i--;
                        }
                    } else {
                        mSet.addAll(this.splitString(crmPrdModel.getModelIds()));
                        //当选择型号的时候，应该通过型号去查询品牌及型号和分类
                    }
                }
            }
        }
        //4、根据代理商查询该代理商下有商品的所有分类
        List<Integer> catIdss = crmPrdCatMapper.queryCatIdsList(agentId);
        List<CrmPrdCat> crmPrdCatList = null;
        if (CollectionUtils.isNotEmpty(catIdss)) {
            crmPrdCatList = crmPrdCatMapper.selectByIds(catIdss);
            //根据前台传的分类名称过滤
            if (CollectionUtils.isNotEmpty(crmPrdCatList)) {
                boolean filterCatFlag = false;
                if (StringUtils.isNotBlank(cKey)) {
                    filterCatFlag = true;
                }
                for (int i = 0; i < crmPrdCatList.size(); i++) {
                    CrmPrdCat crmPrdCat = crmPrdCatList.get(i);
                    Integer id = crmPrdCat.getCatId();
                    String name = crmPrdCat.getCatName();
                    if (id != null && StringUtils.isNotBlank(name)) {
                        if (filterCatFlag) {
                            if (name.contains(cKey)) {
                                cSet.add(id.longValue());
                                continue;
                            } else {
                                crmPrdCatList.remove(i);
                                i--;
                            }
                        } else {
                            cSet.add(id.longValue());
                            //当没有选中分类的时候，应该通过品牌或者系列及型号去查询

                        }
                    }
                }
            }
        }
        List<Long> factoryIds = new ArrayList<Long>();
        if (factoryId != null) {
            factoryIds.add(factoryId);
        }
        //5、根据查询条件查询代理商参与的活动品牌、系列、类型、分类用于页面展示（页面展示的都是有商品的数据）
        List<AgentBlmcVO> actBlmcList = oaAgentMasMapper.findActivityBLMC(agentId, factoryIds, bKey, lKey, mKey, cKey);
        //是否查询普通商品
        boolean queryPTFlg = CollectionUtils.isNotEmpty(agentbandList) || CollectionUtils.isNotEmpty(crmPrdListList)
                || CollectionUtils.isNotEmpty(crmPrdModelList) || CollectionUtils.isNotEmpty(crmPrdCatList);

        if (!queryPTFlg && CollectionUtils.isEmpty(actBlmcList)) {
            term.put("blist", blist);
            term.put("llist", llist);
            term.put("mlist", mlist);
            term.put("clist", clist);
            dataMap.put("count", count);
            dataMap.put("goods", goods);
            dataMap.put("term", term);
            result.setCode(2);
            result.setSuccess(false);
            return result;
        }
        //添加代理商参与的活动商品的品牌、系列、型号、分类商品查询参数
        Set<Long> cActIds = new HashSet<>();//分类id
        Map<Long, String> bActMap = new HashMap<>();//品牌
        Map<Long, String> lActMap = new HashMap<>();//系列
        Map<Long, String> mActMap = new HashMap<>();//型号
        for (AgentBlmcVO agentBlmcVO : actBlmcList) {
            if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                bActMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
            }
            if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                lActMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
            }
            if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                mActMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
            }
            if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                cActIds.add(agentBlmcVO.getCatId());
            }
        }

        if ("c".equals(x)) {
            cids.add(-1L);
        }
        boolean catlf = true;//用来判断关联分类的时候采用左连接还是内连接
        if (StringUtils.isNotBlank(cKey)) {
            catlf = false;
        }
        String queryFlag = "ALL";//判断查询条件
        if (!queryPTFlg && CollectionUtils.isNotEmpty(actBlmcList)) {
            queryFlag = "ACT";
        }
        if (CollectionUtils.isEmpty(actBlmcList) && queryPTFlg) {
            queryFlag = "PT";
        }

        //根据名称查询出来的品牌、系列、型号、分类ID分页查询商品数据
        count = goodsDao.findAllListCount(agentId, bSet, lSet, mSet, cSet, key, catlf,
                bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag);
        if (count > 0) {
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            goods = goodsDao.findAllListByPage(agentId, bSet, lSet, mSet, cSet, key, catlf,
                    bActMap.keySet(), lActMap.keySet(), mActMap.keySet(), cActIds, queryFlag, pageBounds);
            for (Map<String, Object> newGoods : goods) {
                String full_name = StrTools.strDistinct(newGoods.get("full_name").toString());
                newGoods.put("full_name", full_name);
            }
        }
        //封装前台显示的品牌、系列、类型、分类（重名的只显示一个）
        //代理商代理的品牌
        if (CollectionUtils.isNotEmpty(agentbandList)) {
            for (Agentband data : agentbandList) {
                String bandId = data.getBandIds();
                String bandName = data.getBandName();
                //添加品牌
                if (StringUtils.isNotBlank(bandName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : blist) {
                        if (bandName.equals(map.get("band_name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("band_id", bandId);
                        map.put("band_name", bandName);
                        blist.add(map);
                    }
                }
            }
        }
        //代理商代理的序列
        if (CollectionUtils.isNotEmpty(crmPrdListList)) {
            for (CrmPrdList data : crmPrdListList) {
                Integer listId = data.getListId();
                String listName = data.getListName();
                if (listId != null && StringUtils.isNotBlank(listName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : llist) {
                        if (listName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", listId);
                        map.put("name", listName);
                        llist.add(map);
                    }
                }
            }
        }
        //代理商代理的型号
        if (CollectionUtils.isNotEmpty(crmPrdModelList)) {
            for (CrmPrdModel data : crmPrdModelList) {
                Integer modelId = data.getModelId();
                String modelName = data.getModelName();
                //添加类型
                if (modelId != null && StringUtils.isNotBlank(modelName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : mlist) {
                        if (modelName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", modelId);
                        map.put("name", modelName);
                        mlist.add(map);
                    }
                }
            }
        }

        //活动商品的
        if (CollectionUtils.isNotEmpty(actBlmcList)) {
            for (AgentBlmcVO agentBlmcVO : actBlmcList) {
                Long bandId = agentBlmcVO.getBandId();
                String bandName = agentBlmcVO.getBandName();

                Long listId = agentBlmcVO.getListId();
                String listName = agentBlmcVO.getListName();

                Long modelId = agentBlmcVO.getModelId();
                String modelName = agentBlmcVO.getModelName();

                Long catId = agentBlmcVO.getCatId();
                String catName = agentBlmcVO.getCatName();
                //添加品牌
                if (bandId != null && StringUtils.isNotBlank(bandName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : blist) {
                        if (bandName.equals(map.get("band_name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("band_id", bandId);
                        map.put("band_name", bandName);
                        blist.add(map);
                    }
                }
                //添加系列
                if (listId != null && StringUtils.isNotBlank(listName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : llist) {
                        if (listName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", listId);
                        map.put("name", listName);
                        llist.add(map);
                    }
                }
                //添加类型
                if (modelId != null && StringUtils.isNotBlank(modelName)) {
                    boolean addFlag = true;
                    for (Map<String, Object> map : mlist) {
                        if (modelName.equals(map.get("name"))) {
                            addFlag = false;
                            break;
                        }
                    }
                    if (addFlag) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", modelId);
                        map.put("name", modelName);
                        mlist.add(map);
                    }
                }
            }
        }

        cids.addAll(cSet);//添加分类

        //二级分类显示分类名称还是分类备注（当选择的工厂的时候显示的是分类备注）
        boolean showCatNameFlag = true;
        if (factoryId != null) {
            showCatNameFlag = false;
        }

        if (CollectionUtils.isNotEmpty(cids)) {
            //获取分类
            List<CrmPrdCat> cats = crmPrdCatService.findListByIds(cids);

            if (CollectionUtils.isNotEmpty(cats)) {
                for (CrmPrdCat cat : cats) {
                    if (cat.getPcatId() == null || cat.getIsFac() == null) {
                        continue;
                    }
                    CrmPrdCat crmPrdCat = cat;
                    if (0 != cat.getPcatId()) {
                        crmPrdCat = getParentCat(cat.getPcatId());
                    }
                    if (1 == crmPrdCat.getIsFac() || (factoryId != null && 0 == crmPrdCat.getIsFac())) {
                        Integer catId = crmPrdCat.getCatId();
                        String catName = null;
                        if (showCatNameFlag) {
                            catName = crmPrdCat.getCatName();
                        } else {
                            catName = crmPrdCat.getCatDesc();
                        }
                        if (catId != null && StringUtils.isNotBlank(catName)) {
                            boolean addFlag = true;
                            for (Map<String, Object> map : clist) {
                                if (catName.equals(map.get("name"))) {
                                    addFlag = false;
                                    break;
                                }
                            }
                            if (addFlag) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("id", catId);
                                map.put("name", catName);
                                clist.add(map);
                            }
                        }
                    }
                }
            }
        }
        term.put("blist", blist);
        term.put("llist", llist);
        term.put("mlist", mlist);
        term.put("clist", clist);
        dataMap.put("count", count);
        dataMap.put("goods", goods);
        dataMap.put("term", term);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);
        return result;
    }

    private Set<Long> splitString(String ids) {
        Set<Long> idSet = new HashSet<>();
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                idSet.add(Long.valueOf(id));
            }
        }
        return idSet;
    }

    /**
     * 旧的，工厂端品牌查询
     *
     * @param factoryId
     * @param agentId
     * @return
     */
    public List<Map<String, Object>> oldShowGoodsByBand(Long factoryId, Long agentId) {
        Set<Long> ids = new HashSet<>();
        Set<Long> cids = new HashSet<>();
        Map<Long, AgentBlmcVO> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        Map<Long, String> cMap = new HashMap<>();//分类
        List<Map<String, Object>> list = new ArrayList<>();//品牌
        //查出来代理商关联的品牌、系列、型号、分类
        //List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factoryId, null, null, null, null);
        List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factoryId, null, null, null, null);
        //List<Map<String, Object>> brands = oaFactoryInfoService.newFindAllBrandsByFactoryIdAndAgentId(factoryId, member.getAgentId());

        if (CollectionUtils.isNotEmpty(blmcList)) {
            for (AgentBlmcVO agentBlmcVO : blmcList) {
                if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                    bMap.put(agentBlmcVO.getBandId(), agentBlmcVO);
                }
                if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                    lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
                }
                if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                    mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
                }
                if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                    cids.add(agentBlmcVO.getCatId());
                    if (factoryId == null) {
                        //搜索条件只展示平台的1级分类
                        if (agentBlmcVO.getPcatId() != null && agentBlmcVO.getIsFac() != null
                                && 0 == agentBlmcVO.getPcatId() && 1 == agentBlmcVO.getIsFac()) {
                            cMap.put(agentBlmcVO.getCatId(), agentBlmcVO.getCatName());
                        }
                    } else {
                        //搜索条件只展示平台和工厂的1级分类
                        if (agentBlmcVO.getPcatId() != null && 0 == agentBlmcVO.getPcatId()) {
                            cMap.put(agentBlmcVO.getCatId(), agentBlmcVO.getCatName());
                        }
                    }
                }
            }
        }
        for (Long id : bMap.keySet()) {
            ids.clear();
            ids.add(id);
            List<Map<String, Object>> goods = goodsDao.findList(agentId, ids, lMap.keySet(), mMap.keySet(), cids, null, true, 0, 8);
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", bMap.get(id).getBandName());
            map.put("pic_path", bMap.get(id).getBandPicPath());
            map.put("shopGoods", goods);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> showGoodsByBand(Long factoryId, Long agentId) {
        Set<Long> ids = new HashSet<>();
        Set<Long> cids = new HashSet<>();
        Map<Long, AgentBlmcVO> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        Map<Long, String> cMap = new HashMap<>();//分类
        List<Map<String, Object>> list = new ArrayList<>();//品牌
        //查出来代理商关联的品牌、系列、型号、分类
        List<Map<String, Object>> brands = oaFactoryInfoService.newFindAllBrandsByFactoryIdAndAgentId(factoryId, agentId);
        brands = this.distinctBand(brands);
        if (CollectionUtils.isNotEmpty(brands)) {
            for (Map<String, Object> bandMap : brands) {
                ids.clear();
                Long bandId = Long.parseLong(bandMap.get("band_id").toString());
                ids.add(bandId);
                PageBounds pageBounds = new PageBounds(0, 8);
                //List<Map<String, Object>> goods = goodsDao.findList(agentId, ids, lMap.keySet(), mMap.keySet(), cids, null,true, 0, 8);
                List<Map<String, Object>> goods = goodsDao.facQueryGoodsBybrand(ids.stream().collect(Collectors.toList()), agentId, factoryId, pageBounds);
                for (Map<String, Object> map : goods) {
                    String fullName = map.get("fullName").toString();
                    map.put("fullName", StrTools.strDistinct(fullName));
                }
                Map<String, Object> map = new HashMap<>();
                map.put("id", bandId);
                map.put("name", bandMap.get("band_name"));
                map.put("pic_path", bandMap.get("pic_path"));
                map.put("shopGoods", goods);
                list.add(map);
            }
        }
        return this.subList(list,6);
    }


    /**
     * 老的方法
     */
    public List<Map<String, Object>> showGoodsByListOld(Long factoryId, Long agentId) {
        Set<Long> ids = new HashSet<>();
        Set<Long> cids = new HashSet<>();
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, AgentBlmcVO> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        Map<Long, String> cMap = new HashMap<>();//分类
        List<Map<String, Object>> list = new ArrayList<>();//品牌
        //查出来代理商关联的品牌、系列、型号、分类
        List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factoryId, null, null, null, null);
        if (CollectionUtils.isNotEmpty(blmcList)) {
            for (AgentBlmcVO agentBlmcVO : blmcList) {
                if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                    bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
                }
                if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                    lMap.put(agentBlmcVO.getListId(), agentBlmcVO);
                }
                if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                    mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
                }
                if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                    cids.add(agentBlmcVO.getCatId());
                    if (factoryId == null) {
                        //搜索条件只展示平台的1级分类
                        if (agentBlmcVO.getPcatId() != null && agentBlmcVO.getIsFac() != null
                                && 0 == agentBlmcVO.getPcatId() && 1 == agentBlmcVO.getIsFac()) {
                            cMap.put(agentBlmcVO.getCatId(), agentBlmcVO.getCatName());
                        }
                    } else {
                        //搜索条件只展示平台和工厂的1级分类
                        if (agentBlmcVO.getPcatId() != null && 0 == agentBlmcVO.getPcatId()) {
                            cMap.put(agentBlmcVO.getCatId(), agentBlmcVO.getCatName());
                        }
                    }
                }
            }
        }
        for (Long id : lMap.keySet()) {
            ids.clear();
            ids.add(id);
            PageBounds rowBounds = new PageBounds(0, 8);
            List<Map<String, Object>> goods = goodsDao.facQueryGoodsBylist(lMap.keySet().stream().collect(Collectors.toList()), agentId, factoryId, rowBounds);
            //goodsDao.findList(agentId, bMap.keySet(), ids, mMap.keySet(), cids, null, true,0, 8);
            for (Map<String, Object> map : goods) {
                String fullName = map.get("fullName").toString();
                map.put("fullName", StrTools.strDistinct(fullName));
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", lMap.get(id).getListName());
            map.put("pic_path", lMap.get(id).getListPicPath());
            map.put("shopGoods", goods);
            list.add(map);
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> showGoodsByList(Long factoryId, Long agentId) {
        Set<Long> ids = new HashSet<>();
        Map<Long, String> bMap = new HashMap<>();//品牌
        List<Map<String, Object>> list = new ArrayList<>();//品牌
        List<Map<String, Object>> brands = oaFactoryInfoService.newFindAllBrandsByFactoryIdAndAgentId(factoryId, agentId);
        List<Map<String, Object>> newBrands = new ArrayList<>();
        Set<Long> listIdsSet = new HashSet<>();
            for (int index = 0; index < brands.size(); index++) {
                HashMap listMap = (HashMap) brands.get(index);
                ids.clear();
                Long id = Long.parseLong(listMap.get("list_id").toString());
                ids.add(Long.parseLong(listMap.get("list_id").toString()));
                PageBounds rowBounds = new PageBounds(0, 8);
                List<Map<String, Object>> goods = goodsDao.facQueryGoodsBylist2(ids.stream().collect(Collectors.toList()), agentId, factoryId, rowBounds);
                for (Map<String, Object> map : goods) {
                    String fullName = map.get("fullName").toString();
                    map.put("fullName", StrTools.strDistinct(fullName));
                }
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", listMap.get("list_name").toString());
                map.put("pic_path", listMap.get("listpicpath").toString());
                map.put("shopGoods", goods);
                list.add(map);
            }
        return this.subList(list,6);
    }

    // @Override
    public List<Map<String, Object>> showGoodsByCat(Long factoryId, Long agentId) {
        Set<Long> ids = new HashSet<>();
        Set<Long> cids = new HashSet<>();
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        Map<Long, AgentBlmcVO> cMap = new HashMap<>();//分类
        List<Map<String, Object>> list = new ArrayList<>();//品牌
        //查出来代理商关联的品牌、系列、型号、分类
        List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factoryId, null, null, null, null);
        if (CollectionUtils.isNotEmpty(blmcList)) {
            for (AgentBlmcVO agentBlmcVO : blmcList) {
                if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                    bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
                }
                if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                    lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
                }
                if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                    mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
                }
                if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                    cids.add(agentBlmcVO.getCatId());
                    if (factoryId == null) {
                        //搜索条件只展示平台的1级分类
                        if (agentBlmcVO.getPcatId() != null && agentBlmcVO.getIsFac() != null
                                && 0 == agentBlmcVO.getPcatId() && 1 == agentBlmcVO.getIsFac()) {
                            cMap.put(agentBlmcVO.getCatId(), agentBlmcVO);
                        }
                    } else {
                        //搜索条件只展示平台和工厂的1级分类
                        if (agentBlmcVO.getPcatId() != null && 0 == agentBlmcVO.getPcatId()) {
                            cMap.put(agentBlmcVO.getCatId(), agentBlmcVO);
                        }
                    }
                }
            }
        }
        for (Long id : cMap.keySet()) {
            ids.clear();
            ids.add(id);
            ids = getChildCatIds(ids, ids);
            Map<String, Object> map = new HashMap<>();
            //查找6个代理商关联的二级分类
            List<Map<String, Object>> childCats = oaFactoryInfoService.findAllChildCatsByPactId(factoryId, id, lMap.keySet());
            if (childCats.size() > 6) {
                map.put("childCats", childCats.subList(0, 6));
            } else {
                map.put("childCats", childCats);
            }
            List<Map<String, Object>> goods = goodsDao.findList(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), ids, null, false, 0, 8);
            map.put("id", id);
            //map.put("name", cMap.get(id).getCatName());
            map.put("name", cMap.get(id).getCatDesc());
            map.put("pic_path", cMap.get(id).getCatPicPath());
            map.put("shopGoods", goods);
            list.add(map);
        }
        return list;
    }

    /**
     * 工厂端分类查询
     *
     * @param factoryId
     * @param agentId
     * @return
     */
    @Override
    public List<Map<String, Object>> newShowGoodsByCat(Long factoryId, Long agentId) {
        //List<CrmPrdCat> crmPrdCats = crmPrdCatMapper.selectFirstFacCatByAgentId(agentId, factoryId);
        //List<CrmPrdCat> crmPrdCats = crmPrdCatService.selectFirstFacCatByAgentId(agentId, factoryId);
        Map<String,Object> dataMap = crmPrdCatService.findLevelCatByCondition(agentId,factoryId);//查询出一级分类和二级分类
        List<CrmPrdCat> crmPrdCats = (List<CrmPrdCat>) dataMap.get("ONE");
        Map<Integer,List<CrmPrdCat>> twoDataMap = (Map<Integer,List<CrmPrdCat>>) dataMap.get("TWO");
        if(crmPrdCats.size() > 6){
            crmPrdCats = crmPrdCats.subList(0,6);
        }

        List<Map<String, Object>> list = new ArrayList<>();
        List<CrmPrdCat> childCats;
        if (CollectionUtils.isNotEmpty(crmPrdCats)) {
            for (CrmPrdCat crmPrdCat : crmPrdCats) {
                Map<String, Object> map = new HashMap<>();
                /*通过得到的父ID迭代往下找子分类id*/
                Set<Long> sonCatIds = crmPrdCatService.findChildAndOwnById(agentId, factoryId, crmPrdCat.getCatId().longValue());
                //查找6个代理商关联的二级分类
                //childCats = crmPrdCatService.upCatIdQueryConTent3(agentId, sonCatIds, factoryId);
                childCats = twoDataMap.get(crmPrdCat.getCatId());
                if(CollectionUtils.isEmpty(childCats)){
                    childCats = new ArrayList<>();
                }

                if (childCats.size() > 6) {
                    map.put("childCats", childCats.subList(0, 6));
                } else {
                    map.put("childCats", childCats);
                }
                PageBounds rowBounds = new PageBounds(0, 8);
                List<GoodsEntity> goodsEntities = goodsDao.newFacGetGoodsByCatIdWithAgent2(sonCatIds, agentId.intValue(), factoryId,rowBounds);
                for (GoodsEntity goodsEntity : goodsEntities) {
                    String full_name = StrTools.strDistinct(goodsEntity.getFullName());
                    goodsEntity.setFullName(full_name);
                    if (goodsEntity.getImgs() == null || "".equals(goodsEntity.getImgs())){
                        goodsEntity.setImgs("/shopping/img/index/sl3.png");
                    }
                    if (goodsEntity.getFirstImg() == null || "".equals(goodsEntity.getFirstImg())){
                        goodsEntity.setFirstImg("/shopping/img/index/sl3.png");
                    }
                }

                if (crmPrdCat.getPicPath() == null || "".equals(crmPrdCat.getPicPath())){
                    crmPrdCat.setPicPath("/shopping/img/index/sl3.png");
                }
                map.put("id", crmPrdCat.getCatId());
                map.put("name", crmPrdCat.getCatDesc());
                map.put("pic_path", crmPrdCat.getPicPath());
                map.put("shopGoods", goodsEntities);
                list.add(map);

            }
        }
        return list;
    }

    private Set<Long> getChildCatIds(Set<Long> ids, Set<Long> result) {
        if (CollectionUtils.isNotEmpty(ids)) {
            Set<Long> childIds = crmPrdCatService.findChildCatids(ids);
            if (CollectionUtils.isNotEmpty(childIds)) {
                result.addAll(childIds);
                result = getChildCatIds(childIds, result);
            }
        }
        return result;
    }

    private CrmPrdCat getParentCat(Integer catId) {
        CrmPrdCat crmPrdCat = crmPrdCatService.findOneById(catId);
        if (crmPrdCat != null && crmPrdCat.getPcatId() != 0) {
            crmPrdCat = getParentCat(crmPrdCat.getPcatId());
        }
        return crmPrdCat;
    }

    /**
     * 查询商品名称，系列名称，系列名称
     *
     * @param goodsId
     * @return
     */
    public Map<String, Object> goodsFull(Long goodsId, Long agentId) {
        Map<String, Object> maps = goodsDao.goodsFull(goodsId, agentId);
        return maps;
    }

    @Override
    public Map<String, Object> findFactoryId(Long shopId) {
        return goodsDao.findFactoryId(shopId);
    }

    @Override
    public JsonResult queryNameKey(Long agentId, Long factId, String key) {
        JsonResult result = new JsonResult();
        result.setCode(2);
        result.setSuccess(false);
        result.setMessage("查询失败");
        Set<Long> cids = new HashSet<>();//分类id
        Map<Long, String> bMap = new HashMap<>();//品牌
        Map<Long, String> lMap = new HashMap<>();//系列
        Map<Long, String> mMap = new HashMap<>();//型号
        List<String> goods = new ArrayList<>();
        try {
            //查出来代理商关联的品牌、系列、型号、分类
            List<AgentBlmcVO> blmcList = oaAgentMasMapper.findBLMC(agentId, factId, null, null, null, null);
            if (CollectionUtils.isNotEmpty(blmcList)) {
                for (AgentBlmcVO agentBlmcVO : blmcList) {
                    if (agentBlmcVO.getBandId() != null && StringUtils.isNotBlank(agentBlmcVO.getBandName())) {
                        bMap.put(agentBlmcVO.getBandId(), agentBlmcVO.getBandName());
                    }
                    if (agentBlmcVO.getListId() != null && StringUtils.isNotBlank(agentBlmcVO.getListName())) {
                        lMap.put(agentBlmcVO.getListId(), agentBlmcVO.getListName());
                    }
                    if (agentBlmcVO.getModelId() != null && StringUtils.isNotBlank(agentBlmcVO.getModelName())) {
                        mMap.put(agentBlmcVO.getModelId(), agentBlmcVO.getModelName());
                    }
                    if (agentBlmcVO.getCatId() != null && StringUtils.isNotBlank(agentBlmcVO.getCatName())) {
                        cids.add(agentBlmcVO.getCatId());
                    }
                }
            }
            if (MapUtils.isNotEmpty(bMap) && MapUtils.isNotEmpty(lMap) && MapUtils.isNotEmpty(mMap)) {
                goods = goodsDao.queryNameKey(agentId, bMap.keySet(), lMap.keySet(), mMap.keySet(), cids, key);
                result.setData(goods);
                result.setSuccess(true);
                result.setCode(1);
                result.setMessage("查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param catIds
     * @param agentId
     * @return
     */
    public List<GoodsEntity> newGetGoodsByCatIdWithAgent(@Param("catIds") List<Long> catIds, @Param("agentId") Integer agentId) {
        return goodsDao.newGetGoodsByCatIdWithAgent(catIds, agentId, null);
    }

    public JsonResult queryGoodsByPageByCondition(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize,HttpServletRequest request) {
        JsonResult result = new JsonResult();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> term = new HashMap<>();
        int count = 0;//商品数量
        List<Map<String, Object>> goods = new ArrayList<>();//商品列表
        Set<Long> brandSet = new HashSet<>();//品牌ID用于查询
        Set<Long> listSet = new HashSet<>();//序列ID用于查询
        Set<Long> modelSet = new HashSet<>();//型号ID用于查询
        Set<Long> catSet = new HashSet<>();//分类ID用于查询
        //1、根据前台转过来的品牌名称查询对应的品牌ID
        if (StringUtils.isNotBlank(bKey)) {
            List<Map<String, Object>> dataList = custbandMapper.findByName(agentId, factoryId, bKey);
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (Map<String, Object> data : dataList) {
                    if (data.get("id") != null) {
                        brandSet.add(Long.valueOf(data.get("id").toString()));
                    }
                }
            }
        }
        //2、根据前台传过来的序列名称查询对应的序列ID
        if (StringUtils.isNotBlank(lKey)) {
            List<Map<String, Object>> dataList = crmPrdListMapper.findByName(agentId, factoryId, lKey);
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (Map<String, Object> data : dataList) {
                    if (data.get("id") != null) {
                        listSet.add(Long.valueOf(data.get("id").toString()));
                    }
                }
            }
        }
        //3、根据前台传过来的型号名称查询对应的型号ID
        if (StringUtils.isNotBlank(mKey)) {
            List<Map<String, Object>> dataList = crmPrdModelMapper.findByName(agentId, factoryId, mKey);
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (Map<String, Object> data : dataList) {
                    if (data.get("id") != null) {
                        modelSet.add(Long.valueOf(data.get("id").toString()));
                    }
                }
            }
        }
        //4、根据前台传过来的分类名称查询对应的分类ID
        if (StringUtils.isNotBlank(cKey)) {
            List<CrmPrdCat> crmPrdCatList = crmPrdCatService.findChildAndOwnByCatName(agentId, factoryId, cKey);
            if (CollectionUtils.isNotEmpty(crmPrdCatList)) {
                for (CrmPrdCat crmPrdCat : crmPrdCatList) {
                    catSet.add(crmPrdCat.getCatId().longValue());
                }
            }
        }

        count = goodsDao.findGoodsTotalByCondition(agentId, factoryId, brandSet, listSet, modelSet, catSet, key);
        if (count > 0) {
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            goods = goodsDao.findGoodsListByCondition(agentId, factoryId, brandSet, listSet, modelSet, catSet, key, pageBounds);
            for (Map<String, Object> newGoods : goods) {
                String full_name = StrTools.strDistinct(newGoods.get("full_name").toString());
                newGoods.put("full_name", full_name);
                if (ObjectUtils.isNotEmpty(newGoods)){
                    if( newGoods.get("imgs") == null || "".equals(newGoods.get("imgs"))){
                        newGoods.put("imgs","/shopping/img/index/sl3.png");
                    }
                    if (newGoods.get("first_img") == null || "".equals(newGoods.get("first_img"))){
                        newGoods.put("first_img","/shopping/img/index/sl3.png");
                    }
                }
            }
        }


        dataMap.put("count", count);
        dataMap.put("goods", goods);
        dataMap.put("term", term);
        result.setCode(1);
        result.setSuccess(true);
        result.setData(dataMap);
        return result;
    }

    @Override
    public int updateIsMarketByActIds(List<Integer> actIds) {
        return goodsDao.updateIsMarketByActIds(actIds);
    }
    /**
     * 品牌去重
     * @param list
     * @return
     */
    private List<Map<String,Object>> distinctBand(List<Map<String,Object>> list){
        for (int i = 0; i < list.size() - 1; i++){
            String bandName = list.get(i).get("band_name").toString();
            String listId = list.get(i).get("list_id").toString();
            String listIds = list.get(i).get("listIds").toString();
            StringBuilder sb = new StringBuilder(listIds);
            for  ( int  j  =  list.size() - 1 ; j  >  i; j -- )  {
                if  (list.get(j).get("band_name").equals(bandName) && !list.get(j).get("list_id").equals(listId))  {
                    sb.append(",").append(list.get(j).get("list_id"));
                    list.remove(j);
                }
            }
            list.get(i).put("listIds",sb.toString());
        }
        return list;
    }

    /**
     * 集合切割
     * @param dataList
     * @param size
     * @return
     */
    private List<Map<String, Object>> subList(List<Map<String, Object>> dataList,int size){
        if(dataList.size() < size){
            return dataList;
        } else{
            return dataList.subList(0,size);
        }
    }
    
    /**
     * 查询最新库存数
     */
    public Integer queryGoodsNewestStock(GoodsEntity goodsEntity){
        if (ObjectUtils.isEmpty(goodsEntity)){
            return 0;
        }
        if (goodsEntity.getProdType() == 0){
            //上架库存:0-空、1-实际库存 2-自定义
            Integer stockType = goodsEntity.getStockType();
            if (stockType == 1){
                PrdCustPrice prdCustPrice=prdCustPriceMapper.selectByPrimaryKey(goodsEntity.getId().intValue());
                if(null!=prdCustPrice && prdCustPrice.getTotalQty()!=null){
                    //同步库存
                    goodsEntity.setStock(prdCustPrice.getTotalQty());
                    return prdCustPrice.getTotalQty();
                }
                else return 0;
            }
        }
        return  goodsEntity.getStock().intValue();
    }
    
    
    /**
     * 
     * @param goodsEntity 商品对象
     * @param stockNum 增减的库存数
     * @param plus 0 加 1 减
     * @return
     */
    public GoodsEntity updateGoodsRealStock(Integer stockNum,Integer plus,GoodsEntity goodsEntity){
        if (ObjectUtils.isEmpty(goodsEntity)){
            return goodsEntity;
        }
        //上架库存:0-空、1-实际库存 2-自定义
        Integer stockType = goodsEntity.getStockType();
        //当前库存数
        int totalQty=0;
        if (stockType == 1){
            PrdCustPrice prdCustPrice=prdCustPriceMapper.selectByPrimaryKey(goodsEntity.getId().intValue());
            if(null!=prdCustPrice && prdCustPrice.getTotalQty()!=null)
                totalQty=prdCustPrice.getTotalQty();
            if(plus.equals(0)){
                prdCustPrice.setTotalQty(totalQty+stockNum);
                prdCustPriceMapper.updateByPrimaryKey(prdCustPrice);
            }else if(plus.equals(1)){
                prdCustPrice.setTotalQty(totalQty-stockNum);
                prdCustPriceMapper.updateByPrimaryKey(prdCustPrice);
            }
            goodsEntity.setStock(prdCustPrice.getTotalQty());
        }else{
            totalQty = goodsEntity.getStock();
            if(plus.equals(0)){
                goodsEntity.setStock(totalQty+stockNum);
            }else if(plus.equals(1)){
                goodsEntity.setStock(totalQty-stockNum);
            }
        }
    	return goodsEntity;
    }
}

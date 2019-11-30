package com.zlead.service.impl;

import com.zlead.dao.*;
import com.zlead.entity.*;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.*;
import com.zlead.service.ProductBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


@Transactional
@Service
public class ProductBgServiceImpl implements ProductBgService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProdCatDao prodCatDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ProdImgDao prodImgDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ProdattrMapper prodattrMapper;

    @Autowired
    private ProdattrvalMapper prodattrvalMapper;

    @Autowired
    private GoodsattrMapper goodsattrMapper;

    @Autowired
    private GoodsattrvalMapper goodsattrvalMapper;

    @Autowired
    private GoodscatMapper goodscatMapper;

    @Transactional(readOnly = true)
    public PageList<ProductEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = productDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public ProductEntity findById(Long id) {
        return productDao.findById(id);
    }

    public void save(ProductEntity entity) {
        productDao.insert(entity);
    }

    public void update(ProductEntity entity) {
        productDao.update(entity);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    /**
     * 保存产品
     */
    public boolean sysProductSave(ProductEntity productEntity, SysUserEntity sysUserEntity, HttpServletRequest request) {
        boolean b = true;
        try {
            ShopEntity shopEntity = shopDao.findByMgrUserId(sysUserEntity.getId());
            if (shopEntity == null) {
                return false;
            }
            productSave(productEntity, sysUserEntity.getId(), shopEntity, request);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public void insert(ProductEntity productEntity) {
        productDao.insert(productEntity);
        if (CollectionUtils.isNotEmpty(productEntity.getCatEntities())) {
            for (ProdCatEntity catEntity : productEntity.getCatEntities()) {
                catEntity.setProdId(productEntity.getId());
                prodCatDao.insert(catEntity);
            }
        }
    }

    @Override
    public boolean createGoods(Long prodId) {
        boolean flag = false;
        try {
            //获取到产品
            ProductEntity productEntity = productDao.findById(prodId);
            if (productEntity != null) {
                //获取产品的属性
                List<Prodattr> prodattrs = prodattrMapper.selectByProdId(prodId);
                //获取产品属性的值
                List<Prodattrval> prodattrvals = prodattrvalMapper.selectByProdId(prodId);
                Map<Integer, List<String>> attrValMap = new HashMap<>();
                //遍历属性值
                for (Prodattrval prodattrval : prodattrvals) {
                    Integer attrId = prodattrval.getAttrId();
                    if (attrValMap.get(attrId) != null) {
                        attrValMap.get(attrId).add(prodattrval.getValueName());
                    } else {
                        List<String> vals = new ArrayList<>();
                        vals.add(prodattrval.getValueName());
                        attrValMap.put(attrId, vals);
                    }
                }
                //计算出需要生成多少件商品
                int total = 1;
                for (Integer attrId : attrValMap.keySet()) {
                    total = total * attrValMap.get(attrId).size();
                }
                List<Map<String, Object>> groupAttrValList = prodattrvalMapper.selectGroupAttrVal(prodId);
                Map<Integer, List<Map<String, Object>>> resultMap = new HashMap<>();
                int itemLoopNum = 1;
                int loopPerItem = 1;
                int now = 1;
                for (Map<String, Object> currentMap : groupAttrValList) {
                    Integer attrId = Integer.parseInt(currentMap.get("attr_id").toString());
                    String attrName = currentMap.get("attr_name").toString();
                    String valstr = currentMap.get("val").toString();
                    Integer count = Integer.parseInt(currentMap.get("count").toString());
                    if (StringUtils.isNotBlank(valstr)) {
                        now *= count;
                        List<String> list = Arrays.asList(valstr.split(","));
                        int index = 0;
                        int currentSize = list.size();
                        itemLoopNum = total / now;
                        loopPerItem = total / (itemLoopNum * currentSize);
                        int myIndex = 0;
                        for (String s : list) {
                            for (int i = 0; i < loopPerItem; i++) {
                                if (myIndex == list.size()) {
                                    myIndex = 0;
                                }

                                for (int j = 0; j < itemLoopNum; j++) {
                                    if (resultMap.get(index) != null) {
                                        List<Map<String, Object>> vmaplist = resultMap.get(index);
                                        Map<String, Object> nmap = new HashMap<>();
                                        nmap.put("attrId", attrId);
                                        nmap.put("attrName", attrName);
                                        nmap.put("val", list.get(myIndex));
                                        vmaplist.add(nmap);
                                    } else {
                                        List<Map<String, Object>> vmaplist = new ArrayList<>();
                                        Map<String, Object> nmap = new HashMap<>();
                                        nmap.put("attrId", attrId);
                                        nmap.put("attrName", attrName);
                                        nmap.put("val", list.get(myIndex));
                                        vmaplist.add(nmap);
                                        resultMap.put(index, vmaplist);
                                    }
                                    index++;
                                }
                                myIndex++;
                            }
                        }
                    }
                }
                List<ProdCatEntity> prodcats = prodCatDao.selectAllByProdId(prodId);
                try {
                    for (Integer key : resultMap.keySet()) {
                        GoodsEntity goodsEntity = new GoodsEntity();
                        goodsEntity.setProdId(prodId);
                        goodsEntity.setProdType(0);
                        goodsEntity.setCatagoryId(0L);
                        goodsEntity.setBrandId(productEntity.getBandId());
                        goodsEntity.setListId(productEntity.getListId());
                        goodsEntity.setModelId(productEntity.getModelId());
                        goodsEntity.setFullName(productEntity.getProductName());
                        goodsEntity.setChannel(3);
                        goodsEntity.setSupplyPrice(productEntity.getSupplyPrice());
                        goodsEntity.setAgentPrice(productEntity.getAgentPrice());
                        goodsEntity.setMarketPrice(productEntity.getMarketPrice());
                        goodsEntity.setDiscount(productEntity.getDiscount());
                        goodsEntity.setIsMarket(productEntity.getIsMarket());
                        goodsEntity.setIsHome(productEntity.getIsHome());
                        goodsEntity.setShowPrice(productEntity.getMarketPrice());
                        goodsEntity.setPrice(productEntity.getMarketPrice());
                        goodsEntity.setShopId(productEntity.getShopId());
                        goodsEntity.setSupplierShopId(productEntity.getSupplierShopId());
                        goodsEntity.setVersion(1);
                        goodsEntity.setCreateDate(new Date());
                        goodsEntity.setUpdateDate(new Date());
                        goodsEntity.setProdAttrId(0L);
                        goodsDao.insert(goodsEntity);
                        Long goods_id = goodsEntity.getId();
                        List<Map<String, Object>> valMaplist = resultMap.get(key);
                        for (Map<String, Object> map : valMaplist) {
                            Integer attrId = Integer.parseInt(map.get("attrId").toString());
                            String attrName = map.get("attrName").toString();
                            String val = map.get("val").toString();
                            Goodsattrval goodsattrval = new Goodsattrval();
                            goodsattrval.setGoodsId(goods_id.intValue());
                            goodsattrval.setProdId(prodId.intValue());
                            goodsattrval.setAttrId(attrId);
                            goodsattrval.setAttrName(attrName);
                            goodsattrval.setAttrValue(val);
                            goodsattrval.setSort(valMaplist.indexOf(map) + 1);
                            goodsattrvalMapper.insert(goodsattrval);
                        }
                        for (ProdCatEntity prodCatEntity : prodcats) {
                            Goodscat goodscat = new Goodscat();
                            goodscat.setGoodsId(goods_id);
                            goodscat.setCatId(prodCatEntity.getCatId());
                            goodscat.setCatName(prodCatEntity.getCatName());
                            goodscat.setStatus(prodCatEntity.getStatus());
                            goodscat.setProdId(prodId);
                            goodscat.setSort(prodcats.indexOf(prodCatEntity) + 1);
                            goodscatMapper.insert(goodscat);
                        }
                    }
                    for (Prodattr prodattr : prodattrs) {
                        Goodsattr goodsattr = new Goodsattr();
                        goodsattr.setProdId(prodattr.getProdId());
                        goodsattr.setAttrId(prodattr.getAttrId());
                        goodsattr.setAttrName(prodattr.getAttrName());
                        goodsattr.setSort(prodattrs.indexOf(prodattr) + 1);
                        goodsattr.setStatus(1);
                        goodsattrMapper.insert(goodsattr);
                    }
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 保存产品
     */
    public boolean productSave(ProductEntity productEntity, Long userId, ShopEntity shopEntity, HttpServletRequest request) {
        boolean b = true;
        try {
            //拿到图片的信息
            String imgs = request.getParameter("imgs");
            // String firstImg = request.getParameter("firstImg");
            productEntity.setSupplierShopId(shopEntity.getId());
            productEntity.setUserId(userId);
            productEntity.setIsMarket(0);
            productEntity.setIsHome(0);
            productEntity.setIsSpec(0);
            productEntity.setIsAudit(0);
            productEntity.setCreateDate(new Date());
            productDao.insert(productEntity);
            //保存图片的信息
            String[] split = imgs.split(",");
            //获取第一张图片
            String firstImg = split[0];
            ProdImgEntity prodImgEntity = new ProdImgEntity();
            prodImgEntity.setProdId(productEntity.getId());
            prodImgEntity.setFirstImg(firstImg);
            prodImgEntity.setImgs(imgs);
            prodImgDao.insert(prodImgEntity);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 产品审核
     *
     * @return
     */
    public boolean productAudit(Long id, SysUserEntity userEntity) {
        boolean b = true;
        try {
            //查询产品的信息
            ProductEntity ProductEntity = productDao.findById(id);
            if (ProductEntity == null) {
                return false;
            }
            //查询该产品是否已经有商品了（有商品说明已经审核过）
            GoodsEntity goods = goodsDao.findByPordId(ProductEntity.getId());
            if (goods == null) {
                //查询该后台用户的店铺信息
                ShopEntity shopEntity = shopDao.findByMgrUserId(userEntity.getId());
                if (shopEntity == null) {
                    return false;
                }
                ProductEntity.setIsAudit(1);//0:未审核，1：已审核
                ProductEntity.setUpdateDate(new Date());
                productDao.update(ProductEntity);
                //查询图片的信息
                ProdImgEntity prodImgEntity = prodImgDao.findByProdId(ProductEntity.getId());
                //审核通过后在goods表中生成一条信息以便于上架使用
                GoodsEntity goodsEntity = new GoodsEntity();
                goodsEntity.setProdId(ProductEntity.getId());
                goodsEntity.setCatagoryId(ProductEntity.getCatagoryId());
                goodsEntity.setChannel(0);//发布的产品都是自营的
                goodsEntity.setFullName(ProductEntity.getProductName());
                goodsEntity.setSupplyPrice(ProductEntity.getSupplyPrice());
                goodsEntity.setAgentPrice(ProductEntity.getAgentPrice());
                goodsEntity.setMarketPrice(ProductEntity.getMarketPrice());
                goodsEntity.setPrice(new BigDecimal(0));
                goodsEntity.setPoint(0L);
                goodsEntity.setPointPrice(new BigDecimal(0));
                goodsEntity.setStock(0);
                goodsEntity.setSalesNum(0);
                goodsEntity.setClickNum(0);
                goodsEntity.setIsAudit(1);
                goodsEntity.setIsMarket(0);
                goodsEntity.setFirstImg(prodImgEntity.getFirstImg());
                goodsEntity.setImgs(prodImgEntity.getImgs());
                goodsEntity.setAuditTime(new Date());
                goodsEntity.setAuditUserId(userEntity.getId());
                goodsEntity.setIsHome(0);
                goodsEntity.setSupplierShopId(ProductEntity.getSupplierShopId());
                goodsEntity.setCreateDate(new Date());
                goodsEntity.setShopId(shopEntity.getId());
                goodsEntity.setProdType(0);//前期添加的都是普通商品
                goodsEntity.setVersion(0);
                goodsEntity.setFreezeStock(0);
                goodsEntity.setIntro(ProductEntity.getIntro());
                goodsDao.insert(goodsEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 删除产品
     *
     * @return
     */
    public boolean productDelete(Long id) {
        boolean b = true;
        try {
            //删除产品
            productDao.delete(id);
            //删除产品图片
            ProdImgEntity imgEntity = prodImgDao.findByProdId(id);
            if (imgEntity != null) {
                prodImgDao.delete(imgEntity.getId());
            }
            //查询商品信息
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 修改产品
     *
     * @return
     */
    public boolean productUpdate(ProductEntity productEntity, HttpServletRequest request) {
        boolean b = true;
        try {
            //查询原来的产品信息
            ProductEntity prod = productDao.findById(productEntity.getId());
            //查询图片的信息
            ProdImgEntity imgEntity = prodImgDao.findByProdId(productEntity.getId());
            //查询goods的信息
            GoodsEntity goodsEntity = goodsDao.findByPordId(productEntity.getId());
            //拿到图片的信息
            String imgs = request.getParameter("imgs");
            //保存图片的信息
            String[] split = imgs.split(",");
            //获取第一张图片
            String firstImg = split[0];
            //修改产品的信息
            prod.setProductName(productEntity.getProductName());
            prod.setCatagoryId(productEntity.getCatagoryId());
            prod.setAgentPrice(productEntity.getAgentPrice());
            prod.setSupplyPrice(productEntity.getSupplyPrice());
            prod.setMarketPrice(productEntity.getMarketPrice());
            prod.setUpdateDate(new Date());
            productDao.update(prod);
            //修改图片信息
            imgEntity.setImgs(imgs);
            imgEntity.setFirstImg(firstImg);
            prodImgDao.update(imgEntity);
            //修改goods表的信息
            if (goodsEntity != null) {
                goodsEntity.setFullName(prod.getProductName());
                goodsEntity.setCatagoryId(prod.getCatagoryId());
                goodsEntity.setUpdateDate(new Date());
                goodsEntity.setFirstImg(firstImg);
                goodsEntity.setImgs(imgs);
                goodsEntity.setIntro(prod.getIntro());
                goodsDao.update(goodsEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

}

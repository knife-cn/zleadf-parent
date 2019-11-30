package com.zlead.service.impl;

import com.zlead.dao.*;
import com.zlead.service.EnterpriseService;
import com.zlead.entity.*;
import com.zlead.util.page.Order;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.constant.Cnst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private AdsDao adsDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private ShopDao shopDao;
 

    @Autowired
    private GoodsDao goodsDao;



    /**
     * 判断企业信息
     * */
    public Map shopInfo(Long shopId) {
        Map map = new HashMap();
        try {

            ShopEntity shopEntity = shopDao.findById(shopId);
            if (shopEntity == null) {
                map.put("message", "没有查询到企业");
                map.put("success", false);
                return map;
            }
            if(shopEntity.getShopType()==null||shopEntity.getShopType()!=8){
                map.put("message", "企业信息不正确");
                map.put("success", false);
                return map;
            }
            map.put("message", "查询到企业信息");
            map.put("memberId",shopEntity.getMemberid());
            map.put("success",true);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询企业的banner
     */
    public List<AdsEntity> getAdsList(String memberId){
        Map params = new HashMap();
        List<AdsEntity> adsEntityList = null;
        //查询企业的账号信息
        MemberEntity memberEntity = memberDao.findByMemberId(memberId);
        if(memberEntity==null){
            return adsEntityList;
        }
        params.put("addUserId",memberEntity.getId());
        params.put("adstype",1);
        params.put("catagory",1);
        params.put("status",1);
        adsEntityList = adsDao.getAdsList(params);
        return adsEntityList;
    }
//
//    /**
//     * 查询企业的新闻
//     */
//    public List<ArticleEntity> getArticleList(String shopsId,Long categoryid){
//        Map params = new HashMap();
//        List<ArticleEntity> articleEntityList = null;
//        //查询企业的账号信息
////        MemberEntity memberEntity = memberDao.findByMemberId(memberId);
////        if(memberEntity==null){
////            return articleEntityList;
////        }
//        params.put("isPublication",1);
//        params.put("publishType",1);
//        //params.put("userId",memberEntity.getId());
//        params.put("categoryid",categoryid);
//        articleEntityList = articleDao.getList(params);
//        return articleEntityList;
//    }
//
//
//    public List<ArticleEntity> queryArticleListByShopId(Long shopId,Long categoryid){
//        Map params = new HashMap();
//        List<ArticleEntity> articleEntityList = null;
//
//        params.put("isPublication",1);
//        params.put("publishType",1);
//        params.put("shopId",shopId);
////        params.put("categoryid",categoryid);
//        articleEntityList = articleDao.getList(params);
//        return articleEntityList;
//    }
//
//    @Override
//    public List<ArticleEntity> queryArticleListByParam(Long shopId, Long categoryid, Integer isTop){
//
//        Map params = new HashMap();
//        List<ArticleEntity> articleEntityList = null;
//
//        params.put("isPublication",1);
//        params.put("publishType",1);
//        params.put("shopId",shopId);
//        params.put("categoryid",categoryid);
//        params.put("isTop",isTop);
//        articleEntityList = articleDao.getBannerList(params);
//        return articleEntityList;
//
//
//    }
//
//    /**
//     * 查询企业新闻的详细信息
//     */
//    public ArticleEntity getAtrticleDetails(String memberId,Long atrticleId){
//        Map params = new HashMap();
//        ArticleEntity articleEntity = null;
//        //查询企业的账号信息
//        MemberEntity memberEntity = memberDao.findByMemberId(memberId);
//        if(memberEntity==null){
//            return articleEntity;
//        }
//        articleEntity = articleDao.findById(atrticleId);
//        return articleEntity;
//    }

    /**
     * 查询企业的首页商品
     * @param showNum
     * @return
     */
    public List<GoodsEntity> gethomeGoodsList(Integer showNum,Long shopId){
        //查询最新的六个商品
        Map params = new HashMap();
        params.put("showNum",showNum);
        params.put("prodType",0);
        params.put("shopId",shopId);
       // params.put("supplierShopId",shopId);
      //  params.put("channel",5);//查询企业商品
        List<GoodsEntity> list = goodsDao.findHomeGoods(params);
        return list;
    }
    public List<GoodsEntity> getGoodsList(Integer showNum){
        Map params = new HashMap();
        params.put("showNum",showNum);
        params.put("prodType",0);
        // params.put("supplierShopId",shopId);
        //  params.put("channel",5);//查询企业商品
        List<GoodsEntity> list = goodsDao.findHomeGoods(params);
        return list;
    }

    /**
     * 企业商品列表
     */
    public PageList<GoodsEntity> getGoodsList(Integer prodType, Integer page,Long shopId){
        //传入的判断数据
        Map params = new HashMap();
        //页数
        PageBounds pageBounds = new PageBounds(page, Cnst.GOODS_PAGE_SIZE);
        pageBounds.setOrders(Order.formString("update_date.DESC"));
        params.put("isMarketable", 1);
        params.put("isAudit", 1);
        if(prodType!=null){
            params.put("prodType", prodType);
        }
        params.put("supplierShopId",shopId);
        params.put("shopId",shopId);
        //获取信息
        PageList<GoodsEntity> goodslist = goodsDao.findPage(params, pageBounds);
        return goodslist;
    }

    /*
    修改企业资料
     */
    public Boolean updateEnterprise(ShopEntity shop){
        boolean b =true;
        try {
            //查询企业信息
            ShopEntity shopEntity=shopDao.findById(shop.getId());
            if (shopEntity==null){
                return  false;
            }
            shopEntity.setCompanyName(shop.getCompanyName());
            shopEntity.setCompanyAddress(shop.getCompanyAddress());
            shopEntity.setBusinessSn(shop.getBusinessSn());
            shopEntity.setContactName(shop.getContactName());
            shopEntity.setPosition(shop.getPosition());
            shopEntity.setContactPhone(shop.getContactPhone());
            shopEntity.setContactQQ(shop.getContactQQ());
            shopEntity.setContactWeixin(shop.getContactWeixin());
            shopEntity.setContactEmail(shop.getContactEmail());
            shopEntity.setIntroduce(shop.getIntroduce());
            shopEntity.setService(shop.getService());
            shopDao.updateEnterprise(shopEntity);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;

    }
}

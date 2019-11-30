package com.zlead.reception.service.impl;

import com.puqian.payment.apipay.commonUtil.StringUtil;
import com.zlead.constant.Cnst;
import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.Region;
import com.zlead.entity.ShopEntity;
import com.zlead.reception.service.ShopService;
import com.zlead.service.RegionService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.zlead.entity.ArticleEntity;
//import com.zlead.service.ArticleBgService;


//@Transactional
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

//    @Autowired
//    private ArticleBgService articleBgService;

    @Resource
    private RegionService regionService;
    @Override
    @Transactional(readOnly = true)
    public PageList<ShopEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = shopDao.findPage(params, rowBounds);
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ShopEntity findById(Long id) {
        return shopDao.findById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public ShopEntity findByMemberId(String memberId) {
        return shopDao.findByMemeberId(memberId);
    }
    @Override
    public void save(ShopEntity entity) {
        shopDao.insert(entity);
    }
    @Override
    public void update(ShopEntity entity) {
        shopDao.update(entity);
    }
    @Override
    public void delete(Long id) {
        shopDao.delete(id);
    }

    /**
     * 保存供应商信息
     */
    public boolean saveShop(ShopEntity shopEntity,MemberEntity member,HttpServletRequest request){
        //省的编号
        String provinceId = "";
        //市的编号
        String cityId = "";
        //县的编号
        String countryId = "";
        String provinceName= request.getParameter("provinceName");
            String cityName= request.getParameter("cityName");
        String countyName = request.getParameter("countyName");
        boolean b = true;
        try{
            Region regionProvince = regionService.getRegion(1, provinceName);
            if(regionProvince!=null){
                provinceId = regionProvince.getRegionCode();
                //查询市的信息
                Region regionCity = regionService.getRegion(regionProvince.getId(), cityName);
                if(regionCity!=null){
                    cityId = regionCity.getRegionCode();
                    //查询县的信息
                    Region regionCounty = regionService.getRegion(regionCity.getId(), countyName);
                    if(regionCounty!=null){
                        countryId = regionCounty.getRegionCode();
                    }
                }
            }
            if(member!=null){
                //保存省市区ID
                if (provinceId!=null&&provinceId!=""){
                    shopEntity.setProvinceId(Long.valueOf(provinceId));
                }
                if (cityId!=null&&cityId!=""){
                    shopEntity.setCityId(Long.valueOf(cityId));
                }
                if (countryId!=null&&countryId!=""){
                    shopEntity.setRegionId(Long.valueOf(countryId));
                }

                shopEntity.setSn(Cnst.getCurTime()+"");
                shopEntity.setAuditSchedule(3);
                shopEntity.setStatus(1);
                shopEntity.setCreateDate(new Date());
                shopEntity.setDisable(0);//是否禁用
                shopEntity.setShopType(8);
                //shopEntity.setShopType(1);//1为供应商3为代理商8为企业
                //shopType为前台带过来的类型
                shopEntity.setMemberid(member.getMemberId());
                //保存店铺的信息
                shopDao.insert(shopEntity);
                Long id = shopEntity.getId();
                //默认文章
//                initNewINfo(shopEntity.getId(),member.getId());
                //member.setMemberType(1);
                //memberDao.update(member);
            }
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }


//    /**
//     * 初始化新闻信息
//     */
//    public  void  initNewINfo(long shopId,long  numberId){
//        ArticleEntity ac=new ArticleEntity();
//        ArticleEntity ac1=new ArticleEntity();
//        ArticleEntity ac2=new ArticleEntity();
//
//        ac.setShopId(shopId);
//        ac.setContent("致力于成为一家为社会创造最大价值的公司。经过14年砥砺前行" +
//                "，我们在商业领域一次又一次突破创新，取得了跨越式发展。与此同时，不忘初心，积极履行企业社会责任，在促进就业、提升社会效率、反哺实体经济等方面不断为社会做出贡献。");
//        ac.setThumbnail("M00/00/0B/rBCWGVviWKOAfsYNAAPNuvlGwKQ356.jpg");
//        ac.setTitle("关于我们");
//        ac.setCategoryid(3l);
//        ac.setPublishType(1);
//        ac.setIsPublication(1);
//
//
//        ac1.setShopId(shopId);
//        ac1.setContent("全新，全面屏，全方位强大。一切彻底重新设计，并融入我们的各种先进科技。这将启发你重新思考 智能白板能做到什么。 \" +\n" +
//                "                \"全新，全面屏，全方位强大。一切彻底重新设计，并融入我们的各种先进科技。这将启发你重新思考 智能白板能做到什么。。");
//        ac1.setThumbnail("M00/00/0B/rBCWGVviWKOAfsYNAAPNuvlGwKQ356.jpg");
//        ac1.setTitle("业务介绍");
//        ac1.setCategoryid(3l);
//        ac1.setPublishType(1);
//        ac1.setIsPublication(1);
//
//
//        ac2.setShopId(shopId);
//        ac2.setContent("上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务" +
//                "为核心，打造社会所需产品，发展前景非常可观上海蒲钱实业公司，位于上海市普陀区，是一个位于技术高端各方位全发展的科技创业型公司，本公司已服务为核心，打造社会所需产品，发展前景非常可观");
//        ac2.setThumbnail("M00/00/0B/rBCWGVviWKOAfsYNAAPNuvlGwKQ356.jpg");
//        ac2.setTitle("联系我们");
//        ac2.setCategoryid(3l);
//        ac2.setPublishType(1);
//        ac2.setIsPublication(1);
//
//        articleBgService.saveArticle(ac,numberId);
//        articleBgService.saveArticle(ac1,numberId);
//        articleBgService.saveArticle(ac2,numberId);
//    }

    /**
     * 从第三方授权获取到资料并保存
     */
    public boolean saveShopFromSSO(ShopEntity shopEntity,HttpServletRequest request){
                shopEntity.setSn(Cnst.getCurTime()+"");
                shopEntity.setAuditSchedule(3);
                shopEntity.setStatus(1);
                shopEntity.setCreateDate(new Date());
                shopEntity.setDisable(0);//是否禁用
                shopEntity.setShopType(6);
                shopDao.insert(shopEntity);
                return true;
    }


    /**
     * 修改店铺的信息
     */
    @Override
    public boolean updateShop(ShopEntity shopEntity,HttpServletRequest request){
        System.out.println("ShopLogo====="+shopEntity.getShopLogo());
        System.out.println("BannerImg======="+shopEntity.getBannerImg());
        //省的编号
        String provinceId = "";
        //市的编号
        String cityId = "";
        //县的编号
        String countryId = "";
        String provinceName= request.getParameter("provinceName");
        String cityName= request.getParameter("cityName");
        String countyName = request.getParameter("countyName");
        boolean b = true;
        try{
            Region regionProvince = regionService.getRegion(1, provinceName);
            if(regionProvince!=null){
                provinceId = regionProvince.getRegionCode();
                //查询市的信息
                Region regionCity = regionService.getRegion(regionProvince.getId(), cityName);
                if(regionCity!=null){
                    cityId = regionCity.getRegionCode();
                    //查询县的信息
                    Region regionCounty = regionService.getRegion(regionCity.getId(), countyName);
                    if(regionCounty!=null){
                        countryId = regionCounty.getRegionCode();
                    }
                }
            }
            //查询该shop原来的信息
            ShopEntity shop = shopDao.findById(shopEntity.getId());
            if(shop!=null){
                if(!StringUtil.isNullOrEmpty(provinceId)){
                    shop.setProvinceId(Long.valueOf(provinceId));
                }
                if(!StringUtil.isNullOrEmpty(cityId)){
                    shop.setCityId(Long.valueOf(cityId));
                }
                if(!StringUtil.isNullOrEmpty(countryId)){
                    shop.setRegionId(Long.valueOf(countryId));
                }
                //更新输入的信息
                shop.setCompanyName(shopEntity.getCompanyName());
                shop.setCompanyAddress(shopEntity.getCompanyAddress());
                shop.setBusinessSn(shopEntity.getBusinessSn());
                shop.setShopLogo(shopEntity.getShopLogo());
                shop.setContactName(shopEntity.getContactName());
                shop.setPosition(shopEntity.getPosition());
                shop.setContactPhone(shopEntity.getContactPhone());
                shop.setContactQQ(shopEntity.getContactQQ());
                shop.setContactWeixin(shopEntity.getContactWeixin());
                shop.setContactEmail(shopEntity.getContactEmail());
                shop.setIntroduce(shopEntity.getIntroduce());
                shop.setService(shopEntity.getService());
                shop.setMien(shopEntity.getMien());
                shop.setLegalName(shopEntity.getLegalName());
                shop.setBannerImg(shopEntity.getBannerImg());
                shop.setTemplate(shopEntity.getTemplate());
                shopDao.update(shop);
            }
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }
    @Override
    public ShopEntity findByWxUnionid(String WxUnionid) {
        return shopDao.findByWxUnionid(WxUnionid);
    }
    @Override
    public Map<String,Object> shopdsList(Long memid){
        Map<String, Object> map = new HashMap<String, Object>();

        List<ShopEntity> shopEntity = shopDao.shopdsLists(memid);
        return null;
    }
    @Override
    public List<ShopEntity> shopdsLists (Long memid) {
        return shopDao.shopdsLists(memid);

    }

    @Override
    public int shopdsListSize(Long memid){
        return shopDao.shopdsListSize(memid);
    }

    @Override
    public List<ShopEntity> shopdstop(){
        return shopDao.shopdstop();
    }

}

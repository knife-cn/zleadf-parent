package com.zlead.service.impl;

import com.zlead.dao.AdsDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.ShopEntity;
import com.zlead.entity.AdsEntity;
import com.zlead.service.AdsBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;


@Transactional
@Service
public class AdsBgServiceImpl implements AdsBgService {
    @Autowired
    private AdsDao adsDao;
    
    @Autowired
    private ShopDao shopDao;

    @Transactional(readOnly = true)
    public PageList<AdsEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = adsDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public AdsEntity findById(Long id) {
        return adsDao.findById(id);
    }

    public void save(AdsEntity entity,Long memberId) {
        entity.setCreateDate(new Date());
        //FIXME 添加用户的iD暂时写死 缩略图忽略 已改为memberId
        entity.setAdduserId(memberId);
        adsDao.insert(entity);
    }

    public void update(AdsEntity entity) {
        adsDao.update(entity);
    }

    public void delete(Long id) {
        adsDao.delete(id);
    }

    /**
     * 添加banner信息
     * @param entity
     * @param userId
     * @param adsType
     */
    public void saveAds(AdsEntity entity,Long userId,Long adsType){
        entity.setCatagory(1);//轮播图为1 ，有广告的话为2
        entity.setAdstype(adsType);
        entity.setChannel(0);
        entity.setSort(0);
        entity.setContentType(0);
        long shopId=0;
        ShopEntity shopEntity=shopDao.findByMemeberId(userId.toString()) ;
        if(shopEntity!=null && shopEntity.getId()!=null)
        	shopId=shopEntity.getId();
        entity.setShopId(shopId);
        entity.setAdduserId(userId);
        entity.setCreateDate(new Date());
        adsDao.insert(entity);
    }

    /**
     * 修改banner信息
     */
    public boolean updateAds(AdsEntity ads ){
        boolean b = true;
        try{
            AdsEntity adsEntity = adsDao.findById(ads.getId());
            if(adsEntity==null){
                return false;
            }
            adsEntity.setTitle(ads.getTitle());
            if(ads.getThumbnail()!=null){
                adsEntity.setThumbnail(ads.getThumbnail());
            }
            if(ads.getContentPath()!=null){
                adsEntity.setContentPath(ads.getContentPath());
            }
            adsEntity.setAdsImg(ads.getAdsImg());
            adsEntity.setIntroduce(ads.getIntroduce());
            if(ads.getAdsContent()!=null){
                adsEntity.setAdsContent(ads.getAdsContent());
            }
            if(ads.getShopId()!=null){
                adsEntity.setShopId(ads.getShopId());
            }
            if(adsEntity.getShopId()==null){
                System.out.println(" shop id is null ");
                adsEntity.setShopId(0L);
            }
            adsEntity.setHits(ads.getHits());
            adsEntity.setStatus(ads.getStatus());
            adsEntity.setModifyDate(new Date());

            adsDao.update(adsEntity);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    /**
     * 删除banner
     * @return
     */
    public boolean deleteAds(Long id) {
        boolean b = true;
        try{
            //查询banner
            AdsEntity entity = adsDao.findById(id);
            //删除banner
            entity.setStatus(2);//2为删除
            adsDao.update(entity);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }
}

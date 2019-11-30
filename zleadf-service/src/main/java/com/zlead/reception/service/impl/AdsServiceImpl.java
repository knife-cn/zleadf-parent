package com.zlead.reception.service.impl;

import com.zlead.dao.AdsDao;
import com.zlead.entity.AdsEntity;
import com.zlead.reception.service.AdsService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional
@Service
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdsDao adsDao;

    @Transactional(readOnly = true)
    public PageList<AdsEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = adsDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public AdsEntity findById(Long id) {
        return adsDao.findById(id);
    }

    public void save(AdsEntity entity) {
        adsDao.insert(entity);
    }

    public void update(AdsEntity entity) {
        adsDao.update(entity);
    }

    public void delete(Long id) {
        adsDao.delete(id);
    }

    //查询banner图查询前三个
    public List<AdsEntity> getAdsList(Map params){
        //查询
        List<AdsEntity> adsList = adsDao.getAdsList(params);
        return adsList;
    }

    @Override
    public List<AdsEntity> QueryAdsList(Long shopId) {
        return adsDao.QueryAdsList(shopId);
    }

    @Override
    public List<AdsEntity> AdsList(List<Integer> shopId,int ids){

        return adsDao.AdsList(shopId,ids);
  }

}

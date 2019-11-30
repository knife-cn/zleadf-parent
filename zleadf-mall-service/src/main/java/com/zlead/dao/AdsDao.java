package com.zlead.dao;

import com.zlead.entity.AdsEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 广告信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-03 16:42:39
 */
public interface AdsDao {

    void insert(AdsEntity entity);
    
    /**
     * This method:insertSelective
     *   t_ads
     *
     * @ET
     */
    int insertSelective(AdsEntity record);


    void update(AdsEntity entity);

    void delete(Long id);

    PageList<AdsEntity> findPage(Map params, PageBounds rowBounds);

    PageList<AdsEntity> findAllPage(PageBounds rowBounds);
    AdsEntity findById(Long id);

    //获取广告和轮播图的信息
    List<AdsEntity> getAdsList(Map params);
    //获取广告和轮播图的信息2
    List<AdsEntity>  QueryAdsList(Long shopId);

    List<AdsEntity> AdsList(@Param(value = "List")List<Integer> shopId,@Param(value = "ids")int ids);
}

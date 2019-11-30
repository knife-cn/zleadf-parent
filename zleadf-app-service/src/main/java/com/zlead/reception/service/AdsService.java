package com.zlead.reception.service;

import com.zlead.entity.AdsEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 广告信息
 *
 * @author fqf
 * @date 2018-08-03 16:42:39
 */
public interface AdsService {

    PageList<AdsEntity> getPage(Map params, PageBounds rowBounds);

    void save(AdsEntity entity);

    void update(AdsEntity entity);

    void delete(Long id);

    AdsEntity findById(Long id);

    List<AdsEntity> getAdsList(Map params);
    //获取广告和轮播图的信息2
    List<AdsEntity>  QueryAdsList(Long shopId);

    List<AdsEntity> AdsList(@Param(value = "List")List<Integer> shopId,@Param(value = "ids")int ids);
}


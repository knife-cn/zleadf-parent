package com.zlead.service;

import com.zlead.entity.AdsEntity;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 广告信息后台
 *
 * @author fqf
 * @date 2018-08-13 14:02:16
 */
public interface AdsBgService {

    PageList<AdsEntity> getPage(Map params, PageBounds rowBounds);

    void save(AdsEntity entity,Long memberId);

    void update(AdsEntity entity);

    void delete(Long id);

    AdsEntity findById(Long id);

    void saveAds(AdsEntity entity,Long userId,Long adsType);

    boolean deleteAds(Long id);

    boolean updateAds(AdsEntity tAdsEntity );
}


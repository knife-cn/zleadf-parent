package com.zlead.service;

import com.zlead.entity.ProdImgEntity;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 产品颜色图片
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-26 11:35:28
 */
public interface ProdImgBgService {

    PageList<ProdImgEntity> getPage(Map params, PageBounds rowBounds);

    void save(ProdImgEntity entity);

    void update(ProdImgEntity entity);

    void delete(Long id);

    ProdImgEntity findById(Long id);
}


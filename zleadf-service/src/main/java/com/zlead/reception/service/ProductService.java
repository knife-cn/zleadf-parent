package com.zlead.reception.service;

import com.zlead.entity.ProductEntity;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 产品
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-31 09:54:19
 */
public interface ProductService {

    PageList<ProductEntity> getPage(Map params, PageBounds rowBounds);

    void save(ProductEntity entity);

    void update(ProductEntity entity);

    void delete(Long id);

    ProductEntity findById(Long id);
}


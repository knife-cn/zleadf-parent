package com.zlead.dao;

import com.zlead.entity.ProductEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import java.util.Map;

/**
 * 产品
 * 
 * @author fqf
 * @date 2018-07-25 11:41:32
 */
public interface ProductDao {

    void insert(ProductEntity entity);

    void update(ProductEntity entity);

    void delete(Long id);

    PageList<ProductEntity> findPage(Map params, PageBounds rowBounds);

    ProductEntity findById(Long id);
	
}

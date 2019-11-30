package com.zlead.dao;

import com.zlead.entity.ProdImgEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import java.util.Map;

/**
 * 产品颜色图片
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-26 11:35:28
 */
public interface ProdImgDao {

    void insert(ProdImgEntity entity);

    void update(ProdImgEntity entity);

    void delete(Long id);

    PageList<ProdImgEntity> findPage(Map params, PageBounds rowBounds);

    ProdImgEntity findById(Long id);

    //通过prodId查询信息
    ProdImgEntity findByProdId(Long prodId);
	
}

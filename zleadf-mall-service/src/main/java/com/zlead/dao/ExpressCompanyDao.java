package com.zlead.dao;

import com.zlead.entity.ExpressCompanyEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import java.util.List;
import java.util.Map;

/**
 * 3102快递公司表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-14 10:14:02
 */
public interface ExpressCompanyDao {

    void insert(ExpressCompanyEntity entity);

    void update(ExpressCompanyEntity entity);

    void delete(Long id);

    PageList<ExpressCompanyEntity> findPage(Map params, PageBounds rowBounds);

    ExpressCompanyEntity findById(Long id);

    List<ExpressCompanyEntity> getList(Map params);

    ExpressCompanyEntity findByComNo(String comNo);
	
}

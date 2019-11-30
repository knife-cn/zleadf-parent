package com.zlead.service;

import com.zlead.entity.ExpressCompanyEntity;

import java.util.List;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 3102快递公司表
 *
 * @author fqf
 * @date 2018-08-14 10:14:02
 */
public interface ExpressCompanyBgService {

    PageList<ExpressCompanyEntity> getPage(Map params, PageBounds rowBounds);

    void save(ExpressCompanyEntity entity);

    void update(ExpressCompanyEntity entity);

    void delete(Long id);

    ExpressCompanyEntity findById(Long id);

    List<ExpressCompanyEntity> getList(String name);
}


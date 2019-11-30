package com.zlead.service.impl;

import com.zlead.dao.ExpressCompanyDao;
import com.zlead.entity.ExpressCompanyEntity;
import com.zlead.service.ExpressCompanyBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service
public class ExpressCompanyBgServiceImpl implements ExpressCompanyBgService {
    @Autowired
    private ExpressCompanyDao expressCompanyDao;

    @Transactional(readOnly = true)
    public PageList<ExpressCompanyEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = expressCompanyDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public ExpressCompanyEntity findById(Long id) {
        return expressCompanyDao.findById(id);
    }

    public void save(ExpressCompanyEntity entity) {
        expressCompanyDao.insert(entity);
    }

    public void update(ExpressCompanyEntity entity) {
        expressCompanyDao.update(entity);
    }

    public void delete(Long id) {
        expressCompanyDao.delete(id);
    }

    /**
     * 查询所有的快递公司
     */
    public List<ExpressCompanyEntity> getList(String name){
        Map params = new HashMap();
        params.put("name",name);
        List<ExpressCompanyEntity> list = expressCompanyDao.getList(params);
        return list;
    }

}

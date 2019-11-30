package com.zlead.service.impl;

import com.zlead.dao.ProdImgDao;
import com.zlead.entity.ProdImgEntity;
import com.zlead.service.ProdImgBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;


@Transactional
@Service
public class ProdImgBgServiceImpl implements ProdImgBgService {
    @Autowired
    private ProdImgDao prodImgDao;

    @Transactional(readOnly = true)
    public PageList<ProdImgEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = prodImgDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public ProdImgEntity findById(Long id) {
        return prodImgDao.findById(id);
    }

    public void save(ProdImgEntity entity) {
        prodImgDao.insert(entity);
    }

    public void update(ProdImgEntity entity) {
        prodImgDao.update(entity);
    }

    public void delete(Long id) {
        prodImgDao.delete(id);
    }

}

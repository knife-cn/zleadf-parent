package com.zlead.reception.service.impl;

import com.zlead.dao.FinorderDao;
import com.zlead.entity.FinorderEntity;
import com.zlead.reception.service.FinorderService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FinorderServiceImpl implements FinorderService {
    @Autowired
    private FinorderDao finorderDao;
    @Override
    public void save(FinorderEntity finorderEntity) {
        finorderDao.insertFinorder(finorderEntity);
    }

    @Override
    public PageList<FinorderEntity> findFinorder(PageBounds rowBounds,long id) {
        PageList<FinorderEntity> memberid = finorderDao.findFinorderByMemberid(rowBounds, id);

        return memberid;
    }


}

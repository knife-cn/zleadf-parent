package com.zlead.reception.service;

import com.zlead.entity.FinorderEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

public interface FinorderService {

    void save(FinorderEntity finorderEntity);

    PageList<FinorderEntity> findFinorder(PageBounds rowBounds,long id);
}

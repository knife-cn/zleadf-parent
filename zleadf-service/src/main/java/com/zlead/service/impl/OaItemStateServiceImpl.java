package com.zlead.service.impl;

import com.zlead.dao.OaItemStateDao;
import com.zlead.entity.OaItemState;
import com.zlead.service.OaItemStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OaItemStateService")
public class OaItemStateServiceImpl implements OaItemStateService{

    @Autowired
    private OaItemStateDao oaItemStateDao;

    /**
     * 删除上下架内容
     * @param itemId
     * @return
     */
    public int deleteByPrimaryKey(Integer itemId){
        return oaItemStateDao.deleteByPrimaryKey(itemId);
    }
    /**
     * 添加上下架内容
     * @param record
     * @return
     */
    public int insert(OaItemState record){
        return oaItemStateDao.insert(record);
    }
    /**
     * 根据上下架id查询上下架内容
     * @param itemId
     * @return
     */
    public  OaItemState selectByPrimaryKey(Integer itemId){
        return oaItemStateDao.selectByPrimaryKey(itemId);
    }
    /**
     * 修改单个上下架内容
     * @param itemId
     * @return
     */
    public int updateByPrimaryKeySelective(OaItemState record){
        return oaItemStateDao.updateByPrimaryKeySelective(record);
    }
    /**
     * 修改全部上下架内容
     * @param record
     * @return
     */
    public int updateByPrimaryKey(OaItemState record){
        return oaItemStateDao.updateByPrimaryKey(record);
    }
}
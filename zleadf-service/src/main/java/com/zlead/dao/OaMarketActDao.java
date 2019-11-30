package com.zlead.dao;

import com.zlead.entity.OaMarketAct;

import java.util.List;

public interface OaMarketActDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OaMarketAct record);

    int insertSelective(OaMarketAct record);

    OaMarketAct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaMarketAct record);

    int updateByPrimaryKeyWithBLOBs(OaMarketAct record);

    int updateByPrimaryKey(OaMarketAct record);

    List<OaMarketAct> queryNoActiveActivity();

    /**
     * 只要IDs
     * @return
     */
    List<Integer> queryNoActiveActId();

    /**
     * 将过期活动处理
     * @return
     */
    int updateExpiredActConState();

}
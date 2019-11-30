package com.zlead.fplat.dao;

import com.zlead.fplat.entity.GoodsAgentLevel; 
import java.util.List;

public interface GoodsAgentLevelMapper {
    /**
     * This method:deleteByPrimaryKey
     *   t_goods_level
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     *   t_goods_level
     *
     * @ET
     */
    int insert(GoodsAgentLevel record);

    /**
     * This method:insertSelective
     *   t_goods_level
     *
     * @ET
     */
    int insertSelective(GoodsAgentLevel record);
 
    /**
     * This method:selectByPrimaryKey
     *   t_goods_level
     *
     * @ET
     */
    GoodsAgentLevel selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_goods_level
     *
     * @ET
     */
    int updateByPrimaryKeySelective(GoodsAgentLevel record);

    /**
     * This method:updateByPrimaryKey
     *   t_goods_level
     *
     * @ET
     */
    int updateByPrimaryKey(GoodsAgentLevel record);
}
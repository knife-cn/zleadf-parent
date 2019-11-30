package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.entity.Prdlistcats; 

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PrdlistcatsMapper {
    /**
     * This method:deleteByPrimaryKey
     *   crm_prd_list_cats
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     *   crm_prd_list_cats
     *
     * @ET
     */
    int insert(Prdlistcats record);

    /**
     * This method:insertSelective
     *   crm_prd_list_cats
     *
     * @ET
     */
    int insertSelective(Prdlistcats record);

 
    /**
     * This method:selectByPrimaryKey
     *   crm_prd_list_cats
     *
     * @ET
     */
    Prdlistcats selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     *   crm_prd_list_cats
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Prdlistcats record);

    /**
     * This method:updateByPrimaryKey
     *   crm_prd_list_cats
     *
     * @ET
     */
    int updateByPrimaryKey(Prdlistcats record);
    
    //获取平台正常状态的工厂信息
    List<Prdlistcats> findAlllistcats(Integer memberId);
    
    List<Prdlistcats> selectCatsByListIds(@Param("listIds") List listIds,@Param("agentId") Long agentId);
}
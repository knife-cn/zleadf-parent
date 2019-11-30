package com.zlead.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SearchDao {

    /**
     * 根据代理人ID查询代理人关联的工厂相关信息
     * @param agentId 代理人ID ，必传
     * @param factoryId 可有可无，没有的话查询代理人关联的所有工厂
     * @return
     */
    List<Map<String,Object>> findFactoryInfoByAgent(@Param("agentId") Long agentId,@Param("factoryId") Long factoryId);

    /**
     * 根据代理人ID查询代理人参与的有效活动信息
     * @param agentId 代理人ID ，必传
     * @param shopIds 店铺ID，可选
     * @return
     */
    List<Map<String,Object>> findActivityInfoByAgent(@Param("agentId") Long agentId,@Param("shopIds") Collection<Long> shopIds);



    List<Map<String,Object>> findSortBrandByCondition(@Param("agentId") Long agentId,
                                                      @Param("factoryId") Long factoryId,
                                                      @Param("levelIds") Collection<Long> levelIds);


    List<Map<String,Object>> findSortListByCondition(@Param("agentId") Long agentId,
                                                      @Param("factoryId") Long factoryId,
                                                      @Param("levelIds") Collection<Long> levelIds);


    List<Map<String,Object>> findSortCatByCondition(@Param("agentId") Long agentId,
                                                      @Param("factoryId") Long factoryId,
                                                      @Param("levelIds") Collection<Long> levelIds);

    /**
     * 根据条件查询品牌、系列、分类并排序
     * @param agentId
     * @param factoryId
     * @param levelIds
     * @return
     */
    List<Map<String,Object>> findSortKindByCondition(@Param("agentId") Long agentId,
                                                     @Param("factoryId") Long factoryId,
                                                     @Param("levelIds") Collection<Long> levelIds);

}

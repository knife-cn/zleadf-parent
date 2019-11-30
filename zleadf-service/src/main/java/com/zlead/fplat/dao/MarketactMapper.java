package com.zlead.fplat.dao;

import com.zlead.common.PageResponse;
import com.zlead.fplat.entity.Marketact;
import com.zlead.util.page.PageBounds;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarketactMapper {
    /**
     * 查询活动详情
     * @param actId
     * @param agentId
     * @return
     */
    Marketact queryByActId(@Param("actId") int actId,@Param("agentId") Long agentId);
    
    /**
     * APP端查询活动的详情
     * @param actId
     * @return
     */
    Marketact queryByActIdForApp(@Param("actId") int actId);

    //根据类型Ids  查找有效期内的活动
    List<Marketact> findConByType(List<Integer> typeIds);

    /**
     * 查询活动列表
     * @param agentId
     * @return
     */
    List<Marketact> findByAgentIdPage(@Param("agentId")int agentId, PageBounds pageBounds,@Param("factIds") List<Integer> factIds);
    
    
    /**
     * 查询活动列表
     *  
     * @return
     */
    List<Marketact> findAllByPage(PageBounds pageBounds);

    /**
     * 根据添加查询活动列表
     *
     * @author 喻聪聪
     * @date   2019-02-18
     *
     * @param intValue
     * @param pageBounds
     * @param ownShopid
     * @param contType    活动类型ID
     * @param contState   活动状态
     * @param effDate     生效时间
     * @param expDate     过期时间
     * @return
     */
    List<Marketact> findShopActivies(PageBounds pageBounds,@Param("ownShopid") Long ownShopid,
                                     @Param("contType")Integer contType,@Param("contState")Integer contState,@Param("effDate")String effDate,@Param("expDate")String expDate);

    /**
     * 根据添加查询活动列表
     *
     * @author 喻聪聪
     * @date   2019-02-18
     *
     * @param intValue
     * @param ownShopid
     */
    List<Marketact> findActivityTypes(int intValue,@Param("ownShopid") Long ownShopid);

}
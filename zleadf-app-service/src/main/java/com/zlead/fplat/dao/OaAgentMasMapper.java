package com.zlead.fplat.dao;

import com.zlead.entity.vo.*;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.OaAgentMasExample;
import com.zlead.fplat.entity.OaAgentMasRequest;
import com.zlead.fplat.entity.vo.AgentBlmcVO;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OaAgentMasMapper {
    /**
     * This method:deleteByPrimaryKey
     * oa_agent_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer agentId);

    /**
     * This method:insert
     * oa_agent_mas
     *
     * @ET
     */
    int insert(OaAgentMas record);

    /**
     * This method:insertSelective
     * oa_agent_mas
     *
     * @ET
     */
    int insertSelective(OaAgentMas record);

    /**
     * This method:selectByExample
     * oa_agent_mas
     *
     * @ET
     */
    List<OaAgentMas> selectByExample(OaAgentMasExample example);

    /**
     * This method:selectByPrimaryKey
     * oa_agent_mas
     *
     * @ET
     */
    OaAgentMas selectByPrimaryKey(Integer agentId);

    OaAgentMas selectByPhone(String phone);

    /**
     * This method:updateByPrimaryKeySelective
     * oa_agent_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaAgentMas record);

    /**
     * This method:updateByPrimaryKey
     * oa_agent_mas
     *
     * @ET
     */
    int updateByPrimaryKey(OaAgentMas record);

    /**
     * @param rowBounds
     * @param memberId
     * @return
     */
    PageList<OaAgentMas> selectAgentByMemberId(PageBounds rowBounds, Long memberId);

    /**
     * @param rowBounds
     * @param facId
     * @return
     */
    PageList<OaAgentMas> selectAgentByFacId(PageBounds rowBounds, Long facId);

    /**
     * 代理商审批是否启用
     *
     * @param status
     * @param memberId
     * @param agentId
     * @return
     */
    void updateAgentByAgentId(@Param("status") String status, @Param("memberId") Long memberId, @Param("agentId") Long agentId);

    List<Map> findAgentStatus(Long memberId);

    List<OrderAgentMas> findAgentByMemberId(@Param("memberId") Long memberId,@Param("userIds") Set<Integer> userIds);

    PageList<OaAgentMasListVo> queryAgentList(OaAgentMasRequest params);

    /**
     * 查询客户基本信息
     *
     * @param agentId
     * @param factoryId
     * @return
     */
    OaAgentMasBaseInfoVo selectAgentBaseInfoByAgentId(@Param("agentId") Integer agentId, @Param("factoryId") Integer factoryId);

    /**
     * 获取客户图片地址列表
     *
     * @param agentId
     * @param ownShopid
     * @return
     */
    List<AgentPictureListVO> getAgentPictureList(@Param("agentId") Integer agentId, @Param("shopId") Long ownShopid, PageBounds pageBounds);


    List<ActivityAgentVo> findActivityAgent(PageBounds pageBounds,@Param("memberId") Long memberId,@Param("actId") int actId);

    List<AgentBlmcVO> findBLMC(@Param("agentId") Long agentId,
                               @Param("factoryId") Long factoryId,
                               @Param("bKey") String bKey,
                               @Param("lKey") String lKey,
                               @Param("mKey") String mKey,
                               @Param("cKey") String cKey);

    List<AgentBlmcVO> findNewBLMC(@Param("agentId") Long agentId,
                               @Param("factoryId") Long factoryId,
                               @Param("bKey") String bKey,
                               @Param("lKey") String lKey,
                               @Param("mKey") String mKey,
                               @Param("cKey") String cKey);

    /**
     * 查询代理商参与的活动的品牌、系列、型号、分类 add by ykf
     * @param agentId
     * @param factoryIds
     * @param bKey
     * @param lKey
     * @param mKey
     * @param cKey
     * @return
     */
    List<AgentBlmcVO> findActivityBLMC(@Param("agentId") Long agentId,
                                       @Param("factoryIds") List<Long> factoryIds,
                                       @Param("bKey") String bKey,
                                       @Param("lKey") String lKey,
                                       @Param("mKey") String mKey,
                                       @Param("cKey") String cKey);

}
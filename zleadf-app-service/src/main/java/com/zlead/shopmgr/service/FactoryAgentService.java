package com.zlead.shopmgr.service;

import com.zlead.entity.vo.*;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface FactoryAgentService {
    /**
     * 指定工厂下的代理商信息
     * @param pageNum
     * @param pageSize
     * @param memberId
     * @return
     */
    PageList<OaAgentMas> getFactoryAgentList(int pageNum, int pageSize, Long memberId);

    void setAgentStatus(String status,Long memberId,Long agentId);

    List<Map> findAgentStatus(Long memberId);

    List<OrderAgentMas> getAppOrderAgentList(Long memberId);

    /**
     * 查询代理商(客户)列表
     * @param memberId
     * @param keyword
     * @param levelId
     * @return
     */
    Map<String, Object> queryAgentList(Long memberId, String keyword, Integer levelId);

    /**
     * 查询代理商(客户)基本信息
     * @param agentId
     * @param factoryId
     * @return
     */
    OaAgentMasBaseInfoVo getAgentBaseInfo(Integer agentId, Integer factoryId);

    /**
     * 查询客户授权品牌列表
     * @param agentId
     * @param ownShopid
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<AgentBrandListVO> getAgentBrandList(Integer agentId, Long ownShopid, Integer pageNum, Integer pageSize);

    /**
     * 获取客户图片地址列表
     * @param agentId
     * @param ownShopid
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<AgentPictureListVO> getAgentPictureList(Integer agentId, Long ownShopid, Integer pageNum, Integer pageSize);

    List<ActivityAgentVo> findActivityAgent(PageBounds pageBounds, Long id,int actId);

    /**
     * 查询凭证新增客户列表
     * @param memberId
     * @param keyword
     * @param levelId
     * @return
     */
    Map<String, Object> queryVoucherAgentList(Long memberId, String keyword, Integer levelId);
}
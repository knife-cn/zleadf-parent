package com.zlead.shopmgr.service.impl;

import com.zlead.entity.vo.*;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.*;
import com.zlead.shopmgr.service.BaseAddrareaService;
import com.zlead.shopmgr.service.FactoryAgentService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.AppUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class FactoryAgentServiceImpl implements FactoryAgentService {
    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private AgentbandMapper agentbandMapper;
    @Autowired
    private OaAgentRevinfoMapper oaAgentRevinfoMapper;
    @Autowired
    private BaseAddrareaService baseAddrareaService;
    @Autowired
    private OaFactoryInfoMapper oaFactoryInfoMapper;
    @Autowired
    private OaAgentUserinfoMapper oaAgentUserinfoMapper;

    /**
     * 校验手机号码格式正则
     */
    private static Pattern MOBILE_REGEX = Pattern.compile("^1\\d{10}$");

    @Override
    public PageList<OaAgentMas> getFactoryAgentList(int pageNum, int pageSize,  Long memberId) {
        PageList<OaAgentMas> agentList = null;
        Map params = new HashMap<>();
        if (memberId != null) {
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            params.put("memberId", memberId);
            agentList = oaAgentMasMapper.selectAgentByMemberId(pageBounds,memberId);
        }
        return agentList;
    }

    @Override
    public void setAgentStatus(String status, Long memberId, Long agentId) {
         oaAgentMasMapper.updateAgentByAgentId(status,memberId,agentId);
    }

    @Override
    public List<Map> findAgentStatus(Long memberId) {
        return oaAgentMasMapper.findAgentStatus(memberId);
    }

    @Override
    public List<OrderAgentMas> getAppOrderAgentList(Long memberId) {
        return oaAgentMasMapper.findAgentByMemberId(memberId);
    }

    @Override
    public JsonResult queryAgentList(Long memberId, String keyword, Integer levelId) {
        PageList<OaAgentMasListVo> agentList = null;
        if (memberId != null) {
            OaAgentMasRequest params = new OaAgentMasRequest();
            params.setMemberId(memberId.toString());
            if(StringUtils.isNotBlank(keyword)){
                if(MOBILE_REGEX.matcher(keyword).find()){
                    params.setLinkTel(keyword);
                }else{
                    params.setAgentName(keyword);
                }
            }
            params.setLevelId(levelId);
            agentList = oaAgentMasMapper.queryAgentList(params);

            if(CollectionUtils.isNotEmpty(agentList)){
                OaAgentRevinfo queryParam = new OaAgentRevinfo();
                List<OaAgentUserinfo> userinfoList = null;

                for (OaAgentMasListVo agentMasListVo : agentList) {
                    //取出完整地址
                    queryParam.setAgentId(agentMasListVo.getAgentId());
                    queryParam.setSysId(agentMasListVo.getShopId());
                    queryParam.setRevDefault("1");
                    List<OaAgentRevinfo> revinfoList = this.oaAgentRevinfoMapper.findRevinfo(queryParam);//查询默认收货地址
                    if(CollectionUtils.isNotEmpty(revinfoList)){
                        agentMasListVo.setAgentAddr(this.getDetailAddress(revinfoList.get(0)));
                    } else{
                        queryParam.setIsFact(1);
                        queryParam.setRevDefault(null);
                        revinfoList = this.oaAgentRevinfoMapper.findRevinfo(queryParam);//查询工厂端添加的收货地址
                        if(CollectionUtils.isNotEmpty(revinfoList)){
                            agentMasListVo.setAgentAddr(this.getDetailAddress(revinfoList.get(0)));
                        }
                    }
                    //设置联系人信息
                    userinfoList = oaAgentUserinfoMapper.findUserInfoByAgentIdAndShopId(agentMasListVo.getAgentId(),agentMasListVo.getShopId());
                    if(CollectionUtils.isNotEmpty(userinfoList)){
                        for (OaAgentUserinfo agentUserInfo : userinfoList) {
                            agentMasListVo.setLinkName(agentUserInfo.getUserName());
                            if("1".equals(agentUserInfo.getMainUser())) {
                                agentMasListVo.setLinkName(agentUserInfo.getUserName());
                                break;
                            }
                        }
                    }
                }
            }
        }
        JsonResult jsonResult = null;
        if (agentList != null && agentList.size() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("result", agentList);
            data.put("totalNumber", agentList.size());
            jsonResult = new JsonResult(1, "列表信息", data, true);
        } else {
            jsonResult = new JsonResult(1, "无列表信息", null,true);
        }
        return jsonResult;
    }

    @Override
    public OaAgentMasBaseInfoVo getAgentBaseInfo(Integer agentId, Integer factoryId) {
        OaAgentMasBaseInfoVo agent = oaAgentMasMapper.selectAgentBaseInfoByAgentId(agentId, factoryId);
        //折扣保留两位小数
        String agentDiscount = agent.getAgentDiscount();
        agentDiscount = agentDiscount.substring(0,agentDiscount.indexOf(".") + 3);
        try{
            String htmlContent = AppUtil.wordToHtml(agent.getContractUrl());
            agent.setHtmlContent(htmlContent);
        }catch(Exception e){
            e.printStackTrace();
        }
        agent.setAgentDiscount(agentDiscount);
        //处理app收货地址显示，如果有代理商创建的默认地址，就显示默认地址，如果没有就显示工厂创建地址表中的第一条
        OaFactoryInfo oaFactoryInfo = this.oaFactoryInfoMapper.findFacByFactId(factoryId);
        if(oaFactoryInfo != null){
            OaAgentRevinfo queryParam = new OaAgentRevinfo();
            queryParam.setAgentId(agentId);
            queryParam.setSysId(oaFactoryInfo.getShopId());
            queryParam.setRevDefault("1");
            List<OaAgentRevinfo> revinfoList = this.oaAgentRevinfoMapper.findRevinfo(queryParam);//查询默认收货地址
            if(CollectionUtils.isNotEmpty(revinfoList)){
                agent.setReceiveAddr(this.getDetailAddress(revinfoList.get(0)));
            } else{
                queryParam.setIsFact(1);
                queryParam.setRevDefault(null);
                revinfoList = this.oaAgentRevinfoMapper.findRevinfo(queryParam);//查询工厂端添加的收货地址
                if(CollectionUtils.isNotEmpty(revinfoList)){
                    agent.setReceiveAddr(this.getDetailAddress(revinfoList.get(0)));
                }
            }
        }
        if(StringUtils.isBlank(agent.getReceiveAddr())){
            agent.setReceiveAddr("-");
        }
        return agent;
    }

    /**
     * 获取详细地址信息xxx省xxx市xxx区xxx地址
     * @param revinfo
     * @return
     */
    private String getDetailAddress(OaAgentRevinfo revinfo){
        String procinceName = "";
        String cityName = "";
        String countyName = "";
        String province = revinfo.getRevProvince();//省
        if(StringUtils.isNotBlank(province)){
            procinceName = this.baseAddrareaService.findAddrareaNameByCode(province);
        }
        String city = revinfo.getRevCity();//市
        if(StringUtils.isNotBlank(city)){
            cityName = this.baseAddrareaService.findAddrareaNameByCode(city);
        }
        String county = revinfo.getRevCounty();//区
        if(StringUtils.isNotBlank(county)){
            countyName = this.baseAddrareaService.findAddrareaNameByCode(county);
        }
        return procinceName + cityName + countyName + revinfo.getRevAddr();
    }

    @Override
    public List<AgentBrandListVO> getAgentBrandList(Integer agentId, Long ownShopid, Integer pageNum, Integer pageSize) {
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        return agentbandMapper.getAgentBrandList(agentId, ownShopid, pageBounds);
    }

    @Override
    public List<AgentPictureListVO> getAgentPictureList(Integer agentId, Long ownShopid, Integer pageNum, Integer pageSize) {
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        return oaAgentMasMapper.getAgentPictureList(agentId, ownShopid, pageBounds);
    }

    @Override
    public List<ActivityAgentVo> findActivityAgent(PageBounds pageBounds, Long memberId,int actId) {
        return oaAgentMasMapper.findActivityAgent(pageBounds, memberId,actId);
    }

}

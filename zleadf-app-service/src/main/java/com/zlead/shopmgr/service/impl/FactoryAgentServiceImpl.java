package com.zlead.shopmgr.service.impl;

import com.zlead.dao.AclUserDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.vo.*;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.*;
import com.zlead.shopmgr.service.BaseAddrareaService;
import com.zlead.shopmgr.service.FactoryAgentService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.AppUtil;
import com.zlead.utils.MathUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
    @Autowired
    private AgentFacMapper agentFacMapper;
    @Autowired
    private AclUserDao aclUserDao;
    /**
     * 校验手机号码格式正则
     */
    private static Pattern MOBILE_REGEX = Pattern.compile("^1\\d{10}$");

    @Override
    public PageList<OaAgentMas> getFactoryAgentList(int pageNum, int pageSize, Long memberId) {
        PageList<OaAgentMas> agentList = null;
        Map params = new HashMap<>();
        if (memberId != null) {
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            params.put("memberId", memberId);
            agentList = oaAgentMasMapper.selectAgentByMemberId(pageBounds, memberId);
        }
        return agentList;
    }

    @Override
    public void setAgentStatus(String status, Long memberId, Long agentId) {
        oaAgentMasMapper.updateAgentByAgentId(status, memberId, agentId);
    }

    @Override
    public List<Map> findAgentStatus(Long memberId) {
        return oaAgentMasMapper.findAgentStatus(memberId);
    }

    @Override
    public List<OrderAgentMas> getAppOrderAgentList(Long memberId) {
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        if(CollectionUtils.isNotEmpty(agentIds)){
            return oaAgentMasMapper.findAgentByMemberId(memberId, userIds);
        }
        return oaAgentMasMapper.findAgentByMemberId(memberId, null);
    }

    @Override
    public Map<String, Object> queryAgentList(Long memberId, String keyword, Integer levelId) {
        List<OaAgentMasListVo> agentList = null;
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        //查询userId下的客户
//        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        if (memberId != null) {
            OaAgentMasRequest params = new OaAgentMasRequest();
            params.setMemberId(memberId.toString());
            if (StringUtils.isNotBlank(keyword)) {
                if (MOBILE_REGEX.matcher(keyword).find()) {
                    params.setLinkTel(keyword);
                } else {
                    params.setAgentName(keyword);
                }
            }
            params.setLevelId(levelId);
            //设置业务员集合做数据权限
            List<Integer> userIdList = new ArrayList<>();
            for (Integer id : userIds) {
                userIdList.add(id);
            }
            params.setUserIds(userIdList);
            agentList = oaAgentMasMapper.queryAgentList(params);
            if (CollectionUtils.isNotEmpty(agentList)) {
                OaAgentRevinfo queryParam = new OaAgentRevinfo();
                List<OaAgentUserinfo> userinfoList = null;

                for (OaAgentMasListVo agentMasListVo : agentList) {
                    //取出完整地址，修改列表中的地址显示的是代理商地址
                    /*queryParam.setAgentId(agentMasListVo.getAgentId());
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
                    }*/
                    //设置联系人信息
                    userinfoList = oaAgentUserinfoMapper.findUserInfoByAgentIdAndShopId(agentMasListVo.getAgentId(), agentMasListVo.getShopId());
                    if (CollectionUtils.isNotEmpty(userinfoList)) {
                        for (OaAgentUserinfo agentUserInfo : userinfoList) {
                            agentMasListVo.setLinkName(agentUserInfo.getUserName());
                            if ("1".equals(agentUserInfo.getMainUser())) {
                                agentMasListVo.setLinkName(agentUserInfo.getUserName());
                                break;
                            }
                        }
                    }
                }
            }
            if (agentList != null && agentList.size() > 0) {
                Map<String, Object> data = new HashMap<>();
                data.put("result", agentList);
                data.put("totalNumber", agentList.size());
                return data;
            }
        }
        return null;
    }

    @Override
    public OaAgentMasBaseInfoVo getAgentBaseInfo(Integer agentId, Integer factoryId) {
        OaAgentMasBaseInfoVo agent = oaAgentMasMapper.selectAgentBaseInfoByAgentId(agentId, factoryId);
        //折扣保留两位小数
        String agentDiscount = agent.getAgentDiscount();
        if (StringUtils.isNotBlank(agentDiscount)) {
            agentDiscount = agentDiscount.substring(0, agentDiscount.indexOf(".") + 3);
        }
        try {
            String htmlContent = AppUtil.wordToHtml(agent.getContractUrl());
            agent.setHtmlContent(htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        agent.setAgentDiscount(agentDiscount);
        //处理app收货地址显示，如果有代理商创建的默认地址，就显示默认地址，如果没有就显示工厂创建地址表中的第一条
        OaFactoryInfo oaFactoryInfo = this.oaFactoryInfoMapper.findFacByFactId(factoryId);
        if (oaFactoryInfo != null) {
            //设置联系人信息
            if (agent != null) {
                List<OaAgentUserinfo> userinfoList = oaAgentUserinfoMapper.findUserInfoByAgentIdAndShopId(agent.getAgentId(), oaFactoryInfo.getShopId());
                if (CollectionUtils.isNotEmpty(userinfoList)) {
                    for (OaAgentUserinfo agentUserInfo : userinfoList) {
                        agent.setLinkName(agentUserInfo.getUserName());
                        if ("1".equals(agentUserInfo.getMainUser())) {
                            agent.setLinkName(agentUserInfo.getUserName());
                            break;
                        }
                    }
                }
            }

            OaAgentRevinfo queryParam = new OaAgentRevinfo();
            queryParam.setAgentId(agentId);
            queryParam.setSysId(oaFactoryInfo.getShopId());
            queryParam.setRevDefault("1");
            List<OaAgentRevinfo> revinfoList = this.oaAgentRevinfoMapper.findRevinfo(queryParam);//查询默认收货地址
            if (CollectionUtils.isNotEmpty(revinfoList)) {
                agent.setReceiveAddr(this.getDetailAddress(revinfoList.get(0)));
            } else {
                queryParam.setIsFact(1);
                queryParam.setRevDefault(null);
                revinfoList = this.oaAgentRevinfoMapper.findRevinfo(queryParam);//查询工厂端添加的收货地址
                if (CollectionUtils.isNotEmpty(revinfoList)) {
                    agent.setReceiveAddr(this.getDetailAddress(revinfoList.get(0)));
                } else {
                    agent.setReceiveAddr("-");
                }
            }
        }
        if (StringUtils.isBlank(agent.getReceiveAddr())) {
            agent.setReceiveAddr("-");
        }
        if (StringUtils.isBlank(agent.getAgentAddr())) {
            agent.setAgentAddr("-");
        }
        return agent;
    }

    /**
     * 获取详细地址信息xxx省xxx市xxx区xxx地址
     *
     * @param revinfo
     * @return
     */
    private String getDetailAddress(OaAgentRevinfo revinfo) {
        String procinceName = "";
        String cityName = "";
        String countyName = "";
        String province = revinfo.getRevProvince();//省
        if (StringUtils.isNotBlank(province)) {
            procinceName = this.baseAddrareaService.findAddrareaNameByCode(province);
        }
        String city = revinfo.getRevCity();//市
        if (StringUtils.isNotBlank(city)) {
            cityName = this.baseAddrareaService.findAddrareaNameByCode(city) == null ? "" : this.baseAddrareaService.findAddrareaNameByCode(city);
        }
        String county = revinfo.getRevCounty();//区
        if (StringUtils.isNotBlank(county)) {
            countyName = this.baseAddrareaService.findAddrareaNameByCode(county) == null ? "" : this.baseAddrareaService.findAddrareaNameByCode(county);
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
    public List<ActivityAgentVo> findActivityAgent(PageBounds pageBounds, Long memberId, int actId) {
        List<ActivityAgentVo> resultList = oaAgentMasMapper.findActivityAgent(pageBounds, memberId, actId);
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (ActivityAgentVo vo : resultList) {
                //如果代理商级别为空的话写入无
                if (StringUtils.isBlank(vo.getAgentLevel())) {
                    vo.setAgentLevel("无");
                }
                //设置折扣和返点已百分比形式显示
                if (StringUtils.isNotBlank(vo.getAgentDiscount())) {
                    vo.setAgentDiscount(MathUtils.parseDiscountPercent(Double.parseDouble(vo.getAgentDiscount())));
                } else {
                    vo.setAgentDiscount("");
                }
                if (StringUtils.isNotBlank(vo.getAgentReturnValue())) {
                    vo.setAgentReturnValue(MathUtils.parseDiscountPercent(Double.parseDouble(vo.getAgentReturnValue())));
                } else {
                    vo.setAgentReturnValue("");
                }
                //设置地址省市信息
                List<OaAgentRevinfo> revinfoList = this.oaAgentRevinfoMapper.findRevinfoByAgentAndShop(vo.getAgentId(), vo.getShopId());
                if (CollectionUtils.isNotEmpty(revinfoList)) {
                    OaAgentRevinfo revinfo = revinfoList.get(0);
                    String procinceName = "";
                    String cityName = "";
                    String province = revinfo.getRevProvince();//省
                    if (StringUtils.isNotBlank(province)) {
                        procinceName = this.baseAddrareaService.findAddrareaNameByCode(province);
                    }
                    String city = revinfo.getRevCity();//市
                    if (StringUtils.isNotBlank(city)) {
                        cityName = this.baseAddrareaService.findAddrareaNameByCode(city);
                    }
                    vo.setRevProvince(procinceName + cityName);
                }
            }
        }
        return resultList;
    }

    @Override
    public Map<String, Object> queryVoucherAgentList(Long memberId, String keyword, Integer levelId) {
        List<OaAgentMasListVo> agentList = null;
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        //查询userId下的客户
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        if (memberId != null) {
            OaAgentMasRequest params = new OaAgentMasRequest();
            params.setMemberId(memberId.toString());
            if (StringUtils.isNotBlank(keyword)) {
                if (MOBILE_REGEX.matcher(keyword).find()) {
                    params.setLinkTel(keyword);
                } else {
                    params.setAgentName(keyword);
                }
            }
            params.setLevelId(levelId);
            //设置业务员集合做数据权限
            List<Integer> userIdList = new ArrayList<>();
            for (Integer id : userIds) {
                userIdList.add(id);
            }
            if (CollectionUtils.isNotEmpty(agentIds)) {
                params.setUserIds(userIdList);
            }
            agentList = oaAgentMasMapper.queryAgentList(params);
            if (CollectionUtils.isNotEmpty(agentList)) {
                OaAgentRevinfo queryParam = new OaAgentRevinfo();
                List<OaAgentUserinfo> userinfoList = null;

                for (OaAgentMasListVo agentMasListVo : agentList) {
                    //取出完整地址，修改列表中的地址显示的是代理商地址
                    /*queryParam.setAgentId(agentMasListVo.getAgentId());
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
                    }*/
                    //设置联系人信息
                    userinfoList = oaAgentUserinfoMapper.findUserInfoByAgentIdAndShopId(agentMasListVo.getAgentId(), agentMasListVo.getShopId());
                    if (CollectionUtils.isNotEmpty(userinfoList)) {
                        for (OaAgentUserinfo agentUserInfo : userinfoList) {
                            agentMasListVo.setLinkName(agentUserInfo.getUserName());
                            if ("1".equals(agentUserInfo.getMainUser())) {
                                agentMasListVo.setLinkName(agentUserInfo.getUserName());
                                break;
                            }
                        }
                    }
                }
            }
            if (agentList != null && agentList.size() > 0) {
                Map<String, Object> data = new HashMap<>();
                data.put("result", agentList);
                data.put("totalNumber", agentList.size());
                return data;
            }
        }
        return null;
    }

}

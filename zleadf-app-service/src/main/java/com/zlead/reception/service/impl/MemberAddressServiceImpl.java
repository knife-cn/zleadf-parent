package com.zlead.reception.service.impl;


import com.zlead.dao.MemberAddressDao;
import com.zlead.dao.MemberDao;
import com.zlead.entity.MemberAddressEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.MemberAddressDto;
import com.zlead.entity.vo.MemberAddrVo;
import com.zlead.fplat.dao.AgentAddressMapper;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.entity.AgentAddress;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.service.AgentFacService;
import com.zlead.fplat.service.OaFactoryInfoService;
import com.zlead.reception.service.MemberAddressService;
import com.zlead.service.RegionService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.management.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service
public class MemberAddressServiceImpl implements MemberAddressService {
    @Autowired
    private MemberAddressDao memberAddressDao;

    @Autowired
    private RegionService regionService;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private AgentFacService agentFacService;

    @Autowired
    private OaFactoryInfoService oaFactoryInfoService;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    AgentAddressMapper agentAddressMapper;

    @Transactional(readOnly = true)
    public PageList<MemberAddressEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = memberAddressDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public MemberAddressEntity findById(Long id) {
        return memberAddressDao.findById(id);
    }

    public void save(MemberAddressEntity entity) {
        memberAddressDao.insert(entity);
    }

    public void update(MemberAddressEntity entity) {
        memberAddressDao.update(entity);
    }

    public void delete(Long id) {
        memberAddressDao.delete(id);
    }

    @Transactional(readOnly = true)
    public MemberAddressEntity getById(java.lang.Long id) {
        return memberAddressDao.findById(id);
    }

    @Override
    public List<String> getListById(Long memberId) {
        return memberAddressDao.getListById(memberId);
    }

    @Override
    public List<MemberAddrVo> getAppMemberAddr(Integer agentId, Long memberId) {
        List<MemberAddrVo> list = memberAddressDao.findAddrByAgentId(agentId, memberId);
        List<MemberAddrVo> MemberAddrVos = new ArrayList<>();
        for (MemberAddrVo addr : list) {
            MemberAddrVo addrVo = new MemberAddrVo();
            String provinceName = memberAddressDao.findRegionName(addr.getProvinceId());
            String cityName = memberAddressDao.findRegionName(addr.getCityId());
            String areaName = memberAddressDao.findRegionName(addr.getAreaId());
            addrVo.setAddrId(addr.getAddrId());
            addrVo.setAddress(addr.getAddress());
            addrVo.setProvinceId(provinceName==null?"":provinceName);
            addrVo.setCityId(cityName==null?"":cityName);
            addrVo.setAreaId(areaName==null?"":areaName);
            if (1 == addr.getIsFact().intValue()) {//工厂创建的地址不显示为默认地址
                addrVo.setIsDefault(0);
            } else {
                addrVo.setIsDefault(addr.getIsDefault());
            }
            addrVo.setMemberName(addr.getMemberName());
            addrVo.setPhone(addr.getPhone());
            addrVo.setAgentId(agentId);
            MemberAddrVos.add(addrVo);
        }
        return MemberAddrVos;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class},
            isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public boolean save(MemberAddrVo memberAddrVo, MemberEntity memberEntity) {
        MemberAddressEntity addressEntity = new MemberAddressEntity();
        AgentAddress agentAddress = new AgentAddress();
        MemberEntity member = memberDao.findByAgentId(memberAddrVo.getAgentId());
        Integer factId = agentFacMapper.findFacByMemberId(memberEntity.getId());
        if (member == null) {
            //添加rev_info表
            agentAddress.setRevDefault(memberAddrVo.getIsDefault().toString());
            agentAddress.setAgentId(memberAddrVo.getAgentId());
//            agentAddress.setMemberAddrId(addressEntity.getId().intValue());
            agentAddress.setRevProvince(memberAddrVo.getProvinceId());
            agentAddress.setRevCity(memberAddrVo.getCityId());
            agentAddress.setRevCounty(memberAddrVo.getAreaId());
            agentAddress.setRevName(memberAddrVo.getMemberName());
            agentAddress.setRevTel(memberAddrVo.getPhone());
            agentAddress.setIsFact("1");
            agentAddress.setRevAddr(memberAddrVo.getAddress());
            agentAddress.setSysId(memberEntity.getOwnShopid().intValue());
            int b = agentAddressMapper.insert(agentAddress);
            return b==1;
        }else{
            addressEntity.setMemberId(member.getId());
            addressEntity.setMemberName(memberAddrVo.getMemberName());
            addressEntity.setPhone(memberAddrVo.getPhone());
            addressEntity.setAddress(memberAddrVo.getAddress());
            addressEntity.setProvinceId(Long.parseLong(memberAddrVo.getProvinceId()));
            addressEntity.setCityId(Long.parseLong(memberAddrVo.getCityId()));
            addressEntity.setRegionId(Long.parseLong(memberAddrVo.getAreaId()));
            addressEntity.setIsDefault(memberAddrVo.getIsDefault());
            addressEntity.setIsFact(1);
            addressEntity.setFactId(factId);
            if (memberAddrVo.getIsDefault() == 1) {
                memberAddressDao.updateOtherIsNotDefault(member.getId(), addressEntity.getId());
            }
            int a = memberAddressDao.insert(addressEntity);
            //添加rev_info表
            agentAddress.setRevDefault(memberAddrVo.getIsDefault().toString());
            agentAddress.setAgentId(memberAddrVo.getAgentId());
            agentAddress.setMemberAddrId(addressEntity.getId().intValue());
            agentAddress.setRevProvince(memberAddrVo.getProvinceId());
            agentAddress.setRevCity(memberAddrVo.getCityId());
            agentAddress.setRevCounty(memberAddrVo.getAreaId());
            agentAddress.setRevName(memberAddrVo.getMemberName());
            agentAddress.setRevTel(memberAddrVo.getPhone());
            agentAddress.setIsFact("1");
            agentAddress.setRevAddr(memberAddrVo.getAddress());
            agentAddress.setSysId(memberEntity.getOwnShopid().intValue());
            int b = agentAddressMapper.insert(agentAddress);
            return a==1&&b==1;
        }
    }

    @Override
    public List<MemberAddressEntity> getAllAddressByMId(Long id) {
        return memberAddressDao.getAllAddressByMId(id);
    }

    /**
     * 新的代理商地址
     *
     * @param memberEntity
     * @return
     */
    @Override
    public Map<String, List<MemberAddressDto>> getAllAgentAddress(MemberEntity memberEntity) {
        Map params = new HashMap();
        params.put("memberId", memberEntity.getId());
        //获取全部的地址信息
        List<MemberAddressEntity> addressList = memberAddressDao.getAllAgentAddress(params);
        Map<String, List<MemberAddressDto>> map = new HashMap<>();
        if (addressList != null && addressList.size() >= 0) {
            for (MemberAddressEntity memberAddressEntity : addressList) {
                MemberAddressDto memberAddressDto = new MemberAddressDto();
                if (map.containsKey(memberAddressEntity.getFactId() + "")) {//map中存在此id，将数据存放当前key的map中
                    String provinceName = "";
                    String cityName = "";
                    String countyName = "";
                    //查询省的名称
                    if (memberAddressEntity.getProvinceId() != null) {
                        provinceName = regionService.getRegionName(String.valueOf(memberAddressEntity.getProvinceId()));
                        //查询市的名称
                        if (memberAddressEntity.getCityId() != null) {
                            cityName = regionService.getRegionName(String.valueOf(memberAddressEntity.getCityId()));
                            //查询县的名称
                            if (memberAddressEntity.getRegionId() != null) {
                                countyName = regionService.getRegionName(String.valueOf(memberAddressEntity.getRegionId()));
                            }
                        }
                    }
                    if (memberAddressEntity.getFactId() != 0) {//工厂地址
                        OaFactoryInfo factName = oaFactoryInfoService.findFacByFactId(memberAddressEntity.getFactId());
                        memberAddressDto.setFactName(factName.getFactName());
                    }

                    memberAddressDto.setId(memberAddressEntity.getId());
                    memberAddressDto.setMemberId(memberAddressEntity.getMemberId());
                    memberAddressDto.setMemberName(memberAddressEntity.getMemberName());
                    memberAddressDto.setFactId(memberAddressEntity.getFactId());

                    memberAddressDto.setPhone(memberAddressEntity.getPhone());
                    memberAddressDto.setAddress(memberAddressEntity.getAddress());
                    memberAddressDto.setProvinceName(provinceName);//省
                    memberAddressDto.setCityName(cityName);//市

                    memberAddressDto.setRegionName(countyName);//县
                    memberAddressDto.setIsDefault(memberAddressEntity.getIsDefault());
                    memberAddressDto.setIsFact(memberAddressEntity.getIsFact());

                    map.get(memberAddressEntity.getFactId() + "").add(memberAddressDto);
                } else {//map中不存在，新建key，用来存放数据
                    List<MemberAddressDto> memList = new ArrayList<>();
                    String provinceName = "";
                    String cityName = "";
                    String countyName = "";
                    //查询省的名称
                    if (memberAddressEntity.getProvinceId() != null) {
                        provinceName = regionService.getRegionName(String.valueOf(memberAddressEntity.getProvinceId()));
                        //查询市的名称
                        if (memberAddressEntity.getCityId() != null) {
                            cityName = regionService.getRegionName(String.valueOf(memberAddressEntity.getCityId()));
                            //查询县的名称
                            if (memberAddressEntity.getRegionId() != null) {
                                countyName = regionService.getRegionName(String.valueOf(memberAddressEntity.getRegionId()));
                            }
                        }
                    }
                    if (null != memberAddressEntity.getFactId() && memberAddressEntity.getFactId() != 0) {//工厂地址
                        OaFactoryInfo factName = oaFactoryInfoService.findFacByFactId(memberAddressEntity.getFactId());
                        memberAddressDto.setFactName(factName.getFactName());
                    } else {
                        memberAddressDto.setFactName("");
                        memberAddressEntity.setFactId(0);
                    }

                    memberAddressDto.setId(memberAddressEntity.getId());
                    memberAddressDto.setMemberId(memberAddressEntity.getMemberId());
                    memberAddressDto.setMemberName(memberAddressEntity.getMemberName());
                    memberAddressDto.setPhone(memberAddressEntity.getPhone());

                    memberAddressDto.setFactId(memberAddressEntity.getFactId());
                    memberAddressDto.setAddress(memberAddressEntity.getAddress());
                    memberAddressDto.setProvinceName(provinceName);//省
                    memberAddressDto.setCityName(cityName);//市

                    memberAddressDto.setRegionName(countyName);//县
                    memberAddressDto.setIsDefault(memberAddressEntity.getIsDefault());
                    memberAddressDto.setIsFact(memberAddressEntity.getIsFact());
                    memList.add(memberAddressDto);
                    //工厂ID  address
                    map.put(memberAddressEntity.getFactId() + "", memList);
                }
            }
        }
        return map;
    }

    /**
     * 老的代理商地址
     *
     * @param memberEntity
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllAddress(MemberEntity memberEntity) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map params = new HashMap();
        params.put("memberId", memberEntity.getId());

        //获取全部的地址信息
        List<MemberAddressEntity> addressList = memberAddressDao.getAllAddress(params);
        //获取代理商关联的工厂数
        //Integer facCount = agentFacService.countFacByAgentId(memberEntity.getAgentId());
        if (addressList != null && addressList.size() >= 0) {
            for (MemberAddressEntity memberAddressEntity : addressList) {
                Map<String, Object> map = new HashMap<>();
                //返回的信息
                map.put("id", memberAddressEntity.getId());//id
                map.put("memberId", memberAddressEntity.getMemberId());//会员ID
                map.put("memberName", memberAddressEntity.getMemberName());//会员名称
                map.put("phone", memberAddressEntity.getPhone());//电话
                map.put("provinceId", memberAddressEntity.getProvinceId());//省的id
                map.put("cityId", memberAddressEntity.getCityId());//市的id
                map.put("countyId", memberAddressEntity.getRegionId());//县的id
                map.put("detailAddress", memberAddressEntity.getAddress());//详细地址
                map.put("postalCode", memberAddressEntity.getPostcode());//邮编
                map.put("email", memberAddressEntity.getEmail());//邮箱
                map.put("isDefault", memberAddressEntity.getIsDefault());//是否为默认0：否1：是
                map.put("isFact", memberAddressEntity.getIsFact());//是否为工厂创建地址  1：是 0：否
                map.put("factId", memberAddressEntity.getFactId());//工厂主键

                String provinceName = "";
                String cityName = "";
                String countyName = "";
                //查询省的名称
                if (memberAddressEntity.getProvinceId() != null) {
                    provinceName = regionService.getRegionName(String.valueOf(memberAddressEntity.getProvinceId()));
                    //查询市的名称
                    if (memberAddressEntity.getCityId() != null) {
                        cityName = regionService.getRegionName(String.valueOf(memberAddressEntity.getCityId()));
                        //查询县的名称
                        if (memberAddressEntity.getRegionId() != null) {
                            countyName = regionService.getRegionName(String.valueOf(memberAddressEntity.getRegionId()));
                        }
                    }
                }
                map.put("provinceName", provinceName);//省的名称
                map.put("cityName", cityName);//是的名称
                map.put("countyName", countyName);//县的名称


                list.add(map);
            }
        }
        return list;
    }


    public MemberAddressEntity getFirstOfMember(Long memberId) {
        return memberAddressDao.findFirstOfMember(memberId);
    }

    // 把该用户的其他地址设置非默认
    public void updateOtherIsNotDefault(Long memberId, Long id) {
        memberAddressDao.updateOtherIsNotDefault(memberId, id);
    }


}

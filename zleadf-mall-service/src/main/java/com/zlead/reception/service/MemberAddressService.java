package com.zlead.reception.service;

import com.zlead.entity.MemberAddressEntity;

import java.util.List;
import java.util.Map;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.MemberAddressDto;
import com.zlead.entity.vo.MemberAddrVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 会员收货地址
 *
 * @author fqf
 * @date 2018-07-31 15:06:05
 */
public interface MemberAddressService {

    PageList<MemberAddressEntity> getPage(Map params, PageBounds rowBounds);

    void save(MemberAddressEntity entity);

    void update(MemberAddressEntity entity);

    void delete(Long id);

    MemberAddressEntity findById(Long id);

    //新的代理商地址
    Map<String, List<MemberAddressDto>> getAllAgentAddress(MemberEntity memberEntity);

    //老的
    List<Map<String, Object>> getAllAddress(MemberEntity memberEntity);

    MemberAddressEntity getFirstOfMember(Long memberId);

    void updateOtherIsNotDefault(Long memberId, Long id);

    MemberAddressEntity getById(java.lang.Long id);

    List<String> getListById(Long memberId);

    List<MemberAddrVo> getAppMemberAddr(Integer agentId,Long memberId);

    boolean save(MemberAddrVo memberAddrVo,MemberEntity memberEntity);

    List<MemberAddressEntity> getAllAddressByMId(Long id);
}


package com.zlead.dao;

import com.zlead.entity.MemberAddressEntity;
import com.zlead.entity.vo.MemberAddrVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 会员收货地址
 * 
 * @author fqf
 * @date 2018-07-31 15:06:05
 */
public interface MemberAddressDao {

    int insert(MemberAddressEntity entity);

    void update(MemberAddressEntity entity);

    void delete(Long id);

    PageList<MemberAddressEntity> findPage(Map params, PageBounds rowBounds);

    MemberAddressEntity findById(Long id);

    /**
     * 获取全部的地址信息
     * @param params
     * @return
     */
    List<MemberAddressEntity> getAllAddress(Map params);

    /**
     * 新地址 代理商+工厂都有
     * @param params
     * @return
     */
    List<MemberAddressEntity> getAllAgentAddress(Map params);

    /**
     * 获取默认地址
     * @param memberId
     * @return
     */
    MemberAddressEntity findFirstOfMember(@Param(value = "memberId") Long memberId);

    /**
     * 把该用户的其他地址设置非默认
     */
    void updateOtherIsNotDefault(@Param(value = "memberId") Long memberId, @Param(value = "id") Long id);

    /**
     * 获取会员所有地址
     * @param memberId
     * @return
     */
    List<String> getListById(@Param(value = "memberId") Long memberId);


    List<MemberAddrVo> findAddrById(Long memberId);

    String findRegionName(String regionId);

    List<MemberAddrVo> findAddrByAgentId(@Param(value = "agentId")Integer agentId,@Param(value = "memberId")Long memberId);

    List<MemberAddressEntity> getAllAddressByMId(Long id);

    MemberAddressEntity findAddrId();
}

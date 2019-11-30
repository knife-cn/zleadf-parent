package com.zlead.dao;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.vo.MemberAddrVo;
import com.zlead.entity.vo.MemberInfoVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
//import com.up72.zx.model.ZxMember;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

/**
 * 会员
 *
 * @author fqf
 * @date 2018-07-23 14:34:20
 */
public interface MemberDao {

    void insert(MemberEntity entity);

    void insertSelective(MemberEntity entity);

    boolean update(MemberEntity entity);

    int updateByPrimaryKey(MemberEntity entity);

    int updatevipe(MemberEntity entity);

    void delete(Long id);

    PageList<MemberEntity> findPage(Map params, PageBounds rowBounds);

    MemberEntity findById(Long id);

    MemberEntity findByPhone(String phone);

    //集合
    List<MemberEntity> getByAccountList(@Param(value = "account") String account);

    MemberEntity getByAccount(@Param(value = "account") String account);

    boolean updateById(MemberEntity entity);

    MemberEntity findByMemberId(String memberId);

    MemberEntity findByUnionId(String unionId);

    MemberEntity findByOpenId(String openId);

    MemberEntity findByUserName(String username);

    int updatepwd(MemberEntity memberentity);

    MemberEntity fininfoid(Long id);

    List<Long> getByAgentId(@Param("agentId") int agentId);

   Long getSysIdByAgentId(@Param("memberId")Long memberId);

    MemberInfoVo getAppMemberInfo(Long memberId);

    int saveMemberHeadImg(@Param("headImg")String headImg,@Param("memberId") Long memberId);

    MemberEntity findByAgentId(Integer agentId);


    List<MemberEntity> findMembersByAgentId(Integer agentId);

    MemberEntity findMemberByUserId(Integer userId);

    MemberEntity findMemberByOwnshopId(Long ownShopId);
}

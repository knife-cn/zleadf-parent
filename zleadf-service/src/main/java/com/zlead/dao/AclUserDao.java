package com.zlead.dao;

import com.zlead.entity.AclUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AclUserDao {

    AclUserEntity findUserById(Integer userId);

    AclUserEntity findLoginName(String login_name);

    AclUserEntity findUserByMemberId(Long memberId);

    int updatePwd(@Param("password") String password,@Param("userId") Integer userId);

    List<AclUserEntity> findUnitsById(Integer userId);
}

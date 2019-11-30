package com.zlead.dao;

import com.zlead.entity.dto.AclModuUserDto;

import java.util.List;

public interface AclModuleUserDao {
    /**
     * 根据userId查询对应用户模块
     * @param userId
     * @return
     */
    List<AclModuUserDto> findAclModuleByUserId(Integer userId);
}

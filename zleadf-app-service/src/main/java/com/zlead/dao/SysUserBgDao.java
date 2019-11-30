package com.zlead.dao;

import com.zlead.entity.SysUserEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 平台用户
 * 
 * @author fqf
 * @date 2018-07-19 14:20:20
 */
public interface SysUserBgDao {

    void insert(SysUserEntity entity);

    void update(SysUserEntity entity);

    void delete(Long id);

    PageList<SysUserEntity> findPage(Map params, PageBounds rowBounds);

    SysUserEntity findById(Long id);

    /**
     * 通过用户名查找后台用户
     * @param userName
     * @return
     */
    SysUserEntity findByUserName(@Param(value = "userName")String userName);
	
}

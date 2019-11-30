package com.zlead.service;

import com.zlead.entity.SysUserEntity;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 平台用户
 *
 * @author fqf
 * @date 2018-07-19 14:20:20
 */
public interface SysUserBgService {

    PageList<SysUserEntity> getPage(Map params, PageBounds rowBounds);

    void save(SysUserEntity entity);

    void update(SysUserEntity entity);

    void delete(Long id);

    SysUserEntity findById(Long id);

    SysUserEntity findByUserName(String userName);

    boolean updateSysUser(SysUserEntity sysUserEntity);
}


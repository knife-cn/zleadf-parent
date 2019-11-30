package com.zlead.service.impl;

import com.zlead.dao.SysUserBgDao;
import com.zlead.entity.SysUserEntity;
import com.zlead.service.SysUserBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
 *后台用户管理
 */
@Transactional
@Service
public class SysUserBgServiceImpl implements SysUserBgService {
    @Autowired
    private SysUserBgDao sysUserBgDao;

    @Transactional(readOnly = true)
    public PageList<SysUserEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = sysUserBgDao.findPage(params, rowBounds);
        return list;
    }

    public SysUserEntity findByUserName(String userName){
        SysUserEntity sysUserEntity = sysUserBgDao.findByUserName(userName);
        return sysUserEntity;
    }


    @Transactional(readOnly = true)
    public SysUserEntity findById(Long id) {
        return sysUserBgDao.findById(id);
    }

    public void save(SysUserEntity entity) {
        sysUserBgDao.insert(entity);
    }

    public void update(SysUserEntity entity) {
        sysUserBgDao.update(entity);
    }

    public void delete(Long id) {
        sysUserBgDao.delete(id);
    }

    /**
     * 修改店铺的信息
     */
    public boolean updateSysUser(SysUserEntity sysUserEntity){
        boolean b = true;
        try{
            //查询该后台用户
            SysUserEntity user = sysUserBgDao.findById(sysUserEntity.getId());
            if(user!=null){
                user.setUserName(sysUserEntity.getUserName());
                user.setPassword(sysUserEntity.getPassword());
                user.setRealName(sysUserEntity.getRealName());
                user.setGender(sysUserEntity.getGender());
                user.setImgPath(sysUserEntity.getImgPath());
                user.setEmail(sysUserEntity.getEmail());
                user.setMobile(sysUserEntity.getMobile());
                user.setEnabled(sysUserEntity.getEnabled());
                user.setVipType(sysUserEntity.getVipType());
                sysUserBgDao.update(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

}

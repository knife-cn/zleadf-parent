package com.zlead.service.impl;

import com.zlead.dao.SysUserBgDao;
import com.zlead.dao.MemberDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.SysUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.service.ShopBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
//import com.up72.security.shiro.SecurityHelper;
//import com.up72.sys.model.SysUser;
import com.zlead.constant.Cnst;
import com.zlead.util.ProjectProperties;
import com.zlead.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;


@Transactional
@Service
public class ShopBgServiceImpl implements ShopBgService {
    @Autowired
    private ShopDao shopDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private SysUserBgDao sysUserBgDao;

    @Transactional(readOnly = true)
    public PageList<ShopEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = shopDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public ShopEntity findById(Long id) {
        return shopDao.findById(id);
    }
    @Override
    public ShopEntity findByMemeberId(String memeberId) {
        return shopDao.findByMemeberId(memeberId);
    }

    public void save(ShopEntity entity) {
        shopDao.insert(entity);
    }

    public void update(ShopEntity entity) {
        shopDao.update(entity);
    }

    public void delete(Long id) {
        shopDao.delete(id);
    }

    /**
     * 供应商审核和代理商审核以及企业审核
     */
    public boolean auditShop(ShopEntity shopEntity,SysUserEntity userEntity){
        boolean b = true;
        try{
            if(shopEntity==null){
                return false;
            }
            //查询供应商或代理商或企业的信息
            ShopEntity shop = shopDao.findById(shopEntity.getId());
            if(shop==null){
                return false;
            }
            //查询用户信息
            MemberEntity memberEntity = memberDao.findByMemberId(shop.getMemberid());
            if(memberEntity==null){
                return  false;
            }
            shop.setStatus(shopEntity.getStatus());
            shop.setUpdateDate(new Date());
            shopDao.update(shop);
            if(shop.getStatus()==3){
                //修改用户的信息
                memberEntity.setMemberType(shop.getShopType());
                memberDao.update(memberEntity);
                //审核通过后为该供应商或代理商增加管理账户
                String sysUserName = memberEntity.getUsername(); //供应商后台登录账号为会员的userName
                String sysUserPwd = memberEntity.getPassword(); //供应商后台登录密码为会员的密码
                SysUserEntity sysUser = new SysUserEntity();
                if(shop.getShopType()==1){
                    sysUser.setRoleId(ProjectProperties.supplierRoleId); //供应商角色
                    sysUser.setUserType(2);//类型为供应商
                }else if(shop.getShopType()==3){
                    sysUser.setRoleId(ProjectProperties.agentRoleId); //代理商角色
                    sysUser.setUserType(5);//类型为代理商
                }else if(shop.getShopType()==8){
                    sysUser.setRoleId(ProjectProperties.enterpriseRoleId); //企业
                    sysUser.setUserType(6);//类型为企业
                }else{
                    sysUser.setUserType(0);//无类型
                }
                sysUser.setUserName(sysUserName);
                sysUser.setPassword(MD5Util.toMD5(sysUserPwd)); //密码
                sysUser.setRealName(shop.getShopName());
                sysUser.setGender(2);
                sysUser.setEmail(shop.getContactEmail()); //邮箱
                sysUser.setMobile(shop.getContactPhone()); //手机号
                sysUser.setEnabled(1);
                sysUser.setIsDel(0);
                sysUser.setAddTime(Cnst.getCurTime());
                if(userEntity!=null && userEntity.getRealName()!=null)
                sysUser.setAddUserName(userEntity.getRealName());
                sysUser.setAddUserId(userEntity.getId());
                sysUser.setVipType(0);
                sysUserBgDao.insert(sysUser);
                //后台用户添加成功后更新shop的信息
                shop.setMgrUserId(sysUser.getId());
                shopDao.update(shop);
            }
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

}

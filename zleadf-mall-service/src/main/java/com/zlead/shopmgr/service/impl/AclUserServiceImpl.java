package com.zlead.shopmgr.service.impl;

import com.zlead.dao.AclUserDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.shopmgr.service.AclUserService;
import com.zlead.util.ObjectUtils;
import com.zlead.util.TokenUtils;
import com.zlead.utils.AppUtil;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class AclUserServiceImpl implements AclUserService {

    @Autowired
    private AclUserDao aclUserDao;

    @Resource
    private LoginUtil loginUtil;

    @Override
    public Map appLogin(String login_name, String user_pwd, HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        try {
            if (loginUtil.isMobileDevice(request)) {
                if (ObjectUtils.isEmpty(login_name) || ObjectUtils.isEmpty(user_pwd)) {
                    map.put("message", "账号或密码为空");
                    map.put("success", false);
                    return map;
                }
                AclUserEntity aclUserEntity = aclUserDao.findLoginName(login_name);
                if (aclUserEntity == null) {
                    map.put("message", "登录用户不存在");
                    map.put("success", false);
                    return map;
                } else {
//                    String rsa_pwd = AppUtil.decrypt(user_pwd, AppUtil.privateKey);
//                    String pwdMd5 = MD5Util.toMD5(rsa_pwd);
                      String pwdMd5 = MD5Util.toMD5(user_pwd);
                    if (pwdMd5.equals(aclUserEntity.getPassword())) {
                        aclUserEntity = aclUserDao.findUserById(aclUserEntity.getUserId());
                        map.put("success", true);
                        map.put("message", "登录成功");
                        String token = TokenUtils.getToken();
                        loginUtil.setAppLoginUser(token, aclUserEntity);//将用户zxMember信息存入缓存中
                        data.put("token", token);
                        data.put("userId", aclUserEntity.getUserId());
                        data.put("username", aclUserEntity.getUsername());
                        //用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺 8企业
                        map.put("data", data);
                        return map;
                    } else {
                        map.put("message", "密码错误");
                        map.put("success", false);
                        return map;
                    }
                }
            } else {
                map.put("message", "不是IOS或安卓用户");
                map.put("success", false);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public AclUserEntity findUserByMemberId(Long memberId) {
        return aclUserDao.findUserByMemberId(memberId);
    }

    @Override
    public int updatepwd(String password,Integer userId) {
        return aclUserDao.updatePwd(password,userId);
    }

}

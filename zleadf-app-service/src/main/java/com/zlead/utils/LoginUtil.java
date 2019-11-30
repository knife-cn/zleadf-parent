package com.zlead.utils;

import com.puqian.util.RedisCacheClient;
import com.zlead.dao.MemberDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//import com.up72.zx.model.ZxMember;

/**
 *
 */
//@Repository
@Component
public class LoginUtil {

    public static final String sessionMember = "LOGIN_MEMBERSESSION";
    public static final String sessionUser = "LOGIN_UserSESSION";

    //@Autowired
    //private RedisCacheClient redisCacheClient;

    @Autowired
    private RedisCacheClient redisCacheClient;

    @Autowired
    private MemberDao memberDao;

    public static LoginUtil newsInstanse() {
        return new LoginUtil();
    }

    // 管理用户登陆登录
    public void setLoginSysUser(HttpServletRequest request, SysUserEntity user) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        String key = request.getSession().getId() + "_" + sessionUser;
        redisCacheClient.set(key, user, (long) 24 * 60 * 60); // 1天后过期
    }

    // 获取登录管理用户
    public SysUserEntity getLoginSysUser(HttpServletRequest request) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        SysUserEntity user = redisCacheClient.get(request.getSession().getId() + "_" + sessionUser);
        return user;
    }

    // 退出登录
    public void outLoginUser(HttpServletRequest request) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        redisCacheClient.delete(request.getSession().getId() + "_" + sessionUser);
    }

    // 退出登录
    public void outLoginMember(HttpServletRequest request) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        redisCacheClient.delete(request.getSession().getId() + "_" + sessionMember);
    }

    // App退出登录
    public void outLoginMember(String token) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        redisCacheClient.delete(token + "_" + sessionMember);
    }

    // 登录
    public void setLoginMember(HttpServletRequest request, MemberEntity member) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        String key = request.getSession().getId() + "_" + sessionMember;
        redisCacheClient.set(key, member, (long) 24 * 60 * 60); // 1天后过期
    }

    // 获取登录用户
    public MemberEntity getLoginMember(HttpServletRequest request) {
        //RedisCacheClient redis = (RedisCachseClient) ApplicationContextHolder.getBean("redisCacheClient");
        MemberEntity member = null;
        member = redisCacheClient.get(request.getSession().getId() + "_" + sessionMember);
        return member;
    }
    
    // 获取登录用户
    public MemberEntity getAppLoginMember(HttpServletRequest request) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        MemberEntity member = null;
        if (isMobileDevice(request)) {
            String token = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(token)) {
                AclUserEntity user = redisCacheClient.get(token + "_" + sessionMember);
                if(user!=null){
                    member = memberDao.findMemberByUserId(user.getUserId());
                }
            }
        }
        return member;
    }

/*    public static String createUserToken(Long id) {
        return getRandomString(16) + id + new Date().getTime();
    }


    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);//[0,62)
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }*/

    // 是否店铺
    public static boolean isShop(MemberEntity member) {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        //MemberEntity member = redisCacheClient.get(request.getSession().getId() + "_" + sessionMember);
        if (member != null && member.getMemberType() == 6)
            return true;
        else
            return false;

//        return member;
    }

    /**
     * android : 所有android设备
     * mac os : iphone ipad
     * windows phone:Nokia等windows系统的手机
     */
    public boolean isMobileDevice(String requestHeader) {
        String[] deviceArray = new String[]{"android", "ios", "windows phone"};
        if (requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for (int i = 0; i < deviceArray.length; i++) {
            if (requestHeader.contains(deviceArray[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean isMobileDevice(HttpServletRequest request) {
        return isMobileDevice(request.getHeader("user-agent"));
    }

    public void setAppLoginMember(String token, MemberEntity zxMember) {
        redisCacheClient.set(token+"_"+sessionMember, zxMember, (long) 24 * 60 * 60); // 1天后过期
    }

    public void setAppLoginUser(String token, AclUserEntity aclUserEntity) {
        redisCacheClient.set(token+"_"+sessionMember, aclUserEntity, (long) 24 * 60 * 60); // 1天后过期
    }

    public AclUserEntity getAppLoginUser(String token) {
        return redisCacheClient.get(token + "_" + sessionMember);
    }
    
    public MemberEntity getAppLoginMember(String token) {
        return redisCacheClient.get(token + "_" + sessionMember);
    }
    public void setMsg(String Phone, String randNum) {
        redisCacheClient.set(Phone, randNum, (long) 10 * 60); // 10分钟后过期
    }
    public String getMsg(String Phone) {
       return redisCacheClient.get(Phone); // 1天后过期
    }

    public String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }
}

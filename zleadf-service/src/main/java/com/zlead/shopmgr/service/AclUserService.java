package com.zlead.shopmgr.service;

import com.zlead.entity.AclUserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AclUserService {

    Map appLogin(String login_name, String user_pwd, HttpServletRequest request);

    AclUserEntity findUserByMemberId(Long memberId);

    int updatepwd(String password, Integer userId);
}
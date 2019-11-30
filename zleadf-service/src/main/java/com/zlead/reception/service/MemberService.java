package com.zlead.reception.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.vo.MemberInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//import com.zlead.model.ZxMember;

/**
 * 登录注册接口
 */
public interface MemberService {
    /**
     * 注册接口
     * @param phone
     * @param passWord
     * @param
     * @param
     * @return
     */
    boolean saveMember(String phone, String passWord);

    
    /**
     * 注册接口
     * @param nickname
     * @param passWord
     * @param openid
     * @param unionid
     * @return
     */
    boolean saveMemberByNickName(String nickname, String passWord,String openid,String unionid,String headImg,String provinceId,String cityId);
    /**
     * 通过电话查询用户
     * @param phone
     * @return
     */
    MemberEntity findByPhone(String phone);

    //以集合的方式查询单个数据
    MemberEntity getByAccount(String account);

    /**
     * 登录
     * @param account
     * @param password
     * @param request
     * @return
     */
    Map login(String account, String password, HttpServletRequest request);

    Map loginGn(String account, String password, HttpServletRequest request);



    /**
     * app登录接口
     * @param account
     * @param password
     * @return
     */
    Map appLogin(String account, String password,HttpServletRequest request);

    /**
     * 获取登录用户的信息
     * @param memberEntity
     * @return
     */
    Map<String,Object> memebrInfo(MemberEntity memberEntity);

    MemberEntity findById(Long id);
    
    /**
     * 通过登陆名查询用户
     * @param username
     * @return
     */
    MemberEntity findByUserName(String username);

    /**
     * 通过微信的unionid查询用户
     * @param unionid
     * @return
     */
    MemberEntity findByUnionId(String unionid);

    MemberEntity findByOpenId(String openId);


    /**
     * 修改用户
     * @param member
     * @return
     */
    boolean updateMember(MemberEntity member);
    
    boolean updateById(MemberEntity member);

    //修改密码
    int updatepwd(MemberEntity memberentity);
    //修改用户
    int updatevipe(MemberEntity memberentity);
    
	 /**
	  * 登陆
	  * */
	 Map logout(String account,  HttpServletRequest request);

    MemberEntity fininfoid(Long id);


    List<Long> getByAgentId(int agentId);

    /**
     *
     * 短信验证
     */

    String msg(String phone);

    String msgAPI(String phone);


    String msgIs(String phone);


    String msgIsApi(String phone);


    Map appLogout(HttpServletRequest request);

    MemberInfoVo getAppMemberInfo(Long memberId);

    int saveMemberHeadImg(String headImg,Long memberId);

}

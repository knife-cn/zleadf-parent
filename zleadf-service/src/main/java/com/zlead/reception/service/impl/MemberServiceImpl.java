package com.zlead.reception.service.impl;

import com.zlead.dao.MemberDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.entity.vo.MemberInfoVo;
import com.zlead.reception.service.MemberAcctService;
import com.zlead.reception.service.MemberService;
import com.zlead.service.RegionService;
import com.zlead.util.TokenUtils;
import com.zlead.utils.*;
import com.zlead.util.ObjectUtils;
import com.zlead.constant.Cnst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private RegionService regionService;

    @Autowired
    private MemberAcctService MemberAcctService;
    
    @Resource
    private LoginUtil loginUtil;


    private static final String appid = "factory";
    private static final String appsecret = "qzed+suj4+hzudl9zrqw9q==";



    @Override
    public boolean saveMember(String phone, String passWord) {
        // TODO Auto-generated method stub
//        Region rProvince=  regionService.getByName(provinceId);
//        Region rCity=regionService.getRegion(rProvince.getId(), cityId);

        boolean b = true;
        String strPasswd=passWord;
        /*r=regionService.getRegionName(provinceId);*/
        if(passWord!=null && passWord.length()>6){
    		strPasswd=passWord.substring(passWord.length()-6);
    	}

        try{
            //添加member信息
            MemberEntity member =new MemberEntity();
            member.setMemberId(Cnst.getFixLengthString(15)); // 先设置为随机数，后面会修改为跟ID保持一致
            member.setAccount(Cnst.ZERO);
            member.setUsername("u_" + phone); // 设置为手机号，后面会改回来
            member.setPassword(MD5Util.toMD5(strPasswd));
            member.setNickName("n_" + phone); // 先设置为手机号，后面会改回来
            member.setGender(Cnst.MemberCnst.GENDER_SECRET);
            member.setIfPhone(1);
            member.setPhone(phone);
            member.setIsDelete(0);

            member.setIsStaff(0);
            member.setIsSalesmen(0);

            member.setIsDisable(0);
//            member.setProvinceId(Long.valueOf(rProvince.getRegionCode()));
//            member.setCityId(Long.valueOf(rCity.getRegionCode()));
            member.setPoint(0L);
            member.setMemberType(0);
            member.setSystemId(String.valueOf(1));
            member.setCreateTime(new Date());
            member.setStatus(1);
            memberDao.insert(member);

            Long id = member.getId();
            member.setMemberId(id + "");
            member.setUsername("u_" + id);
            member.setNickName("n_" + id);
            memberDao.update(member);
            System.out.println(member.getId());

            //生成账户
            MemberAcctService.saveMemberAcct(member, 0);
            MemberAcctService.saveMemberAcct(member, 1);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }
    
    @Override
    public boolean saveMemberByNickName(String nickname, String passWord,String openid,String unionid,String headImg,String provinceId,String cityId) {
 

        boolean b = true;
        /*r=regionService.getRegionName(provinceId);*/

        try{
            //添加member信息
            MemberEntity member =new MemberEntity();
            member.setMemberId(Cnst.getFixLengthString(15)); // 先设置为随机数，后面会修改为跟ID保持一致
            member.setAccount(Cnst.ZERO);
            MemberEntity byUserName = memberDao.findByUserName(nickname);
            if (byUserName!=null){
                String temp = UUID.randomUUID().toString().substring(0, 2);
                nickname = nickname +temp;
            }
            member.setUsername(nickname); // 设置为手机号，后面会改回来
            member.setPassword(MD5Util.toMD5(nickname));
            member.setOpenId(openid);
            member.setWxUnionId(unionid);
            member.setHeadImg(headImg);
            member.setNickName("n_" + nickname); // 先设置为手机号，后面会改回来
            member.setGender(Cnst.MemberCnst.GENDER_SECRET);
            member.setIfPhone(0);
            member.setIsDelete(0);
            long provinceCode = 0L;
            if (provinceId!=""&&provinceId!=null){
                provinceCode = Long.parseLong(provinceId);
            }
            member.setProvinceId(provinceCode);
            long cityCode = 0L;
            if (cityId!=""&&cityId!=null){
                cityCode = Long.parseLong(cityId);
            }
            member.setCityId(cityCode);

            member.setIsStaff(0);
            member.setIsSalesmen(0);

//            member.setIsDisable(0);
//            member.setProvinceId(Long.valueOf(rProvince.getRegionCode()));
//            member.setCityId(Long.valueOf(rCity.getRegionCode()));
            member.setPoint(0L);
            member.setMemberType(0);
            member.setSystemId(String.valueOf(1));
            member.setCreateTime(new Date());
            member.setStatus(1);
            member.setIsDisable(0);
            memberDao.insert(member);

//            Long id = member.getId();
//            member.setMemberId(id + "");
//            member.setUsername("u_" + id);
//            member.setNickName("n_" + id);
//            memberDao.update(member);
            System.out.println(member.getId());

            //生成账户
            MemberAcctService.saveMemberAcct(member, 0);
            MemberAcctService.saveMemberAcct(member, 1);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    /**
     * 通过电话查询用户
     * @param phone
     * @return
     */
    @Override
    public MemberEntity findByPhone(String phone){
        return  memberDao.findByPhone(phone);

    }
    
    /**
     * 通过登陆名查询用户
     * @param username
     * @return
     */
    @Override
    public MemberEntity findByUserName(String username){
        return  memberDao.findByUserName(username) ;

    }
    /**
     * 通过登陆名查询用户
     * @param
     * @return
     */
    @Override
    public MemberEntity findByUnionId(String unionId){
        return  memberDao.findByUnionId(unionId);

    }

    @Override
    public MemberEntity findByOpenId(String openId){
        return  memberDao.findByOpenId(openId);

    }

    @Override
    public MemberEntity findById(Long id){
        return  memberDao.findById(id);

    }

    /**
     * 登陆
     * */
    public Map login(String account, String password, HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        try {
            if (ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(password)) {
                map.put("message", "账号或密码为空");
                map.put("success", false);
                return map;
            }
            MemberEntity zxMember = getByAccount(account);
            if (zxMember == null) {
            	System.out.println("memberService.login 2: 登录用户不存在 "+account);
                map.put("message", "登录用户不存在");
                map.put("success", false);
                return map;

            }else if(zxMember.getMemberType().toString().equals("6")) {
                System.out.println("memberService.login 2: 登录用户权限不足 "+account);
                map.put("message", "登录用户权限不足");
                map.put("success", false);
                return map;
            }else {

                String pwdMd5 = MD5Util.toMD5(password);
                System.out.println("password:"+password);
                System.out.println("pwdMd5:"+pwdMd5);
                
                System.out.println("zxMember.getPassword():"+zxMember.getPassword());
                if (pwdMd5.equals(zxMember.getPassword())) {
                    zxMember = memberDao.findById(zxMember.getId());
                    map.put("success", true);
                    map.put("message", "登录成功");
//                    String returnurl=request.getParameter("returnurl");
//                    System.out.println("returnurl:"+returnurl);
//                    if(returnurl!=null){
//                    	map.put("returnurl", returnurl);
////                    	data.put("returnurl", returnurl);
//                    }
                    loginUtil.setLoginMember(request, zxMember);//将用户zxMember信息存入缓存中
                    data.put("memberId", zxMember.getId());
                    data.put("memberType", zxMember.getMemberType());
                    data.put("username",zxMember.getUsername());
                    data.put("headImg",zxMember.getHeadImg());
                    //用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺 8企业
                    map.put("data", data);
                    return map;
                } else {
                    map.put("message", "密码错误");
                    map.put("success", false);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 登陆
     * */
    public Map loginGn(String account, String password, HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        try {
            if (ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(password)) {
                map.put("message", "账号或密码为空");
                map.put("success", false);
                return map;
            }
            MemberEntity zxMember = getByAccount(account);
            if (zxMember == null) {
                System.out.println("memberService.login 2: 登录用户不存在 "+account);
                map.put("message", "登录用户不存在");
                map.put("success", false);
                return map;
            } else {
//                String pwdMd5 = MD5Util.toMD5(password);
//                System.out.println("password:"+password);
//                System.out.println("pwdMd5:"+pwdMd5);
//
//                System.out.println("zxMember.getPassword():"+zxMember.getPassword());
//                if (pwdMd5.equals(zxMember.getPassword())) {
//                    zxMember = memberDao.findById(zxMember.getId());
                    map.put("success", true);
                    map.put("message", "登录成功");
//                    String returnurl=request.getParameter("returnurl");
//                    System.out.println("returnurl:"+returnurl);
//                    if(returnurl!=null){
//                    	map.put("returnurl", returnurl);
////                    	data.put("returnurl", returnurl);
//                    }
//                    loginUtil.setLoginMember(request, zxMember);//将用户zxMember信息存入缓存中
                    data.put("memberId", zxMember.getId());
                    data.put("memberType", zxMember.getMemberType());
                    data.put("username",zxMember.getUsername());
                    data.put("headImg",zxMember.getHeadImg());
                    data.put("zxMember",zxMember);
                    //用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺 8企业
                    map.put("data", data);
                    return map;
//                } else {
//                    map.put("message", "密码错误");
//                    map.put("success", false);
//                    return map;
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public Map loginGn2(String account, String password, HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        try {
            if (ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(password)) {
                map.put("message", "账号或密码为空");
                map.put("success", false);
                return map;
            }
            MemberEntity zxMember = getByAccount(account);
            if (zxMember == null) {
                System.out.println("memberService.login 2: 登录用户不存在 "+account);
                map.put("message", "登录用户不存在");
                map.put("success", false);
                return map;
            } else {
                String pwdMd5 = MD5Util.toMD5(password);
                System.out.println("password:"+password);
                System.out.println("pwdMd5:"+pwdMd5);

                System.out.println("zxMember.getPassword():"+zxMember.getPassword());
                if (pwdMd5.equals(zxMember.getPassword())) {
                    zxMember = memberDao.findById(zxMember.getId());
                    map.put("success", true);
                    map.put("message", "登录成功");
//                    String returnurl=request.getParameter("returnurl");
//                    System.out.println("returnurl:"+returnurl);
//                    if(returnurl!=null){
//                    	map.put("returnurl", returnurl);
////                    	data.put("returnurl", returnurl);
//                    }
//                    loginUtil.setLoginMember(request, zxMember);//将用户zxMember信息存入缓存中
                    data.put("memberId", zxMember.getId());
                    data.put("memberType", zxMember.getMemberType());
                    data.put("username",zxMember.getUsername());
                    data.put("headImg",zxMember.getHeadImg());
                    data.put("zxMember",zxMember);
                    //用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺 8企业
                    map.put("data", data);
                    return map;
                } else {
                    map.put("message", "密码错误");
                    map.put("success", false);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }




    /**
     * app登录接口
     * @param account
     * @param password
     * @return
     */
    public Map appLogin(String account, String password,HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        try {
            if(loginUtil.isMobileDevice(request)){
                if (ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(password)) {
                    map.put("message", "账号或密码为空");
                    map.put("success", false);
                    return map;
                }
                MemberEntity zxMember = getByAccount(account);
                if (zxMember == null) {
                    System.out.println("memberService.login 2: 登录用户不存在 " + account);
                    map.put("message", "登录用户不存在");
                    map.put("success", false);
                    return map;
                } else {
                    if(loginUtil.isShop(zxMember)){
                        String pwdMd5 = MD5Util.toMD5(password);
                        if (pwdMd5.equals(zxMember.getPassword())) {
                            zxMember = memberDao.findById(zxMember.getId());
                            map.put("success", true);
                            map.put("message", "登录成功");
                            String token = TokenUtils.getToken();
                            loginUtil.setAppLoginMember(token, zxMember);//将用户zxMember信息存入缓存中
                            data.put("token",token);
                            data.put("memberId", zxMember.getId());
                            data.put("username", zxMember.getUsername());
                            //用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺 8企业
                            map.put("data", data);
                            return map;
                        } else {
                            map.put("message", "密码错误");
                            map.put("success", false);
                            return map;
                        }
                    }else{
                        map.put("message","不是工厂用户");
                        map.put("success", false);
                        return map;
                    }
                }
            }else{
                map.put("message","不是IOS或安卓用户");
                map.put("success", false);
                return map;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //以集合的方式查询单个数据
    @Override
    public MemberEntity getByAccount(String account) {
        List<MemberEntity> list = memberDao.getByAccountList(account);
        MemberEntity member =null;
        if(list!=null&&list.size()>0){
            member = new MemberEntity();
            member = list.get(0);
        }
        return member;
    }

    /**
     * 查询登录人的个人的信息
     */
    public Map<String,Object> memebrInfo(MemberEntity memberEntity){
        Map<String, Object> map = new HashMap<>();
        memberEntity =memberDao.fininfoid(memberEntity.getId());
        if(memberEntity!=null){
        try{
            //查询个人所在的省市区
            String provinceName =  "";
            if(memberEntity!=null && memberEntity.getProvinceId()!=null){
                provinceName = regionService.getRegionName(String.valueOf(memberEntity.getProvinceId()));
            }
            String cityName = "";
            if(memberEntity.getCityId()!=null){
                cityName = regionService.getRegionName(String.valueOf(memberEntity.getCityId()));
            }
            String regionName = "";
            if(memberEntity.getRegionId()!=null){
                regionName = regionService.getRegionName(String.valueOf(memberEntity.getRegionId()));
            }
            ShopEntity shop = shopDao.findEnterprise(memberEntity.getOwnShopid());
            map.put("member",memberEntity);
            map.put("provinceName",provinceName);
            map.put("cityName",cityName);
            map.put("regionName",regionName);
            map.put("shopName",shop!=null?shop.getShopName():"");
            return map;
        }catch (Exception e) {
            e.printStackTrace();
        }
        }
        return map;
    }
    
    //以集合的方式查询单个数据
    @Override
    public boolean updateMember(MemberEntity member) {
       boolean b = memberDao.update(member); 
       
        return b;
    }

    
    //以集合的方式查询单个数据
    @Override
    public boolean updateById(MemberEntity member) {
       boolean b = memberDao.updateById(member);
        return b;
    }
    
	 /**
	 * 退出
	 * */
	 public Map logout(String account, HttpServletRequest request) {
	        Map map = new HashMap();
	        Map data = new HashMap();
	        try {
	            if (ObjectUtils.isEmpty(account) ) {
	            	map.put("message", "账号为空");
	            	map.put("success", false);
	                return map;
	            }
	            //IZxMemberService zxMemberService = (IZxMemberService) ApplicationContextHolder.getBean("zxMemberService");
	            MemberEntity memberEntity = memberDao.getByAccount(account);
	            if (memberEntity == null) {
	            	map.put("message", "登录用户不存在");
	            	map.put("success", false);
	                return map;
	            } else {
	            	loginUtil.outLoginMember(request); 
	            	loginUtil.setLoginMember(request, null); 
	               // String pwdMd5 = MD5Util.toMD5(password);
	               // if (pwdMd5.equals(zxMember.getPassword())) {
//	                    LoginUtil.bindZxMemberOpenId(request, zxMember); // 绑定微信
//	                    zxMember = zxMemberService.getById(zxMember.getId());
	                    map.put("success", true);
	                    map.put("message", "退出成功");
//	                    LoginUtil.setLoginMember(request, zxMember);//将用户zxMember信息存入缓存中
//	                    String backUrl = request.getParameter("urlBack");
	                    data.put("memberId", null);
//	                     if (ToolsUtils.isNotBlank(urlBack)) {
//	                        RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
//	                         String url = redis.getAndDel(urlBack);//根据请求url获取并删除缓存的跳转地址
//	                         if (ToolsUtils.isNotBlank(url)) {
//	                        	 data.put("backUrl", url);
//	                        }
//	                     }
	                     map.put("data", data);
	                    return map;
//	                } else {
//	                	map.put("message", "密码错误");
//		            	map.put("success", false);
//	                    return map;
//	                }
	            }
	        } catch (Exception e) {
	        	
	        }
	        return map;
	    }

public int updatepwd(MemberEntity memberentity){
	     int i=memberDao.updatepwd(memberentity);
	     return i;
}

    //修改用户
    public int updatevipe(MemberEntity member) {
        int b = memberDao.updatevipe(member);
        return b;
    }

  public MemberEntity fininfoid(Long id){
	     return memberDao.fininfoid(id);
  }

    public  List<Long> getByAgentId(int agentId){

	    return memberDao.getByAgentId(agentId);

    }

    public String msg(String phone) {
        String randNum = RandNumIs.createRandNum();
        System.out.println("验证码是：" + randNum);
        String timestamp = SendTelMsgUtils.getTimestamp();
        loginUtil.setMsg(phone, randNum);//将验证码信息存入缓存中 10分钟后过期
        String result = SendTelMsgUtils.sendMsgMobile(phone, randNum);
        System.out.println(result);
        return result;

    }

    public String msgIs(String phone) {
        String msgs = loginUtil.getMsg(phone);
        return msgs;
    }
    public String msgIsApi(String phone) {
        String phones=phone+"APP";
        String msgs = loginUtil.getMsg(phones);
        return msgs;
    }

    @Override
    public Map appLogout(HttpServletRequest request) {
        Map map = new HashMap();
        Map data = new HashMap();
        String token = loginUtil.getToken(request);
        try {
            AclUserEntity appLoginUser = loginUtil.getAppLoginUser(token);
                if(token!=null&&appLoginUser!=null){
                    MemberEntity appLoginMember = memberDao.findMemberByUserId(appLoginUser.getUserId());
                    loginUtil.outLoginMember(token);
                    map.put("success", true);
                    map.put("message", "退出成功");
                    data.put("memberId", appLoginMember.getId());
                    map.put("data", data);
                    return map;
                }else{
                    map.put("success", false);
                    map.put("message", "退出失败");
                    data.put("memberId", null);
                    map.put("data", data);
                    return map;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public MemberInfoVo getAppMemberInfo(Long memberId) {
        return memberDao.getAppMemberInfo(memberId);
    }

    @Override
    public int saveMemberHeadImg(String headImg,Long memberId) {
        return memberDao.saveMemberHeadImg(headImg,memberId);
    }

    public String msgAPI(String phone) {
        String randNum = RandNum.createRandNum();
        System.out.println("验证码是：" + randNum);
        String phones=phone+"APP";
        String timestamp = SendTelMsgUtils.getTimestamp();
        loginUtil.setMsg(phones, randNum);//将验证码信息存入缓存中 10分钟后过期
        String result = SendTelMsgUtils.sendMsgMobile(phone, randNum);
        System.out.println(result);
        return result;

    }



}

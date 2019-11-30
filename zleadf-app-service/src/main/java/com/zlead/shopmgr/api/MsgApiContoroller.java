/**
 * @program: zleadf-parent
 * @description:短信验证APP
 * @author: ytchen
 * @create: 2019-02-28 14:17
 **/
package com.zlead.shopmgr.api;

import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.MsgEntity;
import com.zlead.reception.service.MemberService;
import com.zlead.shopmgr.service.AclUserService;
import com.zlead.util.JsonResult;
import com.zlead.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录注册接口
 */
@RestController
@RequestMapping("/api/api")
public class MsgApiContoroller {
    @Resource
    private MemberService memberService;

    @Resource
    private AclUserService aclUserService;
    //找回密码 短信验证
    @RequestMapping(value = "msg")
    public JsonResult msg(HttpServletRequest request,String phone){
        JsonResult jsonResult = null;
        //查询手机有没有被注册
        MemberEntity member = memberService.findByPhone(phone);
        if(member!=null){
            String result =memberService.msgAPI(phone);
            jsonResult =  new JsonResult(1,result,"",true);
            return jsonResult;
        }else{
            jsonResult =  new JsonResult(2,"该手机号未注册","",false);
        }
        return jsonResult;
    }



    //找回密码 短信验证
    @RequestMapping(value = "msgcode",method = RequestMethod.POST)
    public JsonResult msgcode(@RequestBody MsgEntity msg){
        JsonResult jsonResult = null;
        MemberEntity member = memberService.findByPhone(msg.getPhone());
        if(member!=null){
            String result =memberService.msgIsApi(msg.getPhone());
            if(StringUtils.isEmpty(result)){
                jsonResult =  new JsonResult(2,"验证码已过期","",false);
            }else{
                if(result.equals(msg.getMsg())){
                    jsonResult =  new JsonResult(1,"验证完成","",true);
                }else{
                    jsonResult =  new JsonResult(2,"验证码不正确","",false);
                }
            }
        }else {
            jsonResult =  new JsonResult(2,"该手机号未注册","",false);
        }
        return jsonResult;
    }


    @RequestMapping(value = "updatepwd",method =RequestMethod.POST)
    public JsonResult updatepwd(HttpServletRequest request,@RequestBody MsgEntity msg){
        JsonResult jsonResult = null;
        MemberEntity member = memberService.findByPhone(msg.getPhone());
        if(member!=null){
            String pwd=msg.getPassword();
            String pawt= MD5Util.toMD5(pwd);
            member.setPassword(pawt);
            int i=memberService.updatepwd(member);
            AclUserEntity aclUser = aclUserService.findUserByMemberId(member.getId());
            int b = aclUserService.updatepwd(member.getPassword(),aclUser.getUserId());
            if (i != 0 && b!=0) {
                jsonResult =  new JsonResult(1,"密码修改成功","",true);
            }else {
                jsonResult =  new JsonResult(2,"密码修改失败","",false);
            }
        }else {
            jsonResult =  new JsonResult(2,"该手机号未注册","",false);
        }
        return jsonResult;
    }


}
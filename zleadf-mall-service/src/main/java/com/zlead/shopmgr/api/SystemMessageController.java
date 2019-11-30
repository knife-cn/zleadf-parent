package com.zlead.shopmgr.api;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.vo.SysMsgListVO;
import com.zlead.shopmgr.service.SystemMessageService;
import com.zlead.util.JsonResult;
import com.zlead.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/shopmgr")
public class SystemMessageController {

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private SystemMessageService systemMessageService;

    /**
     * 查询当前登录用户是否有未读的系统消息
     * @param request
     * @return
     */
    @RequestMapping("/hasNotReadSysMsg")
    public JsonResult hasNotReadSysMsg(HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        }else{
            Integer hasNotReadSysMsg = systemMessageService.hasNotReadSysMsg(member.getId());
            jsonResult = new JsonResult(1, "是否有未读的系统消息", hasNotReadSysMsg, true);
        }
        return jsonResult;
    }

    /**
     * 查询当前登录用户的未读系统消息列表
     * @param request
     * @return
     */
    @RequestMapping("/notReadSysMsgList")
    public JsonResult notReadSysMsgList(HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        }else{
            List<SysMsgListVO> sysMsgList = systemMessageService.notReadSysMsgList(member.getId());
            jsonResult = new JsonResult(1, "未读消息列表", sysMsgList, true);
        }
        return jsonResult;
    }

    /**
     * 标记当前登录用的未读系统消息已读
     * @param sysMsg
     * @param request
     * @return
     */
    @RequestMapping(value = "/tagSysMsg", method = RequestMethod.POST)
    public JsonResult tagSysMsg(@RequestBody SysMsgListVO sysMsg,
                                 HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        }else{
            Integer result = systemMessageService.tagSysMsg(member.getId(), sysMsg.getMsgId());
            jsonResult = new JsonResult(1, "标记系统消息已读", result, true);
        }
        return jsonResult;
    }

}

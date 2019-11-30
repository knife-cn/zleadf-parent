package com.zlead.member.controller;

import com.zlead.common.PageResponse;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.service.SysMessageService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
@Controller
@RequestMapping("/zlead/sys")
public class SystemController {

    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private LoginUtil loginUtil;

    @RequestMapping(value = "/message/notice/{pageCurrent}/{pageSize}/{status}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult findByPageSysMessage(HttpServletRequest request, @PathVariable("pageCurrent") int pageCurrent,
                                           @PathVariable("pageSize") int pageSize,@PathVariable("status") int status){
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if(null == memberEntity){
            return new JsonResult(2,"找不到用户","",false);
        }
        if(memberEntity.getAgentId()!=null){
        	PageResponse pageResponse = sysMessageService.findByPage(memberEntity.getAgentId().intValue(),status,
                new PageBounds(pageCurrent,pageSize));
        	if(null == pageResponse.getData() || null==pageResponse || pageResponse.getCount()<1){
        		return new JsonResult(1,"系统消息","无系统消息",true);
        	}else
        		return new JsonResult(1,"系统消息",pageResponse,true);
        }else{
        	return new JsonResult(1,"系统消息","无系统消息",true);
        }
    }

    @RequestMapping(value = "/message/update/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateMessage(HttpServletRequest request,@PathVariable("id") int id){
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if(null == memberEntity){
            return new JsonResult(2,"找不到用户","",false);
        }
        sysMessageService.updateMessage(id,memberEntity.getAgentId().intValue());
        return new JsonResult(1,"更新成功","",true);
    }
}

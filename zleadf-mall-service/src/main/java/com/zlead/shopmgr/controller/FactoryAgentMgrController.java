package com.zlead.shopmgr.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.shopmgr.service.FactoryAgentService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zlead/shopmgr")
public class FactoryAgentMgrController {

    @Resource
    private FactoryAgentService factoryAgentService;

    @Resource
    private LoginUtil loginUtil;

    @RequestMapping(value = "/factoryAgent")
    @ResponseBody
    public JsonResult factoryAgentList(@RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                      @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                      HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "未登录", false);
        }else{
            PageList<OaAgentMas> agentList = factoryAgentService.getFactoryAgentList(Integer.parseInt(pageNum),Integer.parseInt(pageSize),member.getId());
            if (agentList != null && agentList.size() > 0) {
                Page<OaAgentMas> page = new Page<>(agentList, agentList.getPagination());
                jsonResult = new JsonResult(1, "列表信息", page, true);
            } else {
                jsonResult = new JsonResult(2, "无列表信息", false);
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/setAgentStatus")
    @ResponseBody
    public JsonResult setAgentStatus(@RequestParam(value = "status") @Validated @NotNull String status,
                                       @RequestParam(value = "agentId") @Validated @NotNull Long agentId,
                                       HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "未登录", false);
        }else {
            try {
                factoryAgentService.setAgentStatus(status, member.getId(), agentId);
                jsonResult = new JsonResult(1, "更新成功", true);
            } catch(Exception ex){
                jsonResult = new JsonResult(2, "更新失败", false);
                ex.printStackTrace();
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/queryAgentStatus")
    @ResponseBody
    public JsonResult queryAgentStatus(HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "未登录", false);
        }else {
                List<Map> agentStatus = factoryAgentService.findAgentStatus(member.getId());
                if(agentStatus!=null&&agentStatus.size()>0){
                    jsonResult = new JsonResult(1, "查询列表", agentStatus,true);
                }else{
                    jsonResult = new JsonResult(2, "无列表",false);
                }

        }
        return jsonResult;
    }
}

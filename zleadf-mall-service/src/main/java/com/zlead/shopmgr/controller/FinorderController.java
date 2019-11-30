package com.zlead.shopmgr.controller;

import com.zlead.entity.FinorderEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.reception.service.FinorderService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
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

@Controller
@RequestMapping("/zlead/finorder")
public class FinorderController {
    @Resource
    private FinorderService finorderServiceImpl;
    @Resource
    private LoginUtil loginUtil;
    @RequestMapping("/customerBill")
    @ResponseBody
    public JsonResult selectFinorder(@RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                     @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                     HttpServletRequest request){

        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);

        if(member==null){
            jsonResult = new JsonResult(2, "未登录", false);
        }else {
            PageList<FinorderEntity> finorder = finorderServiceImpl.findFinorder(new PageBounds(Integer.parseInt(pageNum), Integer.parseInt(pageSize)), member.getOwnShopid());
            if (finorder != null && finorder.size() > 0) {
                Page<OrderEntity> page = new Page(finorder, finorder.getPagination());
                jsonResult = new JsonResult(1, "详细信息", page, true);
            } else {
                jsonResult = new JsonResult(2, "无信息", true);
            }
        }
        return jsonResult;
    }


}

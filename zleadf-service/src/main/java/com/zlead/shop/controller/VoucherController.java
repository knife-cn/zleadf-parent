package com.zlead.shop.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.fplat.entity.Voucher;
import com.zlead.fplat.service.SysMessageService;
import com.zlead.fplat.service.VoucherService;
import com.zlead.reception.service.MemberService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/22.
 * @Desoription TODO
 */
@RestController
@RequestMapping("/zlead/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 添加凭证
     * */
    @RequestMapping("addVoucher")
    @ResponseBody
    public JsonResult addVoucher(@RequestParam(value="orderIds",required = false) @Validated  List<Integer> orderIds,Voucher voucher,HttpServletRequest request){

        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if(memberEntity==null){
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
        	if(voucher!=null && voucher.getAmount()!=null){
        		voucherService.addVoucher(voucher,memberEntity,orderIds);
                sysMessageService.insertVoucherSysMsg(memberEntity, voucher.getAmount());
        		jsonResult = new JsonResult(1, "添加凭证成功", true);
        	}else{
        		jsonResult = new JsonResult(2, "数据不全,添加凭证失败", false);
        	}
             
        }catch (Exception e){
            jsonResult = new JsonResult(2, "添加凭证失败", false);
            return jsonResult;
        }
        return jsonResult;
    }


    /**
     * 添加凭证
     * */
    @RequestMapping("voucherList")
    @ResponseBody
    public JsonResult voucherList(HttpServletRequest request, PageBounds rowBounds){

        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if(memberEntity==null){
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
            Long agentId = memberEntity.getAgentId();
            List<Long> memberList = memberService.getByAgentId(agentId.intValue());
            String memberIds = "";
            for (int i =0;i<memberList.size();i++){
                memberIds = memberIds+memberList.get(i)+",";
            }
            memberIds = memberIds.substring(0, memberIds.length() - 1);
            PageList<Voucher> vouchers = voucherService.voucherList(memberIds, rowBounds);
            if (vouchers!=null&&vouchers.size()>0){
                jsonResult = new JsonResult(1, "获取凭证成功", vouchers,true);
            }else {
                jsonResult = new JsonResult(2, "无凭证", true);
            }
        }catch (Exception e){
            jsonResult = new JsonResult(3, "获取凭证失败", false);
            return jsonResult;
        }
        return jsonResult;
    }

    /**
     * 获取凭证
     * */
    @RequestMapping("findById")
    @ResponseBody
    public JsonResult findById(@RequestParam(value="voucherId") @Validated int voucherId,HttpServletRequest request){

        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if(memberEntity==null){
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
            Voucher voucher = voucherService.findById(voucherId);
            if (voucher!=null){
                jsonResult = new JsonResult(1, "获取凭证成功", voucher,true);
            }else {
                jsonResult = new JsonResult(2, "获取凭证失败", true);
            }
        }catch (Exception e){
        	e.printStackTrace();
            jsonResult = new JsonResult(2, "获取凭证失败", false);
            return jsonResult;
        }
        return jsonResult;
    }



}

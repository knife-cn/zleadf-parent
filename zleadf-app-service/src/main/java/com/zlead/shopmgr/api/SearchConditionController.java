package com.zlead.shopmgr.api;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.vo.CrmCustLevelListVO;
import com.zlead.shopmgr.service.SearchConditionService;
import com.zlead.util.JsonResult;
import com.zlead.utils.LoginUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shopmgr")
public class SearchConditionController {

    @Resource
    private LoginUtil loginUtil;

    @Resource
    private SearchConditionService searchConditionService;

    /**
     * 获取库存列表筛选条件
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStockSearchCondition")
    public JsonResult getStockSearchCondition(HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        }else{
            Map<String, Object> stockSearchCondition = searchConditionService.getStockSearchCondition(member.getId());
            jsonResult = new JsonResult(1, "库存列表筛选条件", stockSearchCondition,true);
        }
        return jsonResult;
    }

    /**
     * 获取客户级别列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCrmCustLevelList")
    public JsonResult getCrmCustLevelList(HttpServletRequest request){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        }else{
            List<CrmCustLevelListVO> crmPrdCatList = searchConditionService.getCrmCustLevelList(member.getId());
            jsonResult = new JsonResult(1, "客户级别列表", crmPrdCatList,true);
        }
        return jsonResult;
    }

}

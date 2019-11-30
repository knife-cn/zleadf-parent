package com.zlead.shopmgr.api;

import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.vo.*;
import com.zlead.shopmgr.service.AclUserService;
import com.zlead.shopmgr.service.FactoryAgentService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.AppUtil;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.MathUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/api/shopmgr")
public class FactoryAgentAppController {

    @Resource
    private FactoryAgentService factoryAgentService;

    @Resource
    private LoginUtil loginUtil;

    @Resource
    private AclUserService aclUserService;

    /**
     * 查询代理商(客户)列表
     *
     * @param keyword
     * @param levelId
     * @param request
     * @return
     */
    @RequestMapping(value = "/factoryAgent")
    @ResponseBody
    public JsonResult factoryAgentList(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "levelId", required = false) Integer levelId,
                                       HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            try {
                Map<String, Object> map = factoryAgentService.queryAgentList(member.getId(), keyword, levelId);
                if (map != null && map.get("result") != null) {
                    if (CollectionUtils.isNotEmpty((List<OaAgentMasListVo>) map.get("result"))) {
                        jsonResult = new JsonResult(1, "列表信息", map, true);
                    } else {
                        jsonResult = new JsonResult(1, "无列表信息", null, true);
                    }
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonResult;
    }

    /**
     * 查询代理商(客户)列表
     *
     * @param keyword
     * @param levelId
     * @param request
     * @return
     */
    @RequestMapping(value = "/voucherAgent")
    @ResponseBody
    public JsonResult voucherAgentList(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "levelId", required = false) Integer levelId,
                                       HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            try {
                Map<String, Object> map = factoryAgentService.queryVoucherAgentList(member.getId(), keyword, levelId);
                if (map != null && map.get("result") != null) {
                    if (CollectionUtils.isNotEmpty((List<OaAgentMasListVo>) map.get("result"))) {
                        jsonResult = new JsonResult(1, "列表信息", map, true);
                    } else {
                        jsonResult = new JsonResult(1, "无列表信息", null, true);
                    }
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/setAgentStatus")
    @ResponseBody
    public JsonResult setAgentStatus(@RequestParam(value = "status") @Validated @NotNull String status,
                                     @RequestParam(value = "agentId") @Validated @NotNull Long agentId,
                                     HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            try {
                factoryAgentService.setAgentStatus(status, member.getId(), agentId);
                jsonResult = new JsonResult(1, "更新成功", true);
            } catch (Exception ex) {
                jsonResult = new JsonResult(2, "更新失败", false);
                ex.printStackTrace();
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/queryAgentStatus")
    @ResponseBody
    public JsonResult queryAgentStatus(HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            List<Map> agentStatus = factoryAgentService.findAgentStatus(member.getId());
            if (agentStatus != null && agentStatus.size() > 0) {
                jsonResult = new JsonResult(1, "查询列表", agentStatus, true);
            } else {
                jsonResult = new JsonResult(2, "无列表", false);
            }

        }
        return jsonResult;
    }

    /**
     * 查询代理商(客户)基本信息
     *
     * @param agentId
     * @param factoryId
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAgentBaseInfo")
    @ResponseBody
    public JsonResult getAgentBaseInfo(@RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                       @RequestParam(value = "factoryId") @Validated @NotNull Integer factoryId,
                                       HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            OaAgentMasBaseInfoVo oaAgentMas = factoryAgentService.getAgentBaseInfo(agentId, factoryId);
            if (oaAgentMas != null) {
                String agentDiscountType = oaAgentMas.getAgentDiscountType();//折扣类型
                String agentDiscount = oaAgentMas.getAgentDiscount();//折扣
                if (StringUtils.isNotBlank(agentDiscountType)) {
                    if ("1".equals(agentDiscountType)) {
                        agentDiscountType = "经销价";
                    } else if ("2".equals(agentDiscountType)) {
                        agentDiscountType = "批发价";
                    } else if ("3".equals(agentDiscountType)) {
                        agentDiscountType = "零售价";
                    }
                }
                if (StringUtils.isNotBlank(agentDiscount)) {//修改产品要求返回“xx.xx%”显示
                    String percent = MathUtils.parseDiscountPercent(Double.parseDouble(agentDiscount));
                    oaAgentMas.setAgentDiscount(percent);
                } else {
                    oaAgentMas.setAgentDiscount("");
                }
                jsonResult = new JsonResult(1, "客户基本信息", oaAgentMas, true);
            } else {
                jsonResult = new JsonResult(1, "未找到对应的客户信息", null, true);
            }
        }
        return jsonResult;
    }

    /**
     * 客户授权品牌列表
     *
     * @param agentId
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/agentBrandList")
    @ResponseBody
    public JsonResult agentBrandList(@RequestParam(value = "pageNum") @Validated @NotNull Integer pageNum,
                                     @RequestParam(value = "size") @Validated @NotNull Integer pageSize,
                                     @RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                     HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            List<AgentBrandListVO> brandList = factoryAgentService.getAgentBrandList(agentId, member.getOwnShopid(), pageNum, pageSize);
            if (brandList != null && brandList.size() > 0) {
                jsonResult = new JsonResult(1, "客户授权品牌列表", brandList, true);
            } else {
                jsonResult = new JsonResult(1, "客户无授权品牌", null, true);
            }
        }
        return jsonResult;
    }

    /**
     * 获取客户图片地址列表
     *
     * @param agentId
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/agentPictureList")
    @ResponseBody
    public JsonResult agentPictureList(@RequestParam(value = "pageNum") @Validated @NotNull Integer pageNum,
                                       @RequestParam(value = "size") @Validated @NotNull Integer pageSize,
                                       @RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                       HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            List<AgentPictureListVO> agentPictureList = factoryAgentService.getAgentPictureList(agentId, member.getOwnShopid(), pageNum, pageSize);
            if (agentPictureList != null && agentPictureList.size() > 0) {
                jsonResult = new JsonResult(1, "客户图片列表", agentPictureList, true);
            } else {
                jsonResult = new JsonResult(1, "客户无图片", null, true);
            }
        }
        return jsonResult;
    }


    @RequestMapping("/activityAgent")
    @ResponseBody
    public JsonResult findActityAgent(HttpServletRequest request, @RequestParam("pageNum") int pageCurrent,
                                      @RequestParam("size") int pageSize, @RequestParam("actId") int actId) {
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (null == member) {
            return new JsonResult(3, "未登录", "", false);
        }
        List<ActivityAgentVo> list = factoryAgentService.findActivityAgent(new PageBounds(pageCurrent, pageSize), member.getId(), actId);
        return new JsonResult(1, "活动列表", list, true);
    }
}

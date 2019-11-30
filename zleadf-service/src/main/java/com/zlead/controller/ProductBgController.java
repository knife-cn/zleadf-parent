package com.zlead.controller;

import com.zlead.entity.ProductEntity;
import com.zlead.entity.SysUserEntity;
import com.zlead.service.ProductBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


/**
 * 产品--老版本-暂不用
 *
 * @author fqf
 * @date 2018-07-25 11:41:32
 */
@Controller
@RequestMapping("/zlead/tproductBg")
public class ProductBgController {


    @Resource
    private ProductBgService productBgService;

    @Resource
    private LoginUtil loginUtil;

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(@RequestParam(value = "pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value = "size") @Validated @NotNull Integer size) {
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //页数
        PageBounds pageBounds = new PageBounds(pageNum, size);
        PageList<ProductEntity> list = productBgService.getPage(params, pageBounds);
        if (list != null && list.size() > 0) {
            Page page = new Page<ProductEntity>(list, list.getPagination());
            jsonResult = new JsonResult(1, "列表信息", page, true);
        } else {
            jsonResult = new JsonResult(2, "暂无数据信息", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(@RequestParam("id") Long id) {
        JsonResult jsonResult = null;
        ProductEntity tProduct = productBgService.findById(id);

        jsonResult = new JsonResult(1, "详细信息", tProduct, true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated ProductEntity tProduct) {
        JsonResult jsonResult = null;
        productBgService.save(tProduct);

        jsonResult = new JsonResult(1, "修改成功", tProduct, true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除产品
     */
    @RequestMapping("/productDelete")
    @ResponseBody
    public JsonResult productDelete(HttpServletRequest request, @RequestParam("id") Long id) {
        JsonResult jsonResult = null;
        SysUserEntity user = loginUtil.getLoginSysUser(request);
        if (user != null) {
            boolean b = productBgService.productDelete(id);
            if (b) {
                jsonResult = new JsonResult(1, "操作成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "操作失败", "", false);
            }
        } else {
            jsonResult = new JsonResult(2, "后台用户未登录", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存产品(管理员)
     */
    @RequestMapping("/productSave")
    @ResponseBody
    public JsonResult productSave(HttpServletRequest request, @Validated ProductEntity tProduct) {
        JsonResult jsonResult = null;
        SysUserEntity user = loginUtil.getLoginSysUser(request);
        if (user != null) {
            boolean b = productBgService.sysProductSave(tProduct, user, request);
            if (b) {
                jsonResult = new JsonResult(1, "保存成功", tProduct, true);
            } else {
                jsonResult = new JsonResult(2, "保存失败", tProduct, false);
            }
        } else {
            jsonResult = new JsonResult(2, "后台用户未登录", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改产品
     */
    @RequestMapping("/productUpdate")
    @ResponseBody
    public JsonResult productUpdate(HttpServletRequest request, @Validated ProductEntity tProduct) {
        JsonResult jsonResult = null;
        SysUserEntity user = loginUtil.getLoginSysUser(request);

        if (user != null) {
            boolean b = productBgService.productUpdate(tProduct, request);
            if (b) {
                jsonResult = new JsonResult(1, "操作成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "操作失败", "", false);
            }
        } else {
            jsonResult = new JsonResult(2, "后台用户未登录", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 产品审核
     */
    @RequestMapping("/productAudit")
    @ResponseBody
    public JsonResult productAudit(HttpServletRequest request, @RequestParam("id") Long id) {
        JsonResult jsonResult = null;
        //查询登录的用户
        SysUserEntity userEntity = loginUtil.getLoginSysUser(request);
        if (userEntity == null) {
            jsonResult = new JsonResult(2, "未登录", "", false);
            return jsonResult;
        }
        boolean b = productBgService.productAudit(id, userEntity);
        if (b) {
            jsonResult = new JsonResult(1, "操作成功", "", true);
        } else {
            jsonResult = new JsonResult(2, "操作失败", "", false);
        }
        return jsonResult;
    }

    /**
     * @param prodId 产品id
     * @return 创建成功/失败
     */
    @ResponseBody
    @RequestMapping(value = "creategoods", method = RequestMethod.GET)
    public JsonResult createGoods(@RequestParam("prodId") Long prodId) {
        boolean flag = productBgService.createGoods(prodId);
        return new JsonResult(flag ? 1 : 2, flag ? "操作成功" : "操作失败", "", flag);
    }
}

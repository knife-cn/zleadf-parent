package com.zlead.controller;

import com.zlead.entity.ExpressCompanyEntity;
import com.zlead.service.ExpressCompanyBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.constant.Cnst;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3102快递公司表--老版本-暂不用
 * @author fqf
 * @date 2018-08-14 10:14:02
 */
@Controller
@RequestMapping("/zlead/expresscompanyBg")
public class ExpressCompanyBgController {
    @Resource
    private ExpressCompanyBgService expressCompanyBgService;

    @RequestMapping("/pageList")
    @ResponseBody
    public JsonResult pageList(@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<ExpressCompanyEntity>  list = expressCompanyBgService.getPage(params, pageBounds);
        jsonResult =  new JsonResult(1,"列表信息",list,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 获取快递公司的信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(@RequestParam(value="name") @Validated @NotNull String name){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //页数
        List<ExpressCompanyEntity> list = expressCompanyBgService.getList(name);
        if(list!=null&&list.size()>0){
            jsonResult =  new JsonResult(1,"列表信息",list,true);
        }else{
            jsonResult =  new JsonResult(2,"没有找到信息","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(@RequestParam("expressCompanyId") Long expressCompanyId){
        JsonResult jsonResult = null;
		ExpressCompanyEntity expressCompany = expressCompanyBgService.findById(expressCompanyId);

        jsonResult =  new JsonResult(1,"详细信息",expressCompany,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(@Validated ExpressCompanyEntity expressCompany){
        JsonResult jsonResult = null;
        expressCompanyBgService.save(expressCompany);

        jsonResult =  new JsonResult(1,"保存成功",expressCompany,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated ExpressCompanyEntity expressCompany){
        JsonResult jsonResult = null;
            expressCompanyBgService.save(expressCompany);

        jsonResult =  new JsonResult(1,"修改成功",expressCompany,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(@RequestParam("expressCompanyId") Long expressCompanyId){
        JsonResult jsonResult = null;
        expressCompanyBgService.delete(expressCompanyId);

        jsonResult =  new JsonResult(1,"删除成功","",true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

}

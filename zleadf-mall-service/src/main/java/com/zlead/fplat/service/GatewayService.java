package com.zlead.fplat.service;

import com.zlead.util.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2019/1/16.
 * 门户端
 */
public interface GatewayService {


    /*
    * 导航栏筛选和工厂列表
    * */

    public JsonResult navigation(HttpServletRequest request);


    /*
    * 限时活动
    * */

    public JsonResult actives(HttpServletRequest request);


    /*
    * 锚点数据*/
    public JsonResult getCat(HttpServletRequest request);


    /*
    * 首页商品区域*/
    public JsonResult getPrdArea(HttpServletRequest request);

    /*
     * 导航栏筛选和工厂列表
     * */
    JsonResult newNavigation(HttpServletRequest request);

    /*
     * 首页商品区域*/
    JsonResult newGetPrdArea(HttpServletRequest request);
}

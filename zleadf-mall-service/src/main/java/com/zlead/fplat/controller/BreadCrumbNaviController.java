package com.zlead.fplat.controller;

import com.zlead.constant.BreadCrumbConstant;
import com.zlead.entity.vo.BreadCrumbNaviVo;
import com.zlead.util.JsonResult;
import com.zlead.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hyde
 * @Email zhangyayun@fuljoy.com
 * @Date 2019/3/24 15:26
 * @Description 面包屑动态导航controller
 */
@Controller
@RequestMapping("/zlead/fplat")
public class BreadCrumbNaviController {

    @Autowired
    private RedisUtil redisUtil;

    @ResponseBody
    @RequestMapping("breadCrumbNavi")
    public JsonResult BreadCrumbNavi(HttpServletRequest request) {

        String id = request.getSession().getId();

        String secondKey  = BreadCrumbConstant.SECOND_NAVI+id;
        String thirdKey = BreadCrumbConstant.THIRD_NAVI+id;

        BreadCrumbNaviVo  secondVo = redisUtil.query(secondKey);
        BreadCrumbNaviVo  thirdVo = redisUtil.query(thirdKey);

        List<BreadCrumbNaviVo> list =  new ArrayList<>();
        if (null!=secondVo){
            list.add(secondVo);
        }
        if (null!=thirdVo){
            list.add(thirdVo);
        }
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setData(list);
        result.setSuccess(true);

        return result;
    }




}

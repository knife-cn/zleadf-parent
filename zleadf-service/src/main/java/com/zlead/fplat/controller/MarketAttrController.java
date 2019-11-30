package com.zlead.fplat.controller;

import com.zlead.common.PageResponse;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.dto.GoodsPageWrapperDto;
import com.zlead.entity.dto.MarketActDto;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.entity.Marketact;
import com.zlead.fplat.service.MarketagentService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.DateTool;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.RedisUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
@RestController
@RequestMapping("/zlead/attr")
public class MarketAttrController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MarketAttrController.class);

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private MarketagentService marketagentService;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/queryDetail/{actId}")
    @ResponseBody
    public JsonResult queryMarketAttrDetail(HttpServletRequest request,@PathVariable("actId") int actId){
        MemberEntity member = loginUtil.getLoginMember(request);
        if(null == member){
            return new JsonResult(2,"找不到用户","",false);
        }
        Marketact marketact = marketagentService.queryMarketActDetail(actId,member.getAgentId());
        if(null == marketact){
            return new JsonResult(2,"暂无活动","",false);
        }
        try {
            String refererUrl = request.getHeader("Referer");
            System.out.println(refererUrl);
            redisUtil.saveNaviRedis(request,5,refererUrl);



        }catch (Exception e){
            e.printStackTrace();
        }

        MarketActDto marketActDto = new MarketActDto();
        marketActDto.setContName(marketact.getContName());
        marketActDto.setContTitle(marketact.getContTitle());
        marketActDto.setAttrDate(new StringBuffer().append(DateTool.format(marketact.getEffDate())).append("至").
                append(DateTool.format(marketact.getExpDate())).toString());
        marketActDto.setStaticCont(marketact.getStaticCont());
        marketActDto.setAtrPic(marketact.getActPic());
        marketActDto.setActContent(marketact.getTerminal());
        logger.info("活动详情：{}",marketActDto);
        return new JsonResult(1,"详细信息",marketActDto,true);
    }

    @RequestMapping("/findList/{pageCurrent}/{pageSize}/{actId}")
    @ResponseBody
    public JsonResult goodsCollectPageList(HttpServletRequest request, @PathVariable("pageCurrent") int pageCurrent,
                                           @PathVariable("pageSize") int pageSize,@PathVariable("actId") int actId){
        MemberEntity member = loginUtil.getLoginMember(request);
        JsonResult jsonResult = null;
        if(null == member){
            jsonResult = new JsonResult(2,"找不到用户","",false);
            return jsonResult;
        }
        GoodsPageWrapperDto wrapperDto = new GoodsPageWrapperDto();
        List<GoodsPageDto> list = marketagentService.findByPageGoodsAttr(actId,member.getAgentId(), new PageBounds(pageCurrent, pageSize));
        int totalCount = marketagentService.findTotalCount(actId,member.getAgentId());
        wrapperDto.setGoodsPageDtoList(list);
        wrapperDto.setCount(totalCount);
        jsonResult = new JsonResult(1,"活动商品列表",wrapperDto,true);
        return jsonResult;
    }

    @GetMapping("/findPageAct/{pageCurrent}/{pageSize}")
    public JsonResult findByPageAct(HttpServletRequest request,@PathVariable("pageCurrent") int pageCurrent,
                                    @PathVariable("pageSize") int pageSize){
        MemberEntity member = loginUtil.getLoginMember(request);
        if(null == member){
            return new JsonResult(2,"找不到用户","",false);
        }
        PageResponse pageResponse = marketagentService.findByPageAttr(member.getAgentId().intValue(),new PageBounds(pageCurrent,pageSize));
        return new JsonResult(1,"活动列表",pageResponse,true);
    }

    /**
     * 查询当前供应商的有效广告
     * @return
     */
    public JsonResult memberValidAdvertising(HttpServletRequest request){
        JsonResult jsonResult = null;
        //需要得到分类ids，供应商id ， 工厂ids
        MemberEntity member = loginUtil.getLoginMember(request);
        if (null != member){
            Long agentId = member.getAgentId();
            //获取
            List<Map<String, Object>> factoryIdListByAgentId = agentFacMapper.findFactoryIdListByAgentId(agentId, null);

        }else{
            jsonResult = new JsonResult(2,"找不到用户",false);
        }




      //      marketagentService.memberValidAdvertising()
        return jsonResult;
    }


}

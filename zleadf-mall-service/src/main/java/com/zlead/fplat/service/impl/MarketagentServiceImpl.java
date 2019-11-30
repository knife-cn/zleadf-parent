package com.zlead.fplat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zlead.common.PageResponse;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.vo.ActityGoodsVo;
import com.zlead.entity.vo.ActivityAgentVo;
import com.zlead.entity.vo.ActivityTypeVo;
import com.zlead.entity.vo.ActivityVo;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.AcctSaleTypeMapper;
import com.zlead.fplat.dao.MarketactMapper;
import com.zlead.fplat.dao.MarketagentMapper;
import com.zlead.fplat.entity.AcctSaleType;
import com.zlead.fplat.entity.Marketact;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.service.MarketagentService;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
@Service
public class MarketagentServiceImpl implements MarketagentService {

    private static final Logger logger = LoggerFactory.getLogger(MarketagentServiceImpl.class);

    @Autowired
    private MarketagentMapper marketagentMapper;

    @Autowired
    private MarketactMapper marketactMapper;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private AcctSaleTypeMapper acctSaleTypeMapper;

    @Override
    public List<GoodsPageDto> findByPageGoodsAttr(int actId,Long agentId, PageBounds pageBounds) {
        logger.info("查询活动页列表入参:actId = {}, pageBounds = {} " ,actId ,pageBounds);
        PageResponse pageResponse = new PageResponse();
        Page page = PageHelper.startPage(pageBounds.getPageNumber(),pageBounds.getPageSize(),true);
        List<GoodsPageDto> pageList = marketagentMapper.findByPageAttrGoods(actId,agentId,pageBounds);
        for (GoodsPageDto goodsPageDto:pageList ) {
            //活动图片为 "" 或者 null 设置默认图片       活动页面商品图片
            if (null != goodsPageDto && ("".equals(goodsPageDto.getImage()) || null == goodsPageDto.getImage())) {
                goodsPageDto.setImage("/shopping/img/index/sl3.png");
            }
            String goodsName = StrTools.strDistinct(goodsPageDto.getGoodsName());
            goodsPageDto.setGoodsName(goodsName);
        }
        return pageList;
    }

    @Override
    public Marketact queryMarketActDetail(int actId,Long agentId) {
        logger.info("查询活动详情页入参：actId =", actId);
        Marketact marketact = marketactMapper.queryByActId(actId,agentId);
        //活动图片为 "" 或者 null 设置默认图片       活动页面活动图片
        if (null!=marketact && ("".equals(marketact.getActPic())||null == marketact.getActPic())) {
            marketact.setActPic("/shopping/img/index/sl3.png");
        }
        return marketact;
    }
    
    /**
     * app端调用活动商品属性
     */
    @Override
    public Marketact queryMarketActDetailForApp(int actId) {
        logger.info("查询活动详情页入参：actId =", actId);
        Marketact marketact = marketactMapper.queryByActIdForApp(actId);
        //活动图片为 "" 或者 null 设置默认图片       活动页面活动图片
        if (null!=marketact && ("".equals(marketact.getActPic())||null == marketact.getActPic())) {
            marketact.setActPic("/shopping/img/index/sl3.png");
        }
        return marketact;
    }

    @Override
    public PageResponse findByPageAttr(int agentId, PageBounds pageBounds) {
        logger.info("查询活动列表入参 agentId = {} , pageBounds = {}：" ,agentId,pageBounds);

        PageResponse pageResponse = new PageResponse();
        Page page = PageHelper.startPage(pageBounds.getPageNumber(),pageBounds.getPageSize(),true);
        List<Integer> facIds = agentFacMapper.findFacByagentId((long) agentId);
        List<Marketact> list = marketactMapper.findByAgentIdPage(agentId,pageBounds,facIds);
        pageResponse.setPage(pageBounds);
        pageResponse.setCount(page.getTotal());
        List listPage = new ArrayList();
        for(Marketact marketact : list){
            //活动图片为 "" 或者 null 设置默认图片     more更多活动页面
            if (null!=marketact && "".equals(marketact.getActPic()) || null == marketact.getActPic()) {
                marketact.setActPic("/shopping/img/index/sl3.png");
            }
            Map<String,String> map = new HashMap<>();
            map.put("actId",marketact.getActId().toString());
            map.put("image",marketact.getActPic());
            map.put("actTitle",marketact.getContTitle());
            map.put("actContent",marketact.getStaticCont());
            listPage.add(map);
        }
        pageResponse.setData(listPage);
        return pageResponse;
    }

    @Override
    public List<ActivityVo> findShopActivies(PageBounds pageBounds, Long ownShopid,
                                             Integer contType,Integer contState,String effDate,String expDate) {
        List<Marketact> shopActivies = marketactMapper.findShopActivies(pageBounds, ownShopid,contType,contState,effDate,expDate);
        List<ActivityVo> list  = new ArrayList<>();
        for(Marketact activity:shopActivies){
            ActivityVo activityVo = new ActivityVo();
            activityVo.setActId(activity.getActId());
            activityVo.setActPic(activity.getActPic());
            activityVo.setContName(activity.getContName());
            activityVo.setContState(activity.getContState());
            activityVo.setContTitle(activity.getContTitle());
            activityVo.setEffDate(activity.getEffDate()!=null?activity.getEffDate().toLocaleString():null);
            activityVo.setExpDate(activity.getExpDate()!=null?activity.getExpDate().toLocaleString():null);
            activityVo.setShopId(activity.getShopId());
            activityVo.setContType(activity.getContType());
            activityVo.setStaticCont(activity.getStaticCont());
            list.add(activityVo);
        }
        return list;
    }

    @Override
    public List<ActivityTypeVo> findActivityTypes(Long ownShopid) {
        List<AcctSaleType> saleTypes  = acctSaleTypeMapper.findActivityTypes(ownShopid);
        List<ActivityTypeVo> list  = new ArrayList<>();
        for(AcctSaleType saleType : saleTypes){
            ActivityTypeVo vo = new ActivityTypeVo();
            vo.setTypeId(saleType.getTypeId());
            vo.setTypeName(saleType.getTypeName());
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<ActityGoodsVo> findByPageGoods(int actId, int shopId, PageBounds pageBounds) {
        return marketagentMapper.findByPageGoods(actId,shopId,pageBounds);
    }

    @Override
    public int findTotalCount(int actId,Long agentId) {
        return marketagentMapper.findTotalCount(actId,agentId);
    }

    /**
     * 判断活动商品是否在有效期限之中
     * @param agentId
     * @param goodsId
     * @return
     */
    @Override
    public GoodsPageDto queryVaildActivity(@Param("agentId")Long agentId,@Param("goodsId")Long goodsId){
        return marketagentMapper.queryVaildActivity(agentId,goodsId);
    }


}

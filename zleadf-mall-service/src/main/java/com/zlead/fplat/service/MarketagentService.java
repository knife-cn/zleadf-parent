package com.zlead.fplat.service;

import com.zlead.common.PageResponse;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.vo.ActityGoodsVo;
import com.zlead.entity.vo.ActivityAgentVo;
import com.zlead.entity.vo.ActivityTypeVo;
import com.zlead.entity.vo.ActivityVo;
import com.zlead.fplat.entity.Marketact;
import com.zlead.util.page.PageBounds;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
public interface MarketagentService {

    /**
     * 查询 活动商品列表
     * @param actId
     * @param pageBounds
     * @return
     */
    List<GoodsPageDto> findByPageGoodsAttr(int actId,Long agentId, PageBounds pageBounds);

    /**
     * 查询活动详情
     * @param actId
     * @param agentId
     * @return
     */
    Marketact queryMarketActDetail(int actId,Long agentId);

    /**
     * 查询活动列表
     * @param agentId
     * @param pageBounds
     * @return
     */
    PageResponse findByPageAttr(int agentId,PageBounds pageBounds);

    /**
     * 根据添加查询过滤活动
     *
     * @author 喻聪聪
     * @date   2019-02-28
     */
    List<ActivityVo> findShopActivies(PageBounds pageBounds, Long ownShopid,
                                      Integer contType,Integer contState,String effDate,String expDate);

    /**
     * 查询活动类型
     *
     * @author 喻聪聪
     * @date   2019-02-28
     */
    List<ActivityTypeVo> findActivityTypes(Long ownShopid );


    List<ActityGoodsVo> findByPageGoods(int actId, int shopId, PageBounds pageBounds);


    int findTotalCount(int actId,Long agentId);

    /**
     * 判断活动商品是否在有效期限之中
     * @param agentId
     * @param goodsId
     * @return
     */
    GoodsPageDto queryVaildActivity(@Param("agentId")Long agentId,@Param("goodsId")Long goodsId);

	Marketact queryMarketActDetailForApp(int actId);

}

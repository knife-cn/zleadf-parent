package com.zlead.reception.service;

import com.zlead.common.PageResponse;
import com.zlead.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.GoodsAttrValueDto;
import com.zlead.entity.dto.GoodsDto;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.entity.vo.GoodsAttrValVo;
import com.zlead.entity.vo.GoodsAttrValueVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-31 09:55:22
 */
public interface GoodsService {

    PageList<GoodsEntity> getPage(Map params, PageBounds rowBounds);

    void save(GoodsEntity entity);

    int update(GoodsEntity entity);

    void delete(Long id);

    GoodsEntity findById(Long id);

    List<GoodsEntity> gethomeGoodsList(Integer showNum);

    /**
     * 查询商品详情
     * @param goodsId
     * @param agentId
     * @return
     */
    GoodsDto getGoodsDetail(Long goodsId,int agentId);

    /**
     * 收藏列表
     * @param agentId
     * @param rowBounds
     * @return
     */
    PageResponse pageListCollect(Integer agentId, PageBounds rowBounds, HttpServletRequest request);

    /**
     * 查询活动商品
     * @param pageBounds
     * @return
     */
    PageResponse findByProdTypePage(PageBounds pageBounds);

    PageList<GoodsEntity> getGoodsList(Integer prodType,Integer page,Integer shopId);
    
    PageList<GoodsEntity> queryAgentGoodsList(Integer page);

    /**
     * 根据参数获取商品id
     * @param goodsAttrValueResponse
     * @return
     */
    GoodsDto getByGoodsId(List<GoodsAttrValueReponse> goodsAttrValueResponse,int agentId,int prdId);

    Map<String, Object> getGoodsById(List<GoodsAttrValueReponse> goodsAttrValueResponse, MemberEntity member, Long cartId);


    GoodsDto getAttr(Long goodsId,int agentId);

    GoodsAttrValueVo selectGoods(List<GoodsAttrValueReponse> goodsAttrValueResponse, Long prdId);

    GoodsDto findActGoods(Long agentId,Long actId, Long goodId);

    /**
     * 通过商品Id查询有效的商品
     * @param goodsId
     * @return
     */
    GoodsEntity queryGoods(Long goodsId);
}


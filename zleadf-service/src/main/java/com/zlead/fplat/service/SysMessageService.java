package com.zlead.fplat.service;

import com.zlead.common.PageResponse;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.fplat.entity.SysMessage;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;

import java.math.BigDecimal;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/17.
 * @Desoription TODO
 */
public interface SysMessageService {

    /**
     * 查询代理商系统消息
     * @param agentId
     * @param pageBounds
     * @return
     */
    PageResponse findByPage(int agentId,int status, PageBounds pageBounds);

    /**
     * 更新消息
     * @param id
     * @return
     */
    void updateMessage(int id,int agentId);

    /**
     * 添加消息
     * @param sysMessage
     * @return
     */
    JsonResult save(SysMessage sysMessage);

    /**
     * 新增关联工厂系统消息
     * @param member
     * @param factoryId
     */
    void insertAgentFactorySysMsg(MemberEntity member, Integer factoryId);

    /**
     * 新增订单相关系统消息
     * @param order
     * @param memberEntity
     * @param msgType  1.取消订单  2.确认收货
     */
    void insertOrderSysMsg(OrderEntity order, MemberEntity memberEntity, Integer msgType);

    /**
     * 新增上传凭证系统消息
     * @param memberEntity
     * @param amount
     */
    void insertVoucherSysMsg(MemberEntity memberEntity, BigDecimal amount);
}

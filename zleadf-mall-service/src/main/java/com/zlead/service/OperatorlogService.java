package com.zlead.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OperatorLog;

import java.util.Map;

/**
 * 订单节点状态明细
 *
 * @author leiningbo
 * @date 2019年3月6日15:27:23
 */
public interface OperatorlogService {

    /**
     * 新增订单类操作系统日志
     *
     * @param userId
     * @param userName
     * @param orderId
     * @param title
     * @return
     */

    Integer insertOrderOperatorlog(Integer userId, String userName, String orderId, String title);

}

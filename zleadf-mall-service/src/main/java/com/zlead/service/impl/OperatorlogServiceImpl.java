package com.zlead.service.impl;

import com.zlead.dao.OperatorlogDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OperatorLog;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.service.OperatorlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class OperatorlogServiceImpl implements OperatorlogService {

    @Autowired
    private OperatorlogDao operatorlogDao;

    @Override
    public Integer insertOrderOperatorlog(Integer userId, String userName, String orderId, String title) {

        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setMemberId(userId);
        operatorLog.setUserName(userName);
        operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
        operatorLog.setTitle(title);
        operatorLog.setSn(orderId);
        operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
        operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
        operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MALL);
        operatorLog.setCreateDate(new Date());
        return operatorlogDao.insertSelectiveOne(operatorLog);
    }
}

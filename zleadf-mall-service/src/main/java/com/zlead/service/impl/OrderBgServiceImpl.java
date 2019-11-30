package com.zlead.service.impl;

import com.alibaba.fastjson.JSON;
import com.zlead.dao.OperatorlogDao;
import com.zlead.dao.OrderDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OperatorLog;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.fplat.dao.OaAgentUserinfoMapper;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.OaAgentUserinfo;
import com.zlead.fplat.entity.SysMessage;
import com.zlead.fplat.service.AgentFacService;
import com.zlead.fplat.service.SysMessageService;
import com.zlead.service.OrderBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Transactional
@Service
public class OrderBgServiceImpl implements OrderBgService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OperatorlogDao operatorlogDao;

    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private AgentFacService agentFacService;

    @Autowired
    private OaAgentUserinfoMapper oaAgentUserinfoMapper;

    @Transactional(readOnly = true)
    public PageList<OrderEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = orderDao.getPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public OrderEntity findById(Long id) {
        return orderDao.findById(id);
    }

    public void save(OrderEntity entity) {
        orderDao.insert(entity);
    }

    public void update(OrderEntity entity) {
        orderDao.update(entity);
    }

    public void delete(Long id) {
        orderDao.delete(id);
    }

    public boolean orderovid(long orderId, MemberEntity memberEntity, HttpServletRequest request)
    {
        boolean result = orderDao.orderovid(orderId);
        if(result){
            OrderEntity byId = orderDao.findById(orderId);
            sysMessageService.insertOrderSysMsg(byId, memberEntity, 2,byId.getShopId().intValue());
            /**
             * 生成日志操作数据
             */
            OperatorLog operatorLog = new OperatorLog();
            operatorLog.setMemberId(memberEntity.getId().intValue());
            List<OaAgentUserinfo> oaAgentUserinfos = oaAgentUserinfoMapper.selectUserInfosByAgentId(memberEntity.getAgentId());
            for (OaAgentUserinfo oaAgentUserinfo : oaAgentUserinfos) {
                operatorLog.setUserName(oaAgentUserinfo.getUserName());
                if ("1".equals(oaAgentUserinfo.getMainUser())) {
                    operatorLog.setUserName(oaAgentUserinfo.getUserName());
                    break;
                }
            }
            if (operatorLog.getUserName()==null) {
                operatorLog.setUserName(memberEntity.getUsername());
            }
//            operatorLog.setUserName(memberEntity.getUsername());
            operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
            operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_RECEIVE);
            operatorLog.setRemark("商城"+memberEntity.getId()+"收货成功");
            operatorLog.setSn(orderId+"");
            operatorLog.setLinkUrl(request.getRequestURI().toString());
            operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
            operatorLog.setOperatorParams(JSON.toJSONString(orderId));
            operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
            operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MALL);
            operatorLog.setCreateDate(new Date());
            operatorLog.setAddIp(request.getRemoteAddr());
            operatorlogDao.insertSelectiveOne(operatorLog);


        }
        return result;
    }

}

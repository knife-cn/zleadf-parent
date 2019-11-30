package com.zlead.shopmgr.service.impl;

import com.zlead.dao.AclUserDao;
import com.zlead.dao.MemberDao;
import com.zlead.dao.OperatorlogDao;
import com.zlead.dao.OrderDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OperatorLog;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.entity.vo.SysMsgListVO;
import com.zlead.fplat.dao.OaAgentUserinfoMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.dao.SysMessageMapper;
import com.zlead.fplat.entity.OaAgentUserinfo;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.shopmgr.service.SystemMessageService;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.AppUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    private SysMessageMapper sysMessageMapper;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OaAgentUserinfoMapper oaAgentUserinfoMapper;
    @Autowired
    private OperatorlogDao operatorlogDao;
    @Autowired
    private OaFactoryInfoMapper factoryInfoMapper;

    @Override
    public Integer hasNotReadSysMsg(Long memberId) {
        Set<Integer> agentIds = AppUtil.findSubAgentByMember(memberId);
        Integer count = sysMessageMapper.hasNotReadSysMsg(memberId, agentIds);
        int result = 0;
        if(count > 0){
            result = 1;
        }
        return result;
    }

    @Override
    public List<SysMsgListVO> notReadSysMsgList(Long memberId,int pageNum,int size) {
        Set<Integer> agentIds = AppUtil.findSubAgentByMember(memberId);
        List<SysMsgListVO> sysMsgList = sysMessageMapper.notReadSysMsgList(memberId,agentIds,new PageBounds(pageNum,size));
        List<SysMsgListVO> list = new ArrayList<>();
        sysMsgList.forEach(sysMsgListVO ->{
            SysMsgListVO vo = new SysMsgListVO();
            vo.setContent(sysMsgListVO.getContent());
            vo.setMsgId(sysMsgListVO.getMsgId());
            vo.setSn(sysMsgListVO.getSn());
            vo.setSnType(sysMsgListVO.getSnType());
            vo.setTitle(sysMsgListVO.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
            try {
                if (StringUtils.isNotEmpty(sysMsgListVO.getCreateTime())){
                    Date time = sdf.parse(sysMsgListVO.getCreateTime());
                    vo.setCreateTime(sdf.format(time));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (sysMsgListVO.getTitle().equals("发货")){
                //取到工厂名称
                OaFactoryInfo factoryInfo = factoryInfoMapper.getFactoryNameByMemberId(memberId);
                String content = sysMsgListVO.getContent();
                String[] contentList = content.split("【直链网】");
                String[] contentList2 = contentList[1].split("，");
                String factName =factoryInfo!=null?factoryInfo.getFactName2():"";
                if(content.contains("全部")) factName ="供货商"+factName + "已全部发货";
                else if(content.contains("部分")) factName ="供货商"+ factName + "已部分发货";
                else factName ="供货商"+ factName + "已发货";
                for(int i = 1;i<contentList2.length;i++){

                    factName = factName+","+contentList2[i]+",";
                    factName = factName.substring(0,factName.length()-1);

                }
                String result = "【直链网】" + factName;
                vo.setContent(result);

                if(StringUtils.isNotEmpty(sysMsgListVO.getCreateTime())){
                    vo.setCreateTime(sysMsgListVO.getCreateTime().substring(0,sysMsgListVO.getCreateTime().indexOf(".")));
                }
                if(StringUtils.isNotEmpty(sysMsgListVO.getSn())){
                    OrderEntity order = orderDao.findBySn(sysMsgListVO.getSn());
                    if(order!=null){
                        vo.setOrderId(order.getId());
                    }
                }
            }

            vo.setAgentId(sysMsgListVO.getAgentId());
            if (sysMsgListVO.getTitle().equals("发货".trim()) || sysMsgListVO.getTitle().equals("创建订单".trim()) || sysMsgListVO.getTitle().equals("确认收货".trim())){
                OrderEntity order = null;
                if (StringUtils.isNotEmpty(sysMsgListVO.getSn())){
                    order = orderDao.findBySn(sysMsgListVO.getSn());
                }
                if (order != null){
                   List<OperatorLog> operatorLogList = null;
                   switch (sysMsgListVO.getTitle()){
                       case "发货":
                           operatorLogList = operatorlogDao.getOperatorLogBySend(order.getId(), TOperatorLogConstant.TITLE_ORDER_SEND);
                           break;
                       case "创建订单":
                           operatorLogList = operatorlogDao.getOperatorLogBySend(order.getId(), TOperatorLogConstant.TITLE_ORDER_CREATE);
                           break;
                       case "确认收货":
                           operatorLogList = operatorlogDao.getOperatorLogBySend(order.getId(), TOperatorLogConstant.TITLE_ORDER_RECEIVE);
                           break;
                   }

                   if (operatorLogList != null && operatorLogList.size()>0){
                       OperatorLog operatorLog = operatorLogList.get(0);
                       vo.setOperaterName(operatorLog.getUserName());
                   }
                }
            }else {
                OaAgentUserinfo oaAgentUser = oaAgentUserinfoMapper.findOaAgentUser(sysMsgListVO.getAgentId(),sysMsgListVO.getShopId());
                if(oaAgentUser != null){
                    vo.setOperaterName(oaAgentUser.getUserName());
                }
            }

            list.add(vo);
        });

        return list;
    }

    @Override
    public Integer tagSysMsg(Long memberId, Integer msgId) {
        return sysMessageMapper.tagSysMsg(memberId, msgId);
    }

    @Override
    public Integer tagSysMsgAll(Long memberId) {
        return sysMessageMapper.tagSysMsgAll(memberId);
    }

}

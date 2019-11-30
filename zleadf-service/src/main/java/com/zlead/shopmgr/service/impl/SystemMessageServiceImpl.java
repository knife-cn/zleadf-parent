package com.zlead.shopmgr.service.impl;

import com.zlead.dao.OrderDao;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.vo.SysMsgListVO;
import com.zlead.fplat.dao.SysMessageMapper;
import com.zlead.shopmgr.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    private SysMessageMapper sysMessageMapper;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer hasNotReadSysMsg(Long memberId) {
        Integer count = sysMessageMapper.hasNotReadSysMsg(memberId, 1);
        int result = 0;
        if(count > 0){
            result = 1;
        }
        return result;
    }

    @Override
    public List<SysMsgListVO> notReadSysMsgList(Long memberId) {
        List<SysMsgListVO> sysMsgList = sysMessageMapper.notReadSysMsgList(memberId, 1);
        sysMsgList.forEach(sysMsgListVO ->{
            if(sysMsgListVO.getSnType() != null && sysMsgListVO.getSnType() == 1){
                OrderEntity orderEntity = orderDao.findBySn(sysMsgListVO.getSn());
                    sysMsgListVO.setOrderId(orderEntity.getId());
                    sysMsgListVO.setOperaterName(orderEntity.getMemberName());
            }
        });
        return sysMsgList;
    }

    @Override
    public Integer tagSysMsg(Long memberId, Integer msgId) {
        return sysMessageMapper.tagSysMsg(memberId, msgId);
    }

}

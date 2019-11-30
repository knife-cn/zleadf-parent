package com.zlead.shopmgr.service;

import com.zlead.entity.vo.SysMsgListVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SystemMessageService {

    /**
     * 查询member是否有未读系统消息
     * @param memberId
     * @return
     */
    Integer hasNotReadSysMsg(Long memberId);

    /**
     * 查询member未读的系统消息列表
     * @param memberId
     * @return
     */
    List<SysMsgListVO> notReadSysMsgList(Long memberId);

    /**
     * 标记未读系统消息已读
     * @param memberId
     * @param msgId
     * @return
     */
    Integer tagSysMsg(Long memberId, Integer msgId);
}

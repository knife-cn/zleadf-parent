package com.zlead.fplat.dao;

import com.zlead.entity.vo.SysMsgListVO;
import com.zlead.fplat.entity.SysMessage;
import com.zlead.fplat.entity.dto.SysMessagePageDto;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName zleadf-mall-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/17.
 * @Desoription TODO
 */
@Repository
public interface SysMessageMapper {

    /**
     * 系统消息
     * @param agentId
     * @param pageBounds
     * @return
     */
    PageList<SysMessagePageDto> findByPageList(@Param("agentId") Integer agentId,@Param("status") int status, PageBounds pageBounds);
    
    /**
     * 系统消息
     * @param agentId
     * @param pageBounds
     * @return
     */
    Integer findCntByPageList(@Param("agentId") Integer agentId,@Param("status") int status);

    /**
     * 更新消息 已读
     * @param id
     * @return
     */
    int updateMessage(@Param("id") int id,@Param("agentId") int agentId);

    /**
     * 添加消息
     * @param sysMessage
     * @return
     */
    int saveMessage(SysMessage sysMessage);

    /**
     * 查询member是否有未读系统消息
     * @param memberId
     * @param snType 消息编号类型 1.订单 2.关联工厂
     * @return
     */
    Integer hasNotReadSysMsg(@Param("memberId") Long memberId, @Param("snType")int snType);

    /**
     * 查询member未读的系统消息列表
     * @param memberId
     * @param snType
     * @return
     */
    List<SysMsgListVO> notReadSysMsgList(@Param("memberId")Long memberId, @Param("snType")int snType);

    /**
     * 标记member未读系统消息已读
     * @param memberId
     * @param msgId
     * @return
     */
    Integer tagSysMsg(@Param("memberId") Long memberId, @Param("msgId") Integer msgId);

    /**
     * 选择性插入系统消息
     * @param sysMessage
     * @return
     */
    Integer insertSelective(SysMessage sysMessage);

}

package com.zlead.fplat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zlead.common.PageResponse;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.constant.SysMsgConstant;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.dao.SysMessageMapper;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.entity.SysMessage;
import com.zlead.fplat.entity.dto.SysMessagePageDto;
import com.zlead.fplat.service.SysMessageService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/17.
 * @Desoription TODO
 */
@Service
@Transactional
public class SysMessageServiceImpl implements SysMessageService{

    private static final Logger logger = LoggerFactory.getLogger(SysMessageServiceImpl.class);

    @Autowired
    private SysMessageMapper sysMessageMapper;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private OaFactoryInfoMapper oaFactoryInfoMapper;

    @Override
    public PageResponse findByPage(int agentId,int status, PageBounds pageBounds) {
        logger.info("查询系统消息入参：agentId = {},分页参数：{}",agentId,pageBounds);
        PageResponse pageResponse = new PageResponse();
        Page page = PageHelper.startPage(pageBounds.getPageNumber(),pageBounds.getPageSize(),true);
        PageList<SysMessagePageDto> pageList = sysMessageMapper.findByPageList(agentId,status,pageBounds);
        Integer cnt=sysMessageMapper.findCntByPageList(agentId,status);
        //pageResponse.setCount(pageList.size());
        pageResponse.setCount(cnt);
        pageResponse.setPage(pageBounds);
        pageResponse.setData(pageList);
        logger.info("系统消息返回：{}",pageResponse);
        return pageResponse;
    }

    @Override
    public void updateMessage(int id,int agentId) {
        logger.info("消息状态已读 id= {}" ,id);
        int status = sysMessageMapper.updateMessage(id,agentId);
        logger.info("更新消息状态 status = {}" ,status);
    }

    @Override
    public JsonResult save(SysMessage sysMessage) {
        logger.info("添加消息入参 ：{}",sysMessage);
        if(sysMessage.getAgentId() == 0){
            return new JsonResult(2,"代理商id不能为空","",false);
        }
        if(StringUtils.isEmpty(sysMessage.getContent())){
            return new JsonResult(2,"消息内容不能为空","",false);
        }
        if(sysMessage.getMsgtype() == 0){
            return new JsonResult(2,"消息类型不能为空","",false);
        }
        if(sysMessage.getMemberId() == 0){
            return new JsonResult(2,"会员id不能为空","",false);
        }
        int status = sysMessageMapper.saveMessage(sysMessage);
        logger.info("消息添加状态:{}",status);
       return new JsonResult(1,"添加消息成功","",true);
    }

    @Override
    public void insertAgentFactorySysMsg(MemberEntity member, Integer factoryId) {
        String agentName = null;
        if(member.getMemberType() == 6){
            agentName = member.getUsername();
        }else if(member.getMemberType() == 3){
            OaAgentMas agent = oaAgentMasMapper.selectByPrimaryKey(member.getAgentId().intValue());
            agentName = agent.getAgentName();
        };
        OaFactoryInfo factory = oaFactoryInfoMapper.selectByPrimaryKey(factoryId);
        SysMessage message = new SysMessage();
        this.setSysMsgBaseInfo(message,member);
        Date now = new Date();
        message.setTitle(SysMsgConstant.APPLY_AGENT_FAC_TITLE);
        message.setContent(MessageFormat.format(SysMsgConstant.APPLY_AGENT_FAC_CONTENT, agentName, SysMsgConstant.sdf.format(now), factory.getFactName()));
        message.setSnType(2);
        message.setSn(factoryId.toString());
        message.setCreateTime(now);
        sysMessageMapper.insertSelective(message);
    }

    @Override
    public void insertOrderSysMsg(OrderEntity order, MemberEntity memberEntity, Integer msgType) {
        SysMessage message = new SysMessage();
        this.setSysMsgBaseInfo(message,memberEntity);
        Date now = new Date();
        if(msgType == 1){
            message.setTitle(SysMsgConstant.CANCLE_ORDER_TITLE);
            message.setContent(MessageFormat.format(SysMsgConstant.CANCLE_ORDER_CONTENT, order.getShopName(), SysMsgConstant.sdf.format(now), order.getSn()));
        }else if(msgType == 2){
            OaAgentMas agentMas = oaAgentMasMapper.selectByPrimaryKey(memberEntity.getAgentId().intValue());
            message.setTitle(SysMsgConstant.RECEIVE_ORDER_TITLE);
            message.setContent(MessageFormat.format(SysMsgConstant.RECEIVE_ORDER_CONTENT, agentMas.getAgentName(), SysMsgConstant.sdf.format(now), order.getSn()));
        }
        message.setSnType(1);
        message.setSn(order.getSn());
        message.setCreateTime(now);
        sysMessageMapper.insertSelective(message);
    }

    @Override
    public void insertVoucherSysMsg(MemberEntity memberEntity, BigDecimal amount,Boolean isApp) {
        String agentName = null;
        if(memberEntity.getMemberType() == 6){
            agentName = memberEntity.getUsername();
        }else if(memberEntity.getMemberType() == 3){
            OaAgentMas agent = oaAgentMasMapper.selectByPrimaryKey(memberEntity.getAgentId().intValue());
            agentName = agent.getAgentName();
        }
        SysMessage message = new SysMessage();
        this.setSysMsgBaseInfo(message,memberEntity);
        Date now = new Date();
        message.setTitle(SysMsgConstant.UPLOAD_VOUCHER_TITLE);
        message.setContent(MessageFormat.format(SysMsgConstant.UPLOAD_VOUCHER_CONTENT, agentName, SysMsgConstant.sdf.format(now), amount));
        message.setCreateTime(now);
        if (isApp){  //如果是App上传的凭证，设置已读时间
            message.setReadTime(now);
        }
        sysMessageMapper.insertSelective(message);
    }

    /**
     * 设置系统消息共有信息
     * @param message
     * @param memberEntity
     */
    private void setSysMsgBaseInfo(SysMessage message, MemberEntity memberEntity){
        message.setMemberId(memberEntity.getId().intValue());
        message.setAgentId(memberEntity.getAgentId()!=null?memberEntity.getAgentId().intValue():memberEntity.getId().intValue());
        message.setSysId(memberEntity.getOwnShopid() != null? memberEntity.getOwnShopid().intValue() : 0);
        message.setStatus(0);
        message.setMsgtype(0);
    }

}

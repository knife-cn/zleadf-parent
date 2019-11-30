package com.zlead.fplat.service.impl;

import com.zlead.dao.AclUserDao;
import com.zlead.dao.MemberDao;
import com.zlead.dao.OrderDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.dto.VoucherDto;
import com.zlead.entity.vo.VoucherVo;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.AcctPayType;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.Voucher;
import com.zlead.fplat.service.VoucherService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import com.zlead.utils.AppUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.management.Agent;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/22.
 * @Desoription TODO
 */
@Transactional
@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private AclUserDao aclUserDao;
    @Autowired
    private AgentFacMapper agentFacMapper;
    @Autowired
    private AcctPayTypeMapper acctPayTypeMapper;
    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private MemberDao memberDao;

    //添加凭证
    @Override
    @Transactional
    public void addVoucher(Voucher voucher, MemberEntity memberEntity, List<Integer> orderIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long memberId = memberEntity.getId();
            voucher.setUploadMember(memberId.intValue());

            voucher.setModifyMember(memberId.intValue());
            voucher.setMemberId(memberId);
            if (memberEntity.getAgentId() != null)
                voucher.setBuyer_corpid(memberEntity.getAgentId());
            if (CollectionUtils.isNotEmpty(orderIds)) {
                OrderEntity order = orderDao.getOrderInfoId(orderIds.get(0).longValue());
                Long shopId = 0L;
                shopId = order.getShopId();
                voucher.setShopId(shopId.intValue());
                //上传凭证时添加saleUser及isFact，方便数据权限筛选
                voucher.setSaleUser(order.getUserId());
                voucher.setIsFact("0");
                voucherMapper.save(voucher);
                int voucherId = voucher.getId();
                orderDao.updateVoucher(orderIds, voucherId);
            }
        } catch (Exception e) {
            System.out.println("保存失败");
            throw new RuntimeException("插入异常");
        }
        System.out.println("保存成功");
    }

    //凭证列表
    @Override
    public PageList<Voucher> voucherList(String memberIds, PageBounds rowBounds) {
        PageList<Voucher> voucherList = null;
        try {
            voucherList = voucherMapper.getAllVoucher(memberIds, rowBounds);
        } catch (Exception e) {
            System.out.println("保存失败");
            return null;
        }
        System.out.println("保存成功");
        return voucherList;
    }

    //根据id获取凭证
    @Override
    public VoucherVo findVoucherById(int id, Integer sysId) {
        Voucher voucher = voucherMapper.findById(id);
        VoucherVo voucherVo = new VoucherVo();
        String format = "yyyy-MM-dd";//日期格式
        if ("1".equals(voucher.getIsFact())) {
            voucherVo.setAgentName(voucher.getAgentName());
            voucherVo.setMemberId(voucher.getMemberId());
            voucherVo.setPayType(voucher.getPayType());
            voucherVo.setPayTypeName(voucher.getPayTypeName());
        } else {
            OrderEntity order = orderDao.findOrderByVoucherId(id, voucher.getMemberId());
            MemberEntity memberEntity = memberDao.findById(order.getMemberId().longValue());
            OaAgentMas oaAgentMas = oaAgentMasMapper.selectByPrimaryKey(memberEntity.getAgentId().intValue());
            //商城端付款方式
            String payRemark = voucherMapper.findPayRemarkById(id);
            voucherVo.setPayType(order.getPayType());
            voucherVo.setPayTypeName(voucher.getPayTypeName());
            voucherVo.setMemberId(memberEntity.getAgentId());
            voucherVo.setAgentName(oaAgentMas.getAgentName());
            voucherVo.setPayTypeName(payRemark);
        }
        String date = new SimpleDateFormat(format).format(voucher.getPayTime());
        voucherVo.setPayTime(date);
        voucherVo.setPayerPhone(voucher.getPayerPhone());
        voucherVo.setPayer(voucher.getPayer());
        if (StringUtils.isBlank(voucherVo.getAgentName())) {
            //如果凭证的代理商名称为空那么根据代理商的custId在拿一次
            Integer custId = voucher.getCustId();
            AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId2(custId, sysId);
            voucherVo.setPayer(agentFac.getAgentName());
            voucherVo.setAgentName(agentFac.getAgentName());
        }
        voucherVo.setAmount(voucher.getAmount());
        voucherVo.setImg(voucher.getImg());
        voucherVo.setVoucherId(voucher.getId());


        return voucherVo;
    }

    /**
     * 编辑凭证接口
     *
     * @param voucherDto
     * @return int 1:成功 0:失败
     */
    @Override
    public int editVoucherById(VoucherDto voucherDto) {
        Voucher voucher = new Voucher();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            voucher.setPayTime(sdf.parse(voucherDto.getPayTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        voucher.setId(voucherDto.getVoucherId());
        voucher.setBuyerCorpId(voucherDto.getAgentId().longValue());
        voucher.setPayType(voucherDto.getPayType());
        voucher.setAmount(voucherDto.getAmount());
        voucher.setImg(voucherDto.getImg());
        int isSuccess = voucherMapper.updateByPrimaryKey(voucher);
        return isSuccess;
    }

    //根据id获取凭证
    @Override
    public Voucher findByOrderId(int orderId) {
        return voucherMapper.findById(orderId);
    }

    @Override
    public int insertVoucher(VoucherDto voucherDto, MemberEntity member, HttpServletRequest request) throws Exception {
        int signResult = AppUtil.verifySign(request);
        if (signResult == AppUtil.SIGN_OK) {
            AclUserEntity aclUserEntity = aclUserDao.findUserByMemberId(member.getId());
            //Integer agentId = agentFacMapper.findAgentFacBySysId(aclUserEntity.getSysId());
            Integer agentId = voucherDto.getAgentId();
            if (agentId == null) {
                AgentFac agentFac = agentFacMapper.findAgentByShopIdAndAgentName(aclUserEntity.getSysId(), voucherDto.getAgentName());
                if (agentFac != null) {
                    agentId = agentFac.getAgentId();
                }
            }
            String payTypeName = acctPayTypeMapper.findByPayType(voucherDto.getPayType());
            Voucher voucher = new Voucher();
            voucher.setMemberId(member.getId());
            voucher.setUploadMember(aclUserEntity.getUserId());
            voucher.setShopId(member.getOwnShopid().intValue());
            voucher.setAmount(voucherDto.getAmount());
            voucher.setPayType(voucherDto.getPayType());
            String format = "yyyy-MM-dd";//日期格式
            Date date = new SimpleDateFormat(format).parse(voucherDto.getPayTime());
            voucher.setPayTime(date);
            voucher.setImg(voucherDto.getImg());
            voucher.setUploadTime(new Date());
            voucher.setModifyTime(new Date());
            voucher.setModifyMember(member.getId().intValue());
            voucher.setPayTypeName(payTypeName);
            voucher.setSaleUser(aclUserEntity.getUserId());
            voucher.setBuyerCorpId(agentId.longValue());
            voucher.setAgentName(voucherDto.getAgentName());
            voucher.setIsFact("1");
            return voucherMapper.insertVoucher(voucher);
        } else {
            return signResult;
        }
    }

    @Override
    public List<VoucherVo> getVoucherList(Long memberId, PageBounds pageBounds) {
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        return voucherMapper.getVouchers(memberId, userIds, pageBounds);
    }

}

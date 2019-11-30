package com.zlead.fplat.service.impl;

import com.zlead.dao.AclUserDao;
import com.zlead.dao.OrderDao;
import com.zlead.entity.AclUserEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.dto.VoucherDto;
import com.zlead.entity.vo.VoucherVo;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.VoucherMapper;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.entity.Voucher;
import com.zlead.fplat.service.VoucherService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.management.Agent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/22.
 * @Desoription TODO
 */
@Transactional
@Service
public class VoucherServiceImpl implements VoucherService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private AclUserDao aclUserDao;
    @Autowired
    private AgentFacMapper agentFacMapper;
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
            if(memberEntity.getAgentId()!=null)
            	voucher.setBuyer_corpid(memberEntity.getAgentId());
            if (CollectionUtils.isNotEmpty(orderIds)){
                OrderEntity order= orderDao.getOrderInfoId(orderIds.get(0).longValue());
                Long shopId=0L;
                shopId=order.getShopId();
                voucher.setShopId(shopId.intValue());
                //上传凭证时添加saleUser及isFact，方便数据权限筛选
                voucher.setSaleUser(order.getUserId());
                voucher.setIsFact("0");
                voucherMapper.save(voucher);
                int voucherId = voucher.getId();
                orderDao.updateVoucher(orderIds, voucherId);
            }
        }catch (Exception e){
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
        }catch (Exception e){
            System.out.println("保存失败");
            return null;
        }
        System.out.println("保存成功");
        return voucherList;
    }

    //根据id获取凭证
    @Override
    public Voucher findById(int id){
        return voucherMapper.findById(id);
    }
    
    //根据id获取凭证
    @Override
    public Voucher findByOrderId(int orderId){
        return voucherMapper.findById(orderId);
    }

    @Override
    public int insertVoucher(VoucherDto voucherDto,MemberEntity member) throws ParseException {
        AclUserEntity aclUserEntity = aclUserDao.findUserByMemberId(member.getId());
        Integer agentId = agentFacMapper.findAgentFacBySysId(aclUserEntity.getSysId());
        Voucher voucher = new Voucher();
        voucher.setMemberId(member.getId());
        voucher.setUploadMember(aclUserEntity.getUserId());
        voucher.setShopId(member.getOwnShopid().intValue());
        voucher.setAmount(voucherDto.getAmount());
        voucher.setPayer(voucherDto.getPayer());
        voucher.setPayType(voucherDto.getPayType());
        String format = "yyyy/MM/dd HH:mm:ss";//日期格式
        Date date = new SimpleDateFormat(format).parse(voucherDto.getPayTime());
        voucher.setPayTime(date);
        voucher.setPayerPhone(voucherDto.getPayerPhone());
        voucher.setImg(voucherDto.getImg());
        voucher.setUploadTime(new Date());
        voucher.setModifyTime(new Date());
        voucher.setModifyMember(member.getId().intValue());
        voucher.setSaleUser(aclUserEntity.getUserId());
        voucher.setBuyerCorpId(agentId.longValue());
        voucher.setAgentName(voucherDto.getAgentName());
        voucher.setIsFact("1");
        return voucherMapper.insertVoucher(voucher);
    }

    @Override
    public List<VoucherVo> getVoucherList(Long memberId, PageBounds pageBounds) {
        return voucherMapper.getVouchers(memberId,pageBounds);
    }

}

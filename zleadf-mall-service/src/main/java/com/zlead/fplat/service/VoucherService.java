package com.zlead.fplat.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.VoucherDto;
import com.zlead.entity.vo.VoucherVo;
import com.zlead.fplat.entity.Voucher;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import java.text.ParseException;
import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/22.
 * @Desoription TODO
 */
public interface VoucherService {

    //添加凭证
    void addVoucher(Voucher voucher, MemberEntity memberEntity, List<Integer> orderIds);

    PageList<Voucher> voucherList(String memberIds, PageBounds rowBounds);

    Voucher findById(int id);
    
    Voucher findByOrderId(int OrderId);

    int insertVoucher(VoucherDto voucherDto,MemberEntity member) throws ParseException;

    List<VoucherVo> getVoucherList(Long memberId, PageBounds pageBounds);
}

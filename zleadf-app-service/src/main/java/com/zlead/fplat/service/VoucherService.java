package com.zlead.fplat.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.VoucherDto;
import com.zlead.entity.vo.VoucherVo;
import com.zlead.fplat.entity.Voucher;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import javax.servlet.http.HttpServletRequest;
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

    Voucher findByOrderId(int OrderId);

    int insertVoucher(VoucherDto voucherDto, MemberEntity member, HttpServletRequest request) throws Exception;

    List<VoucherVo> getVoucherList(Long memberId, PageBounds pageBounds);

    VoucherVo findVoucherById(int id, Integer sysId);

    int editVoucherById(VoucherDto voucherDto);
}

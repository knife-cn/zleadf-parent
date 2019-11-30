package com.zlead.fplat.dao;

import java.util.List;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.VoucherDto;
import com.zlead.entity.vo.VoucherVo;
import com.zlead.fplat.entity.Voucher;

import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherMapper {

    void save(Voucher voucher);
    
    /**
     * This method:deleteByPrimaryKey
     *   t_voucher
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     *   t_voucher
     *
     * @ET
     */
    int insert(Voucher record);

    /**
     * This method:insertSelective
     *   t_voucher
     *
     * @ET
     */
    int insertSelective(Voucher record);

 
    /**
     * This method:selectByPrimaryKey
     *   t_voucher
     *
     * @ET
     */
    Voucher selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_voucher
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Voucher record);

    /**
     * This method:updateByPrimaryKey
     *   t_voucher
     *
     * @ET
     */
    int updateByPrimaryKey(Voucher record);
    
    List<Voucher> selectByOrder(@Param(value = "shopId") Long shopId);



    PageList<Voucher> getAllVoucher(@Param("memberIds") String memberIds, PageBounds rowBounds);


    Voucher findById(@Param("id") int id);
    
    Voucher findByOrderId(@Param("orderId") int orderId);

    int insertVoucher(Voucher voucher);

    List<VoucherVo> getVouchers(@Param("memberId") Long memberId, PageBounds rowBounds);

    int updateSaleNameById(@Param("saleName")String saleName,@Param("voucherId")int voucherId);
}
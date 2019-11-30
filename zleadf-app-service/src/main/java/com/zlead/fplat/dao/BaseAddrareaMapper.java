package com.zlead.fplat.dao;


import com.zlead.fplat.entity.BaseAddrarea;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseAddrareaMapper {

    /**
     * 根据区域代理查询区域名称
     * @param areaCodes
     * @return
     */
    List<BaseAddrarea> findAddressNameByCodes(@Param("areaCodes")Collection<String> areaCodes);

    /**
     * 查询所有的地址
     * @return
     */
    List<Map<String,String>> findAllAddress();

    /**
     * 根据代码查询名称
     * @param code
     * @return
     */
    String findAddressNameByCode(@Param("code") String code);

}
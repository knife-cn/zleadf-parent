package com.zlead.shopmgr.service.impl;

import com.puqian.util.RedisCacheClient;
import com.zlead.fplat.dao.BaseAddrareaMapper;
import com.zlead.shopmgr.service.BaseAddrareaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseAddrareaServiceImpl implements BaseAddrareaService {
    @Autowired
    private BaseAddrareaMapper baseAddrareaMapper;
    @Autowired
    private RedisCacheClient redisCacheClient;

    /**
     * 根据代码查询名称
     * @param code
     * @return
     */
    @Override
    public String findAddrareaNameByCode(String code) {
        String key = "ADDRESS_" + code;
        String name = redisCacheClient.get(key);
        if(StringUtils.isBlank(name)){
            name = this.baseAddrareaMapper.findAddressNameByCode(code);
            redisCacheClient.set(key,name);
        }
        return name;
    }
}

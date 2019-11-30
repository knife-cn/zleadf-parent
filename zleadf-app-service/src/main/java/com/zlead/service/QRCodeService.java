package com.zlead.service;

import com.zlead.entity.MemberEntity;

import javax.servlet.http.HttpServletRequest;

public interface QRCodeService {
    /**
     * 生成企业商城二维码
     * @param request
     * @return
     */
    String getTwoBarCode(HttpServletRequest request, MemberEntity memberEntity);
}

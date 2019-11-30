package com.zlead.service;

import java.math.BigDecimal;

 
public interface AccountChangesService {
 
    boolean addAmount(String memberId, Integer accountType, BigDecimal amount, String remoteIp, String systemId,
                      String businessName);
    boolean minusAmount(String memberId, Integer accountType, BigDecimal amount, String remoteIp, String systemId,
                        String businessName);
    boolean freezeAmount(String memberId, Integer accountType, BigDecimal amount, String remoteIp, String systemId,
                         String businessName);
    boolean unfreezeAmount(String memberId, Integer accountType, BigDecimal amount, String remoteIp, String systemId,
                           String businessName);
//    boolean memberConsumeOrderPoint(TOrderEntity order, BigDecimal payPoint, String systemId, String remoteIp,
//                                    String businessName);
//    boolean freezeAndSetOrderPointBeforePay(TOrderEntity order, BigDecimal totalPoint, String systemId, String remoteIp,
//                                            String businessName);
//    boolean refundUnpaidOrderPoint(TOrderEntity order, String systemId, String remoteIp, String businessName);
//    boolean hasEnoughPoint(Long memberId,BigDecimal point,String systemId);
//    boolean refundPaidOrderPoint(TOrderEntity order, String systemId, String remoteIp, String businessName);
 
}

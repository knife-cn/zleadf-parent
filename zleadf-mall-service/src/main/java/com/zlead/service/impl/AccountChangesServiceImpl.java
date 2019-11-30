package com.zlead.service.impl;

import com.zlead.dao.MemberAcctDao;
import com.zlead.dao.MemberDao;
import com.zlead.dao.OrderDao;
import com.zlead.entity.MemberAcctEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.service.AccountChangesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Transactional
@Service("AccountChangesService")
public class AccountChangesServiceImpl implements AccountChangesService {

/*
    @Autowired
    private IZxLogService logService;

    @Autowired
    private TransLogMapper transLogMapper;*/

    @Autowired
    private MemberAcctDao memberAcctDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    public MemberAcctEntity cloneObj(MemberAcctEntity zxMemberAcct){

        MemberAcctEntity cloneZxMemberAcct=new MemberAcctEntity();
        cloneZxMemberAcct.setBlance(zxMemberAcct.getBlance());
        cloneZxMemberAcct.setFreezeBlance((zxMemberAcct.getFreezeBlance()));
        cloneZxMemberAcct.setAccount(zxMemberAcct.getAccount());
        cloneZxMemberAcct.setModifyDate(zxMemberAcct.getModifyDate()!=null?new Date(zxMemberAcct.getModifyDate().getTime
                ()):null);
        cloneZxMemberAcct.setFreezeDate(zxMemberAcct.getFreezeDate()!=null?new Date(zxMemberAcct.getFreezeDate().getTime()):null);
        //cloneZxMemberAcct.set(zxMemberAcct.getUpdateDate()!=null?new Date(zxMemberAcct.getUpdateDate().getTime()):null);
        cloneZxMemberAcct.setCreateDate(zxMemberAcct.getCreateDate()!=null?new Date(zxMemberAcct.getCreateDate().getTime()):null);
        cloneZxMemberAcct.setId(zxMemberAcct.getId());
        cloneZxMemberAcct.setAcctName(zxMemberAcct.getAcctName());
        cloneZxMemberAcct.setAccounttype(zxMemberAcct.getAccounttype());
        cloneZxMemberAcct.setMemberid(zxMemberAcct.getMemberid());
        cloneZxMemberAcct.setMembertype(zxMemberAcct.getMembertype());
        cloneZxMemberAcct.setRemark(zxMemberAcct.getRemark());
        cloneZxMemberAcct.setSystemid(zxMemberAcct.getSystemid());
        return cloneZxMemberAcct;
    }
/*
    private void addTransLog(MemberAcctEntity oldAcct,
                             MemberAcctEntity newAcct,
                             BigDecimal dealamount,
                             Integer tradeCode,
                             String remoteIp,
                             String systemId, String businessName) {
        try{
            //ThreadLocal备用
//        HashMap<Long, String> record = SystemInfoUtils.serialNo.get();
//        if(record.containsKey(newAcct.getUserAcctid())){
//            String operationNo = record.get(newAcct.getUserAcctid())+1;
//            record.put(newAcct.getUserAcctid(),operationNo);
//
//        }else{
//            record.clear();
//            record.put(newAcct.getUserAcctid(),"1");
//        }
            //添加资金流水记录
            ZxTransLog transLog = new ZxTransLog();
            if (newAcct.getMemberid() != null) {
                transLog.setMemberid(Integer.parseInt(String.valueOf(newAcct.getMemberid())));
            }
            transLog.setUsername(newAcct.getUsername());
            transLog.setTradeCode(String.valueOf(tradeCode));
            transLog.setAccount(oldAcct.getAccount());
            transLog.setDealMoney(dealamount);
            transLog.setBlance(oldAcct.getBlance());
            transLog.setFreezeBlance(oldAcct.getFreezeBlance());
            transLog.setSystemid(systemId);//newAcct.getSystemid());
            transLog.setMembertype(newAcct.getMembertype());
            transLog.setCreateDate(new Date());
            transLog.setAddip(remoteIp);
            transLog.setAccounttype(newAcct.getAccounttype());
            //FIXME remark长度挺容易超过 256的。
            transLog.setRemark(newAcct.getAcctName() +
                    ",业务:"+businessName+
                    ",操作:"+tradeCode+" " +
                    ",deal:" + dealamount +
                    ", 变化前"+ oldAcct.toString() +
                    ",变化后"+ newAcct.toString() );
//        System.out.println("addTransLog-------memberid:" + newAcct.getMemberid() + " username: " + newAcct.getUsername
//                () + " dealamount:" + dealamount + " " + transLog.getRemark());
            transLogMapper.insert(transLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/

/**
     * 增加金额（积分） 根据账户类型
     *
     * @param memberId    被操作的用户会员id
     * @param accountType 账户类型是0则对总账和现金账户操作，账户类型是1则对总积分和积分账户操作
     * @param amount      这次操作的金额（积分数值）
     * @param remoteIp
     * @param businessName
     * @return
     */
    @Override
    public boolean addAmount(String memberId, Integer accountType, BigDecimal amount, String remoteIp, String systemId,
                             String businessName) {

        if (remoteIp == null || remoteIp.isEmpty()) {
            remoteIp = "<NO_IP>";
        }
        //改总账
        MemberEntity zxMember = memberDao.findByMemberId(memberId);

        //基本账户和 电商现金账户
        if (accountType.equals(MemberAcctEntity.BASE_ACCOUNT) || accountType.equals(MemberAcctEntity.SHOPPINGCASH_ACCOUNT)||
                accountType.equals(MemberAcctEntity.AGENT_CASH_ACCOUNT)||accountType.equals(MemberAcctEntity.VOUCHER_ACCOUNT)) {
            zxMember.setAccount(zxMember.getAccount().add(amount));
        } else {
            if(zxMember.getPoint()==null){
                System.out.println("zxMember.getPoint() is null, memberid: "+memberId);
                zxMember.setPoint(amount.longValue());
            }else{
                zxMember.setPoint(zxMember.getPoint() + amount.longValue());
            }
        }
        memberDao.update(zxMember);

        MemberAcctEntity zxMemberAcct = checkAndGetMemberAcct(memberId, accountType, systemId, "增加账户金额时，没有此账户，新增");

        MemberAcctEntity oldAcct = cloneObj(zxMemberAcct);

        zxMemberAcct.setAccount(zxMemberAcct.getAccount().add(amount));
        zxMemberAcct.setBlance(zxMemberAcct.getBlance().add(amount));//加钱
        zxMemberAcct.setModifyDate(new Date());
        //addTransLog(oldAcct, zxMemberAcct, amount, 1, remoteIp, systemId, businessName);
        return memberAcctDao.updateByPrimaryKeySelective(zxMemberAcct) > 0;
    }

    private MemberAcctEntity checkAndGetMemberAcct(String memberId, Integer accountType, String systemId, String
            remark ) {
        MemberAcctEntity memberAcctEntity = memberAcctDao.findByMemberid(memberId, accountType, systemId);
        MemberEntity zxMember=memberDao.findByMemberId(memberId);
        if (memberAcctEntity == null) {
            memberAcctEntity = MemberAcctEntity.newInstance(zxMember,
                    accountType,
                    remark + zxMember.getUsername() + "账户类型为" + accountType + "的账户",
                    systemId);

            memberAcctDao.insert(memberAcctEntity);
        }
        memberAcctEntity = memberAcctDao.findByMemberid(memberId, accountType, systemId);
        return memberAcctEntity;
    }

   @Override
    public boolean minusAmount(String memberId,
                               Integer accountType,
                               BigDecimal amount,
                               String remoteIp,
                               String systemId, String businessName) {
        if (remoteIp == null || remoteIp.isEmpty()) {
            remoteIp = "<NO_IP>";
        }
        amount = amount.negate();
        //改总账
       MemberEntity memberEntity = memberDao.findByMemberId(memberId);
        if (accountType.equals(MemberAcctEntity.BASE_ACCOUNT) || accountType.equals(MemberAcctEntity.SHOPPINGCASH_ACCOUNT)
                ||accountType.equals(MemberAcctEntity.AGENT_CASH_ACCOUNT)||accountType.equals(MemberAcctEntity.VOUCHER_ACCOUNT)) {
            memberEntity.setAccount(memberEntity.getAccount().add(amount));
        } else {
            memberEntity.setPoint(memberEntity.getPoint() + amount.longValue());
        }
        memberDao.update(memberEntity);


       MemberAcctEntity memberAcctEntity = checkAndGetMemberAcct(memberId, accountType, systemId, "扣减账户金额时，没有此账户，新增");

//        ZxMemberAcct zxMemberAcct = listMemberAcct.get(0);
        MemberAcctEntity oldAcct = cloneObj(memberAcctEntity);


        memberAcctEntity.setAccount(memberAcctEntity.getAccount().add(amount));
        memberAcctEntity.setBlance(memberAcctEntity.getBlance().add(amount));
        memberAcctEntity.setModifyDate(new Date());
      //  memberAcctEntity(oldAcct, zxMemberAcct, amount, 2, remoteIp, systemId, businessName);

        return memberAcctDao.updateByPrimaryKeySelective(memberAcctEntity) > 0;
    }

    /**
     * 根据账户类型冻结金额（积分），会增加冻结现金/积分，减少可用现金/积分
     *
     * @param memberId
     * @param accountType 账户类型是0则是冻结基本账户金额，1则是冻结积分账户积分
     * @param amount
     * @param remoteIp
     * @param businessName
     * @return
     */
    @Override
    public boolean freezeAmount(String memberId,
                                Integer accountType,
                                BigDecimal amount,
                                String remoteIp,
                                String systemId, String businessName) {
        if (remoteIp == null || remoteIp.isEmpty()) {
            remoteIp = "<NO_IP>";
        }
        MemberAcctEntity zxMemberAcct = memberAcctDao.findByMemberid(memberId, accountType, "1");
        if (zxMemberAcct == null || zxMemberAcct.getUsername() == null) {
            MemberEntity zxMember = memberDao.findByMemberId(memberId);
            zxMemberAcct = MemberAcctEntity.newInstance(zxMember, accountType, null, systemId);
            memberAcctDao.insert(zxMemberAcct);
            System.out.println("accountService freezeAmount:" + zxMemberAcct.getUsername());
        }
        MemberAcctEntity oldAcct = cloneObj(zxMemberAcct);


        zxMemberAcct.setBlance(zxMemberAcct.getBlance().add(amount.negate()));//减余额
        zxMemberAcct.setFreezeBlance(zxMemberAcct.getFreezeBlance().add(amount));//加冻结金额
        zxMemberAcct.setFreezeDate(new Date());
        //addTransLog(oldAcct, zxMemberAcct, amount, 3, remoteIp, systemId, businessName);
        return memberAcctDao.updateByPrimaryKeySelective(zxMemberAcct) > 0;
    }

    @Override
    public boolean unfreezeAmount(String memberId,
                                  Integer accountType,
                                  BigDecimal amount,
                                  String remoteIp,
                                  String systemId, String businessName) {
        if (remoteIp == null || remoteIp.isEmpty()) {
            remoteIp = "<NO_IP>";
        }
        //amount=amount.negate();
        MemberAcctEntity zxMemberAcct = memberAcctDao.findByMemberid(memberId, accountType, "1");
        if (zxMemberAcct == null || zxMemberAcct.getUsername() == null) {
            MemberEntity zxMember = memberDao.findByMemberId(memberId);
            zxMemberAcct = MemberAcctEntity.newInstance(zxMember, accountType, "unfreezeAmount 创建的账户。", systemId);
            memberAcctDao.insert(zxMemberAcct);
            System.out.println("accountService unfreezeAmount:" + zxMemberAcct.getUsername());
        }
        MemberAcctEntity oldAcct = cloneObj(zxMemberAcct);

        zxMemberAcct.setBlance(zxMemberAcct.getBlance().add(amount));//加余额
        zxMemberAcct.setFreezeBlance(zxMemberAcct.getFreezeBlance().subtract(amount));//减冻结金额
        zxMemberAcct.setFreezeDate(new Date());
        //addTransLog(oldAcct, zxMemberAcct, amount, 4, remoteIp, systemId, businessName);
        return memberAcctDao.updateByPrimaryKeySelective(zxMemberAcct) > 0;
    }


     /*
    @Override
    public boolean memberConsumeOrderPoint(ZxOrder order, BigDecimal payPoint, String systemId, String remoteIp,
                                           String businessName) {
        //订单状态不为初始状态或待支付状态，或者订单支付积分>0 说明是已支付的订单
//    	if(order.getStatus()>0 || order.getPayPoint()>0L){//FIXME 调用该方法前订单状态已经被别人改变
//            return false;
//        }

        String remark="--------买家扣除订单消耗积分-------"+"\n";
        Long idOfMember = order.getMemberId();
        remark+="买家基本积分账户-解冻："+order.getBasepoint()+"\n"+
                "买家基本积分账户-扣除："+order.getBasepoint()+"\n"+
                "买家购物积分账户-解冻："+order.getGoodspoint()+"\n"+
                "买家购物积分账户-扣除："+order.getGoodspoint()+"\n";

        unfreezeAmount(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, new BigDecimal(order.getGoodspoint()), remoteIp, "",
                businessName );
        minusAmount(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, new BigDecimal(order.getGoodspoint()), remoteIp, "",
                businessName);

        unfreezeAmount(idOfMember, ZxMemberAcct.POINT_ACCOUNT, new BigDecimal(order.getBasepoint()), remoteIp, "",
                businessName );
        minusAmount(idOfMember, ZxMemberAcct.POINT_ACCOUNT, new BigDecimal(order.getBasepoint()), remoteIp, "",
                businessName);

        order.setRemark((order.getRemark()==null?"":order.getRemark())+remark);
        zxOrderMapper.update(order);
        return true;
    }



    @Override
    public boolean freezeAndSetOrderPointBeforePay(ZxOrder order, BigDecimal totalPoint, String systemId, String remoteIp,
                                                   String businessName) {
        String remark="--------下单时冻结买家积分-------"+"\n";
        //此订单已经冻结过了，不应再次冻结
        if((order.getGoodspoint()!=null&&order.getGoodspoint()>0L)||
                (order.getBasepoint()!=null&&order.getBasepoint()>0L)){
            logService.addOperationLog(order.getMemberId(),
                    "",
                    "逻辑错误:积分订单用户积分重复冻结",
                    1,
                    "",
                    1,
                    "",
                    "<NO_IP>",
                    "payPoint: " + order.getPayPoint() +"basepoint: " + order.getBasepoint() +"， goodspoint: " +
                            order.getGoodspoint()
                            +"， orderId: " + order.getId
                            () +
                            "， 操作时间:" + new Date());
            throw new RuntimeException("此订单已经冻结过了，不应再次冻结");
        }


        Long idOfMember = order.getMemberId();

        ZxMemberAcct shoppingPointAcct = checkAndGetMemberAcct(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, systemId,
                "积分订单下单冻结积分时，没有此账户，新增");
        ZxMemberAcct pointAccount = checkAndGetMemberAcct(idOfMember, ZxMemberAcct.POINT_ACCOUNT, systemId,
                "积分订单下单冻结积分时，没有此账户，新增");


        BigDecimal availablePoint = shoppingPointAcct.getBlance().add(pointAccount.getBlance());


        //积分不够，应该在调用时自行检查，返回json告之前端
        if (availablePoint.compareTo(totalPoint) < 0) {
            throw new RuntimeException("逻辑错误：积分不够，不应该调用该方法");
        }

        //-----------冻结逻辑开始---------
        if (shoppingPointAcct.getBlance().compareTo(totalPoint) >= 0) {
            //购物积分余额大于等于totalPoint只需要冻结购物积分
            order.setGoodspoint(totalPoint.longValue());//存
            order.setBasepoint(0L);//存
            remark+="买家购物积分账户-冻结："+totalPoint+"\n";
            freezeAmount(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, totalPoint, "<NO_IP>", systemId, businessName);

        } else {

            //先冻结购物积分
            order.setGoodspoint(shoppingPointAcct.getBlance().longValue());//存
            totalPoint = totalPoint.subtract(shoppingPointAcct.getBlance());

            remark+="买家购物积分账户-冻结："+shoppingPointAcct.getBlance()+"\n";
            //购物积分账户全部冻结
            freezeAmount(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, shoppingPointAcct.getBlance(), "<NO_IP>", systemId, businessName);
            remark+="买家基本积分账户-冻结："+totalPoint+"\n";
            //剩余的从基本积分账户冻结
            order.setBasepoint(totalPoint.longValue());//存
            freezeAmount(idOfMember, ZxMemberAcct.POINT_ACCOUNT, totalPoint, "<NO_IP>", systemId, businessName);
        }
        //------------冻结逻辑结束-----------

        order.setPayPoint(order.getGoodspoint()+order.getBasepoint());
        zxOrderMapper.update(order);


        logService.addOperationLog(order.getMemberId(),
                "",
                "积分换/购支付前冻结积分",
                1,
                "",
                1,
                "",
                "<NO_IP>",
                "payPoint: " + order.getPayPoint() +"basepoint: " + order.getBasepoint() +"， goodspoint: " +
                        order.getGoodspoint()
                        +"， orderId: " + order.getId
                        () +
                        "， 操作时间:" + new Date());
        order.setRemark((order.getRemark()==null?"":order.getRemark())+remark);
        zxOrderMapper.update(order);

        return true;
    }

    //未付款订单处理，即支付回调失败后的订单处理
    public boolean refundUnpaidOrderPoint(ZxOrder order, String systemId, String remoteIp, String businessName) {
        //别的状态都可能退积分
        if(order.getStatus().intValue() == Cnst.OrderCnst.STATUS_SUCCESS||order.getStatus().intValue() == Cnst.OrderCnst.STATUS_CANCEL){
            throw new RuntimeException("订单状态是成功或已取消不应该退积分");
        }

        Long basepoint = order.getBasepoint();
        Long goodspoint = order.getGoodspoint();
        logService.addOperationLog(order.getMemberId(),
                "",
                "订单付款失败，原路退回积分",
                1,
                "",
                1,
                "",
                "<NO_IP>",
                "basepoint: " + basepoint +"， goodspoint: " + goodspoint +"， orderId: " + order.getId
                        () +
                        "， 操作时间:" + new Date());


        Long idOfMember = order.getMemberId();

        String remark="--------解冻未付款订单买家积分-------"+"\n";
        //解冻积分账户
        if(basepoint!=null){
            remark+="买家基本积分账户-解冻："+basepoint+"\n";
            unfreezeAmount(idOfMember, ZxMemberAcct.POINT_ACCOUNT, new BigDecimal(basepoint), remoteIp,systemId, businessName);
        }

        //解冻购物积分
        if(goodspoint!=null){
            remark+="买家购物积分账户-解冻："+basepoint+"\n";
            unfreezeAmount(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, new BigDecimal(goodspoint), remoteIp,systemId,
                    businessName);
        }

//        order.setStatus(Cnst.OrderCnst.STATUS_CANCEL);
        order.setRemark((order.getRemark()==null?"":order.getRemark())+remark);
        zxOrderMapper.update(order);


        return true;
    }

    *//**
     * 七日定时积分购用户积分恢复处理：+总额 +可用，即付款完成后，撤掉积分扣除
     * *//*
    public boolean refundPaidOrderPoint(ZxOrder order, String systemId, String remoteIp, String businessName){
        //别的状态都可能退积分
        if(order.getStatus().intValue() == Cnst.OrderCnst.STATUS_SUCCESS||order.getStatus().intValue() == Cnst.OrderCnst.STATUS_CANCEL){
            throw new RuntimeException("订单状态是成功或已取消不应该退积分");
        }
        String remark="";
        Long basepoint = order.getBasepoint();
        Long goodspoint = order.getGoodspoint();
        logService.addOperationLog(order.getMemberId(),
                "",
                "订单付款失败，积分购原路退回积分",
                1,
                "",
                1,
                "",
                "<NO_IP>",
                "basepoint: " + basepoint +"， goodspoint: " + goodspoint +"， orderId: " + order.getId
                        () +
                        "， 操作时间:" + new Date());


        Long idOfMember = order.getMemberId();


        //解冻积分账户
        if(basepoint!=null){
            remark+="买家基本积分账户-增加："+basepoint+"\n";
            addAmount(idOfMember, ZxMemberAcct.POINT_ACCOUNT, new BigDecimal(basepoint), remoteIp,systemId, businessName);
        }

        //解冻购物积分
        if(goodspoint!=null){
            remark+="买家购物积分账户-增加："+goodspoint+"\n";
            addAmount(idOfMember, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, new BigDecimal(goodspoint), remoteIp,systemId,
                    businessName);
        }

//        order.setStatus(Cnst.OrderCnst.STATUS_CANCEL);
        order.setRemark((order.getRemark()==null?"":order.getRemark())+remark);
        zxOrderMapper.update(order);


        return true;
    }

    @Override
    public ZxMemberAcct getMemberAcct(Long id, Integer accountType, String systemId) {

        return checkAndGetMemberAcct(id,accountType,"1","查询子账户时，没有此账户，新增");
    }


    @Override
    public boolean hasEnoughPoint(Long memberId,BigDecimal point, String systemId) {
        ZxMemberAcct shoppingPointAcct = checkAndGetMemberAcct(memberId, ZxMemberAcct.SHOPPINGPOINT_ACCOUNT, systemId,
                "积分订单下单判断积分是否足够时，没有此账户，新增");
        ZxMemberAcct pointAccount = checkAndGetMemberAcct(memberId, ZxMemberAcct.POINT_ACCOUNT, systemId,
                "积分订单下单判断积分是否足够时，没有此账户，新增");


        BigDecimal availablePoint = BigDecimal.ZERO;

        if(shoppingPointAcct!=null && shoppingPointAcct.getBlance()!=null)
            availablePoint=shoppingPointAcct.getBlance();
        if(pointAccount!=null && pointAccount.getBlance()!=null)
            availablePoint=availablePoint.add(pointAccount.getBlance());

        return availablePoint.compareTo(point) >=0;
    }*/
}

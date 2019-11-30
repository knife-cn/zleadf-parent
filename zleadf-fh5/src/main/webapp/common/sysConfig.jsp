<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="com.up72.zx.constant.Cnst" %>
<%@ page import="com.up72.zx.model.ZxSysConfig" %>
<%@ page import="java.util.Calendar" %>
<%!

    /** 验证当前时间是否是提现日 */
    public boolean isWithdrawDate(ZxSysConfig zxSysConfig) {
        boolean isWithdrawDate = false;
        try {
            if (zxSysConfig == null) {
                return isWithdrawDate;
            }
            Calendar cal = Calendar.getInstance();
            // 按日申请
            if (Integer.parseInt(zxSysConfig.getValue()) == Cnst.SysConfigCnst.WITHDRAW_BY_MONTH) {
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); //DAY_OF_MONTH 本月第几天
                String[] extendValueArr = zxSysConfig.getExtendValue().split(",");
                for (String extendValue : extendValueArr) {
                    if (dayOfMonth == Integer.parseInt(extendValue)) {
                        return true;
                    }
                }
            }

            // 按周申请
            if (Integer.parseInt(zxSysConfig.getValue()) == Cnst.SysConfigCnst.WITHDRAW_BY_WEEK) {
                int week = cal.get(Calendar.DAY_OF_WEEK) - 1; //DAY_OF_WEEK 本周几
                if (week == Integer.parseInt(zxSysConfig.getExtendValue())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isWithdrawDate;
    }

    /** 获取提现日文本描述 */
    public String getWithdrawDateStr(ZxSysConfig zxSysConfig) {
        String result = "";
        try {
            if (zxSysConfig == null) {
                return "";
            }

            // 按日申请
            if (Integer.parseInt(zxSysConfig.getValue()) == Cnst.SysConfigCnst.WITHDRAW_BY_MONTH) {
                result = "提现日：" + zxSysConfig.getExtendValue().replaceAll(",", "日、") + "日";
            }

            // 按周申请
            if (Integer.parseInt(zxSysConfig.getValue()) == Cnst.SysConfigCnst.WITHDRAW_BY_WEEK) {
                String ws = "";
                if ("1".equals(zxSysConfig.getExtendValue())) {
                    ws = "一";
                }
                if ("2".equals(zxSysConfig.getExtendValue())) {
                    ws = "二";
                }
                if ("3".equals(zxSysConfig.getExtendValue())) {
                    ws = "三";
                }
                if ("4".equals(zxSysConfig.getExtendValue())) {
                    ws = "四";
                }
                if ("5".equals(zxSysConfig.getExtendValue())) {
                    ws = "五";
                }
                if ("6".equals(zxSysConfig.getExtendValue())) {
                    ws = "六";
                }
                if ("7".equals(zxSysConfig.getExtendValue())) {
                    ws = "日";
                }
                result = "提现日：每周" + ws;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
%>
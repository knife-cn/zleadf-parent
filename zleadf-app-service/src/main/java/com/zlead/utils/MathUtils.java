package com.zlead.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class MathUtils {

    /**
     * 数字转百分比
     * @param number
     * @return
     */
    public static String parsePercentP(Double number){
        if(number == null){
            return "0.00%";
        }
        DecimalFormat format = new DecimalFormat("0.00%");
        format.setMaximumFractionDigits(2);
        return format.format(number);
    }

    /**
     * 这个是在基础上乘了个100
     * @param number
     * @return
     */
    public static String parseDiscountPercent(Double number){
        if(number == null){
            return "0.00%";
        }
        if(number > 1){
            number = number / 100 ;
        }
        DecimalFormat format = new DecimalFormat("0.00%");
        format.setMaximumFractionDigits(2);
        return format.format(number);
    }

    public static void main(String[] args){
        System.out.println(MathUtils.parseDiscountPercent(1D));
    }
}

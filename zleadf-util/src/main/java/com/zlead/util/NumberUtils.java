package com.zlead.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    private static DecimalFormat decimalFormat=new DecimalFormat();
    static{
        decimalFormat.applyPattern("#.##");
    }
    public static String format(double number){
        return decimalFormat.format(number);
    }
    
    public static String format(BigDecimal number){
        return decimalFormat.format(number);
    }
    
}

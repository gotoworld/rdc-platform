package com.hsd.devops.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class NumUtil {

    
    public static String keepRandomPoint(Double value, int n) {
        if (value == null) {
            value = 0.00;
            return new BigDecimal(value).setScale(n, RoundingMode.HALF_UP).toString();
        } else {
            return new BigDecimal(value).setScale(n, RoundingMode.HALF_UP).toString();
        }
    }

    
    public static String keep2Point(double value) {
        return keepRandomPoint(value, 2);
    }

    
    public static String keep1Point(double value) {
        return keepRandomPoint(value, 1);
    }

    
    public static String keepRandomPointZero(double value, int n) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Double.valueOf(keepRandomPoint(value, n)));
    }

    
    public static String keep2PointZero(double value) {
        return keepRandomPointZero(value, 2);
    }

    
    public static String percentRandomPoint(double value, int n) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setGroupingUsed(false);
        percent.setMaximumFractionDigits(n);
        return percent.format(value);
    }

    
    public static String percent2Point(double value) {
        return percentRandomPoint(value, 2);
    }

    
    public static String latLngPoint(double value) {
        return keepRandomPoint(value, 3);
    }

}

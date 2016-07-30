package com.sunger.plugin.utils;

/**
 * Created by zhangqiangze on 2016/7/11.
 */
public class PatternUtils {

    public  static  boolean isReference(String value){
        return value.contains("@");
    }


    public  static  double getNumber(String value){
         return Double.parseDouble(value.replaceAll("[a-zA-Z]+",""));
    }

    public  static  String getUnit(String value){
        String unit=value.replaceAll("\\d+","");
        return  unit;
    }

}

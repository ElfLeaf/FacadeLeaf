package com.elfleaf.framework.utils;

public interface UtilsLeafRegx {
    
    /**email正则**/
    public final static String EMAIL_REGX = "^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\\.][a-z]{2,3}([\\.][a-z]{2})?$";
    
    /**手机号正则**/
    public final static String MOBILE_REGX = " ^1[3|4|5|8][0-9]\\d{4,8}$";
   
}

package com.elfleaf.framework.JCaptcha;

import org.apache.shiro.authc.UsernamePasswordToken;

@SuppressWarnings("serial")
public class CaptchaUsernamePasswordToken  extends UsernamePasswordToken {
    private String captcha; 

    // 省略 getter 和 setter 方法

    public CaptchaUsernamePasswordToken(String username, String password, 
            boolean rememberMe, String host,String captcha) { 
        super(username, password, rememberMe, host); 
        this.captcha = captcha; 
    } 
}

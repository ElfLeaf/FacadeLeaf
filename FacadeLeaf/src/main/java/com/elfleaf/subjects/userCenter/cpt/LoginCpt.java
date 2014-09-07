package com.elfleaf.subjects.userCenter.cpt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elfleaf.framework.JCaptcha.svc.ValidateCaptchaService;

@Component
public class LoginCpt {
    
    @Autowired
    private ValidateCaptchaService validateCaptchaService;
    
    /**
     * 验证用户输入的验证码是否正确
     * @param request
     * @param challengeResponse 输入的验证码
     */
    public boolean validateCaptcha(final HttpServletRequest request, String challengeResponse) {
        return validateCaptchaService.validateCaptchaChallenge(request, challengeResponse);
    }
    
    
}

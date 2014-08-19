package com.elfleaf.framework.JCaptcha;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.octo.captcha.service.CaptchaServiceException;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter{ 


    public static final String DEFAULT_CAPTCHA_PARAM = "captcha"; 

    private String captchaParam = DEFAULT_CAPTCHA_PARAM; 

    public String getCaptchaParam() { 
        return captchaParam; 
    } 

    public void setCaptchaParam(String captchaParam) { 
        this.captchaParam = captchaParam; 
    } 

    protected String getCaptcha(ServletRequest request) { 
        return WebUtils.getCleanParam(request, getCaptchaParam()); 
    } 

    // 创建 Token 
    protected CaptchaUsernamePasswordToken createToken( 
            ServletRequest request, ServletResponse response) { 

        String username = getUsername(request); 
        String password = getPassword(request); 
        String captcha = getCaptcha(request); 
        boolean rememberMe = isRememberMe(request); 
        String host = getHost(request); 

        return new CaptchaUsernamePasswordToken( 
                username, password, rememberMe, host,captcha); 
    } 

    // 验证码校验
    protected Boolean doCaptchaValidate(final HttpServletRequest request, String challengeResponse){ 
        try {
            // 获取产生验证码的id,用session的id来产生验证码
            String captchaID = request.getSession().getId();
            // 获取输入的验证码
            // String challengeResponse =
            // request.getParameter(captchaParamterName);
            return CaptchaService.getInstance().validateResponseForID(captchaID, challengeResponse);
        } catch (CaptchaServiceException e) {
            System.out.println(e);
            return false;
        }
    } 

    // 认证
    protected boolean executeLogin(ServletRequest request, 
            ServletResponse response) throws Exception { 
        CaptchaUsernamePasswordToken token = createToken(request, response); 

        try { 
            //doCaptchaValidate( (HttpServletRequest)request,token ); 

            Subject subject = getSubject(request, response); 
            subject.login(token); 

            return onLoginSuccess(token, subject, request, response); 
        } catch (AuthenticationException e) { 
            return onLoginFailure(token, e, request, response); 
        } 
    } 

}
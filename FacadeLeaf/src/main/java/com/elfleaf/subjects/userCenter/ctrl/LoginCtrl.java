package com.elfleaf.subjects.userCenter.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elfleaf.constant.CRequest;
import com.elfleaf.subjects.userCenter.cpt.LoginCpt;

/**
 * 用户登陆类
 */
@RequestMapping("/userCenter")
@Controller
public class LoginCtrl {

    @Autowired
    private LoginCpt loginCpt;
    
    
    /**
     * 登陆页请求方法
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginPage")
    public String login(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception { 
        /*
         * @STEP 1 获得用户请求跳转到登陆页面前的url地址
         * @STEP 2 将用户登陆前的url地址放入到session中
         * @STEP 3 返回用户登陆页面
         */
        
        //@STEP 1 获得用户请求跳转到登陆页面前的url地址
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if (savedRequest != null) {
            String lastURL = savedRequest.getRequestUrl();
            //@STEP 2 将用户登陆前的url地址放入到session中
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute(CRequest.LAST_REQUEST_URL, lastURL);
        }

        //@STEP 3 返回用户登陆页面
        return "userCenter/loginPage";
    }
    
    /**
     * 执行登陆验证请求方法
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/doLogin", method=RequestMethod.POST)
    public String doLogin(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
        
        /*
         * @STEP 1检测验证码是否输入正确
         * @STEP 2获得用户的登陆名，登陆密码
         * @STEP 3验证登陆
         * @STEP 4从SESSION中取出用户登陆之前请求的lastUrl
         * @STEP 5若存在lastUrl那么重定向到lastUrl,若不存在则返回首页
         */
        
        // TODO @STEP 1检测验证码是否输入正确
        String challengeResponse = (String) request.getParameter("challengeResponse");
        loginCpt.validateCaptcha(request, challengeResponse);
        
        //@STEP 2获得用户的登陆名，登陆密码
        String loginName = (String) request.getParameter("loginName");
        String password = (String) request.getParameter("password");
        
        //@STEP 3验证登陆
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        try {  
            //登录，即身份验证  
            subject.login(token);  
        } catch (AuthenticationException e) {  
            throw new Exception("用户名或密码错误");
        }  
        
        //@STEP 4从SESSION中取出用户登陆之前请求的lastUrl
        Session session = subject.getSession();
        String lastURL = (String) session.getAttribute(CRequest.LAST_REQUEST_URL);
        
        //@STEP 5若存在lastUrl那么重定向到lastUrl,若不存在则返回首页
        if (lastURL != null) {
            //若存在lastUrl那么重定向到lastUrl
            //需要配置shiro的rememberMe 否则session丢失
            return "redirect:" + lastURL;
        } else {
            //若不存在则返回首页
            return "testSession";
        }
    }
    
    
}

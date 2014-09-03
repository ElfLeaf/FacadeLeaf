package com.elfleaf.subjects.login.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elfleaf.constant.CRequest;


@RequestMapping("/*")
@Controller
public class LoginCtrl {

    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception { 
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        //登录前请求的url
        String lastURL = savedRequest.getRequestUrl();
        
        //登录前请求的url保存于session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(CRequest.LAST_REQUEST_URL, lastURL);
        
        return "login/login";
    }
    
    @RequestMapping(value="/doLogin", method=RequestMethod.POST)
    public String doLogin(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
        
        String loginName = (String) request.getParameter("loginName");
        String password = (String) request.getParameter("password");
        
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        
        try {  
            //4、登录，即身份验证  
            subject.login(token);  
        } catch (AuthenticationException e) {  
            throw new Exception("用户名或密码错误");
        }  
        
        //获得登录前，用户想访问的url
        Session session = subject.getSession();
        String lastURL = (String) session.getAttribute(CRequest.LAST_REQUEST_URL);
        
        //需要rememberMe 否则session丢失
        //return "redirect:" + lastURL;
        return "testSession";
    }
    
    
}

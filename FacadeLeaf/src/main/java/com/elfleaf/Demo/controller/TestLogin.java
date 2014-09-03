package com.elfleaf.Demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/*")
@Controller
public class TestLogin {
    
//    @RequestMapping("/login")
//    public String login(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
//        
//        return "login/login";
//    }
//    
//    @RequestMapping("/doLogin")
//    public String doLogin(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("abc", "aqw");
//        try {  
//            //4、登录，即身份验证  
//            subject.login(token);  
//        } catch (AuthenticationException e) {  
//            //5、身份验证失败  
//        }  
//        
//        return "testSession";
//    }
}

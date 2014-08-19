package com.elfleaf.Demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/session")
@Controller
public class TestSessionController {

    @RequestMapping("/view")
    public String view(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        return "testSession";
    }
    
    @RequestMapping("/write")
    public String write(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("test", "hahaha");
        return "writeSession";
    }
}

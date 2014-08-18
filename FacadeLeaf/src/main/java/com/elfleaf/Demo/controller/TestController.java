package com.elfleaf.Demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {
    
    @RequestMapping(value="/test")
    public String test(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        HttpSession session = request.getSession();
        session.setAttribute("user", "admin");
        model.addAttribute("action", "加入了");
        //forward /jsp/test.jsp
        int a=0;
        if (a==1) {
            throw new Exception("出错了");
        }
        
        return "test-tiles";
    }
}

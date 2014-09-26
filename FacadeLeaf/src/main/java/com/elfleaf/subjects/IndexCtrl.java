package com.elfleaf.subjects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页
 */
@RequestMapping("/")
@RestController
public class IndexCtrl {
    
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        return new ModelAndView("index");
    }
    
    
    @RequestMapping("")
    public ModelAndView deafultIndex(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        return new ModelAndView("index");
    }
}

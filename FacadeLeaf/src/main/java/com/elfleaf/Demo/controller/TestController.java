package com.elfleaf.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {
    
    @RequestMapping(value="*")
    public String test() {
        //forward /jsp/test.jsp
        return "test-tiles";
    }
}
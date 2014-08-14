package com.elfleaf.Demo.aop;

import org.springframework.stereotype.Component;

@Component
public class Monkey {  
    
    public void stealPeaches(String name){  
        System.out.println("【猴子】"+name+"正在偷桃...");  
    }  
}  
package com.elfleaf.Demo.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect  
public class Guardian {  
      
    @Pointcut("execution(* com.elfleaf.Demo.aop.Monkey.stealPeaches(..))")  
    public void foundMonkey(){
        System.out.println("【守护者】foundMonkey...");  
    }  
  
    @Before(value="foundMonkey()")  
    public void foundBefore(){  
        System.out.println("【守护者】发现猴子正在进入果园...");  
    }  
      
    @AfterReturning("foundMonkey() && args(name,..)")  
    public void foundAfter(String name){  
        System.out.println("【守护者】抓住了猴子,守护者审问出了猴子的名字叫“"+name+"”...");  
    }  
      
}  
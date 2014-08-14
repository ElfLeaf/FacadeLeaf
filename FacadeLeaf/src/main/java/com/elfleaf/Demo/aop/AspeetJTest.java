package com.elfleaf.Demo.aop;

import org.aspectj.weaver.Utils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.elfleaf.utils.UtilsSpringTest;

public class AspeetJTest extends UtilsSpringTest{
    @Autowired
    private Monkey monkey;
    
    @Test
    public void test() {  
        //ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");  
        //Monkey monkey = (Monkey) context.getBean("monkey");  
        try {  
            monkey.stealPeaches("孙大圣的大徒弟");  
        }  
        catch(Exception e) {}  
    } 
}

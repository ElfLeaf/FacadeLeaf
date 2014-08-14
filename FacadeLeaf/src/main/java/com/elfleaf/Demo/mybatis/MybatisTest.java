package com.elfleaf.Demo.mybatis;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.elfleaf.utils.UtilsSpringTest;

public class MybatisTest extends UtilsSpringTest{
    
    @Autowired
    private TestMyBatisService service;
    
    
    @Test
    public void test() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/config/spring/spring-mybatis.xml");
        //UserService service = context.getBean(UserService.class);



        Integer users = service.select();
        System.out.println(users);
   }
}

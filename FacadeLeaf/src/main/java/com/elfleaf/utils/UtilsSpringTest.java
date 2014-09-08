package com.elfleaf.utils;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>
 * spring 测试基类，如果测试中需要进行spring注入，把测试类继承该类即可
 * </pre>
 * @author blackcatIan
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:config/spring/spring-application.xml",
        "classpath*:config/spring/spring-mybatis.xml",
        "classpath*:config/spring/spring-aop.xml",
        "classpath*:config/spring/spring-shiro.xml",
        "classpath*:config/spring/mvc-dispatcher-servlet.xml"}) 
public class UtilsSpringTest extends AbstractJUnit4SpringContextTests {
}

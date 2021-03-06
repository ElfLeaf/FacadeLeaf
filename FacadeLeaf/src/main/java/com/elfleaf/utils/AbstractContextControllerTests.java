package com.elfleaf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",  
"classpath:xxxx-servlet.xml" })
public class AbstractContextControllerTests {

    @Autowired
    protected WebApplicationContext wac;

}
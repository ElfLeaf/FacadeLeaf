package com.elfleaf.Demo.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestMyBatisService {
    @Autowired
    private TestMyBatisDAO userDAO;
    
    public Integer select() {
        return userDAO.findAll();
    }
    
}

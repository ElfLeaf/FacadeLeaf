package com.elfleaf.Demo.mybatis;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TestMyBatisDAO extends SqlSessionDaoSupport implements TestDAO {
    
    
    /**
     * 可能是为了解决多数据源的问题吧，取消了自动注入。没用到多数据源，不太关心这个。

解决方案：因为我们dao层是继承于一个dao基类，所以只要在这个基类中注入任意一个属性即可。SqlSessionFactory在spring配置文件中已经配置。
     */
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public int count() {
        return 0;
    }

    public Integer findAll() {
        return this.getSqlSession().selectOne("ATOM-USER.test.findAll");
    }




}
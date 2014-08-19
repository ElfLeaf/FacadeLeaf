package com.elfleaf.framework.mybatis;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * <pre>
 * @author blackcat
 * </pre>
 * DAO的抽象类
 * 对于myBatis的映射空间名必须按照泛型入口的bean的类名写
 */
public abstract class AMyBatisBaseDAO<T> extends SqlSessionDaoSupport{
    
    /**泛型入口类**/
    @SuppressWarnings("unchecked")
    private Class<T> entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    
    
    /**
     * 可能是为了解决多数据源的问题吧，取消了自动注入。没用到多数据源，不太关心这个。
     *解决方案：因为我们dao层是继承于一个dao基类，所以只要在这个基类中注入任意一个属性即可。SqlSessionFactory在spring配置文件中已经配置。
     */
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    
    /**
     * 获取实类名
     * @return 泛型的实类名
     */
    private String getEntityName() {
        return entityClass.getSimpleName();
    }
    
    /**
     * 构建取得sqlMap中的sql方法名,以供子类调用
     * @param methodName
     * @return
     */
    protected String getSqlMethod(String methodName) {
        return getEntityName() + "." + methodName;
    }
    
    /**
     * 根据主键id去查找唯一一条数据，并返回到bean中
     * @param id
     * @return 泛型入口类型，也就是传入的bean类
     */
    @SuppressWarnings("unchecked")
    public T findById(Integer id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return (T) this.getSqlSession().selectOne(getEntityName() + "." + methodName, id);
    }
    
    
    
    
    
    
    
    
}







package com.elfleaf.framework.mybatis;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
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
     * 为了解决多数据源的问题吧，取消了自动注入。没用到多数据源，不太关心这个。
     * 解决方案：因为我们dao层是继承于一个dao基类，所以只要在这个基类中注入任意一个属性即可。SqlSessionFactory在spring配置文件中已经配置。
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
     * 构建取得sqlMap中的sql方法名
     * @param methodName
     * @return sqlMap完整映射名"空间名.方法名"
     */
    private String getSqlMethod(String methodName) {
        return getEntityName() + "." + methodName;
    }
    
    /**
     * 删除记录
     * @param statement sqlmap中方法名
     * @return 删除条数
     */
    public int delete(String statement) {
        return this.getSqlSession().delete(getSqlMethod(statement));
    }
    
    /**
     * 删除记录
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @return 删除条数
     */
    public int delete(String statement, Object parameter) {
        return this.getSqlSession().delete(getSqlMethod(statement), parameter);
    }
    
    /**
     * 批量删除记录
     * @param statement sqlmap中方法名
     * @param list 批量操作的数据
     * @return 删除条数
     */
    public int deleteBatch(String statement, List<T> list) {
        int rtn = this.getSqlSession().delete(getSqlMethod(statement), list);
        this.getSqlSession().commit();
        return rtn;
    }
    
    /**
     * 插入记录
     * @param statement sqlmap中方法名
     * @return 插入条数
     */
    public int insert(String statement) {
        return this.getSqlSession().insert(getSqlMethod(statement));
    }
    
    /**
     * 插入记录
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @return 插入条数
     */
    public int insert(String statement, Object parameter) {
        return this.getSqlSession().insert(getSqlMethod(statement), parameter);
    }
    
    /**
     * <pre>
     * 批量插入多条记录
     * 在sqlMap中请用foreach标签取出list中的参数
     * eg: 
     *      INSERT INTO STUDENT (id,name,sex,tel,address)  
     *      VALUES
     *      &ltforeach collection="list" item="T" index="index" separator=",">
     *          (#{T.id},#{T.name},#{T.sex},#{T.tel},#{T.address})  
     *      &lt/foreach>
     * </pre>
     * @param statement
     * @param list 存放着DOMAIN的数据集合
     * @return 插入条数
     */
    public int insertBatch(String statement, List<T> list) {
        int rtn = this.getSqlSession().insert(getSqlMethod(statement), list);
        this.getSqlSession().commit();
        return rtn;
    }
    
    /**
     * 不推荐使用这种方法，容易搞不清handler存取引用
     * @param statement sqlmap中方法名
     * @param handler 取数据对象，该对象会实现MAP或者LIST，是对地址的引用，查询结果会直接放回在handler中
     * @see org.apache.ibatis.executor.result.DefaultMapResultHandler
     * @see org.apache.ibatis.executor.result.DefaultResultHandler
     * @deprecated
     * 
     */
    public void select(String statement, ResultHandler handler) {
        this.getSqlSession().select(getSqlMethod(statement), handler);
    }

    /**
     * 不推荐使用这种方法，容易搞不清handler存取引用
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @param handler 取数据对象，该对象会实现MAP或者LIST，是对地址的引用，查询结果会直接放回在handler中
     * @see org.apache.ibatis.executor.result.DefaultMapResultHandler
     * @see org.apache.ibatis.executor.result.DefaultResultHandler
     * @deprecated
     */
    public void select(String statement, Object parameter, ResultHandler handler) {
        this.getSqlSession().select(getSqlMethod(statement), parameter, handler);
    }
    
    /**
     * 执行一段SQL查询结果返回到存放DOMAIN的LIST
     * @param statement sqlmap中方法名
     * @return 存放DOMAIN的LIST
     */
    public List<T> selectList(String statement) {
        return this.getSqlSession().selectList(getSqlMethod(statement));
    }
    
    /**
     * <pre>
     * 特殊说明:如果你想要得到的结果是一个数据库没用的字段，是一个泛型结果集，请用这个方法
     * 执行一段SQL查询结果返回到List的DOMAIN中
     * </pre>
     * @param statement sqlmap中方法名
     * @return 存放泛型的LIST
     */
    public <E> List<E> selectListGeneric(String statement) {
        return this.getSqlSession().selectList(getSqlMethod(statement));
    }
    
    /**
     * 执行一段SQL查询结果返回到存放DOMAIN的LIST
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数，
     * @return 存放DOMAIN的LIST
     */
    public List<T> selectList(String statement, Object parameter) {
        return this.getSqlSession().selectList(getSqlMethod(statement), parameter);
    }
    
    /**
     * <pre>
     * 特殊说明:如果你想要得到的结果是一个数据库没用的字段，是一个泛型结果集，请用这个方法
     * 执行一段SQL查询结果返回到List的DOMAIN中
     * </pre>
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数，
     * @return 存放泛型的LIST
     */
    public <E> List<E> selectListGeneric(String statement, Object parameter) {
        return this.getSqlSession().selectList(getSqlMethod(statement), parameter);
    }
    
    /**
     * 不推荐使用这种方法，mybatis自带的分页对数据量较大的表容易内存溢出，如需分页请通过SQL自行实现
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @param rowBounds 查询结果行号偏移量
     * @return List 存放Domain Model的LIST
     * @deprecated
     */
    public List<T> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return this.getSqlSession().selectList(getSqlMethod(statement), parameter, rowBounds);
    }
    
    /**
     * <pre>
     * SQL结果是查询唯一一条记录,注意如果返回多条结果则会异常！
     * 结果的会将数据库mapkey列字段值放在map的k中，而mapkey列的值对应的整条结果会以一个对象的形式返回到Map的v中,注意v对象为本表的Domain Model
     * </pre>
     * @param statement sqlmap中方法名
     * @param mapKey 数据库表中的列名
     * @return MAP 每个Map的键值对是一条记录
     */
    public Map<String,T> selectMap(String statement, String mapKey) {
        return this.getSqlSession().selectMap(getSqlMethod(statement), mapKey);
    }
    
    /**
     * <pre>
     * 特殊说明:如果你想要得到的结果是一个数据库没用的字段，是一个泛型结果集，请用这个方法
     * SQL结果是查询唯一一条记录,注意如果返回多条结果则会异常！
     * 结果的会将数据库mapkey列字段值放在map的k中，而mapkey列的值对应的整条结果会以一个对象的形式返回到Map的v中,注意v对象为本表的Domain Model
     * </pre>
     * @param statement sqlmap中方法名
     * @param mapKey 数据库表中的列名
     * @return MAP 每个Map的键值对是一条记录
     */
    public <E> Map<String,E> selectMapGeneric(String statement, String mapKey) {
        return this.getSqlSession().selectMap(getSqlMethod(statement), mapKey);
    }
    
    
    /**
     * <pre>
     * 根据parameter输入参数，去查询多条记录
     * 结果的会将数据库mapkey列字段值放在map的k中，而mapkey列的值对应的整条结果会以一个对象的形式返回到Map的v中,注意v对象为本表的omain Model
     * 由于是多条结果，最后会放在List集合中
     * </pre>
     * @param statement sqlmap中方法名
     * @param statement 查询输入参数,一般为HashMap
     * @param mapKey 数据库表中的列名
     * @return 数据存储在Map中的List,每个Map的键值对是一条记录
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,T>> selectMap(String statement, Object parameter, String mapKey) {
        return (List<Map<String,T>>) this.getSqlSession().selectMap(getSqlMethod(statement), parameter, mapKey);
    }
    
    
    /**
     * <pre>
     * 特殊说明:如果你想要得到的结果是一个数据库没用的字段，是一个泛型结果集，请用这个方法
     * 根据parameter输入参数，去查询多条记录
     * 结果的会将数据库mapkey列字段值放在map的k中，而mapkey列的值对应的整条结果会以一个对象的形式返回到Map的v中,注意v对象为本表的omain Model
     * 由于是多条结果，最后会放在List集合中
     * </pre>
     * @param statement sqlmap中方法名
     * @param statement 查询输入参数,一般为HashMap
     * @param mapKey 数据库表中的列名
     * @return 数据存储在Map中的List,每个Map的键值对是一条记录
     */
    @SuppressWarnings("unchecked")
    public <E> List<Map<String,E>> selectMapGeneric(String statement, Object parameter, String mapKey) {
        return (List<Map<String,E>>) this.getSqlSession().selectMap(getSqlMethod(statement), parameter, mapKey);
    }
    

    /**
     * <pre>
     * 本方法不常用，也不推荐用
     * 根据parameter输入参数，去查询多条记录,并且分页显示其中的 一部分,mybatis的分页容易内存溢出，不推荐用这个方法
     * 结果的会将数据库mapkey列字段值放在map的k中，而mapkey列的值对应的整条结果会以一个对象的形式返回到Map的v中,注意v对象为本表的Domain Model
     * 由于是多条结果，最后会放在List集合中
     * </pre>
     * @param statement sqlmap中方法名
     * @param statement 查询输入参数,一般为HashMap
     * @param mapKey 数据库表中的列名
     * @param rowBounds 查询结果行号偏移量
     * @return 数据存储在Map中的List,每个Map的键值对是一条记录
     * @deprecated
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,T>> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return (List<Map<String,T>>) this.getSqlSession().selectMap(getSqlMethod(statement), parameter, mapKey, rowBounds);
    }
    
    /**
     * 选出一条记录，若多条会有异常!
     * @param statement sqlmap中方法名
     * @return T Domain Model
     */
    public T selectOne(String statement) {
        return this.getSqlSession().selectOne(getSqlMethod(statement));
    }
    
    /**
     * <pre>
     * 特殊说明:如果你想要得到的结果是一个数据库没用的字段，是一个泛型结果集，请用这个方法
     * 选出一条记录，若多条会有异常!
     * </pre>
     * @param statement sqlmap中方法名
     * @return E 泛型
     */
    public <E> E selectOneGeneric(String statement) {
        return this.getSqlSession().selectOne(getSqlMethod(statement));
    }
    
    /**
     * 选出一条记录，若多条会有异常!
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @return T Domain Model
     */
    public T selectOne(String statement, Object parameter) {
        return this.getSqlSession().selectOne(getSqlMethod(statement), parameter);
    }
    
    
    /**
     * <pre>
     * 特殊说明:如果你想要得到的结果是一个数据库没用的字段，是一个泛型结果集，请用这个方法
     * 选出一条记录，若多条会有异常!
     * </pre>
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @return E 泛型
     */
    public <E> E selectOneGeneric(String statement, Object parameter) {
        return this.getSqlSession().selectOne(getSqlMethod(statement), parameter);
    }
    
    
    
    /**
     * 更新数据库记录
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @return 更新条数
     */
    public int update(String statement) {
        return this.getSqlSession().update(getSqlMethod(statement));
    }
    
    /**
     * 更新数据库记录
     * @param statement sqlmap中方法名
     * @param parameter 查询输入参数,一般为HashMap
     * @return 更新条数
     */
    public int update(String statement, Object parameter) {
        return this.getSqlSession().update(getSqlMethod(statement), parameter);
    }
     
    /**
     * <pre>
     * 批量更新多条数据库记录
     * eg:
     *      &ltupdate id="batchUpdateStudent" parameterType="java.util.List">  
     *          UPDATE STUDENT SET name = "5566" WHERE id IN  
     *      &ltforeach collection="list" item="T" index="index" open="(" separator="," close=")" >  
     *          #{T.id}  
     *      &lt/foreach>  
     * 或者遇到需要带参更新情况
     * eg:
      *      &ltupdate id="batchUpdateStudent" parameterType="java.util.List">  
     *      &ltforeach collection="list" item="T" index="index" open="(" separator="," close=")" >  
     *          UPDATE STUDENT SET name = #{T.name} WHERE id IN #{T.id}  
     *      &lt/foreach>  
     * </pre>
     * @param statement sqlmap中方法名
     * @param list 要更新的数据的DOMAIN集合
     * @return 更新条数
     */
    public int updateBatch(String statement, List<T> list) {
        int rtn = this.getSqlSession().update(getSqlMethod(statement), list);
        this.getSqlSession().commit();
        return rtn;
    }
}







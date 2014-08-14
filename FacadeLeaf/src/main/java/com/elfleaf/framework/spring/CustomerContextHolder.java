package com.elfleaf.framework.spring;

/**
 * 数据源切换类
 * 
 * 这样就将数据源动态的设置成了dataSourceB.  
    CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_B);  
    配合AOP实现切换
      
@Aspect  
public class DynamicDataSourceAspect {  
    @Pointcut("execution (public service.impl..*.*(..))")  
    public void serviceExecution(){}  
      
    @Before("serviceExecution()")  
    public void setDynamicDataSource(JoinPoint jp) {  
        for(Object o : jp.getArgs()) {  
            //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取切换DataSource  
        }  
    }  
}  
    
 */
public class CustomerContextHolder {
    public static final String DATA_SOURCE_A = "dataSourceA";  
    
    public static final String DATA_SOURCE_B = "dataSourceB";  
      
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
      
    public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);  
    }  
      
    public static String getCustomerType() {  
        return contextHolder.get();  
    }  
      
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
}

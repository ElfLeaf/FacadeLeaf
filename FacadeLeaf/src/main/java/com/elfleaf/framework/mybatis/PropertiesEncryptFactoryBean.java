package com.elfleaf.framework.mybatis;

import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;

import com.elfleaf.framework.encrypt.AESEncrypt;
import com.elfleaf.framework.utils.UtilsLeafDataFormat;

/**
 * 配置文件连接密码解密
 */
@SuppressWarnings("rawtypes")
public class PropertiesEncryptFactoryBean implements FactoryBean {  
    
    private Properties properties;  
      
    public Object getObject() throws Exception {  
        return getProperties();  
    }  
  
    public Class getObjectType() {  
        return java.util.Properties.class;  
    }  
  
    public boolean isSingleton() {  
        return true;  
    }  
  
    public Properties getProperties() {  
        return properties;  
    }  
  
    /**
     * 读取配置文件并对加密密码解密
     * @param inProperties
     */
    public void setProperties(Properties inProperties) {  
        this.properties = inProperties;  
//        String originalUsername = properties.getProperty("user");  
        String originalPassword = properties.getProperty("password");  
//        if (originalUsername != null){  
//            String newUsername = deEncryptUsername(originalUsername);  
//            properties.put("user", newUsername);  
//        }  
        if (originalPassword != null){  
            String newPassword = deEncryptPassword(originalPassword);  
            properties.put("password", newPassword);  
        }  
    }  
      
//    private String deEncryptUsername(String originalUsername){  
//        return deEncryptString(originalUsername);  
//    }  
    
    /**
     * 解密密码
     * @param originalPassword
     * @return
     */
    private String deEncryptPassword(String originalPassword){  
        //解密   
        byte[] decryptFrom = UtilsLeafDataFormat.parseHexStr2Byte(originalPassword);  
        byte[] decryptResult = AESEncrypt.decrypt(decryptFrom,AESEncrypt.PASSWORD);  
        
        return new String(decryptResult);
    }  
    
    @Test
    public void test() {
        String str = deEncryptPassword("C4215F9F773B5AFA6AA8C42A2EACC52B");
        System.out.println(str);
    }
    
 
  
}  
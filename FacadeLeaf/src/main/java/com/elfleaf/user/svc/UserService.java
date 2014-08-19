package com.elfleaf.user.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Service;

import com.elfleaf.framework.utils.UtilsLeafRegx;
import com.elfleaf.models.User;
import com.elfleaf.user.dao.UserDAO;


@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    
    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
//    public UserService() {
//        //手工装配DAO
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        RootBeanDefinition userDAOBean = new RootBeanDefinition(UserDAO.class);
//        //将Bean注册到容器  
//        beanFactory.registerBeanDefinition("userDAO", userDAOBean);  
//        userDAO = (UserDAO)beanFactory.getBean("userDAO");
//    }



    /**
     * 根据登录名(可以是用户名/email等方式)查询是否存在这个用户。
     * 若存在则返回用户信息。
     * 若不存在则返回null
     */
    public User findLoginUserInfo(String LoginName) {
        //STEP 判断loginName类型
        String loginType = null;
        //判断是否为EMAIL类型
        if (LoginName.matches(UtilsLeafRegx.EMAIL_REGX)) {
            loginType = "email";
        } else {
            loginType = "uname";
        }
        
        return userDAO.findLoginUserInfo(LoginName, loginType);
    }
}

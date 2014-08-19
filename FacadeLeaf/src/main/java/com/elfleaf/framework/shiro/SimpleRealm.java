package com.elfleaf.framework.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.elfleaf.models.User;
import com.elfleaf.user.svc.UserService;

public class SimpleRealm extends AuthorizingRealm{

    private static Logger logger = Logger.getLogger(SimpleRealm.class);

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SimpleRealm() {

//        //手工装配service
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        RootBeanDefinition userSeriveBean = new RootBeanDefinition(UserService.class);
//        //将Bean注册到容器  
//        beanFactory.registerBeanDefinition("userService", userSeriveBean);  
//        userService = (UserService)beanFactory.getBean("userService");

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //登录名(可以是用户名/EMAIL/手机号等方式)
        String LoginName = usernamePasswordToken.getUsername();  
        String password = new String(usernamePasswordToken.getPassword());
        logger.info("shiro认证用户" + LoginName + "密码" + password);

        if ( StringUtils.isBlank(LoginName)) {
            throw new AccountException("用户名不能为空");  
        }

        String tmpPassword = null;  

        //查询是否存在该用户，登录方式为用户名/email等登录方式
        User user = userService.findLoginUserInfo(LoginName);

        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }


        //比较用户名密码

        return new SimpleAuthenticationInfo(user.getUname(), user.getPassword(), getName());

        //        //调用service查询db用户信息,包括用户名，密码
        //        Admin user = adminService.findByLoginName(username);  
        //        
        //        
        //        Manager manager = null;  
        //        CommonVariableModel model = new CommonVariableModel();  
        //        if (user == null)  
        //        {  
        //            manager = managerDAO.findByLoginName(username);  
        //            if (manager == null)  
        //                throw new UnknownAccountException("用户不存在");  
        //            else  
        //            {  
        //                tmpPassword = manager.getLoginPwd();  
        //                model.setManager(manager);  
        //            }  
        //        } else  
        //        {  
        //            tmpPassword = user.getPassword();  
        //            model.setUser(user);  
        //        }  
        //  
        //        return new SimpleAuthenticationInfo(model, tmpPassword, getName()); 
    }

}

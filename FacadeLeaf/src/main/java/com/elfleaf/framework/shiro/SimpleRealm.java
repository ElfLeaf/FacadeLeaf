package com.elfleaf.framework.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.elfleaf.models.user.bean.User;
import com.elfleaf.models.user.svc.UserService;

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
        //明文password
        String password = new String(usernamePasswordToken.getPassword());
        logger.info("shiro认证用户" + LoginName + "密码" + password);

        if (StringUtils.isBlank(LoginName)) {
            throw new AccountException("用户名不能为空");  
        }

        //查询是否存在该用户，登录方式为用户名/email等登录方式
        User user = userService.findLoginUserInfo(LoginName);

        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }

        //比较用户名密码与数据库中的值是否正确
        if (!userService.verifyPwd(user.getUid(), password)) {
            //密码错误
            throw new IncorrectCredentialsException("密码错误");  
        }
        
        return new SimpleAuthenticationInfo(user.getUname(), password, getName());
    }

}

package com.elfleaf.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.elfleaf.framework.mybatis.AMyBatisBaseDAO;
import com.elfleaf.models.User;

@Repository
public class UserDAO extends AMyBatisBaseDAO<User> {

    /**
     * 找出登录名相关登录信息
     * @param LoginName 登录名(可以是用户名/EMAIL多形式)
     * @param loginType 登录名类型,也就是是用户名，还是email或者是手机号类型
     * @return 用户信息
     */
    public User findLoginUserInfo(String LoginName,String loginType) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("LoginName", LoginName);
        params.put("loginType", loginType);
        return this.getSqlSession().selectOne(getSqlMethod("findLoginUserInfo"), params);
    }
}

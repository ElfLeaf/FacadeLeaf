package com.elfleaf.models.user.bean;

import java.util.Date;

public class User {
    /**用户id**/
    protected Integer uid;
    /**用户名**/
    protected String uname;
    /**密码**/
    protected String password;
    /**email**/
    protected String email;
    /**注册时间**/
    protected Date createdAt;
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
}
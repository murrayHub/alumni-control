package com.techGoal.pojo.dao;

public class UserLogin {
    private Integer id;

    private String loginNo;

    private String pwd;

    private Long userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo == null ? null : loginNo.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
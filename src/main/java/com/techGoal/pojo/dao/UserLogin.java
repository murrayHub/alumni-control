package com.techGoal.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_user_login")
public class UserLogin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "login_no")
    private String loginNo;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "user_id")
    private Long userId;

}
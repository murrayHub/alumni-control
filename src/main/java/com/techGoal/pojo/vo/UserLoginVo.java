package com.techGoal.pojo.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private Integer id;
    private String loginNo;
    private String pwd;
    private Long userId;

}
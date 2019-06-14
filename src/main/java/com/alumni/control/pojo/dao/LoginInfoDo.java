package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_login_info")
public class LoginInfoDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 登录账号
     */
    @Column(name = "login_no")
    private String loginNo;

    /**
     * 登录账号类型：1-手机号 2-邮箱
     */
    @Column(name = "login_type")
    private String loginType;

    /**
     * 登录账号状态：-1-禁用，0-待激活，1-正常，2-锁定
     */
    @Column(name = "login_state")
    private String loginState;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_at")
    private String lastLoginAt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

}
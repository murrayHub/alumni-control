package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user_login_log")
public class UserLoginLogDo {
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
    private Integer loginType;

    /**
     * 登录账号状态：0-禁用，1-正常 2-锁定
     */
    @Column(name = "login_state")
    private Integer loginState;

    /**
     * 登录状态：0-登录失败 1-登录成功
     */
    private Integer state;

    /**
     * 登录IP
     */
    @Column(name = "login_ip")
    private String loginIp;

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
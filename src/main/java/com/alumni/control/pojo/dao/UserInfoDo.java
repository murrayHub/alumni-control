package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user_info")
public class UserInfoDo {
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
     * 用户姓名
     */
    @Column(name = "user_real_name")
    private String userRealName;

    /**
     * 性别：1-男 2-女
     */
    private Integer gender;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 头像图片
     */
    @Column(name = "person_image")
    private String personImage;

    /**
     * 主页图片
     */
    @Column(name = "home_image")
    private String homeImage;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * 微信号
     */
    @Column(name = "weixin_no")
    private String weixinNo;

    /**
     * 邮箱可见权限  0-所有人可见 1-仅关注我可见 2-仅自己可见
     */
    @Column(name = "email_auth")
    private Integer emailAuth;

    /**
     * 电话可见权限  0-所有人可见 1-仅关注我可见 2-仅自己可见
     */
    @Column(name = "phone_no_auth")
    private Integer phoneNoAuth;

    /**
     * 微信号可见权限  0-所有人可见 1-仅关注我可见 2-仅自己可见
     */
    @Column(name = "weixin_no_auth")
    private Integer weixinNoAuth;

    /**
     * 核心标签
     */
    @Column(name = "core_label")
    private String coreLabel;

    /**
     * 所属行业
     */
    private Integer domain;

    /**
     * 认证状态  0-未认证 1-待审核 2-已认证 3-认证失败
     */
    @Column(name = "realname_status")
    private Integer realnameStatus;

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
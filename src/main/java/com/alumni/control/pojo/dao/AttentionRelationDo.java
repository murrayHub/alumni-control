package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_attention_relation")
public class AttentionRelationDo {
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
     * 目标对象
     */
    @Column(name = "target_object")
    private Long targetObject;

    /**
     * 关系类型：当目标对象为关注者，标示为1；当目标对象为被关注者，标示为2；当双方互相关注，标示为3
     */
    @Column(name = "relation_type")
    private Integer relationType;

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

    /**
     * 用户姓名
     */
    @Transient
    private String userRealname;

}
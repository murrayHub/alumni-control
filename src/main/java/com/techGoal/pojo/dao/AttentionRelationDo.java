package com.techGoal.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_attention_relation")
public class AttentionRelationDo {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 目标对象
     */
    @Column(name = "target_object")
    private String targetObject;

    /**
     * 关系类型：当目标对象为关注者，标示为1；当目标对象为被关注者，标示为2；当双方互相关注，标示为3
     */
    @Column(name = "relation_type")
    private Integer relationType;

}
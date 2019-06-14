package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_circle")
public class CircleDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 圈编号
     */
    @Column(name = "circle_no")
    private Long circleNo;

    /**
     * 圈主
     */
    @Column(name = "circle_host_no")
    private Long circleHostNo;

    /**
     * 圈名称
     */
    @Column(name = "circle_name")
    private String circleName;

    /**
     * 圈主题
     */
    @Column(name = "circle_theme")
    private String circleTheme;

    /**
     * 圈标签
     */
    @Column(name = "circle_label")
    private String circleLabel;

    /**
     * 圈头像
     */
    private String image;

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
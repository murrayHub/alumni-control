package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_ucas_institute")
public class UcasInstituteDo {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 学院编号
     */
    @Column(name = "institute_no")
    private Long instituteNo;

    /**
     * 学院名称
     */
    @Column(name = "institute_name")
    private String instituteName;

    /**
     * 状态 0-失效 1-有效 
     */
    private Integer enabled;

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
     * 单位所在地
     */
    private String location;

}
package com.alumni.control.utils.page;

import lombok.Data;

import java.io.Serializable;

/**
 * description : 分页器-父类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:10
 */
@Data
public class BasePage implements Serializable {
    private static final long serialVersionUID = 1608643753400864307L;
    private String beginTime;
    private String endTime;
    private Integer currentPage;
    private Integer pageSize;

    public BasePage() {
    }

}

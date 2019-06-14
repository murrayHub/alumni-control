package com.alumni.control.utils.page;

/**
 * description : 分页器
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:16
 */
public class Pagination {
    private Long total;
    private Integer pageSize;
    private Integer pages;
    private Integer current;

    public Long getTotal() {
        return this.total;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPages() {
        return this.pages;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String toString() {
        return "Pagination(total=" + this.getTotal() + ", pageSize=" + this.getPageSize() + ", pages=" + this.getPages() + ", current=" + this.getCurrent() + ")";
    }

    public Pagination() {
    }

    public Pagination(Long total, Integer pageSize, Integer pages, Integer current) {
        this.total = total;
        this.pageSize = pageSize;
        this.pages = pages;
        this.current = current;
    }
}

package com.alumni.control.utils.page;


import java.io.Serializable;
import java.util.List;

/**
 * description : 分页器
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:15
 */
public class PageRespDTO<T> implements Serializable {
    private static final long serialVersionUID = 3417019478991894661L;
    private List<T> list;
    private Pagination pagination;
    private Object paramList;

    public List<T> getList() {
        return this.list;
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public Object getParamList() {
        return this.paramList;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public void setParamList(Object paramList) {
        this.paramList = paramList;
    }

    public String toString() {
        return "PageRespDTO(list=" + this.getList() + ", pagination=" + this.getPagination() + ", paramList=" + this.getParamList() + ")";
    }

    public PageRespDTO() {
    }

    public PageRespDTO(List<T> list, Pagination pagination, Object paramList) {
        this.list = list;
        this.pagination = pagination;
        this.paramList = paramList;
    }
}

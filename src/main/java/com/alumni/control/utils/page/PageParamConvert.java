package com.alumni.control.utils.page;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * description : 分页器-参数转化
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:15
 */
public class PageParamConvert {
    public PageParamConvert() {
    }

    public static Integer getCurrentPage(Integer currentPage) {
        Integer result;
        if (currentPage == null) {
            result = 0;
        } else {
            result = currentPage - 1;
        }

        return result;
    }

    public static Integer getPageSize(Integer pageSize) {
        Integer result;
        if (pageSize == null) {
            result = 10;
        } else {
            result = pageSize;
        }

        return result;
    }

    public static PageRespDTO getPageRespDto(Page pageData) {
        PageRespDTO pageRespDTO = new PageRespDTO();
        Pagination pagination = new Pagination();
        pagination.setCurrent(pageData.getPageNum());
        pagination.setPages(pageData.getPages());
        pagination.setPageSize(pageData.getPageSize());
        pagination.setTotal(pageData.getTotal());
        pageRespDTO.setList(pageData.getResult());
        pageRespDTO.setPagination(pagination);
        return pageRespDTO;
    }

    public static PageRespDTO getPageRespDto(Page pageData, List result) {
        PageRespDTO pageRespDTO = new PageRespDTO();
        Pagination pagination = new Pagination();
        pagination.setCurrent(pageData.getPageNum());
        pagination.setPages(pageData.getPages());
        pagination.setPageSize(pageData.getPageSize());
        pagination.setTotal(pageData.getTotal());
        pageRespDTO.setList(result);
        pageRespDTO.setPagination(pagination);
        return pageRespDTO;
    }

    public static PageRespDTO getErrorPage() {
        PageRespDTO pageRespDTO = new PageRespDTO();
        Pagination pagination = new Pagination();
        pagination.setCurrent(0);
        pagination.setPages(0);
        pagination.setPageSize(0);
        pagination.setTotal(0L);
        pageRespDTO.setPagination(pagination);
        return pageRespDTO;
    }
}

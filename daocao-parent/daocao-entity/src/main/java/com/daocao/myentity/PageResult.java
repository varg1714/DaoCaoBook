package com.daocao.myentity;

import java.io.Serializable;
import java.util.List;

/**
 * @author varg
 * @date 2019/11/2 16:33
 */
public class PageResult implements Serializable {

    private Long total;
    private List rows;
    private Long totalPage;

    public Long getTotalPage() {
        return totalPage;
    }

    public PageResult(Long total, List rows, Long totalPage) {
        this.total = total;
        this.rows = rows;
        this.totalPage = totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

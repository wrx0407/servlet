package com.wrx.core;

import java.util.List;

/**
 * <p>
 * 封装分页常用数据
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/25
 */
public class Page<T> {
    private final Integer total;
    private Integer pageSize;
    private Integer pageNum;
    private Integer pages;
    private List<T> result;

    /**
     * 构造方法
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示条数
     * @param total    总数
     */
    public Page(Integer pageNum, Integer pageSize, Integer total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.setPages();
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", pages=" + pages +
                ", result=" + result +
                '}';
    }

    private void setPages() {
        if (total % getPageSize() == 0) {
            pages = total / getPageSize();
        } else {
            pages = (total / getPageSize()) + 1;
        }
        if (getPageNum() > pages) {
            pageNum = pages;
        }
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        return pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPageNum() {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        return pageNum;
    }

    public Integer getPages() {
        return pages;
    }

    public List<T> getResult() {
        return result;
    }

    /**
     * 设置结果集合
     *
     * @param result 结果集合
     */
    public void setResult(List<T> result) {
        this.result = result;
    }
}

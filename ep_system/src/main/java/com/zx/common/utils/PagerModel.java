package com.zx.common.utils;

import java.io.Serializable;

import java.util.List;

/**
 * Created by V.E. on 2017/3/9.
 *
 * @param <T>
 */
public class PagerModel<T> implements Serializable {

    /** Field description */
    public int TotalItemCount;

    /** Field description */
    public int PageSize;

    /** Field description */
    public int CurrentPage;

    /** Field description */
    public List<T> Items;

    /**
     * Constructs ...
     *
     *
     * @param pageSize 每页条数
     * @param currentPage 当前页
     * @param totalItemCount 总数
     * @param items 当前页数量
     */
    public PagerModel(int pageSize, int currentPage, int totalItemCount, List<T> items) {
        this.TotalItemCount = totalItemCount;
        this.CurrentPage    = currentPage;
        this.PageSize       = pageSize;
        this.Items          = items;
    }
}



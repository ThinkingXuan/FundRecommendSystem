package com.njupt.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageHelper<T> {
    @JsonProperty(value = "current_page")
    private long currentPage;
    private long total;
    @JsonProperty(value = "page_size")
    private long pageSize;
    private List<T> list;

    public PageHelper(long pageNum, long total, long pageSize, List<T> list) {
        this.currentPage = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        this.list = list;
    }

    public PageHelper(long pageNum, long pageSize, List<T> list) {
        this.currentPage = pageNum;
        this.pageSize = pageSize;
        this.list = list;
    }
}
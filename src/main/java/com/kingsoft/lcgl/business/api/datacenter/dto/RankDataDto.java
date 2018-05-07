package com.kingsoft.lcgl.business.api.datacenter.dto;

import java.util.Date;

/**
 * Created by yangdiankang on 2018/3/12.
 */
public class RankDataDto {
    private Date date;

    private String title;   //排名    标题
    private Long total;     //数量

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

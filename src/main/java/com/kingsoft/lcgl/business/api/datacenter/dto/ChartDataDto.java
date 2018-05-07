package com.kingsoft.lcgl.business.api.datacenter.dto;

import java.util.Date;

/**
 * Created by yangdiankang on 2018/3/12.
 */
public class ChartDataDto {
    private Date date;

    private String x; //横坐标（日期）
    private int y;   //纵坐标（数值）


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

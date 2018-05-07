package com.kingsoft.lcgl.business.api.datacenter.dto;

import java.util.Date;

/**
 * Created by yangdiankang on 2018/3/9.
 */
public class TaskDayDataDto {
    private Date date;
    private Long userId;
    private Long taskNumber;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Long taskNumber) {
        this.taskNumber = taskNumber;
    }
}

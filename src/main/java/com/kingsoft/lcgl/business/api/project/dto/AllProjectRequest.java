package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.util.TimeUtil;

import javax.validation.constraints.Min;

/**
 * Created by yangdiankang on 2018/2/9.
 */
public class AllProjectRequest {
    @Min(value = 1, message = "page的值小于1")
    private int page = 1;			// 第几页，从1开始计数

    private Long projectTypeId;
    private String date[];
    private Long stateValue;
    private String keyWord;
    private Long projectIds[];
    private Long userId;

    private  Long startDate;
    private  Long endDate;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getStartDate() {
        if(date.length>0){
            System.out.println("date"+date[0]+"--"+date[1]);
            return TimeUtil.getLongByStr(date[0], TimeEnum.TimeType1);
        }
        return -1L;
    }

    public Long getEndDate() {
        if(date.length>0){
            return TimeUtil.getLongByStr(date[1], TimeEnum.TimeType1);
        }
        return -1L;
    }
    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public Long getStateValue() {
        return stateValue;
    }

    public void setStateValue(Long stateValue) {
        this.stateValue = stateValue;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Long[] projectIds) {

        this.projectIds = projectIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

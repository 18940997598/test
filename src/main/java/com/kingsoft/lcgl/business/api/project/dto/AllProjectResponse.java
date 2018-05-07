package com.kingsoft.lcgl.business.api.project.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/2/9.
 */
public class AllProjectResponse {
    private int code = 0;
    private String msg;
    private Long totalRecord;
    private List<ProjectDto> data;


    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ProjectDto> getData() {
        return data;
    }

    public void setData(List<ProjectDto> data) {
        this.data = data;
    }
}

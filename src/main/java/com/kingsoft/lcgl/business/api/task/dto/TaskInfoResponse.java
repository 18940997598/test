package com.kingsoft.lcgl.business.api.task.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/1/30.
 */
public class TaskInfoResponse {
    private int code = 0;
    private String msg;
    private List<TaskInfoDto> data;

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

    public List<TaskInfoDto> getData() {
        return data;
    }

    public void setData(List<TaskInfoDto> data) {
        this.data = data;
    }
}

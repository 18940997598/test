package com.kingsoft.lcgl.business.api.task.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/2/1.
 */
public class TaskListResponse {
    private int code = 0;
    private String msg;
    private List<TaskListDto> data;

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

    public List<TaskListDto> getData() {
        return data;
    }

    public void setData(List<TaskListDto> data) {
        this.data = data;
    }
}

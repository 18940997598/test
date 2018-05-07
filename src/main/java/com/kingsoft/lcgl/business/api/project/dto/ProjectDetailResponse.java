package com.kingsoft.lcgl.business.api.project.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/1/26.
 */
public class ProjectDetailResponse {
    private int code;
    private String msg;
    private ProjectDto data;

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

    public ProjectDto getData() {
        return data;
    }

    public void setData(ProjectDto data) {
        this.data = data;
    }
}

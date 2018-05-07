package com.kingsoft.lcgl.business.api.common.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class ProjectTypeRespone {
    private int code;
    private String msg;
    private List<ProjectTypeDto> data;

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

    public List<ProjectTypeDto> getData() {
        return data;
    }

    public void setData(List<ProjectTypeDto> data) {
        this.data = data;
    }
}

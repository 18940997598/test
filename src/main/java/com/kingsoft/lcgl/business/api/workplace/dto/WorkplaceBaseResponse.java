package com.kingsoft.lcgl.business.api.workplace.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/1/25.
 */
public class WorkplaceBaseResponse {
    private int code = 0;
    private String msg;
    WorkplaceBaseDto data;

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

    public WorkplaceBaseDto getData() {
        return data;
    }

    public void setData(WorkplaceBaseDto data) {
        this.data = data;
    }
}

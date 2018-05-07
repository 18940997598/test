package com.kingsoft.lcgl.business.api.common.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/2/27.
 */
public class DepartmentTypeRespone {
    private int code = 0;
    private String msg;
    private List<DepartmentTypeDto> data;

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

    public List<DepartmentTypeDto> getData() {
        return data;
    }

    public void setData(List<DepartmentTypeDto> data) {
        this.data = data;
    }
}

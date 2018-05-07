package com.kingsoft.lcgl.business.api.project.dto;

/**
 * Created by yangdiankang on 2018/2/9.
 */
public class TypeNumberResponse {
    private int code = 0;
    private String msg;
    private TypeNumberDto data;

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

    public TypeNumberDto getData() {
        return data;
    }

    public void setData(TypeNumberDto data) {
        this.data = data;
    }
}

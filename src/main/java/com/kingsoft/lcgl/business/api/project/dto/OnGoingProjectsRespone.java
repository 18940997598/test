package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.api.project.dto.OnGoingProjectsDto;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class OnGoingProjectsRespone {
    private int code = 0;
    private String msg;
    private List<OnGoingProjectsDto> data;


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

    public List<OnGoingProjectsDto> getData() {
        return data;
    }

    public void setData(List<OnGoingProjectsDto> data) {
        this.data = data;
    }
}

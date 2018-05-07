package com.kingsoft.lcgl.business.api.journal.dto;

import com.kingsoft.lcgl.business.api.common.dto.DepartmentDto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/1/25.
 */
public class JournalResponse {
    private int code = 0;
    private String msg;
    private List<JournalDto> data;

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

    public List<JournalDto> getData() {
        return data;
    }

    public void setData(List<JournalDto> data) {
        this.data = data;
    }
}

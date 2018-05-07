package com.kingsoft.lcgl.business.api.admin.dto;

import com.kingsoft.lcgl.business.api.common.dto.DepartmentDto;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/3/20.
 */
public class DepartmentListResponse {
    private int code = 0 ;
    private String  msg ;
    private List<DepartmentDto> data;

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

    public List<DepartmentDto> getData() {
        return data;
    }

    public void setData(List<DepartmentDto> data) {
        this.data = data;
    }
}

package com.kingsoft.lcgl.business.api.admin.dto;

import com.kingsoft.lcgl.business.api.user.dto.UserDto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/2/28.
 */
public class UserListResponse {
    private int code = 0 ;
    private String  msg ;
    private List<UserDto> data;

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

    public List<UserDto> getData() {
        return data;
    }

    public void setData(List<UserDto> data) {
        this.data = data;
    }
}

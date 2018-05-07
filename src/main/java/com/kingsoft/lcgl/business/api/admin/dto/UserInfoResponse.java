package com.kingsoft.lcgl.business.api.admin.dto;

import com.kingsoft.lcgl.business.api.user.dto.UserDto;

/**
 * Created by yangdiankang on 2018/3/1.
 */
public class UserInfoResponse {
    private int code = 0;
    private String msg;
    private UserDto data;

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

    public UserDto getData() {
        return data;
    }

    public void setData(UserDto data) {
        this.data = data;
    }
}

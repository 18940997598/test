package com.kingsoft.lcgl.business.api.common.dto;

import com.kingsoft.lcgl.business.api.user.dto.UserDto;


import java.util.List;

/**
 * Created by yangdiankang on 2017/11/17.
 */
public class DepartmentRespone {
    private int code;
    private String msg;
    private DepartmentDto dto;
    private List<UserDto> userList;
    private List<DepartmentDto> data;


    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }

    public DepartmentDto getDto() {
        return dto;
    }

    public void setDto(DepartmentDto dto) {
        this.dto = dto;
    }

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

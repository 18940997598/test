package com.kingsoft.lcgl.business.api.user.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/3/2.
 */
public class UserBaseInfoResponse {
    private int code = 0;
    private String msg;
    private String name;
    private String img;
    private Long adminLevel;

    private List<MessageDto> data;


    public Long getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Long adminLevel) {
        this.adminLevel = adminLevel;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<MessageDto> getData() {
        return data;
    }

    public void setData(List<MessageDto> data) {
        this.data = data;
    }
}

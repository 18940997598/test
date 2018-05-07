package com.kingsoft.lcgl.business.api.user.dto;

/**
 * Created by yangdiankang on 2018/3/2.
 */
public class MessageDto {
    private String title;
    private String description;
    private Long endTime;
    private String type;
    private String status;
    private String avatar = "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png";

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

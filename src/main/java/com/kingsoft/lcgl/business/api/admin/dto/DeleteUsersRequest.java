package com.kingsoft.lcgl.business.api.admin.dto;

/**
 * Created by yangdiankang on 2018/3/1.
 */
public class DeleteUsersRequest {
    private  Long userIds[];

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }
}

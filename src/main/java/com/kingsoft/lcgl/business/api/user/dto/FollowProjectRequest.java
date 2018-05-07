package com.kingsoft.lcgl.business.api.user.dto;

/**
 * Created by yangdiankang on 2018/2/22.
 */
public class FollowProjectRequest {
    private int follow;
    private Long projectId;

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}

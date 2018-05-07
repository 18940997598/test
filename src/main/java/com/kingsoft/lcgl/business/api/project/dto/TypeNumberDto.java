package com.kingsoft.lcgl.business.api.project.dto;

/**
 * Created by yangdiankang on 2018/2/9.
 */
public class TypeNumberDto {
    private Long onGoingProject;
    private Long completeProject;
    private Long stopProject;
    private Long projectIds[];
    private Long userId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Long[] projectIds) {
        this.projectIds = projectIds;
    }

    public Long getOnGoingProject() {
        return onGoingProject;
    }

    public void setOnGoingProject(Long onGoingProject) {
        this.onGoingProject = onGoingProject;
    }

    public Long getCompleteProject() {
        return completeProject;
    }

    public void setCompleteProject(Long completeProject) {
        this.completeProject = completeProject;
    }

    public Long getStopProject() {
        return stopProject;
    }

    public void setStopProject(Long stopProject) {
        this.stopProject = stopProject;
    }
}

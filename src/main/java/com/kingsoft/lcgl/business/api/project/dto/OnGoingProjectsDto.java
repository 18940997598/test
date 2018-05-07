package com.kingsoft.lcgl.business.api.project.dto;

/**
 * Created by yangdiankang on 2018/1/24.
 */
public class OnGoingProjectsDto {
    private String title;
    private String content;        //项目简述
    private String founder;       //项目负责人
    private Long time;             //创建时间
    private Long projectId;
    private Long founderId;
    private String projectType;  //项目所属类型


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getFounderId() {
        return founderId;
    }

    public void setFounderId(Long founderId) {
        this.founderId = founderId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}

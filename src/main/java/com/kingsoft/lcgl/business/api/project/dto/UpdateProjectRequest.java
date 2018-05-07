package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.util.TimeUtil;

import java.util.List;

/**
 * 修改项目
 * Created by yangdiankang on 2018/2/23.
 */
public class UpdateProjectRequest {
    private Long projectId;
    private Boolean mail;
    private String mailContent;
    private String projectName;
    private String[] projectDate;
    private List<UpdateTasksDto> dataSourse;

    //
    private Long startTime;
    private Long endTime;


    public Long getStartTime() {
        return TimeUtil.getLongByStr(projectDate[0], TimeEnum.TimeType1);
    }

    public Long getEndTime() {
        return TimeUtil.getLongByStr(projectDate[1], TimeEnum.TimeType1);
    }

    public Boolean getMail() {

        return mail;
    }


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setMail(Boolean mail) {
        this.mail = mail;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String[] getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(String[] projectDate) {
        this.projectDate = projectDate;
    }

    public List<UpdateTasksDto> getDataSourse() {
        return dataSourse;
    }

    public void setDataSourse(List<UpdateTasksDto> dataSourse) {
        this.dataSourse = dataSourse;
    }
}

package com.kingsoft.lcgl.business.api.project.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/2/22.
 */
public class UpdateInfoResponse {

    private int code  = 0;
    private String msg;
    private Long projectId;
    private String projectName;
    private String projectDate[];
    private Long dateLong[];
    private List<UpdateTaskDto> taskDtos;


    public Long[] getDateLong() {
        return dateLong;
    }

    public void setDateLong(Long[] dateLong) {
        this.dateLong = dateLong;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public List<UpdateTaskDto> getTaskDtos() {
        return taskDtos;
    }

    public void setTaskDtos(List<UpdateTaskDto> taskDtos) {
        this.taskDtos = taskDtos;
    }
}

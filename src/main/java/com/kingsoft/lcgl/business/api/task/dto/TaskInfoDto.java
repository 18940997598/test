package com.kingsoft.lcgl.business.api.task.dto;

import com.kingsoft.lcgl.business.common.enums.TaskStateEnum;

import java.util.List;

/**
 * Created by yangdiankang on 2018/1/30.
 * 根据 项目id  获取任务详情 dto
 */
public class TaskInfoDto {
    private Long id;
    private String taskName;
    private Long startTime;
    private Long endTime;
    private String personMail;
    private int taskState;
    private String taskNotes;
    private List<RemarkDto> remarkData;
    private Long userId;
    private Long projectId;



    private String taskStateStr;            //任务状态


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskStateStr() {
        return TaskStateEnum.getNameById(this.taskState);
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getPersonMail() {
        return personMail;
    }

    public void setPersonMail(String personMail) {
        this.personMail = personMail;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    public List<RemarkDto> getRemarkData() {
        return remarkData;
    }

    public void setRemarkData(List<RemarkDto> remarkData) {
        this.remarkData = remarkData;
    }
}

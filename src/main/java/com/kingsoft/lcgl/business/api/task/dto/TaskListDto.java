package com.kingsoft.lcgl.business.api.task.dto;

import com.kingsoft.lcgl.business.common.enums.TaskStateEnum;

/**
 * 获取任务列表 dto
 * Created by yangdiankang on 2018/2/1.
 */
public class TaskListDto {
    private Long taskId;
    private String taskName;
    private String taskDocumentAdress;
    private int taskState;
    private String taskStateStr;
    private Long startTime;
    private Long endTime;
    private String userName;
    private Long userId;
    private Long departmentId;
    private String departmentName;
    private String departmentImg;
    private String img;   // 用户头像


    public void setTaskStateStr(String taskStateStr) {
        this.taskStateStr = taskStateStr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDepartmentImg() {
        return departmentImg;
    }

    public void setDepartmentImg(String departmentImg) {
        this.departmentImg = departmentImg;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDocumentAdress() {
        return taskDocumentAdress;
    }

    public void setTaskDocumentAdress(String taskDocumentAdress) {
        this.taskDocumentAdress = taskDocumentAdress;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public String getTaskStateStr() {
        return TaskStateEnum.getNameById(this.taskState);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

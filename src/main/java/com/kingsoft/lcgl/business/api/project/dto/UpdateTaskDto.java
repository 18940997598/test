package com.kingsoft.lcgl.business.api.project.dto;

/**
 * Created by yangdiankang on 2018/2/22.
 */
public class UpdateTaskDto {
    private Long taskId;
    private String taskName;
    private String datatime[];
    private Long datatimeLong[];
    private String personName;
    private String personValue;
    private String describe;
    private Long departmentId;
    private Long userId;
    private Long startTime;
    private Long endTime;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String[] getDatatime() {
        return datatime;
    }

    public void setDatatime(String[] datatime) {
        this.datatime = datatime;
    }

    public Long[] getDatatimeLong() {
        return datatimeLong;
    }

    public void setDatatimeLong(Long[] datatimeLong) {
        this.datatimeLong = datatimeLong;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonValue() {
        return personValue;
    }

    public void setPersonValue(String personValue) {
        this.personValue = personValue;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}

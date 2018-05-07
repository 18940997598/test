package com.kingsoft.lcgl.business.api.workplace.dto;

/**
 * Created by yangdiankang on 2018/1/25.
 */
public class WorkplaceBaseDto {
    private Long userId;
    private String name;                              //姓名
    private String departmentName;                  //部门
    private String img;                               //用户头像
    private int onGoingProject;                     //正在进行中的项目
    private int completeTask;                    //已完成任务数
    private int notCompleteTask;                 //尚未完成的任务数


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getOnGoingProject() {
        return onGoingProject;
    }

    public void setOnGoingProject(int onGoingProject) {
        this.onGoingProject = onGoingProject;
    }

    public int getCompleteTask() {
        return completeTask;
    }

    public void setCompleteTask(int completeTask) {
        this.completeTask = completeTask;
    }

    public int getNotCompleteTask() {
        return notCompleteTask;
    }

    public void setNotCompleteTask(int notCompleteTask) {
        this.notCompleteTask = notCompleteTask;
    }
}

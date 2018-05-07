package com.kingsoft.lcgl.business.api.task.dto;

import java.util.List;

/**
 * 提交任务 request 类
 * Created by yangdiankang on 2018/1/19.
 */
public class SubmitTaskRequest {
    private Long taskId;
    private String taskRemark ="";
    private String taskDocumentAdress="";
    private Boolean mail;
    private String []mailReciver;
    private String mailContent;
    private Long projectId;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public String getTaskDocumentAdress() {
        return taskDocumentAdress;
    }

    public void setTaskDocumentAdress(String taskDocumentAdress) {
        this.taskDocumentAdress = taskDocumentAdress;
    }

    public Boolean getMail() {
        return mail;
    }

    public void setMail(Boolean mail) {
        this.mail = mail;
    }

    public String[] getMailReciver() {
        return mailReciver;
    }

    public void setMailReciver(String[] mailReciver) {
        this.mailReciver = mailReciver;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }
}

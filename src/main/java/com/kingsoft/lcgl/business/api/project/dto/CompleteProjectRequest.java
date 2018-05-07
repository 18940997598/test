package com.kingsoft.lcgl.business.api.project.dto;

import java.util.List;

/**
 * 项目验收
 *Created by yangdiankang on 2018/1/19.
 */
public class CompleteProjectRequest {
    private String projectRemark;             //项目备注
    private String [] mailReciver;            //邮件接收者
    private String mailContent;              //邮件内容
    private Boolean mail;                     //是否发送邮件
    private Long projectId;
    private String type;                    //状态


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
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

    public Boolean getMail() {
        return mail;
    }

    public void setMail(Boolean mail) {
        this.mail = mail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}

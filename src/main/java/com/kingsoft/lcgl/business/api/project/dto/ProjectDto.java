package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.api.common.dto.ProjectTypeDto;
import com.kingsoft.lcgl.business.common.enums.ProjectStateEnum;

/**
 * Created by yangdiankang on 2018/1/25.
 */
public class ProjectDto {
   private String projectName;
   private Long startDate;
   private Long endDate;
   private String projectDescribe;

   private String projectType;
   private String projectTypeName;
   private String documentAddress;
   private String userId;
   private String userName;
   private int projectState;
   private Long creatTime;
   private String userDepartment;
   private String userMail;
   private Long projectId;
   private Long follow;

   private String stateStr;


    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public Long getFollow() {
        return follow;
    }

    public void setFollow(Long follow) {
        this.follow = follow;
    }

    public String getStateStr() {
        return ProjectStateEnum.getNameById(this.projectState);
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }


    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProjectState() {
        return projectState;
    }

    public void setProjectState(int projectState) {
        this.projectState = projectState;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }
}

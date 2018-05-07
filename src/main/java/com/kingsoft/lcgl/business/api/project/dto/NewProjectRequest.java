package com.kingsoft.lcgl.business.api.project.dto;

import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.util.TimeUtil;

/**
 * Created by yangdiankang on 2017/12/18.
 */
public class NewProjectRequest {
    private Long id;
    private String name;                                                //项目标题名
    private String date[];                                              //
    private String describe;                                           //项目描述
    private String projectTypeId[];                                    //参与部门的id
    private String documentAddress;
    private String mail;                                               //创建人邮箱



    private  Long startDate;
    private  Long endDate;

    private String projectTypeIds;
    private Long userId;                                              //负责人邮箱


    public String[] getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String[] projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectTypeIds() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<projectTypeId.length;i++){
            if(i!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append(projectTypeId[i]);
        }
        return stringBuilder.toString();
    }

    public void setProjectTypeIds(String projectTypeIds) {
        this.projectTypeIds = projectTypeIds;
    }

    public Long getId() {
        return id;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartDate() {
        return TimeUtil.getLongByStr(date[0], TimeEnum.TimeType1);
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return TimeUtil.getLongByStr(date[1], TimeEnum.TimeType1);
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}

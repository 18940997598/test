package com.kingsoft.lcgl.business.api.journal.dto;

import com.kingsoft.lcgl.business.common.enums.JournalTypeEnum;

/**
 * Created by yangdiankang on 2018/1/25.
 */
public class JournalDto {
    private Long id;
    private Long creatTime;
    private String operatorName;
    private String content;
    private Long operatorNameId;
    private String projectType;
    private String remark;
    private int journalType;
    private String journalTypeStr;


    public String getJournalTypeStr() {
        return JournalTypeEnum.getNameById(this.journalType);
    }

    public int getJournalType() {
        return journalType;
    }

    public void setJournalType(int journalType) {
        this.journalType = journalType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Long getOperatorNameId() {
        return operatorNameId;
    }

    public void setOperatorNameId(Long operatorNameId) {
        this.operatorNameId = operatorNameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

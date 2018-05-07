package com.kingsoft.lcgl.business.api.task.dto;

import com.kingsoft.lcgl.business.api.common.service.CommonService;
import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yangdiankang on 2018/1/19.
 */
public class TaskDto {

    @Autowired
    private CommonService commonService;

    private String taskName;
    private String datatime[];
    private String datatimeLong[];
    private String personName;
    private String personValue;
    private String describe;
    private Boolean isMail;

    //预计开始结束时间
    private Long startTime;
    private Long endTime;
    private Long departmentId;      //部门id
    private Long userId;             //任务执行人id
    private Long departmentUserId; //任务执行人部门主管id


    //获取   任务执行人部门主管id


    public Long getDepartmentUserId() {
        return departmentUserId;
    }

    public void setDepartmentUserId(Long departmentUserId) {
        this.departmentUserId = departmentUserId;
    }

    public String[] getDatatime() {
        return datatime;
    }

    public void setDatatime(String[] datatime) {
        this.datatime = datatime;
    }

    public String[] getDatatimeLong() {
        return datatimeLong;
    }

    public void setDatatimeLong(String[] datatimeLong) {
        this.datatimeLong = datatimeLong;
    }

    public Long getDepartmentId() {
        if(personValue.contains("/")){
            return Long.valueOf(personValue.split("/")[0]);
        }
        return Long.valueOf(personValue);
    }

    public Long getUserId() {
        if(personValue.contains("/")){
            return Long.valueOf(personValue.split("/")[1]);
        }
        return 0L;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Long getStartTime() {
        return TimeUtil.getLongByStr(datatimeLong[0], TimeEnum.TimeType1);
    }

    public Long getEndTime() {
        return TimeUtil.getLongByStr(datatimeLong[1], TimeEnum.TimeType1);
    }

}

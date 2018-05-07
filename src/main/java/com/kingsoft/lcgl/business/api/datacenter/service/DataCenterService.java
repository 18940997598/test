package com.kingsoft.lcgl.business.api.datacenter.service;


import com.kingsoft.lcgl.business.api.datacenter.dto.ProjectDataResponse;
import com.kingsoft.lcgl.business.api.datacenter.dto.TaskDataResponse;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface DataCenterService {

    //每天数据统计
    void everyDayData();

    //每月数据统计
    void everyMonthData();

    //查询任务统计数据
    TaskDataResponse getTaskData(String type,Long year,Long month,String day[],String mail);

    ProjectDataResponse getProjectData(String mail);
}

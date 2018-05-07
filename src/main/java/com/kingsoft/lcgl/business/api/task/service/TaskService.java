package com.kingsoft.lcgl.business.api.task.service;


import com.kingsoft.lcgl.business.api.datacenter.dto.TaskDayDataDto;
import com.kingsoft.lcgl.business.api.task.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.api.task.dto.SubmitTaskRequest;
import com.kingsoft.lcgl.business.api.task.dto.TaskInfoResponse;
import com.kingsoft.lcgl.business.api.task.dto.TaskListResponse;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface TaskService {

    CommonApiResponse addTask(ProjectTaskRequest request);

    TaskInfoResponse getTaskInfo(Long projectId,String mail);

    CommonApiResponse addTaskRemark(Long taskId,String content);

    TaskListResponse getTaskListByProjectId(Long projectId);

    CommonApiResponse submitTask(SubmitTaskRequest request);

    CommonApiResponse reminderTask(Long taskId,String userMail);

    CommonApiResponse deleteTaskById(Long taskId);

    void updateNeedHandle();

    //取得当前数据库中最小天数
    Date getStartDate();

    //取得聚合后的数据
    List<TaskDayDataDto> getTaskEveryDayData(Date day, Long dateTypeDay);

    List<TaskDayDataDto> getTaskEveryMonthData(Date day, Long dateTypeMonth);

    CommonApiResponse updateState(Long taskId,String state);
}

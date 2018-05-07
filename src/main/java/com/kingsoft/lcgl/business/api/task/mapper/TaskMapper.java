package com.kingsoft.lcgl.business.api.task.mapper;

import com.kingsoft.lcgl.business.api.datacenter.dto.TaskDayDataDto;
import com.kingsoft.lcgl.business.api.project.dto.UpdateTaskDto;
import com.kingsoft.lcgl.business.api.project.dto.UpdateTasksDto;
import com.kingsoft.lcgl.business.api.task.dto.*;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface TaskMapper {

    int addTask(@Param("taskList") List<TaskDto> taskList, @Param("projectId") Long projectId);

    int deleteTaskByProjectId(@Param("projectId") Long projectId);

    List<TaskInfoDto> getTaskInfo(@Param("projectId") Long projectId,@Param("userId")  Long userId);

    List<RemarkDto> getRemarkList(@Param("taskId") Long taskId);

    void addTaskRemark(@Param("taskId")Long taskId,@Param("content") String content);

    List<TaskListDto> getTaskListByProjectId(@Param("projectId") Long projectId);

    SubmitTaskSendMailDto getInfoTaskSubmit(@Param("taskId")Long taskId);

    int submitTask(SubmitTaskRequest request);

    void updateTaskStateByProjectId(@Param("projectId")Long projectId);

    List<Long> getProjectIdsByUserId(@Param("userId")Long userId);

    void updateFollow(@Param("projectId")Long projectId,@Param("userId") Long userId, @Param("follow")int follow);

    List<UpdateTaskDto> getTaskInfoByProjectId(@Param("projectId")Long projectId);

    void updateInfo(UpdateTasksDto dto);

    void addOneTask(UpdateTasksDto dto);

    int deleteTaskById(@Param("taskId") Long taskId);

    List<TaskInfoDto> getNotCompleTask();

    int getOrder(@Param("taskId")Long taskId);

    Long getNextUserIdOrder(@Param("projectId")Long projectId,@Param("order") int order);


    List<Long> getUserIdByProjectId(@Param("projectId") Long projectId);

    Long queryMinTime();


    List<TaskDayDataDto> getTaskEveryDayData(@Param("startDay") Long startTime, @Param("endDay")Long day);

    List<TaskDayDataDto> getTaskEveryMonthData(@Param("startDay")Long startTime,@Param("endDay") Long endDate);

    Long getFollow(@Param("userId")Long id,@Param("projectId") Long projectId);

    void updateState(@Param("taskId")Long taskId,@Param("state") String state);
}

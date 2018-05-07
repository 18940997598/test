package com.kingsoft.lcgl.business.api.project.mapper;

import com.kingsoft.lcgl.business.api.datacenter.dto.ChartDataDto;
import com.kingsoft.lcgl.business.api.datacenter.dto.ProChartDataDto;
import com.kingsoft.lcgl.business.api.project.dto.*;
import com.kingsoft.lcgl.business.api.task.dto.TaskDto;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import com.kingsoft.lcgl.core.mybatis.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface ProjectMapper {


    void addProject(NewProjectRequest request);

    int deleteTaskByProjectId(@Param("projectId") Long projectId );

    String getProjectNameByProjectId(@Param("projectId") Long projectId);

    ProjectDto getProjectInfoByProjectId(@Param("projectId") Long projectId);

    List<OnGoingProjectsDto> getOngoingProjects (@Param("userId") Long userId);

    void updateProjectState (@Param("projectId")Long projectId,@Param("type")int type);

    CompleteProjectSendMailDto getInfoProjectMail(@Param("projectId")Long projectId);

    List<StateDto> getTypeNumber(@Param("userId")Long userId, @Param("projectIds")Long[] projectIds);

    List<ProjectDto> getAllProject(@Param("list") List list,@Param("userId") Long userId,@Param("startDate") Long startDate,
                                   @Param("endDate")Long endDate, @Param("keyWord")String keyWord,@Param("stateValue") Long stateValue,
                                   @Param("projectTypeId")Long projectTypeId,Pagination<ProjectDto> pagination);

    void updateFollow(@Param("projectId")Long projectId,@Param("follow") int follow);

    void updateInfo(@Param("projectId")Long projectId,@Param("projectName") String projectName, @Param("startTime")Long startTime,@Param("endTime") Long endTime);

    Long getIdByProject(@Param("projectId")Long projectId);

    List<ProChartDataDto> getProjectData(@Param("userId") Long userId,@Param("list") List list);

}

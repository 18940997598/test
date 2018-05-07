package com.kingsoft.lcgl.business.api.project.service.impl;

import com.kingsoft.lcgl.business.api.common.mapper.CommonMapper;
import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.business.api.journal.service.JournalService;
import com.kingsoft.lcgl.business.api.project.dto.*;
import com.kingsoft.lcgl.business.api.project.mapper.ProjectMapper;
import com.kingsoft.lcgl.business.api.project.service.ProjectService;

import com.kingsoft.lcgl.business.api.task.mapper.TaskMapper;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.business.common.util.TimeUtil;
import com.kingsoft.lcgl.core.mybatis.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private JournalService journalService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private CommonMapper commonMapper;


    @Override
    public CommonApiResponse addProject(NewProjectRequest request) {
        CommonApiResponse commonApiResponse = new CommonApiResponse();
        request.setUserId(userMapper.getIdByMail(request.getMail()));
        try{

            projectMapper.addProject(request);
            commonApiResponse.setCode(0);
            commonApiResponse.setData(request.getId().toString());
            journalService.addProjectJournal(request);
        }catch (Exception e){
            commonApiResponse.setCode(500);
            commonApiResponse.setMsg(e.toString());
        }
        return commonApiResponse;
    }
    @Override
    public OnGoingProjectsRespone getOngoingProjects(String mail) {

        OnGoingProjectsRespone respone = new OnGoingProjectsRespone();
        try {
            Long userId = userMapper.getIdByMail(mail);
            respone.setData( projectMapper.getOngoingProjects(userId));
        }catch (Exception e){
            respone.setCode(500);
            logger.error(e.toString());
        }

        return respone;
    }

    @Override
    public ProjectDetailResponse getDetailProjectById(Long projectId,String mail) {
        ProjectDetailResponse response = new ProjectDetailResponse();
        ProjectDto dto = projectMapper.getProjectInfoByProjectId(projectId);
        UserDto userDto = userMapper.getUserInfoByMail(mail);
        Long userId = projectMapper.getIdByProject(projectId);
        Long follow = 0L;
        if(userId !=  userDto.getId()){
            follow =  taskMapper.getFollow(userDto.getId(),projectId);
            dto.setFollow(follow);
        }
        response.setData(dto);
        return response;
    }

    @Override
    public CommonApiResponse completeProject(CompleteProjectRequest request) {

        CommonApiResponse response = new CommonApiResponse();
        CompleteProjectSendMailDto dto = projectMapper.getInfoProjectMail(request.getProjectId());
        dto.setRemark(request.getProjectRemark());
        dto.setType(request.getType().equalsIgnoreCase("1")?"验收":"暂停");
        if(request.getMail()){
            this.sendMailCompleteProject(request,dto);
        }else if(request.getType().equals("1")) {
            this.sendSystemMailCompleteProject(request,dto);
        }
        try{
            projectMapper.updateProjectState(request.getProjectId(),Integer.valueOf(request.getType()));
            taskMapper.updateTaskStateByProjectId(request.getProjectId());
            journalService.completeProjectJournal(dto);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("项目验收失败");
            new Throwable(e);
        }
        return response;
    }

    private void sendSystemMailCompleteProject(CompleteProjectRequest request, CompleteProjectSendMailDto dto) {
         String receive [] = null;

        List<Long> userIds =  taskMapper.getUserIdByProjectId(request.getProjectId());
        HashSet hashSet = new HashSet(userIds);
        userIds.clear();
        userIds.addAll(hashSet);
        receive = new String[userIds.size()];
        int i = 0;

        for (Long userId:userIds){
            //查询所有关注此项目的参与人员的邮箱
            receive[i] =  userMapper.getMailById(userId);
            i++;
            //记录网站通知信息
            userMapper.addNotice("《"+ dto.getProjectName()+"》已经全部完成，经验收合格，感谢各位的配合和努力！",userId,"项目验收信息");
        }
        String subject = dto.getProjectName();
        String content ="发件人："+dto.getUserName()+"\n《"+ dto.getProjectName()+"》已经全部完成，经验收合格，感谢各位的配合和努力！";

        emailService.sendMail(receive,subject,content);
    }

    /**
     *提交任务发送邮件
     * @param request
     */
    private void sendMailCompleteProject(CompleteProjectRequest request, CompleteProjectSendMailDto dto) {
        String subject = dto.getProjectName();
        String content ="发件人："+dto.getUserName()+"\n"+ request.getMailContent();
        emailService.sendMail(request.getMailReciver(),subject,content);
    }

    @Override
    public TypeNumberResponse getTypeNumeber(String mail) {
        Long userId = userMapper.getIdByMail(mail);
        List<Long> projectIds = taskMapper.getProjectIdsByUserId(userId);
        Long [] projectIdsLong = new Long[projectIds.size()];
        for(int i = 0 ;i<projectIds.size();i++){
            projectIdsLong[i] = projectIds.get(i);
        }
        TypeNumberResponse response = new TypeNumberResponse();
        TypeNumberDto dto = new TypeNumberDto();
        dto.setProjectIds(projectIdsLong);
        dto.setUserId(userId);
        try {
            List<StateDto> stateDtos =  projectMapper.getTypeNumber(userId,projectIdsLong);
            for(StateDto stateDto:stateDtos){
                if(stateDto.getState() == 0){
                    dto.setOnGoingProject(stateDto.getCount());
                }else if(stateDto.getState() == 1){
                    dto.setCompleteProject(stateDto.getCount());
                }else {
                    dto.setStopProject(stateDto.getCount());
                }
            }
            response.setData(dto);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg(e.toString());
            new Throwable(e);
        }
        return response;
    }

    @Override
    public AllProjectResponse getAllProject(AllProjectRequest request,String mail) {
        request.setUserId(userMapper.getIdByMail(mail));
        List<Long> projectIds = taskMapper.getProjectIdsByUserId(request.getUserId());
        AllProjectResponse response = new AllProjectResponse();
        try{

            Pagination<ProjectDto> pagination = new Pagination<>();
            pagination.setCurrentPage(request.getPage());
            pagination.setPageSize(10);

            List<ProjectDto> projectDtoList =  projectMapper.getAllProject(projectIds,request.getUserId(),request.getStartDate(),request.getEndDate(),request.getKeyWord(),request.getStateValue(),request.getProjectTypeId(),pagination);

            response.setTotalRecord(pagination.getTotalRecord());
            response.setData(projectDtoList);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg(e.toString());
            new Throwable(e);
        }
        return response;
    }

    @Override
    public UpdateInfoResponse getUpdateInfo(Long projectId) {

        UpdateInfoResponse response = new UpdateInfoResponse();

        ProjectDto proDto = projectMapper.getProjectInfoByProjectId(projectId);
        response.setProjectId(proDto.getProjectId());
        response.setProjectName(proDto.getProjectName());
        String projectDate[] = {TimeUtil.getStrByLong(proDto.getStartDate(), TimeEnum.TimeType1),TimeUtil.getStrByLong(proDto.getEndDate(),TimeEnum.TimeType1)};
        response.setProjectDate(projectDate);
        Long date[] = {proDto.getStartDate(),proDto.getEndDate()};
        response.setDateLong(date);

        List<UpdateTaskDto> updateTaskDtos = taskMapper.getTaskInfoByProjectId(projectId);
        for(UpdateTaskDto taskDto:updateTaskDtos){
            Long dateLong[] = {taskDto.getStartTime(),taskDto.getEndTime()};
            taskDto.setDatatimeLong(dateLong);
            String dateTime[] =  {TimeUtil.getStrByLong(taskDto.getStartTime(),TimeEnum.TimeType1),TimeUtil.getStrByLong(taskDto.getEndTime(),TimeEnum.TimeType1)};
            taskDto.setDatatime(dateTime);
            taskDto.setPersonValue(taskDto.getDepartmentId()+"/"+taskDto.getUserId());
            taskDto.setPersonName(commonMapper.getDepartNameById(taskDto.getDepartmentId())+"/"+userMapper.getNameById(taskDto.getUserId()));
        }
        response.setTaskDtos(updateTaskDtos);
        return response;
    }

    @Override
    public CommonApiResponse updateProject(UpdateProjectRequest request) {
        CommonApiResponse response = new CommonApiResponse();

        projectMapper.updateInfo(request.getProjectId(),request.getProjectName(),request.getStartTime(),request.getEndTime());

        int i = 1;
        Long taskIds[] = new Long[request.getDataSourse().size()];
        for(UpdateTasksDto dto:request.getDataSourse()){
            dto.setOrder(i);
            dto.setProjectId(request.getProjectId());
            if(dto.getTaskId()!=null){
                taskMapper.updateInfo(dto);
            }else {
                taskMapper.addOneTask(dto);
            }
            taskIds[i-1] = dto.getTaskId();
                i++;
        }
        return response;
    }
}

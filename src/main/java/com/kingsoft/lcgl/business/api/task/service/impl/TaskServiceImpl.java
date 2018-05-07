package com.kingsoft.lcgl.business.api.task.service.impl;

import com.kingsoft.lcgl.business.api.common.service.CommonService;
import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.business.api.datacenter.dto.TaskDayDataDto;
import com.kingsoft.lcgl.business.api.journal.service.JournalService;
import com.kingsoft.lcgl.business.api.project.mapper.ProjectMapper;
import com.kingsoft.lcgl.business.api.task.dto.*;
import com.kingsoft.lcgl.business.api.task.mapper.TaskMapper;
import com.kingsoft.lcgl.business.api.task.service.TaskService;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.business.common.util.DateTimeUtils;
import com.kingsoft.lcgl.business.common.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonService commonService;

    @Override
    public CommonApiResponse addTask(ProjectTaskRequest request) {
        CommonApiResponse response  = new CommonApiResponse();
        if (request.getMail()){
            this.sendMailCreatTask(request);
        }
        try{
            for(TaskDto dto:request.getDataSourse()){
                dto.setDepartmentUserId(commonService.getDepartmentUserIdById(dto.getDepartmentId()));
            }
            int addTask =  taskMapper.addTask(request.getDataSourse(),request.getProjectId());
            journalService.addTaskJournal(request,0);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("操作数据库失败:"+e.toString());
            logger.error(e.toString());
        }
        return response;
    }



    @Override
    public TaskInfoResponse getTaskInfo(Long projectId, String mail) {

        TaskInfoResponse response =  new TaskInfoResponse();
        Long userId = userMapper.getIdByMail(mail);

        try {
            List<TaskInfoDto> dtoList = taskMapper.getTaskInfo(projectId,userId);
            for(TaskInfoDto dto:dtoList){
                UserDto userDto = userMapper.getUserInfoByprojectId(projectId);
                dto.setPersonMail(userDto.getMail());
                dto.setRemarkData(taskMapper.getRemarkList(dto.getId()));
            }
            response.setData(dtoList);
            return  response;
        }catch (Exception e){
            new Throwable(e);
        }
        response.setCode(500);
        return response;
    }



    @Override
    public CommonApiResponse addTaskRemark(Long taskId, String content) {
        CommonApiResponse response = new CommonApiResponse();
        try {
            taskMapper.addTaskRemark(taskId,content);
        }catch (Exception e){
            new Throwable(e);
        }
        response.setCode(0);
        return response;
    }

    @Override
    public TaskListResponse getTaskListByProjectId(Long projectId) {
        TaskListResponse response = new TaskListResponse();
        try {
           List<TaskListDto> taskListDtos = taskMapper.getTaskListByProjectId(projectId);

           response.setData(taskListDtos);
        }catch (Exception e){
            response.setCode(500);
            new Throwable(e);
        }
        return response;
    }

    @Override
    public CommonApiResponse submitTask(SubmitTaskRequest request) {
        CommonApiResponse response = new CommonApiResponse();
        SubmitTaskSendMailDto dto = taskMapper.getInfoTaskSubmit(request.getTaskId());
        dto.setRemark(request.getTaskRemark());

        if(request.getMail()){
            //自定义邮件
            this.sendMailSubmitTask(request,dto);
            this.systemMailSubmitTask(request,dto);
        }else {
            //发送系统提醒邮件
            this.systemMailSubmitTask(request,dto);
        }
        try{
            taskMapper.submitTask(request);
            journalService.submitTaskJournal(dto);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("任务提交失败");
            new Throwable(e);
        }
        return response;
    }

    private void systemMailSubmitTask(SubmitTaskRequest request, SubmitTaskSendMailDto dto) {
            dto.getProjectId();
            int taskOrder = taskMapper.getOrder(dto.getTaskId());
            Long userId = taskMapper.getNextUserIdOrder(dto.getProjectId(),taskOrder+1);
            String []receive = null ;
            if(userId!=null){
                receive = new String[2];
                receive[0] = userMapper.getMailById(userId);
                Long ownerId = projectMapper.getIdByProject(request.getProjectId());
                receive[1] = userMapper.getMailById(ownerId);
                String content =dto.getUserName()+"\n"+ "在项目《"+dto.getProjectName()+"》下的任务《"+dto.getTaskName()+"》已经完成，请进行后续的工作";
                userMapper.addNotice(content,userId,"任务完成通知");
                if(userId != ownerId) {
                    userMapper.addNotice(content,ownerId,"任务完成通知");
                }
            }else {
                receive = new String[1];
                Long ownerId = projectMapper.getIdByProject(request.getProjectId());
                receive[0] = userMapper.getMailById(ownerId);
                String content =dto.getUserName()+"\n"+ "在项目《"+dto.getProjectName()+"》下的任务《"+dto.getTaskName()+"》已经完成，请进行后续的工作";
                userMapper.addNotice(content,ownerId,"任务完成通知");
            }

            //发送邮件
        String content ="发件人："+dto.getUserName()+"\n"+ "在项目《"+dto.getProjectName()+"》下的任务《"+dto.getTaskName()+"》已经完成，请进行后续的任务";
        String subject = dto.getProjectName()+"-"+dto.getTaskName();


        emailService.sendMail(receive,subject,content);
    }


    @Override
    public CommonApiResponse reminderTask(Long taskId, String userMail) {
        CommonApiResponse response = new CommonApiResponse();

        try{
            UserDto userDto = userMapper.getUserInfoByMail(userMail);
            SubmitTaskSendMailDto taskDto = taskMapper.getInfoTaskSubmit(taskId);
            String []receive = userMapper.getMailById(taskDto.getUserId()).split(",");
            String subject =taskDto.getProjectName()+"-"+taskDto.getTaskName();
            String content = userDto.getName()+"提醒您尽快完成项目《"+taskDto.getProjectName()+"》下的任务《"+taskDto.getTaskName()+"》";
            userMapper.addNotice(content,taskDto.getUserId(),"提醒通知");
            emailService.sendMail(receive,subject,content);
        }catch (Exception e){
            response.setCode(500);
            new Throwable(e);
        }

        return response;
    }

    /**
     *提交任务发送邮件（自定义）
     * @param request
     */
    private void sendMailSubmitTask(SubmitTaskRequest request,SubmitTaskSendMailDto dto) {
        String subject = dto.getProjectName()+"-"+dto.getTaskName();
        String content ="发件人："+dto.getUserName()+"\n"+ request.getMailContent();
        emailService.sendMail(request.getMailReciver(),subject,content);
    }

    /**
     *创建任务发送邮件（自定义）
     * @param request
     */
    private void sendMailCreatTask(ProjectTaskRequest request) {

        String[] receiver = new String[request.getDataSourse().size()];
        for (int i= 0;i<request.getDataSourse().size();i++){
            receiver[i] = userMapper.getMailById(request.getDataSourse().get(i).getUserId());
        }
        UserDto dto = userMapper.getUserInfoByprojectId(request.getProjectId());
        String content ="发件人："+dto.getName()+"\n"+ request.getMailContent();
        emailService.sendMail(receiver,projectMapper.getProjectNameByProjectId(request.getProjectId()),content);
    }

    @Override
    public CommonApiResponse deleteTaskById(Long taskId) {
        CommonApiResponse response = new CommonApiResponse();
        try{
        int delete = taskMapper.deleteTaskById(taskId);
            System.out.println(delete);
        }catch (Exception e){
            response.setCode(500);
            new Throwable(e);
        }
        return response;
    }

    @Override
    public void updateNeedHandle() {

        try{
            List<TaskInfoDto> taskDtos = taskMapper.getNotCompleTask();
            userMapper.delteAllHandle();

            for(TaskInfoDto dto:taskDtos){
                String content = "项目《"+projectMapper.getProjectNameByProjectId(dto.getProjectId())+"》下的任务《"+dto.getTaskName()+"》截止日期在"+ TimeUtil.getStrByLong(dto.getEndTime(), TimeEnum.TimeType4)+"请尽快完成任务";
                userMapper.addHandle(dto.getUserId(),dto.getEndTime(),content,"任务通知",dto.getProjectId());
            }
        }catch (Exception e){
            new Throwable(e);
        }

    }

    @Override
    public Date getStartDate() {
        Long millisecond = taskMapper.queryMinTime();
        if(millisecond == null) {
            return null;
        } else {
            return new Date(millisecond*1000);
        }
    }

    @Override
    public List<TaskDayDataDto> getTaskEveryDayData(Date day, Long dateTypeDay) {
        Long startTime = DateTimeUtils.getStartMillTime(day, dateTypeDay);
        Long endTime = DateTimeUtils.getEndMillTime(day,dateTypeDay);
        return taskMapper.getTaskEveryDayData(startTime, endTime);
    }

    @Override
    public List<TaskDayDataDto> getTaskEveryMonthData(Date day, Long dateTypeMonth) {
        Long startTime = DateTimeUtils.getStartMillTime(day,dateTypeMonth);
        Long endTime = DateTimeUtils.getEndMillTime(day,dateTypeMonth);
        return taskMapper.getTaskEveryMonthData(startTime, endTime);
    }

    @Override
    public CommonApiResponse updateState(Long taskId, String state) {

        CommonApiResponse response = new CommonApiResponse();
        try{
            taskMapper.updateState(taskId,state);
        }catch (Exception e){
            response.setCode(500);
        }

        return response;
    }
}

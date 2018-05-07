package com.kingsoft.lcgl.business.api.task.controller;

import com.kingsoft.lcgl.business.api.task.dto.*;
import com.kingsoft.lcgl.business.api.task.service.TaskService;
import com.kingsoft.lcgl.business.common.support.CommonApiRequest;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskService taskService;
    @Autowired
    private HttpServletRequest httpRequest;

    /**
     * 新建项目时添加任务
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="addProjectTask", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse projectTask(@RequestBody ProjectTaskRequest request) {
        return  taskService.addTask(request);
    }

    /**
     * 获取任务内容根据项目id  和 邮箱
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getTaskInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskInfoResponse getTaskInfo(Long projectId) {

        logger.info("Id"+projectId);
        return  taskService.getTaskInfo(projectId,httpRequest.getHeader("mail"));
    }

    /**
     * 获取任务内容根据项目id  和 邮箱
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="addTaskRemark", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse addTaskRemark(@RequestBody TaskRemarkRequest remarkRequest) {
        return  taskService.addTaskRemark(remarkRequest.getTaskId(),remarkRequest.getContent());
    }

    /**
     * 获取项目id  获取任务列表
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getTaskListByProjectId", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskListResponse getTaskListByProjectId(Long projctId) {
        return  taskService.getTaskListByProjectId(projctId);
    }

    /**
     * 提交任务
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="submitTask", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse submitTask(@RequestBody SubmitTaskRequest request) {
        return  taskService.submitTask(request);
    }

    /**
     * 任务催促邮件发送
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="reminderTask", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse reminderTask(@RequestBody ReminderTaskRequest request) {
        return  taskService.reminderTask(request.getTaskId(),httpRequest.getHeader("mail"));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteTaskById", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse deleteTaskById(Long taskId) {
        return  taskService.deleteTaskById(taskId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="updateState", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse updateState(@RequestBody CommonApiRequest request  ) {
        return  taskService.updateState(request.getId(),request.getState());
    }

}

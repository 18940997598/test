package com.kingsoft.lcgl.business.api.project.controller;

import com.kingsoft.lcgl.business.api.project.dto.*;
import com.kingsoft.lcgl.business.api.project.service.ProjectService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
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
@RequestMapping("/api/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;
    @Autowired
    private HttpServletRequest httpRequest;

    /**
     * 创建项目
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="new_project", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse newProject(@RequestBody NewProjectRequest request) {
        return  projectService.addProject(request);
    }

    /**
     * 根据邮箱查询 正在进行中的项目
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getOnGoingProjects", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public OnGoingProjectsRespone getOngoingProjects() {
        return projectService.getOngoingProjects(httpRequest.getHeader("mail"));
    }

    /**
     * 根据项目id 查询项目详情
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getBaseInfoProject", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDetailResponse getBaseInfoProject(Long projectId) {
        String mail = httpRequest.getHeader("mail");
        return projectService.getDetailProjectById(projectId,mail);
    }

    /**
     * 验收项目
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="completeProject", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse completeProject(@RequestBody CompleteProjectRequest request) {
        return  projectService.completeProject(request);
    }

    /**
     * 获取项目完成，未完成，已终止数量
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getTypeNumeber", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeNumberResponse getTypeNumeber() {
        return projectService.getTypeNumeber(httpRequest.getHeader("mail"));
    }

    /**
     * 查询全部项目
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getAllProject", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public AllProjectResponse getAllProject(@RequestBody AllProjectRequest request) {
        return  projectService.getAllProject(request,httpRequest.getHeader("mail"));
    }

    /**
     * 获取项目详情已备更新数据使用
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getUpdateInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateInfoResponse getUpdateInfo(Long projectId) {
        return projectService.getUpdateInfo(projectId);
    }

    /**
     * 修改项目
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="updateProject", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse updateProject(@RequestBody UpdateProjectRequest request) {
        return  projectService.updateProject(request);
    }
}

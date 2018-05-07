package com.kingsoft.lcgl.business.api.project.service;


import com.kingsoft.lcgl.business.api.project.dto.*;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface ProjectService {

    CommonApiResponse addProject (NewProjectRequest request);


    OnGoingProjectsRespone getOngoingProjects(String mail);

    ProjectDetailResponse getDetailProjectById(Long projectId,String mail);

    CommonApiResponse completeProject(CompleteProjectRequest request);


    TypeNumberResponse getTypeNumeber(String mail);

    AllProjectResponse getAllProject(AllProjectRequest request,String mail);

    UpdateInfoResponse getUpdateInfo(Long projectId);

    CommonApiResponse updateProject(UpdateProjectRequest request);
}

package com.kingsoft.lcgl.business.api.common.service;


import com.kingsoft.lcgl.business.api.common.dto.*;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentTypeRespone;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface CommonService {

    ProjectTypeRespone commonGetProjectType();

    DepartmentPersonRespone commonGetDepartmentPerson();

    TaskRespone getTaskName();

    EmailListRespone commonGetAllEmail();

    DepartmentTypeRespone commonGetDepartmentType();

    Long getDepartmentUserIdById(Long departmentId);
}

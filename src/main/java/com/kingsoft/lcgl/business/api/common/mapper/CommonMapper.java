package com.kingsoft.lcgl.business.api.common.mapper;

import com.kingsoft.lcgl.business.api.common.dto.*;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface CommonMapper {

    List<DepartmentDto> getDepartments();

    List<ProjectTypeDto> commonGetProjectType();

    List<PersonDto> getPersons(@Param("departmentId")Long departmentId);

    List<TaskDto> getTaskName();

    List<EmailDto> commonGetAllEmail();

    String getDepartNameById(@Param("departmentId")Long departmentId);

    List<DepartmentTypeDto> commonGetDepartmentType();

    Long getDepartmentUserIdById(@Param("departmentId")Long departmentId);
}

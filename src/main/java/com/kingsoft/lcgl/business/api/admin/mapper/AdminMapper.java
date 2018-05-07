package com.kingsoft.lcgl.business.api.admin.mapper;

import com.kingsoft.lcgl.business.api.admin.dto.AddDepartmentRequest;
import com.kingsoft.lcgl.business.api.admin.dto.AddUserRequest;
import com.kingsoft.lcgl.business.api.common.dto.*;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface AdminMapper {


    int checkAdmin(@Param("userId") Long userId);

    void addUser(AddUserRequest request);

    void addAdmin(@Param("userId")Long userId,@Param("adminLevel") int adminLevel);

    Long getAdminLevel(@Param("userId") Long id);

    void deleteAdmin(@Param("userId")Long userId);

    List<DepartmentDto> getDepartments(@Param("name")String name);

    DepartmentDto getDepartmentInfoById(@Param("id") Long id);

    void addDepartment(AddDepartmentRequest request);

    void updateDepartment(AddDepartmentRequest request);

    void deleteDepartment(@Param("id") Long id);

    void deleteDepartments(@Param("departments")Long[] departments);
}

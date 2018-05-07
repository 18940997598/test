package com.kingsoft.lcgl.business.api.admin.service;


import com.kingsoft.lcgl.business.api.admin.dto.*;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentRespone;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface AdminService {
  void  checkAdminAuthority(HttpServletRequest request, HttpServletResponse response);

  CommonApiResponse addUser(AddUserRequest request);

  UserListResponse getUserList(String name, Long departmentId);

    UserInfoResponse getUserInfo(Long userId);

  CommonApiResponse updateUser(AddUserRequest request);

  CommonApiResponse deleteUser(Long userId);

  CommonApiResponse delteUsers(Long userIds[]);

  DepartmentListResponse getDepartmentList(String name);

  DepartmentRespone getDepartmentInfo(Long id);

  CommonApiResponse addDepartment(AddDepartmentRequest request);

  CommonApiResponse updateDepartment(AddDepartmentRequest request);

  CommonApiResponse deleteDepartment(Long id);


  CommonApiResponse deleteDepartments(Long[] departments);
}

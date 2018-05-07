package com.kingsoft.lcgl.business.api.admin.service.impl;


import com.kingsoft.lcgl.business.api.admin.dto.*;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentRespone;
import com.kingsoft.lcgl.business.api.common.mapper.CommonMapper;
import com.kingsoft.lcgl.business.api.project.service.impl.ProjectServiceImpl;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.admin.mapper.AdminMapper;
import com.kingsoft.lcgl.business.api.admin.service.AdminService;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.webservice.exception.RestCheckException;
import com.kingsoft.lcgl.core.webservice.support.ExceptionMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public void checkAdminAuthority(HttpServletRequest request, HttpServletResponse response) {
        Long userId = userMapper.getIdByMail(request.getHeader("mail"));
        if(adminMapper.checkAdmin(userId)==0){
            throw new RestCheckException(ExceptionMsg.getRestExceptionResponse(ExceptionMsg.AUTHORITY_ERROR));
        }
    }

    @Override
    public CommonApiResponse addUser(AddUserRequest request) {
        CommonApiResponse response = new CommonApiResponse();
        adminMapper.addUser(request);
        if(request.getAdminLevel()!=0){
            adminMapper.addAdmin(request.getUserId(),request.getAdminLevel());
        }
        return response;
    }

    @Override
    public UserInfoResponse getUserInfo(Long userId) {

        UserInfoResponse response = new UserInfoResponse();
        try{
            UserDto userDto = userMapper.getUserInfoById(userId);
            userDto.setAdminLevel(adminMapper.getAdminLevel(userDto.getId()));
            response.setData(userDto);
        }catch (Exception e){
            new Throwable(e);
        }
        return response;
    }

    @Override
    public UserListResponse getUserList(String name, Long departmentId) {

        UserListResponse response = new UserListResponse();
        List<UserDto> userdtoList =  userMapper.getAllUser(name,departmentId);
        try{
            for(UserDto userdto:userdtoList){
                userdto.setAdminLevel(adminMapper.getAdminLevel(userdto.getId())==null?0L:adminMapper.getAdminLevel(userdto.getId()));
            }
            response.setData(userdtoList);
        }catch (Exception e){
            logger.error("错误;"+e.toString());
            response.setCode(500);
            new Throwable(e);
        }
        return response;
    }

    @Override
    public CommonApiResponse updateUser(AddUserRequest request) {
        CommonApiResponse response = new CommonApiResponse();

        userMapper.updateUser(request);
        adminMapper.deleteAdmin(request.getUserId());
        if(request.getAdminLevel() != 0){
            adminMapper.addAdmin(request.getUserId(),request.getAdminLevel());
        }
        return response;
    }

    @Override
    public CommonApiResponse deleteUser(Long userId) {
        CommonApiResponse response = new CommonApiResponse();

        try{
            userMapper.deleteUser(userId);
        }catch (Exception e){
            new Throwable(e);
        }

        return response ;
    }

    @Override
    public CommonApiResponse delteUsers(Long userIds[]) {
        CommonApiResponse response = new CommonApiResponse();
        try {
            userMapper.deleteUsers(userIds);
        }catch (Exception e){
            response.setCode(500);
        }
        return response;
    }

    @Override
    public DepartmentListResponse getDepartmentList(String name) {

        DepartmentListResponse departmentListResponse = new DepartmentListResponse();
        departmentListResponse.setData(adminMapper.getDepartments(name));
        return departmentListResponse;

    }

    @Override
    public DepartmentRespone getDepartmentInfo(Long id) {
        DepartmentRespone respone = new DepartmentRespone();

        respone.setUserList(userMapper.getUserByDepartId(id));
        respone.setDto(adminMapper.getDepartmentInfoById(id));
        return respone;
    }

    @Override
    public CommonApiResponse addDepartment(AddDepartmentRequest request) {
        CommonApiResponse response = new CommonApiResponse();

        adminMapper.addDepartment(request);
        return response;
    }

    @Override
    public CommonApiResponse updateDepartment(AddDepartmentRequest request) {
        CommonApiResponse response = new CommonApiResponse();
        adminMapper.updateDepartment(request);
        return response;
    }

    @Override
    public CommonApiResponse deleteDepartment(Long id) {
        CommonApiResponse response = new CommonApiResponse();
        adminMapper.deleteDepartment(id);
        return response;
    }

    @Override
    public CommonApiResponse deleteDepartments(Long[] departments) {
        CommonApiResponse response = new CommonApiResponse();
        adminMapper.deleteDepartments(departments);
        return response;
    }
}

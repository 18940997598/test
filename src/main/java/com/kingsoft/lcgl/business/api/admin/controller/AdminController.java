package com.kingsoft.lcgl.business.api.admin.controller;


import com.kingsoft.lcgl.business.api.admin.dto.*;
import com.kingsoft.lcgl.business.api.admin.mapper.AdminMapper;
import com.kingsoft.lcgl.business.api.admin.service.AdminService;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentRespone;
import com.kingsoft.lcgl.business.api.task.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * 公共方法类
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="addUser", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse addUser(@RequestBody AddUserRequest request) {
        return  adminService.addUser(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getUserList", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public UserListResponse getUserList(String name, Long departmentId) {
        return  adminService.getUserList(name,departmentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getUserInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public UserInfoResponse getUserInfo(Long userId) {
        return  adminService.getUserInfo(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="updateUser", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse updateUser(@RequestBody AddUserRequest request) {
        return  adminService.updateUser(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteUser", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse deleteUser(Long userId) {
        return  adminService.deleteUser(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteUsers", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse deleteUsers(@RequestBody DeleteUsersRequest request) {
        return  adminService.delteUsers(request.getUserIds());
    }

    /**
     * 获取部门信息
     * @param name
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getDepartmentList", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public DepartmentListResponse getDepartmentList(String name) {
        return  adminService.getDepartmentList(name);
    }

    /**
     * 获取单个部门详细信息
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getDepartmentInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public DepartmentRespone getDepartmentInfo(Long  id) {
        return  adminService.getDepartmentInfo(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="addDepartment", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse addDepartment(@RequestBody AddDepartmentRequest request) {
        return  adminService.addDepartment(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="updateDepartment", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "updateDepartment")
    public CommonApiResponse updateDepartment(@RequestBody AddDepartmentRequest request) {
        return  adminService.updateDepartment(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteDepartment", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse deleteDepartment(Long id) {
        return  adminService.deleteDepartment(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteDepartments", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "admin")
    public CommonApiResponse deleteDepartments(@RequestBody DeleteDepartmentsRequest request) {
        return  adminService.deleteDepartments(request.getDepartmentIds());
    }
}

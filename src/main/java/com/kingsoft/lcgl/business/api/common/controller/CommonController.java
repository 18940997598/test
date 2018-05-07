package com.kingsoft.lcgl.business.api.common.controller;

import com.kingsoft.lcgl.business.api.common.dto.*;
import com.kingsoft.lcgl.business.api.common.service.CommonService;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentTypeRespone;
import com.kingsoft.lcgl.core.webservice.exception.MyException;
import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.core.webservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共方法类
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 获取项目类型
     * @return
     */
    @RequestMapping(value="commonGetProjectType", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProjectTypeRespone commonGetProjectType() throws MyException {
            return  commonService.commonGetProjectType();
    }
    /**
     * 获取部门信息
     * @return
     */
    @RequestMapping(value="commonGetDepartmentType", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DepartmentTypeRespone commonGetDepartmentType() throws MyException {
        return  commonService.commonGetDepartmentType();
    }



    /**
     * 获取 部门成员信息
     * @return
     * @throws MyException
     */
    @RequestMapping(value="commonGetDepartmentPerson", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DepartmentPersonRespone commonGetDepartmentPerson() throws MyException {
        return  commonService.commonGetDepartmentPerson();
    }

    /**
     * 获取任务名称
     * @return
     */
    @RequestMapping(value="commonGetTaskName", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskRespone commonGetTaskName() throws MyException {
        return  commonService.getTaskName();
    }

    /**
     *获取token
     * @return
     */
    @RequestMapping(value="commonGetToken", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TokenRespone commonGetToken() {
        return tokenService.commonGetToken(httpServletRequest.getHeader("mail"),httpServletRequest.getHeader("refreshToken"));
    }
    /**
     * 获取所有邮件联系人
     */
    @RequestMapping(value="commonGetAllEmail", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmailListRespone commonGetAllEmail(){
        return  commonService.commonGetAllEmail();
    }
}

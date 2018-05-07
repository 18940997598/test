package com.kingsoft.lcgl.business.api.common.service.impl;


import com.kingsoft.lcgl.business.api.common.dto.*;
import com.kingsoft.lcgl.business.api.common.mapper.CommonMapper;
import com.kingsoft.lcgl.business.api.common.service.CommonService;
import com.kingsoft.lcgl.business.api.common.dto.DepartmentTypeRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonMapper commonMapper;
    @Override
    public ProjectTypeRespone commonGetProjectType() {
        ProjectTypeRespone respone  = new ProjectTypeRespone();
        try{
            respone.setData(commonMapper.commonGetProjectType());
            respone.setCode(0);
        }catch (Exception ex){
            respone.setCode(500);
        }
        return respone;
    }

    @Override
    public DepartmentPersonRespone commonGetDepartmentPerson() {
        DepartmentPersonRespone respone = new DepartmentPersonRespone();
        List<DepartmentDto> departmentDtoList =  commonMapper.getDepartments();
        for(DepartmentDto dto:departmentDtoList){
            dto .setData(commonMapper.getPersons(dto.getId()));
        }
        respone.setCode(0);
        respone.setData(departmentDtoList);
        respone.setMsg("success");
        return respone;
    }

    @Override
    public TaskRespone getTaskName() {
        TaskRespone respone = new TaskRespone();
        try {
            respone.setData(commonMapper.getTaskName());
            respone.setCode(0);
        } catch (Exception ex) {
            respone.setCode(500);
        }
        return respone;
    }
    @Override
    public EmailListRespone commonGetAllEmail() {
        EmailListRespone respone = new EmailListRespone();
        try{
            respone.setData(commonMapper.commonGetAllEmail());
        }catch (Exception e){
            new Throwable(e);
        }
        return respone;
    }

    @Override
    public DepartmentTypeRespone commonGetDepartmentType() {
        DepartmentTypeRespone respone  = new DepartmentTypeRespone();
        try{
            respone.setData(commonMapper.commonGetDepartmentType());
            respone.setCode(0);
        }catch (Exception ex){
            respone.setCode(500);
        }
        return respone;
    }

    @Override
    public Long getDepartmentUserIdById(Long departmentId) {
        return commonMapper.getDepartmentUserIdById(departmentId);
    }
}

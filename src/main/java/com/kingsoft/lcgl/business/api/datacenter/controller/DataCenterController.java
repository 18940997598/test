package com.kingsoft.lcgl.business.api.datacenter.controller;

import com.kingsoft.lcgl.business.api.common.dto.ProjectTypeRespone;
import com.kingsoft.lcgl.business.api.datacenter.dto.ProjectDataResponse;
import com.kingsoft.lcgl.business.api.datacenter.dto.TaskDataResponse;
import com.kingsoft.lcgl.business.api.datacenter.service.DataCenterService;
import com.kingsoft.lcgl.core.webservice.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据挖掘接口
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/datacenter")
public class DataCenterController {
    @Autowired
    private DataCenterService dataCenterService;
    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * 获取个人任务统计数据
     * @return
     */
    @RequestMapping(value="getTaskData", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDataResponse getTaskData(String type,Long year,Long month,String day[]) {

        return dataCenterService.getTaskData(type,year,month,day,httpServletRequest.getHeader("mail"));
    }

    /**
     * 获取项目统计数据
     * @return
     */
    @RequestMapping(value="getProjectData", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProjectDataResponse getProjectData() {


        return dataCenterService.getProjectData(httpServletRequest.getHeader("mail"));
    }

}

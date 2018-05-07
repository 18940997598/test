package com.kingsoft.lcgl.business.api.workplace.controller;

import com.kingsoft.lcgl.business.api.workplace.dto.WorkplaceBaseResponse;
import com.kingsoft.lcgl.business.api.workplace.service.WorkplaceService;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
import com.kingsoft.lcgl.core.webservice.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志流
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/workplace")
public class WorkplaceController {
    private static final Logger logger = LoggerFactory.getLogger(WorkplaceController.class);
    @Autowired
    private WorkplaceService workplaceService;
    @Autowired
    private HttpServletRequest httpRequest;

    @RequestMapping(value="getWorkplaceBaseInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @Authority(value = "checkToken")
    @ResponseStatus(HttpStatus.OK)
    public WorkplaceBaseResponse getWorkplaceBaseInfo() throws MyException {

        return  workplaceService.getWorkplaceBaseInfo(httpRequest.getHeader("mail"));
    }

}

package com.kingsoft.lcgl.business.api.journal.controller;


import com.kingsoft.lcgl.business.api.journal.dto.JournalResponse;
import com.kingsoft.lcgl.business.api.journal.service.JournalService;
import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.util.TimeUtil;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
import com.kingsoft.lcgl.core.webservice.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 日志流
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/journal")
public class JournalController {
    private static final Logger logger = LoggerFactory.getLogger(JournalController.class);
    @Autowired
    private JournalService journalService;


    /**
     *获取最近动态
     * @return
     */
    @RequestMapping(value="getDynamicNow", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public JournalResponse getDynamicNow() throws MyException {
        return  journalService.getDynamicNow();
    }

    /**
     * 获取项目的 操作日志
     */
    @RequestMapping(value="getJournalByProjectId", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public JournalResponse getJournalByProjectId(Long projectId){
        return  journalService.getJournalByProjectId(projectId);
    }

    /**
     * 日志查询（多条件）管理员权限
     */
    @Authority("admin")
    @RequestMapping(value="getJournal", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public JournalResponse getJournal(String date[],String projectType,String content){
        Long startTime = TimeUtil.getLongByStr(date[0], TimeEnum.TimeType1);
        Long endTime = TimeUtil.getLongByStr(date[1], TimeEnum.TimeType1);
        return  journalService.getJournal(startTime,endTime,projectType,content);
    }


}

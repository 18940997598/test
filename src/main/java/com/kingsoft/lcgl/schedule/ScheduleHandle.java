package com.kingsoft.lcgl.schedule;

import com.kingsoft.lcgl.business.api.datacenter.service.DataCenterService;
import com.kingsoft.lcgl.business.api.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 定时任务类
 * Created by yangdiankang on 2018/1/25.
 */
@Component
@EnableScheduling
public class ScheduleHandle {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleHandle.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private DataCenterService dataCenterService;

    //每天凌晨一点
    @Scheduled(cron = "0 0 1 * * ?")
    public void everyDay() {
        logger.info("更新待办任务列表-start");
        taskService.updateNeedHandle();
        logger.info("更新待办任务列表-end");

        logger.info("统计每天任务数-start");
        dataCenterService.everyDayData();
        logger.info("统计每天任务数-end");

    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void onlineStart() {
        logger.info("online role count agg start");

        logger.info("online role count agg end");
    }

    //每周天凌晨一点
    @Scheduled(cron = "0 0 1 ? * ?")
    public void everyWeek(){

    }

    //每月一号凌晨一点
    @Scheduled(cron = "0 0 1 1 * ?")
    public void everyMonth(){
        logger.info("统计每月任务数-start");
        dataCenterService.everyMonthData();
        logger.info("统计每月任务数-end");
    }

    public void init() {
        everyDay();
        everyMonth();
    }
}

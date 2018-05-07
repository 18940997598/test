package com.kingsoft.lcgl.business.api.datacenter.service.impl;

import com.kingsoft.lcgl.business.api.datacenter.dto.*;
import com.kingsoft.lcgl.business.api.datacenter.mapper.DataCenterMapper;
import com.kingsoft.lcgl.business.api.datacenter.service.DataCenterService;
import com.kingsoft.lcgl.business.api.project.mapper.ProjectMapper;
import com.kingsoft.lcgl.business.api.task.mapper.TaskMapper;
import com.kingsoft.lcgl.business.api.task.service.TaskService;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.common.enums.TimeEnum;
import com.kingsoft.lcgl.business.common.util.DateTimeUtils;
import com.kingsoft.lcgl.business.common.util.TimeUtil;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
import org.joda.time.DateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class DataCenterServiceImpl implements DataCenterService {

    private static final Logger logger = LoggerFactory.getLogger(DataCenterServiceImpl.class);
    @Autowired
    private DataCenterMapper dataCenterMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private TaskMapper taskMapper;


    @Override
    public void everyDayData() {

        Date startDate = dataCenterMapper.queryMaxDateMonth();

        Date endDate = DateTime.now().minusDays(1).toDate();
        DateTime startDateTime;
        DateTime endDateTime = new DateTime(endDate).withTime(23, 59, 59, 999);
        if(startDate == null){
            startDate = taskService.getStartDate();
            if(startDate == null){
                logger.info("当前暂没有任务数据统计");
                return;
            }
            startDateTime = new DateTime(startDate).withTime(0, 0, 0, 0);
        }else {
            startDateTime = new DateTime(startDate).plusDays(1).withTime(0, 0, 0, 0);
        }
        for(DateTime currentDateTime = startDateTime; currentDateTime.getMillis() <= endDateTime.getMillis(); currentDateTime = currentDateTime.plusDays(1))
        {
            Date day = currentDateTime.toDate();
            List<TaskDayDataDto>  taskDayDataDtoList = taskService.getTaskEveryDayData(day, TimeEnum.DAY);
           if(taskDayDataDtoList.size()>0&&taskDayDataDtoList!=null){
               for (TaskDayDataDto dto:taskDayDataDtoList){
                   dto.setDate(day);
               }
               dataCenterMapper.insertTaskEveryDayDataBatch(taskDayDataDtoList);
           }
        }
    }

    @Override
    public void everyMonthData() {
        Date startDate = dataCenterMapper.queryMaxDateMonth();

        Date endDate = DateTime.now().minusDays(1).toDate();
        DateTime startDateTime;
        DateTime endDateTime = new DateTime(endDate).dayOfMonth().withMaximumValue().withTime(23, 59, 59, 999);
        if(startDate == null){
            startDateTime = endDateTime.dayOfYear().withMinimumValue().withTime(0, 0, 0, 0);
        }else {
            startDateTime = new DateTime(startDate).plusMonths(1).dayOfMonth().withMinimumValue().withTime(0, 0, 0, 0);
        }
        for(DateTime currentDateTime = startDateTime; currentDateTime.getMillis() <= endDateTime.getMillis(); currentDateTime = currentDateTime.plusMonths(1))
        {
            Date day = currentDateTime.toDate();
            Date date =  currentDateTime.withTime(2,2,2,0).toDate();
            List<TaskDayDataDto>  taskDayDataDtoList = taskService.getTaskEveryMonthData(day,TimeEnum.MONTH);
            if(taskDayDataDtoList.size()>0&&taskDayDataDtoList!=null){
                for (TaskDayDataDto dto:taskDayDataDtoList){
                    dto.setDate(date);
                }
                dataCenterMapper.insertTaskEveryMonthDataBatch(taskDayDataDtoList);
            }
        }
    }


    @Override
    public TaskDataResponse getTaskData(String type,Long year,Long month,String day[],String mail) {
        //type : 0按照日期查询，1 按照年份查询所有月，2.按照月份查询所有天

        Long userId = userMapper.getIdByMail(mail);
        TaskDataResponse response = new TaskDataResponse();
        if(type.equals("0")){
            Date startDate = TimeUtil.getDateByLong(DateTimeUtils.getStartMillTime(TimeUtil.getDateByStr(day[0],TimeEnum.TimeType1),TimeEnum.DAY));
            Date endDate = TimeUtil.getDateByLong(DateTimeUtils.getEndMillTime(TimeUtil.getDateByStr(day[1],TimeEnum.TimeType1),TimeEnum.DAY));

            List<ChartDataDto>  charDataDtoList = dataCenterMapper.getChartData(userId,startDate,endDate);

            for(ChartDataDto dto:charDataDtoList){
                dto.setX(DateTimeUtils.getMonthByDate(dto.getDate())+"月"+DateTimeUtils.getDayByDate(dto.getDate())+"号");
            }
            List<RankDataDto>  rankDataDtoList = dataCenterMapper.getRankData(userId,startDate,endDate);
            for(RankDataDto dto:rankDataDtoList){
                dto.setTitle(DateTimeUtils.getMonthByDate(dto.getDate())+"月"+DateTimeUtils.getDayByDate(dto.getDate())+"号");
            }
            response.setChartData(charDataDtoList);
            response.setRankData(rankDataDtoList);
        }else if(type.equals("1")){
            String startStr = year+"-01-01 00:00:00";
            String endStr = String.valueOf(Long.valueOf(year)+1)+"-01-01 00:00:00";

            List<ChartDataDto> chartDataDtoList = dataCenterMapper.getChartDataByYear(userId,TimeUtil.getDateByStr(startStr,TimeEnum.TimeType3),TimeUtil.getDateByStr(endStr,TimeEnum.TimeType3));
            for(ChartDataDto dto:chartDataDtoList){
                dto.setX(DateTimeUtils.getMonthByDate(dto.getDate())+"月");
            }
            List<RankDataDto>  rankDataDtoList = dataCenterMapper.getRankDataByYear(userId,TimeUtil.getDateByStr(startStr,TimeEnum.TimeType3),TimeUtil.getDateByStr(endStr,TimeEnum.TimeType3));
            for(RankDataDto dto:rankDataDtoList){
                dto.setTitle(DateTimeUtils.getMonthByDate(dto.getDate())+"月");
            }

            response.setChartData(chartDataDtoList);
            response.setRankData(rankDataDtoList);
        }else {
            Date date = TimeUtil.getDateByLong(month);

            Date startDate = TimeUtil.getDateByLong(DateTimeUtils.getStartMillTime(date,TimeEnum.MONTH));
            Date endDate = TimeUtil.getDateByLong(DateTimeUtils.getEndMillTime(date,TimeEnum.MONTH));

            List<ChartDataDto>  charDataDtoList = dataCenterMapper.getChartData(userId,startDate,endDate);
            for(ChartDataDto dto:charDataDtoList){
                dto.setX(DateTimeUtils.getDayByDate(dto.getDate())+"号");
            }
            List<RankDataDto>  rankDataDtoList = dataCenterMapper.getRankData(userId,startDate,endDate);
            for(RankDataDto dto:rankDataDtoList){
                dto.setTitle(DateTimeUtils.getDayByDate(dto.getDate())+"号");
            }
            response.setChartData(charDataDtoList);
            response.setRankData(rankDataDtoList);
        }

        //无数据时做处理
        if(response.getChartData().size()<1){
            ChartDataDto dto = new ChartDataDto();
            dto.setX("当前日期暂无数据");
            dto.setY(0);
            List<ChartDataDto>  charDataDtoList = new ArrayList<>();
            charDataDtoList.add(dto);
            response.setChartData(charDataDtoList);
        }

        return response;
    }


    @Override
    public ProjectDataResponse getProjectData(String mail) {

        ProjectDataResponse response = new ProjectDataResponse();

        Long userId = userMapper.getIdByMail(mail);
        List<Long> projectIdList = taskMapper.getProjectIdsByUserId(userId);
        List<ProChartDataDto> chartDataDtoList = projectMapper.getProjectData(userId,projectIdList);

        response.setChartData(chartDataDtoList);
        return response;
    }
}

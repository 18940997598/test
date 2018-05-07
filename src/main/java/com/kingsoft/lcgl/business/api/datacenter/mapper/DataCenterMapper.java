package com.kingsoft.lcgl.business.api.datacenter.mapper;


import com.kingsoft.lcgl.business.api.datacenter.dto.ChartDataDto;
import com.kingsoft.lcgl.business.api.datacenter.dto.RankDataDto;
import com.kingsoft.lcgl.business.api.datacenter.dto.TaskDayDataDto;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface DataCenterMapper {

    Date queryMaxDate();

    void insertTaskEveryDayDataBatch(List<TaskDayDataDto> taskDayDataDtoList);

    Date queryMaxDateMonth();

    void insertTaskEveryMonthDataBatch(List<TaskDayDataDto> taskDayDataDtoList);

    List<ChartDataDto> getChartData(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<RankDataDto> getRankData(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<ChartDataDto> getChartDataByYear(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<RankDataDto> getRankDataByYear(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}

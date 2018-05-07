package com.kingsoft.lcgl.business.api.datacenter.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/3/12.
 */
public class TaskDataResponse {
    private int code = 0;
    private String msg;
    private List<ChartDataDto> chartData;
    private List<RankDataDto> rankData;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ChartDataDto> getChartData() {
        return chartData;
    }

    public void setChartData(List<ChartDataDto> chartData) {
        this.chartData = chartData;
    }

    public List<RankDataDto> getRankData() {
        return rankData;
    }

    public void setRankData(List<RankDataDto> rankData) {
        this.rankData = rankData;
    }
}

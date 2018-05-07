package com.kingsoft.lcgl.business.api.datacenter.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/3/12.
 */
public class ProjectDataResponse {
    private int code = 0;
    private String msg;
    private List<ProChartDataDto> chartData;

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

    public List<ProChartDataDto> getChartData() {
        return chartData;
    }

    public void setChartData(List<ProChartDataDto> chartData) {
        this.chartData = chartData;
    }
}

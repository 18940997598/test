package com.kingsoft.lcgl.business.common.support;

/**
 * Created by yangdiankang on 2018/3/20.
 */
public class CommonApiRequest {
    private Long id;
    private String state;
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

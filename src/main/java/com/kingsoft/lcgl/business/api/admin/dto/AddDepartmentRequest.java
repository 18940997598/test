package com.kingsoft.lcgl.business.api.admin.dto;

/**
 * Created by yangdiankang on 2018/2/27.
 */
public class AddDepartmentRequest {
    private Long id;
    private String name;
    private String introduce;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

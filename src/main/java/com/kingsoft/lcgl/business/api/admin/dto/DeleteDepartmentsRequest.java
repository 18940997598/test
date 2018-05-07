package com.kingsoft.lcgl.business.api.admin.dto;

import java.util.List;

/**
 * Created by yangdiankang on 2018/3/21.
 */
public class DeleteDepartmentsRequest {
    private  Long departmentIds[];

    public Long[] getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(Long[] departmentIds) {
        this.departmentIds = departmentIds;
    }
}

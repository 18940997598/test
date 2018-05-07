package com.kingsoft.lcgl.business.api.workplace.service;

import com.kingsoft.lcgl.business.api.workplace.dto.WorkplaceBaseResponse;

/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface WorkplaceService {

    WorkplaceBaseResponse getWorkplaceBaseInfo(String mail);
}

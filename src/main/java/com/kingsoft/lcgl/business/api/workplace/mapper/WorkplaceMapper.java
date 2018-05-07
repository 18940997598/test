package com.kingsoft.lcgl.business.api.workplace.mapper;

import com.kingsoft.lcgl.business.api.workplace.dto.WorkplaceBaseDto;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface WorkplaceMapper {



    int getCompleteTask (@Param("userId") Long userId);

    int getNotCompleteTask (@Param("userId") Long userId);
}

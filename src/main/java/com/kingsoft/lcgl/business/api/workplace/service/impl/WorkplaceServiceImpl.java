package com.kingsoft.lcgl.business.api.workplace.service.impl;

import com.kingsoft.lcgl.business.api.common.mapper.CommonMapper;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.api.workplace.dto.WorkplaceBaseDto;
import com.kingsoft.lcgl.business.api.workplace.dto.WorkplaceBaseResponse;
import com.kingsoft.lcgl.business.api.workplace.mapper.WorkplaceMapper;
import com.kingsoft.lcgl.business.api.workplace.service.WorkplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class WorkplaceServiceImpl implements WorkplaceService {
    private static final Logger logger = LoggerFactory.getLogger(WorkplaceServiceImpl.class);
    @Autowired
    private WorkplaceMapper workplaceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public WorkplaceBaseResponse getWorkplaceBaseInfo(String mail) {

        WorkplaceBaseResponse response = new WorkplaceBaseResponse();

        try{
            UserDto userDto = userMapper.getUserInfoByMail(mail);
            WorkplaceBaseDto dto =  new WorkplaceBaseDto();

            dto.setDepartmentName(commonMapper.getDepartNameById(userDto.getDepartmentId()));
            dto.setCompleteTask(workplaceMapper.getCompleteTask(userDto.getId()));
            dto.setNotCompleteTask(workplaceMapper.getNotCompleteTask(userDto.getId()));
            dto.setName(userDto.getName());
            dto.setImg(userDto.getImg());
            dto.setDepartmentName(commonMapper.getDepartNameById(userDto.getDepartmentId()));
            response.setData(dto);
        }catch (Exception e){
            logger.error(e.toString());
        }

        return response;
    }
}

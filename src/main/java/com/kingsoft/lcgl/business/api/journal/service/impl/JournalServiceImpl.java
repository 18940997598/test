package com.kingsoft.lcgl.business.api.journal.service.impl;

import com.kingsoft.lcgl.business.api.journal.dto.JournalResponse;
import com.kingsoft.lcgl.business.api.journal.mapper.JournalMapper;
import com.kingsoft.lcgl.business.api.journal.service.JournalService;
import com.kingsoft.lcgl.business.api.project.dto.CompleteProjectSendMailDto;
import com.kingsoft.lcgl.business.api.project.dto.NewProjectRequest;
import com.kingsoft.lcgl.business.api.project.dto.ProjectDto;
import com.kingsoft.lcgl.business.api.task.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.api.project.mapper.ProjectMapper;
import com.kingsoft.lcgl.business.api.task.dto.SubmitTaskSendMailDto;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class JournalServiceImpl implements JournalService {
    private static final Logger logger = LoggerFactory.getLogger(JournalServiceImpl.class);
    @Autowired
    private JournalMapper journalMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public void addTaskJournal(ProjectTaskRequest request, int delteNumber) {

        ProjectDto projectDto  = projectMapper.getProjectInfoByProjectId(request.getProjectId());
        String content =  delteNumber == 0?"为项目《"+projectDto.getProjectName()+"》添加了新的任务":"修改了项目《"+projectDto.getProjectName()+"》的任务";
        UserDto dto = userMapper.getUserInfoByprojectId(request.getProjectId());
        journalMapper.addJournal(dto.getId(),request.getProjectId(),delteNumber == 0?4:6,"",content,0L,String.valueOf(projectDto.getProjectType()));
    }

    @Override
    public JournalResponse getDynamicNow() {
        JournalResponse response = new JournalResponse();
        response.setData(journalMapper.getDynamicNow());
        return response;
    }

    @Override
    public void addProjectJournal(NewProjectRequest request) {
        String content = "新建项目《"+request.getName()+"》";
        journalMapper.addJournal(request.getUserId(),request.getId(),1,"",content,0L,request.getProjectTypeIds());
    }

    @Override
    public void submitTaskJournal(SubmitTaskSendMailDto dto) {
        String content = "提交了在项目《"+dto.getProjectName()+"》下的任务《"+dto.getTaskName()+"》";
        journalMapper.addJournal(dto.getUserId(),dto.getProjectId(),5,dto.getRemark(),content,dto.getTaskId(),dto.getProjectTypeId());
    }

    @Override
    public JournalResponse getJournalByProjectId(Long projectId) {
        JournalResponse response = new JournalResponse();
        response.setData(journalMapper.getJournalByProjectId(projectId));
        return response;
    }

    @Override
    public void completeProjectJournal(CompleteProjectSendMailDto dto) {
        String content = dto.getType()+"了在项目《"+dto.getProjectName()+"》";
        journalMapper.addJournal(dto.getUserId(),dto.getProjectId(),5,dto.getRemark(),content,0L,dto.getProjectTypeId());
    }


    @Override
    public JournalResponse getJournal(Long startTime, Long endTime, String projectType, String content) {
        JournalResponse response = new JournalResponse();
        response.setData(journalMapper.getJournal(startTime,endTime,projectType,content));
        return response;
    }
}

package com.kingsoft.lcgl.business.api.journal.service;

import com.kingsoft.lcgl.business.api.journal.dto.JournalDto;
import com.kingsoft.lcgl.business.api.journal.dto.JournalResponse;
import com.kingsoft.lcgl.business.api.project.dto.CompleteProjectSendMailDto;
import com.kingsoft.lcgl.business.api.project.dto.NewProjectRequest;
import com.kingsoft.lcgl.business.api.task.dto.ProjectTaskRequest;
import com.kingsoft.lcgl.business.api.task.dto.SubmitTaskRequest;
import com.kingsoft.lcgl.business.api.task.dto.SubmitTaskSendMailDto;

import java.util.List;


/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface JournalService {

    void addProjectJournal(NewProjectRequest request);

    void addTaskJournal(ProjectTaskRequest request,int delteNumber);

    void submitTaskJournal(SubmitTaskSendMailDto dto);

    void completeProjectJournal(CompleteProjectSendMailDto dto);

    JournalResponse getDynamicNow();

    JournalResponse getJournalByProjectId(Long projectId);

    JournalResponse getJournal(Long startTime, Long endTime, String projectType, String content);
}

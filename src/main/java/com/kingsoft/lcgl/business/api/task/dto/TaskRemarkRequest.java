package com.kingsoft.lcgl.business.api.task.dto;

/**
 * Created by yangdiankang on 2018/1/31.
 */
public class TaskRemarkRequest {
    private Long taskId;
    private String content;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

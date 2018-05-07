package com.kingsoft.lcgl.business.api.task.dto;

/**
 * Created by yangdiankang on 2018/1/30.
 */
public class RemarkDto {
    private Long id;
    private String content;
    private Long taskId;
    private Long creatTime;


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }
}

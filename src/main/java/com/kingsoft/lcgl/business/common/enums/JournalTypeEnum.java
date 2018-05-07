package com.kingsoft.lcgl.business.common.enums;

/**
 * Created by yangdiankang on 2018/2/2.
 */
public enum JournalTypeEnum {
    newProject(1,"新建项目"), erroProject(2,"终止项目"),completeProject(3,"完成项目"), addTask(4,"添加任务"),completeTask(5,"完成任务"),updateTask(6,"修改任务");
    JournalTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private int id;
    private String name ;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public static String getNameById(int id){
        for(JournalTypeEnum type : JournalTypeEnum.values()) {
            if(type.id == id) {
                return type.name;
            }
        }
        return "找不到状态";
    }
}

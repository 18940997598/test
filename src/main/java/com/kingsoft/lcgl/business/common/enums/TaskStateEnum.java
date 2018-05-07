package com.kingsoft.lcgl.business.common.enums;

/**
 * Created by yangdiankang on 2018/1/31.
 */
public enum TaskStateEnum {
    runing(0,"进行中"), complete(1,"完成"),end(2,"终止任务"), notstart(3,"未开始");
    TaskStateEnum(int id, String name) {
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
        for(TaskStateEnum type : TaskStateEnum.values()) {
            if(type.id == id) {
                return type.name;
            }
        }
        return "找不到状态";
    }
}

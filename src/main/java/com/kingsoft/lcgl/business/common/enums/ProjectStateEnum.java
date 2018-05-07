package com.kingsoft.lcgl.business.common.enums;

/**
 * Created by yangdiankang on 2018/2/11.
 */
public enum  ProjectStateEnum {
    onGoingProject(0,"进行中"), completeProject(1,"完成项目"), stopProect(2,"终止");
    ProjectStateEnum(int id, String name) {
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
        for(ProjectStateEnum type : ProjectStateEnum.values()) {
            if(type.id == id) {
                return type.name;
            }
        }
        return "找不到状态";
    }
}

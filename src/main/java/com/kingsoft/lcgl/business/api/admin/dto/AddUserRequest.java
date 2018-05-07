package com.kingsoft.lcgl.business.api.admin.dto;

/**
 * Created by yangdiankang on 2018/2/27.
 */
public class AddUserRequest {
    private String adminMail;     //操作者mail
    private Long userId;          //插入数据库返回id
    private String name;
    private String mail;
    private String phone;
    private Long departmentId;    //部门id
    private int adminLevel;       //管理员权限


    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

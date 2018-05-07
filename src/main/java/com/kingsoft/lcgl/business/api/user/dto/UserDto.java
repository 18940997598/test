package com.kingsoft.lcgl.business.api.user.dto;

/**
 * Created by yangdiankang on 2018/2/28.
 */
public class UserDto {
    private Long id;
    private String name;
    private String mail;
    private String phone;
    private String pass;
    private Long departmentId;
    private String department;
    private Long adminLevel = 0L;
    private String img;
    private String adminLevelStr;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAdminLevelStr() {

        return adminLevelStr;
    }

    public void setAdminLevelStr(String adminLevelStr) {
        this.adminLevelStr = adminLevelStr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Long adminLevel) {
        switch(String.valueOf(adminLevel)){
            case "1":
                this.adminLevelStr = "一级权限";
                break;
            case "2":
                this.adminLevelStr = "二级权限";
                break;
            default:
                this.adminLevelStr = "无管理权限";
                break;
        }
        this.adminLevel = adminLevel;
    }
}

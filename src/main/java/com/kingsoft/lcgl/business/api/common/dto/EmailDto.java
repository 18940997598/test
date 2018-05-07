package com.kingsoft.lcgl.business.api.common.dto;

/**
 * Created by yangdiankang on 2018/2/2.
 */
public class EmailDto {
    private Long id;
    private String name;
    private String mail;
    private Long userId;

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


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

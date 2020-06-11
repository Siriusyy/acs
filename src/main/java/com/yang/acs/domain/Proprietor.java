package com.yang.acs.domain;

import java.util.Date;

/**
 * 业主实体类
 */
public class Proprietor {
    private Integer id;
    private String name;
    private Boolean sex;    //false :女
    private String idnum;
    private String phonenum;
    private String email;
    private String address;
    private Date regDate;
    private Boolean status; //true  :启用
    private String imagePath;

    public Proprietor() {
    }

    @Override
    public String toString() {
        return "Proprietor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idnum='" + idnum + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", regDate=" + regDate +
                ", status=" + status +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Proprietor(Integer id, String name, Boolean sex, String idnum, String phonenum, String email,
                      String address, Date regDate, Boolean status, String imagePath) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.idnum = idnum;
        this.phonenum = phonenum;
        this.email = email;
        this.address = address;
        this.regDate = regDate;
        this.status = status;
        this.imagePath = imagePath;
    }

    public Proprietor(String name, Boolean sex, String idnum, String phonenum, String email,
                      String address, Boolean status) {
        this.name = name;
        this.sex = sex;
        this.idnum = idnum;
        this.phonenum = phonenum;
        this.email = email;
        this.address = address;
        this.regDate = new Date();
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}

package com.yang.acs.domain;

import java.util.Date;

public class VisitorLog {
    private Integer id;
    private Date date;
    private String address;
    private String proName;
    private Boolean status;

    @Override
    public String toString() {
        return "VisitorLog{" +
                "id=" + id +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", proName='" + proName + '\'' +
                ", status=" + status +
                '}';
    }

    public VisitorLog(Integer id, Date date, String address, String proName, Boolean status) {
        this.id = id;
        this.date = date;
        this.address = address;
        this.proName = proName;
        this.status = status;
    }

    public Integer getUuid() {
        return id;
    }

    public void setUuid(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

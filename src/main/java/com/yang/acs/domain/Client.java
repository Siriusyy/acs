package com.yang.acs.domain;

import java.util.Date;

public class Client {
    private String id;
    private String address;
    private Date regDate;
    private String version;
    private Boolean status;

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", date=" + regDate +
                ", version='" + version + '\'' +
                ", status=" + status +
                '}';
    }

    public Client(String id, String address, Date regDate, String version, Boolean status) {
        this.id = id;
        this.address = address;
        this.regDate = regDate;
        this.version = version;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

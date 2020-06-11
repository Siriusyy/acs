package com.yang.acs.domain;

import java.util.Date;

/**
 * @type:   1-升级，2-维护，3保养，4-检修
 */
public class ClientLog {
    private String id;
    private Date date;
    private Integer type;
    private String  address;

    @Override
    public String toString() {
        return "ClientLog{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", address='" + address + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientLog(String id, Date date, Integer type, String address) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.address = address;

    }
}

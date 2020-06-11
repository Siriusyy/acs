package com.yang.acs.domain;

import java.util.Date;


public class SysLog {
    private Integer id;
    private Date date;
    private String operation;
    private String  operator;
    private Boolean result;

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", operation='" + operation + '\'' +
                ", operator='" + operator + '\'' +
                ", result=" + result +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public SysLog() {
    }

    public SysLog(Integer id, Date date, String operation, String operator, Boolean result) {
        this.id = id;
        this.date = date;
        this.operation = operation;
        this.operator = operator;
        this.result = result;
    }
    public SysLog(String operation, String operator, Boolean result) {

        this.date = new Date();
        this.operation = operation;
        this.operator = operator;
        this.result = result;
    }
}

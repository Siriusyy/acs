package com.yang.acs.service;

import com.yang.acs.domain.Admin;

import java.util.List;

public interface ClientService {

    List getAllClient();
    List getClientlogByPage(int page);
    Integer getClientlogPages();
}

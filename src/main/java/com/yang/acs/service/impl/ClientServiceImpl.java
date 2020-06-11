package com.yang.acs.service.impl;

import com.yang.acs.mapper.ClientMapper;
import com.yang.acs.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientMapper mapper;

    @Override
    public Integer getClientlogPages() {
        return mapper.getClientLogCount() / 8 + 1;
    }

    @Override
    public List getClientlogByPage(int page) {
        return mapper.getClientlogByPage((page - 1) * 8);
    }

    @Override
    public List getAllClient() {
        return mapper.getAllClient();
    }
}

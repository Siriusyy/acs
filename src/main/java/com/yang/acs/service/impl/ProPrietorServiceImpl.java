package com.yang.acs.service.impl;

import com.yang.acs.domain.Proprietor;
import com.yang.acs.mapper.ProprietorMapper;
import com.yang.acs.service.ProprietorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProPrietorServiceImpl implements ProprietorService {
    @Autowired
    ProprietorMapper mapper;

    @Override
    public int updateProprietor(Proprietor proprietor) {
        return mapper.updateProprietor(proprietor);
    }

    @Override
    public int getProprietorCount() {
        return mapper.getProprietorCount();
    }

    @Override
    public int delProprietor(int pid) {
        return mapper.delProprietor(pid);
    }

    @Override
    public int addProprietor(Proprietor proprietor) {
        proprietor.setRegDate(new Date());

        return mapper.addProprietor(proprietor);
    }

    @Override
    public Proprietor getProprietorByIdnum(int idnum) {
        return mapper.getProprietorByIdnum(idnum);
    }

    @Override
    public Proprietor getProprietorById(int pid) {
        return mapper.getProprietorById(pid);
    }

    @Override
    public List getProprietorByPage(int page) {
        return mapper.getProprietorByPage((page - 1) * 8);
    }

    @Override
    public List showAllProps() {
        return mapper.getAllProprietor();
    }
}

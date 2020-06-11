package com.yang.acs.service;

import com.yang.acs.domain.Admin;
import com.yang.acs.domain.Proprietor;

import java.util.List;

public interface ProprietorService {

    List showAllProps();
    List getProprietorByPage(int page);
    Proprietor getProprietorById(int pid);
    Proprietor getProprietorByIdnum(int idnum);
    int addProprietor(Proprietor proprietor);
    int delProprietor(int pid);
    int getProprietorCount();
    int updateProprietor(Proprietor proprietor);
}

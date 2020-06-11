package com.yang.acs.mapper;

import com.yang.acs.domain.Proprietor;
import com.yang.acs.domain.VisitorLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface ProprietorMapper {

    List <VisitorLog> getAllLogs();
    List <VisitorLog> get8Logs();
    List <VisitorLog> getLogByPage(int page);
    List <Proprietor> getAllProprietor();
    List <Proprietor> getProprietorByPage(int page);
    Proprietor getProprietorById(int id);
    Proprietor getProprietorByIdnum(int idnum);
    Integer addProprietor(Proprietor proprietor);
    Integer getLogCount();
    Integer delProprietor(int id);
    Integer getProprietorCount();
    Integer updateProprietor(Proprietor proprietor);
}

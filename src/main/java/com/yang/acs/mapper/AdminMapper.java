package com.yang.acs.mapper;

import com.yang.acs.domain.Admin;
import com.yang.acs.domain.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<Admin> getAllAdmin();
    List<SysLog> getSysLogByPage(int page);
    Admin getAdminById(int id);
    Admin getAdminUsername(String username);
    Integer addadmin(Admin admin);
    Integer getSysLogCount();
    Integer addSyslog(SysLog log);
    Integer delAdmin(int id);
}

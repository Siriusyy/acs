package com.yang.acs.service;

import com.yang.acs.domain.Admin;
import com.yang.acs.domain.SysLog;

import java.util.List;

public interface AdminService {
    Admin login(String username, String password);
    List show8Logs();
    List getAllLogs();
    List getLogByPage(int page);
    List getSysLogByPage(int page);
    int getLogPages();
    int getSysLogPages();
    List getAllAdmin();
    int addadmin(Admin admin);
    int addSyslog(SysLog log);
    Admin getAdminByName(String username);
    int delAdmin(int id);
}

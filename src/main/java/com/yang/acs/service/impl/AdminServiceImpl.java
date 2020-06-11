package com.yang.acs.service.impl;

import com.yang.acs.domain.Admin;
import com.yang.acs.domain.SysLog;
import com.yang.acs.mapper.AdminMapper;
import com.yang.acs.mapper.ProprietorMapper;
import com.yang.acs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper mapper;

    @Autowired
    ProprietorMapper pmapper;

    @Override
    public int delAdmin(int id) {
        return mapper.delAdmin(id);
    }

    @Override
    public Admin getAdminByName(String username) {
        return mapper.getAdminUsername(username);
    }

    @Override
    public int addadmin(Admin admin) {
        return mapper.addadmin(admin);
    }

    @Override
    public int addSyslog(SysLog log) {
        return mapper.addSyslog(log);
    }

    @Override
    public List show8Logs() {
        return pmapper.get8Logs();
    }

    @Override
    public List getAllLogs() {
        return pmapper.getAllLogs();
    }

    @Override
    public List getLogByPage(int page) {
        return pmapper.getLogByPage((page - 1) * 8);
    }

    @Override
    public List getSysLogByPage(int page) {
        return mapper.getSysLogByPage((page - 1) * 8);
    }

    @Override
    public int getSysLogPages() {
        return mapper.getSysLogCount() / 8 + 1;
    }

    @Override
    public int getLogPages() {
        return pmapper.getLogCount() / 8 + 1;
    }

    @Override
    public List getAllAdmin() {
        return mapper.getAllAdmin();
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = mapper.getAdminUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}

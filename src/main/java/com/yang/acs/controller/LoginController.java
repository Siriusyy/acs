package com.yang.acs.controller;

import com.yang.acs.domain.Admin;
import com.yang.acs.domain.SysLog;
import com.yang.acs.service.AdminService;
import com.yang.acs.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    AdminService service;

    @RequestMapping("/login")
    public String login() {
        return "pages_login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        service.addSyslog(new SysLog("注销",(String) session.getAttribute("loginuser"),true));
        session.removeAttribute("loginuser");
        return "pages_login";
    }

    @RequestMapping("/dolog")
    @ResponseBody
    public String dolog(Model model, String username, String password, HttpSession session) {
        Admin admin = service.login(username, password);
        if (admin!=null) {

            session.setAttribute("loginuser", username);
            service.addSyslog(new SysLog("登录",username,true));
            return "1";
        } else {
            service.addSyslog(new SysLog("登录", username, false));
            return "0";
        }
    }

    @RequestMapping({"/", "/index.html"})
    public String index(Model model) {
        model.addAttribute("memusage",CommonUtils.getMemery());
        List list = service.show8Logs();
        model.addAttribute("vlogs",list);
        return "index";
    }

}

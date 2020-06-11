package com.yang.acs.controller;

import com.yang.acs.domain.Client;
import com.yang.acs.domain.ClientLog;
import com.yang.acs.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientService cs;

    @RequestMapping("/clientlist.html")
    public String lisClient(Model model) {
        List list = cs.getAllClient();
        model.addAttribute("clientlist", list);
        return "pages_clientlist";
    }

    @RequestMapping("/clientloglist.html")
    public String lisClientLog(Model model, Integer p) {
        if (p == null) {
            p = 1;
        }
        List temp = cs.getClientlogByPage(p);
        model.addAttribute("clientloglist", temp);
        model.addAttribute("currentpage", p);
        model.addAttribute("totalpage", cs.getClientlogPages());
        return "pages_clientloglist";
    }

}

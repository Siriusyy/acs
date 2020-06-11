package com.yang.acs.controller;

import com.yang.acs.domain.Admin;
import com.yang.acs.domain.SysLog;
import com.yang.acs.service.AdminService;

import com.yang.acs.utils.CommonUtils;
import com.yang.acs.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminService aservice;

    @RequestMapping("/adminlist.html")
    public String getAdminList(Model model) {
        List list = aservice.getAllAdmin();
        model.addAttribute("adminlist", list);
        return "pages_adminlist";
    }

    @RequestMapping("/dashboard.html")
    public String goDashboard(Model model, Integer p) {
        if (p == null) {
            p = 1;
        }
        List list = aservice.getSysLogByPage(p);
        model.addAttribute("syslogs",list);
        model.addAttribute("currentpage", p);
        model.addAttribute("totalpage", aservice.getSysLogPages());
        return "pages_dashboard";
    }

    @RequestMapping("/visitorlogs.html")
    public String getVlogsByPage(Model model, Integer p) {
        if (p == null) {
            p = 1;
        }

        List list = aservice.getLogByPage(p);
        model.addAttribute("vlogs", list);
        model.addAttribute("currentpage", p);
        model.addAttribute("totalpage", aservice.getLogPages());
        return "pages_visitorlogs";
    }

    @RequestMapping("/exportCsv")
    public ResponseEntity <byte[]> exportCsv(HttpSession session) {
        //设置excel文件名
        String fileName = "logs";
        //设置HttpHeaders，设置fileName编码，排除导出文档名称乱码问题
        HttpHeaders headers = CsvUtil.setCsvHeader(fileName);
        byte[] content = null;
        try {
            String[] sTitles = new String[]{"id", "日期", "地址", "业主名", "识别结果(true为识别成功)"};
            List dataList = aservice.getAllLogs();

            ByteArrayOutputStream os = CsvUtil.doExport(sTitles, dataList);
            content = os.toByteArray();
            aservice.addSyslog(new SysLog("下载访客日志", (String) session.getAttribute("loginuser"), true));
        } catch (Exception e) {
            e.printStackTrace();
            aservice.addSyslog(new SysLog("下载访客日志", (String) session.getAttribute("loginuser"), false));
        }
        return new ResponseEntity <byte[]>(content, headers, HttpStatus.OK);
    }


    @RequestMapping("/adminprofile.html")
    public String getProfileByUsername(Model model, String username) {
        Admin admin = aservice.getAdminByName(username);
        model.addAttribute("admin", admin);
        return "pages_profile";
    }

    @RequestMapping("/addadmin.html")
    public String addAdmin() {
        return "pages_profile";
    }

    @RequestMapping("/addadmin")
    public String doAddAdmin(Admin admin, HttpSession session) {
        //System.out.println("debug============>"+admin);
        int i = aservice.addadmin(admin);
        if (i > 0) {
            aservice.addSyslog(new SysLog("添加管理员", (String) session.getAttribute("loginuser"), true));
        } else {
            aservice.addSyslog(new SysLog("添加管理员", (String) session.getAttribute("loginuser"), false));
        }
        return "redirect:/pages_adminlist";
    }

    @RequestMapping("/deladmin")
    public String dodel(int id, HttpSession session) {
        //System.out.println("debug============>"+admin);
        int i = aservice.delAdmin(id);
        if (i > 0) {
            aservice.addSyslog(new SysLog("删除管理员", (String) session.getAttribute("loginuser"), true));
        } else {
            aservice.addSyslog(new SysLog("删除管理员", (String) session.getAttribute("loginuser"), false));
        }
        return "redirect:/adminlist.html";
    }

    @RequestMapping("/deladmins")
    @ResponseBody
    public int delprops(@RequestParam("ids") int[] ids, HttpSession session) {
        try {
            //循环
            for (int id : ids) {
                aservice.delAdmin(id);
            }
            aservice.addSyslog(new SysLog("批量删除管理员", (String) session.getAttribute("loginuser"), true));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            aservice.addSyslog(new SysLog("批量删除管理员", (String) session.getAttribute("loginuser"), false));
            return 0;
        }
    }

    @RequestMapping("/updateIndex")
    @ResponseBody
    public Map <String, Object> doUpdateIndex(HttpSession session) {
        Map <String, Object> map = new HashMap <>();
        try {
            String host = CommonUtils.getHost();
            Socket socket = new Socket(host, 2022);
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            if ("ok".equals(sb.toString())) {
                //成功
                System.out.println("更新成功！");
                map.put("res", "ok");
                map.put("date", new Date());
                aservice.addSyslog(new SysLog("更新人脸特征索引", (String) session.getAttribute("loginuser"), true));
            }
            //关闭资源
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            map.put("res", "fail");
            aservice.addSyslog(new SysLog("更新人脸特征索引", (String) session.getAttribute("loginuser"), false));
        }
        return map;
    }

    @RequestMapping("/sysInfo")
    @ResponseBody
    public Map <String, Object> getSysInfo() {
        Map <String, Object> map = new HashMap <>();
        map.put("memusage", CommonUtils.getMemery());
        map.put("uptime", CommonUtils.getUpTime());

        String homePath = System.getProperty("user.home");
        String path = File.separator + "acs" + File.separator + "data" + File.separator + "face_vector.nn";
        File file = new File(homePath + path);
        if (file.exists()) {
            Date date = new Date(file.lastModified());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String s = format.format(date).toString();
            map.put("indextime", s);
        } else {
            map.put("indextime", "未创建索引文件!");
        }

        return map;
    }


}

package com.yang.acs.controller;

import com.yang.acs.domain.Proprietor;
import com.yang.acs.domain.SysLog;
import com.yang.acs.service.AdminService;
import com.yang.acs.service.ProprietorService;
import com.yang.acs.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ProprietorController {

    @Autowired
    ProprietorService pservice;
    @Autowired
    AdminService aservice;

    @RequestMapping("/proplist.html")
    public String listProprietor(Model model, Integer p) {
        if (p == null) {
            p = 1;
        }
        List list = pservice.getProprietorByPage(p);
        model.addAttribute("proplist", list);
        model.addAttribute("currentpage", p);
        model.addAttribute("totalpage", pservice.getProprietorCount() / 8 + 1);
        return "pages_proplist";
    }

    @RequestMapping("/searchprop")
    public String searchProp(Model model, String search_field, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            List list = new ArrayList();
            if (search_field.equals("id")) {
                Proprietor p = pservice.getProprietorById(Integer.valueOf(keyword));
                if (p != null) {
                    list.add(p);
                }
            } else {
                Proprietor p = pservice.getProprietorByIdnum(Integer.valueOf(keyword));
                if (p != null) {
                    list.add(p);
                }
            }
            model.addAttribute("proplist", list);
            return "pages_proplist";
        }
        return "redirect:/proplist.html";
    }

    @RequestMapping("/addproprietor.html")
    public String page_addProprietor(Model model) {
        return "pages_proprietor";
    }


    @RequestMapping("/pages_proprietorinfo.html")
    public String propInfo(Model model, int pid) {
        //System.out.println("debug=======>"+pid);
        Proprietor proprietor = pservice.getProprietorById(pid);
        String fileName = null;
        if (proprietor != null) {
            String imagePath = proprietor.getImagePath();
            try {

                fileName = imagePath.substring(imagePath.lastIndexOf(File.separator));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fileName != null) {
                model.addAttribute("imgUrl", "acs/faceImg" + fileName);
            }
            model.addAttribute("propritor", proprietor);
        }
        return "pages_proprietor";
    }

    @RequestMapping("/addproprietor")
    public String addPropritor(MultipartFile faceimg, Model model, HttpSession session, Proprietor proprietor) throws IOException {
        //System.out.println(proprietor.getPhonenum());
        if (!faceimg.isEmpty()) {
            String fileName = faceimg.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String newFileName = UUID.randomUUID().toString();
            newFileName += proprietor.getName() + suffixName;
            String homePath = System.getProperty("user.home");
            String path = File.separator + "acs" + File.separator + "faceImg" + File.separator + newFileName;
            File destFile = new File(homePath + path);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            try {
                File file = new File(proprietor.getImagePath());
                if (file.exists()) {

                    file.delete();

                }
            } catch (Exception e) {
                System.out.println("删除旧人脸图像失败");
            }
            proprietor.setImagePath(homePath + path);
            faceimg.transferTo(destFile);

        }
        if (proprietor.getId() != null) {
            //更新用户
            pservice.updateProprietor(proprietor);
        } else {
            //插入新用户
            pservice.addProprietor(proprietor);
        }
        Integer id = proprietor.getId();
        try {
            String host = CommonUtils.getHost();
            Socket socket = new Socket(host, 2021);
            //给服务端发送消息
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write("" + id + "|" + proprietor.getImagePath());
            printWriter.flush();

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
                aservice.addSyslog(new SysLog("添加业主",(String) session.getAttribute("loginuser"),true));
            }
            inputStream.close();

            //关闭资源

            printWriter.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("人脸识别模块通信失败,撤销添加业主操作");
            aservice.addSyslog(new SysLog("添加业主",(String) session.getAttribute("loginuser"),false));
            pservice.delProprietor(id);
            e.printStackTrace();
        }
        return "redirect:/proplist.html";
    }

    @RequestMapping("/delproprietor")
    public String delProprietor(int pid,HttpSession session) {


        int i = pservice.delProprietor(pid);
        if (i>0) {
            aservice.addSyslog(new SysLog("删除业主", (String) session.getAttribute("loginuser"), true));
        }else {
            aservice.addSyslog(new SysLog("删除业主", (String) session.getAttribute("loginuser"), false));
        }
        return "redirect:/proplist.html";
    }


    @RequestMapping("/qiyong")
    @ResponseBody
    public int qiyong(@RequestParam("ids") int[] ids,HttpSession session) {
        try {
            //循环
            for (int id : ids) {
                Proprietor p = pservice.getProprietorById(id);
                if (p != null) {
                    p.setStatus(true);
                    pservice.updateProprietor(p);
                }

            }
            aservice.addSyslog(new SysLog("批量启用业主", (String) session.getAttribute("loginuser"), true));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            aservice.addSyslog(new SysLog("批量启用业主", (String) session.getAttribute("loginuser"), false));
            return 0;
        }
    }

    @RequestMapping("/jinyong")
    @ResponseBody
    public int jinyong(@RequestParam("ids") int[] ids,HttpSession session) {
        try {
            //循环
            for (int id : ids) {
                Proprietor p = pservice.getProprietorById(id);
                if (p != null) {
                    p.setStatus(false);
                    pservice.updateProprietor(p);
                }

            }
            aservice.addSyslog(new SysLog("批量禁用业主", (String) session.getAttribute("loginuser"), true));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            aservice.addSyslog(new SysLog("批量禁用业主", (String) session.getAttribute("loginuser"), false));
            return 0;
        }
    }

    @RequestMapping("/delprops")
    @ResponseBody
    public int delprops(@RequestParam("ids") int[] ids,HttpSession session) {
        try {
            //循环
            for (int id : ids) {
                pservice.delProprietor(id);
            }
            aservice.addSyslog(new SysLog("批量删除业主", (String) session.getAttribute("loginuser"), true));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            aservice.addSyslog(new SysLog("批量删除业主", (String) session.getAttribute("loginuser"), false));
            return 0;
        }
    }

}

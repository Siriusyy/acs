package com.yang.acs.controller;

import com.yang.acs.domain.Admin;
import com.yang.acs.mapper.AdminMapper;
import com.yang.acs.service.AdminService;
import com.yang.acs.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

@Controller
public class DemoController {
    @Autowired
    AdminMapper mapper;

    @Autowired
    AdminService service;

    @RequestMapping("/test.html")
    public String test(Model model, Integer p) {
        if (p == null) {
            p = 1;
        }
        model.addAttribute("currentpage", p);
        model.addAttribute("totalpage", 1);
        return "test";
    }

    @RequestMapping("/test")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            Socket socket = new Socket("173.1.1.121", 30004);

            //给服务端发送消息
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write("1|acs/faceImg/test.jpg");
            printWriter.flush();

            //关闭资源

            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "test";
    }



}

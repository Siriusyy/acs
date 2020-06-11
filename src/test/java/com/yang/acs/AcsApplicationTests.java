package com.yang.acs;

import com.yang.acs.domain.*;
import com.yang.acs.mapper.AdminMapper;
import com.yang.acs.mapper.ClientMapper;
import com.yang.acs.mapper.ProprietorMapper;
import com.yang.acs.utils.CommonUtils;

import com.yang.acs.utils.CsvUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AcsApplicationTests {


    @Test
    public void tset() {
        /*try {
            Socket socket = new Socket("127.0.0.1", 2022);
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


            }
            //关闭资源
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }*/
        String host = null;
        try {
            host = CommonUtils.getHost();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(host);
    }
}

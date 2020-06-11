package com.yang.acs.utils;

import com.sun.management.OperatingSystemMXBean;
import org.ini4j.Wini;
import org.springframework.util.StringUtils;


import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
import java.util.Properties;

public class CommonUtils {
    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private final static long uptime = System.currentTimeMillis();

    public static String getMemery() {

        long totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

        DecimalFormat df = new DecimalFormat("0.0");//格式化小数
        String num1 = df.format((float) totalvirtualMemory / 1024 / 1024 / 1024);//返回的是String类型
        String num2 = df.format((float) freePhysicalMemorySize / 1024 / 1024 / 1024);//返回的是String类型Double compare=(Double)(1-freePhysicalMemorySize*1.0/totalvirtualMemory)*100;

        return num2 + "G/" + num1 + "G";
    }


    public static String getCpuInfo() {

        double cpu = osmxb.getSystemLoadAverage();
        return String.valueOf(cpu);
    }

    public static String getUpTime() {
        long time = System.currentTimeMillis();
        time = time - uptime;
        int fen = (int) (time / 1000 / 60 % 60);
        int shi = (int) (time / 1000 / 60 / 60);
        return "" + shi + "小时" + fen + "分";
    }

    public static String getHost() throws IOException {
        String dirPath = System.getProperty("user.home");
        String fileName = dirPath + "/acs/config.ini";
        Wini ini = new Wini(new File(fileName));
        return ini.get("ip", "host");

    }

}

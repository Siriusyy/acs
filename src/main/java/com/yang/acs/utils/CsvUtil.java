package com.yang.acs.utils;
import com.yang.acs.domain.VisitorLog;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 导出至csv文件
 * */
public class CsvUtil {
    //CSV文件分隔符
    private final static String NEW_LINE_SEPARATOR="\n";
    /** CSV文件列分隔符 */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /** CSV文件列分隔符 */
    private static final String CSV_RN = "\r\n";





    /**
     * @param colNames 表头部数据
     * @param dataList 集合数据
     */
    public static ByteArrayOutputStream doExport(String[] colNames,  List<VisitorLog> dataList) {
        try {
            StringBuffer buf = new StringBuffer();

            // 完成数据csv文件的封装
            // 输出列头
            for (int i = 0; i < colNames.length; i++) {
                buf.append(colNames[i]).append(CSV_COLUMN_SEPARATOR);
            }
            buf.append(CSV_RN);

            if (null != dataList) { // 输出数据
//                for (int i = 0; i < dataList.size(); i++) {
//                    for (int j = 0; j < mapKeys.length; j++) {
//                        buf.append(dataList.get(i).get(mapKeys[j])).append(CSV_COLUMN_SEPARATOR);
//                    }
//                    buf.append(CSV_RN);
//                }
                for (VisitorLog visitorLog : dataList) {
                    buf.append(visitorLog.getUuid()).append(CSV_COLUMN_SEPARATOR);
                    buf.append(visitorLog.getDate()).append(CSV_COLUMN_SEPARATOR);
                    buf.append(visitorLog.getAddress()).append(CSV_COLUMN_SEPARATOR);
                    buf.append(visitorLog.getProName()).append(CSV_COLUMN_SEPARATOR);
                    buf.append(visitorLog.getStatus()).append(CSV_COLUMN_SEPARATOR);
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //OutputStream os = new ByteArrayOutputStream();
            os.write(buf.toString().getBytes("GBK"));
            os.flush();
            os.close();
            return os;
        } catch (Exception e) {
//            LogUtils.error("doExport错误...", e);
            e.printStackTrace();
        }
        return null;
    }
    public static HttpHeaders setCsvHeader(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        try {
            // 设置文件后缀
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = new String(fileName.getBytes("gbk"), "iso8859-1") + sdf.format(new Date()) + ".csv";
            headers.add("Pragma", "public");
            headers.add("Cache-Control", "max-age=30");
            headers.add("Content-Disposition", "attachment;filename="+filename);
            headers.setContentType(MediaType.valueOf("application/vnd.ms-excel;charset=UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return headers;
    }

}
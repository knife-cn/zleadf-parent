package com.zlead.utils;


import org.csource.FastDFSTest;
//import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.Properties;
import java.util.Random;


/**
 * @author
 */
public class FastDfsUtil {
    public static File downloadFromUrl(String urlStr) throws Exception {
        //获取URL对象
        URL url = null;
        HttpURLConnection conn = null;
        File file;
        try {
            url = new URL(urlStr);
            //获取连接
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(6000);
            //设置超时时间是3秒
            conn.setReadTimeout(6000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.19 Safari/537.36");
            //得到临时文件
            InputStream is = conn.getInputStream();
            if (is == null || is.available() <= 0) {
//                throw new DownloadException(url,"图片为空");
                throw new Exception();
            }
            file = getTemplateFile(is);
        } catch (MalformedURLException e) {
//            throw new DownloadException(url,"图片下载失败");
            throw new Exception();
        } catch (Exception e) {
//            throw new DownloadException(url,"图片下载失败");
            throw new Exception();
        } finally {
            //关闭连接
            if (conn != null) {
                conn.disconnect();
            }
        }
        return file;
    }
    /**
     * 获取模板文件--获取到的文件为临时文件，用完后需要手动删除
     *
     * @param inputStream
     * @return 模板文件
     * @throws Exception 异常抛出
     */
    public static File getTemplateFile(InputStream inputStream) throws Exception {
        File file = File.createTempFile("temp_image", null);
        inputStreamToFile(inputStream, file);
        if (file.exists() && file.length() <= 0) {
//            throw new DownloadException("临时文件为空");
            throw new Exception();
        }
        return file;
    }
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            try {
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String download2UploadImg(String oldUrl) throws Exception {
        //下载图片
        File tempFile = null;
        InputStream is = null;

        String[] newPic;
        try {
            tempFile = downloadFromUrl(oldUrl);
            Random random1 = new Random(100000);
            random1.nextInt();
            String uploadFileName= random1.nextInt()+".jpg";
            System.out.println(uploadFileName);
            is = new FileInputStream(tempFile);
            if (tempFile == null || is == null || is.available() <= 0) {
                throw new Exception();
            }
            newPic = FastDFSTest.uploadFile(tempFile, uploadFileName,tempFile.length());
        } finally {
            //手动删除临时文件
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new IOException("文件流关闭失败");
                }
            }
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        System.out.println(newPic[0]);
        return "http://116.62.124.171/"+newPic[0]+newPic[1];
    }
     
    public static void main(String[] args) throws Exception {

        ClientGlobal.init("C:\\Users\\Admin\\hardware-F\\zleadFMall\\trunk\\zlead_parent\\zleadf-service\\src\\main\\java\\org\\csource\\fdfs_client.conf");
        String s = FastDfsUtil.download2UploadImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548409836&di=8f7895e53455a0785af1114e2e19cd1c&imgtype=jpg&er=1&src=http%3A%2F%2Fit.chinairn.com%2Fuserfiles%2F20160414%2F20160414102420_2179.png");
        System.out.println(s);
    }
}

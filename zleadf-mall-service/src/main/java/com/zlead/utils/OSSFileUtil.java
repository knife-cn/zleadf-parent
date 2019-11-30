//package com.zlead.utils;
//
//
//import com.aliyun.oss.*;
//import org.csource.common.MyException;
//import org.csource.fastdfs.ClientGlobal;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Date;
//import java.util.Properties;
//import java.util.Random;
//
//
///**
// * @author
// */
//public class OSSFileUtil {
//
//
//    public static String picOSS(MultipartFile uploadFile) throws Exception {
//		String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
//		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
//		// https://ram.console.aliyun.com 创建
//		String accessKeyId = "";
//		String accessKeySecret = "";
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		// 上传
//		long time = new Date().getTime();
//		ossClient.putObject("bucketName", "filename", new ByteArrayInputStream(uploadFile.getBytes()));
//		// 关闭client
//		ossClient.shutdown();
//		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
//		String url = ossClient.generatePresignedUrl("bucketName", "filename", expiration).toString();
//		return url;
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        ClientGlobal.init("C:\\Users\\Admin\\hardware-F\\zleadFMall\\trunk\\zlead_parent\\zleadf-service\\src\\main\\java\\org\\csource\\fdfs_client.conf");
////        String s = OSSFileUtil.download2UploadImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548409836&di=8f7895e53455a0785af1114e2e19cd1c&imgtype=jpg&er=1&src=http%3A%2F%2Fit.chinairn.com%2Fuserfiles%2F20160414%2F20160414102420_2179.png");
////        System.out.println(s);
//    }
//}

package com.zlead.controller;


import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;

import org.csource.fastdfs.TrackerServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zlead.util.JsonUtil;
import com.zlead.util.JsonResult;



/**
 * 接口controller
 * @author 
 *
 */
@Controller
@RequestMapping("/ajeasy/fdfs")
public class FastDFSController {
	
	private static String basePath = "fdfs_client.properties";  
	private static String path=""; 
	
	
	//个人信息接口
	@RequestMapping("uploading")
	@ResponseBody
        public String uploading(@RequestPart("demo_input")MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException, MyException{
		JsonResult jsonResult = null;
		String result=null;
			System.out.println( " uploading ");
	        try {
	            ClientGlobal.init(this.getClass().getResource("/fdfs_client.properties").toURI().toURL().getPath());
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        TrackerClient tracker = new TrackerClient();  
	        TrackerServer trackerServer = tracker.getConnection();  
	        StorageServer storageServer = null;  
	        StorageClient client = new StorageClient(trackerServer, storageServer);  
	        Random random1 = new Random(100000);
	        random1.nextInt();
	        String uploadFileName= random1.nextInt()+".jpg";
	        
	        String fileExtName = "";  //随机名
	        if (uploadFileName.contains(".")) {  
	            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);  
	        } else {  
	            System.out.println("uploading Fail to upload file, because the format of filename is illegal.");  
	            return null;  
	        }
	         NameValuePair[] metaList = new NameValuePair[3];  
             metaList[0] = new NameValuePair("fileName", uploadFileName);//文件全名  
	         metaList[1] = new NameValuePair("fileExtName", fileExtName);//截取文件后缀的文件名
	         metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));//文件长度
	        String[] files = client.upload_file(file.getBytes(),fileExtName, metaList);
	        String message="1";
	       System.out.println("uploading "+Arrays.asList(files));  
	       for(String num : Arrays.asList(files)){
	    	   System.out.println(num+" ");
	    	   message=num;
	       }
	       System.out.println(message+" uploading ");
	      //下载文件  
	     // downloadFile(files[0],files[1]);  
	       String pic_url=ClientGlobal.pic_url;
	       System.out.println("上传图片成功: "+pic_url+message);
	       jsonResult =  new JsonResult(1,"上传图片成功",pic_url+message,true);
		
		result = JsonUtil.getJson(jsonResult);
		JsonUtil.render(response, result);
        return null;
	}
	/** 
     * 上传文件 
     */  
    public static String[] uploadFile(File file, String uploadFileName, long fileLength) throws IOException {  
         System.out.println("上传文件=======================");  
        byte[] fileBuff = getFileBuffer(new FileInputStream(file), fileLength);  
        String[] files = null;  
        String fileExtName = "";  
        if (uploadFileName.contains(".")) {  
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);  
        } else {  
            System.out.println("Fail to upload file, because the format of filename is illegal.");  
            return null;  
        }  
  
        // 建立连接  
        TrackerClient tracker = new TrackerClient();  
        TrackerServer trackerServer = tracker.getConnection();  
        StorageServer storageServer = null;  
        StorageClient client = new StorageClient(trackerServer, storageServer);  
  
        // 设置元信息  
        NameValuePair[] metaList = new NameValuePair[3];  
        metaList[0] = new NameValuePair("fileName", uploadFileName);//文件全名  
        metaList[1] = new NameValuePair("fileExtName", fileExtName);//截取文件后缀的文件名
        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));//文件长度
  
        // 上传文件  
        try {  
            files = client.upload_file(fileBuff, fileExtName, metaList);//文件字节流文件、文件截取后缀名字、文件组装的元信息
        } catch (Exception e) {  
            System.out.println("Upload file \"" + uploadFileName + "\"fails");  
        }  
        trackerServer.close();  
        return files;  
    }  
    private static byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {  
  
        byte[] buffer = new byte[256 * 1024];  
        byte[] fileBuffer = new byte[(int) fileLength];  
  
        int count = 0;  
        int length = 0;  
  
        while ((length = inStream.read(buffer)) != -1) {  
            for (int i = 0; i < length; ++i) {  
                fileBuffer[count + i] = buffer[i];  
            }  
            count += length;  
        }  
        return fileBuffer;  
    }  
      
    //下载文件  
    public static void downloadFile(String groupName,String filepath) throws Exception{  
         System.out.println("下载文件=======================");  
         TrackerClient tracker = new TrackerClient();  
         TrackerServer trackerServer = tracker.getConnection();  
         StorageServer storageServer = null;  
  
         StorageClient storageClient = new StorageClient(trackerServer, storageServer);  
         byte[] b = storageClient.download_file(groupName, filepath);  
         System.out.println("文件大小:"+b.length);  
         String fileName = "src/main/resources/test1.jpg";    
         FileOutputStream out = new FileOutputStream(fileName);  
         out.write(b);  
         out.flush();  
         out.close();  
    }  
      
    //查看文件信息  
    public static void getFileInfo(String groupName,String filepath) throws Exception{  
        System.out.println("获取文件信息=======================");  
        TrackerClient tracker = new TrackerClient();  
        TrackerServer trackerServer = tracker.getConnection();  
        StorageServer storageServer = null;  
  
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);  
        FileInfo fi = storageClient.get_file_info(groupName,filepath);  
        System.out.println("所在服务器地址:"+fi.getSourceIpAddr());  
        System.out.println("文件大小:"+fi.getFileSize());  
        System.out.println("文件创建时间:"+fi.getCreateTimestamp());  
        System.out.println("文件CRC32 signature:"+fi.getCrc32());  
    }  
      
    public static void getFileMate(String groupName,String filepath) throws Exception{  
        System.out.println("获取文件Mate=======================");  
        TrackerClient tracker = new TrackerClient();  
        TrackerServer trackerServer = tracker.getConnection();  
        StorageServer storageServer = null;  
  
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);  
        NameValuePair nvps[] = storageClient.get_metadata(groupName,filepath);  
        for (NameValuePair nvp : nvps) {  
                System.out.println(nvp.getName() + ":" + nvp.getValue());  
        }  
    }  
      
    public static void deleteFile(String groupName,String filepath) throws Exception{  
        System.out.println("删除文件=======================");  
        TrackerClient tracker = new TrackerClient();  
        TrackerServer trackerServer = tracker.getConnection();  
        StorageServer storageServer = null;  
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);  
        int i = storageClient.delete_file(groupName, filepath);  
        System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);  
    }  
	
	
	
}

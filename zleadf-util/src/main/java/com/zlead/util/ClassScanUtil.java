package com.zlead.util;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.zlead.exception.UtilException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 类相关的工具类
 * 
 * @author hejie
 * 
 */
public class ClassScanUtil {
	
	protected Log log = LogFactory.getLog(getClass());
    /**
     * 取得某一类所在包的所有类名 不含迭代
     */
    public  String[] getPackageAllClassName(String classLocation, String packageName){
        //将packageName分解
        String[] packagePathSplit = packageName.split("[.]");
        String realClassLocation = classLocation;
        int packageLength = packagePathSplit.length;
        for(int i = 0; i< packageLength; i++){
            realClassLocation = realClassLocation + File.separator+packagePathSplit[i];
        }
        File packeageDir = new File(realClassLocation);
        if(packeageDir.isDirectory()){
            String[] allClassName = packeageDir.list();
            return allClassName;
        }
        return null;
    }
    
    /**
     * 从包package中获取所有的Class
     * @param pack
     * @return
     */
    public Set<String> getClasses(Package pack){
        //获取包的名字 并进行替换
        String packageName = pack.getName();
        return getClasses(packageName);
    }
    
    public Set<String> getClasses(String packageName){
        //第一个class类的集合
        Set<String> classes = new LinkedHashSet<String>();
        //是否循环迭代
        boolean recursive = true;
        
        String packageDirName = packageName.replace('.', '/');
        packageDirName +="/";
        
        packageDirName = packageDirName.replaceAll("[/]+", "/");
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            
            //循环迭代下去
            while (dirs.hasMoreElements()){
                //获取下一个元素
                URL url = dirs.nextElement();
                log.debug("classscanUtil-url:" + url.getFile());
                //得到协议的名称
                String protocol = url.getProtocol();
                
                if (isJarURL(url)){
                	doFindPathMatchingJarResources(url, recursive, packageDirName, packageName, classes);
                }else if(url.getProtocol().startsWith("vfs")){
                	// no codeing
                }else if("file".equals(protocol)) {
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    log.debug("classscanUtil-protocol file:" + filePath);
                    //以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                }
                
            }
        } catch (IOException e) {
        	throw new UtilException(e);
        }
        return classes;
    }
    
    private  void doFindPathMatchingJarResources(URL url,boolean recursive, String packageDirName,String packageName, Set<String> classes) throws IOException {
    	 URLConnection con = url.openConnection();
         
         //定义一个JarFile
         JarFile jarFile = null;
         boolean newJarFile = false;
         if ((con instanceof JarURLConnection)){
             //获取jar
        	 jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
            
         } else {
             String urlFile = url.getFile();
             int separatorIndex = urlFile.indexOf("!/");
             if (separatorIndex != -1) {
               String jarFileUrl = urlFile.substring(0, separatorIndex);
               jarFile = getJarFile(jarFileUrl);
             }else {
               jarFile = new JarFile(urlFile);
             }
             newJarFile = true;
           }
         
        
         
         //从此jar包 得到一个枚举类
         Enumeration<JarEntry> entries = jarFile.entries();
         //同样的进行循环迭代
         while (entries.hasMoreElements()) {
             //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
             JarEntry entry = entries.nextElement();
             String name = entry.getName();
             //如果是以/开头的
             if (name.charAt(0) == '/') {
                 //获取后面的字符串
                 name = name.substring(1);
             }
             //如果前半部分和定义的包名相同
             if (name.startsWith(packageDirName)) {
                 int idx = name.lastIndexOf('/');
                 //如果以"/"结尾 是一个包
                 if (idx != -1) {
                     //获取包名 把"/"替换成"."
                     packageName = name.substring(0, idx).replace('/', '.');
                 }
                 //如果可以迭代下去 并且是一个包
                 if ((idx != -1) || recursive){
                     //如果是一个.class文件 而且不是目录
                     if (name.endsWith(".class") && !entry.isDirectory()) {
                         //去掉后面的".class" 获取真正的类名
                         String className = name.substring(packageName.length() + 1, name.length() - 6);
                         //添加到classes
                         classes.add(packageName + '.' + className);
                       }
                 }
             }
         }
         
         if (newJarFile)
             jarFile.close();
    }
    
    protected  JarFile getJarFile(String jarFileUrl)throws IOException{
	    if (jarFileUrl.startsWith("file:")) {
	      try {
	        return new JarFile(toURI(jarFileUrl).getSchemeSpecificPart());
	      }
	      catch (URISyntaxException localURISyntaxException){
	        return new JarFile(jarFileUrl.substring("file:".length()));
	      }
	    }
	    return new JarFile(jarFileUrl);
  }
    
    public static URI toURI(String location) throws URISyntaxException{
    	return new URI(replace(location, " ", "%20"));
    }
    
    public static String replace(String inString, String oldPattern, String newPattern){
		  if ((!hasLength(inString)) || (!hasLength(oldPattern)) || (newPattern == null)) {
		    return inString;
		  }
		  StringBuilder sb = new StringBuilder();
		  int pos = 0;
		  int index = inString.indexOf(oldPattern);
		
		  int patLen = oldPattern.length();
		  while (index >= 0) {
		    sb.append(inString.substring(pos, index));
		    sb.append(newPattern);
		    pos = index + patLen;
		    index = inString.indexOf(oldPattern, pos);
		  }
		  sb.append(inString.substring(pos));
		
		  return sb.toString();
    }
    
    public static boolean hasLength(CharSequence str){
      return (str != null) && (str.length() > 0);
    }

    public static boolean hasLength(String str){
      return hasLength(str);
    }
   
    
    public  boolean isJarURL(URL url){
      String protocol = url.getProtocol();

      return ("jar".equals(protocol)) || 
        ("zip".equals(protocol)) || 
        ("wsjar".equals(protocol)) || (
        ("code-source".equals(protocol)) && (url.getPath().contains("!/")));
    }
    
    /**
     * 以文件的形式来获取包下的所有Class
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    @SuppressWarnings({ "unchecked" })
    private void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set classes){
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
        //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
              public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
              }
            });
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                                      file.getAbsolutePath(),
                                      recursive,
                                      classes);
            }
            else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                //添加到集合中去
                classes.add(packageName + '.' + className);
            }
        }
    }
    
    @SuppressWarnings({ "unchecked"})
    public static void main(String[] args) throws Exception {
//    	ClassScanUtil classUtil = new ClassScanUtil();
//    	Set set = classUtil.getClasses("cn.com.up72.v");
//    	Iterator it = set.iterator();
//    	while(it.hasNext()){
//    		Object key = it.next();
//    		System.out.println(key);
//    	}
        String[] str = { "1", "2", "3", "4", "5", "6", "7" };
        System.out.println(str);
    	
    }
}

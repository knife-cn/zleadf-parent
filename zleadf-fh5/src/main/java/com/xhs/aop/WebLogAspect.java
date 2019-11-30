package com.xhs.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Aspect
@Component
public class WebLogAspect {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("");
        System.out.println(" > " + request.getMethod() + " " + getUrl(request));
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(" > " + key + ":" + value);
        }
        for (Object object : joinPoint.getArgs()) {
            if (object instanceof MultipartFile
                    || object instanceof HttpServletRequest
                    || object instanceof HttpServletResponse) {
                continue;
            }
            try {
                System.out.println(JSONObject.toJSONString(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("");
    }

    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) {
        if (response != null) {
            if (response instanceof List) {
                if (((List) response).size() > 10) {
                    System.out.println(JSONObject.toJSONString(((List) response).subList(0, 10)) + "...");
                } else {
                    System.out.println(JSONObject.toJSONString(response));
                }
            } else {
                System.out.println(JSONObject.toJSONString(response));
            }
        }
    }

    private static String getUrl(HttpServletRequest request) {
        StringBuffer uri = request.getRequestURL();
        String url = uri.toString();
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entry = map.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entry.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> item = iterator.next();
            String key = item.getKey();
            for (String value : item.getValue()) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        String string = sb.toString();
        if (sb.lastIndexOf("&") > 0) {
            url = url + "?" + string.substring(0, string.lastIndexOf("&"));
        }
        return url;
    }
}

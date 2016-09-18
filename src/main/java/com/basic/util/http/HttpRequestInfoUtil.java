package com.basic.util.http;

import javax.servlet.http.HttpServletRequest;
/**
 * 获取主机地址
 * 
 * @author qiushi.zhou  
 * @date 2016年8月24日 上午10:40:14
 */
public class HttpRequestInfoUtil {
	
	public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    public static String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }
    
    public static int getRemotePort(HttpServletRequest request) {
        return request.getRemotePort();
    }
    
    public static String getRemoteUser(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
    
    public static String getRequestURL(HttpServletRequest request) {
        return new String(request.getRequestURL());
    }
}

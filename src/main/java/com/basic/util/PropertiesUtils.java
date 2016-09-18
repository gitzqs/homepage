package com.basic.util;

import java.io.InputStream;
import java.util.Properties;
/**
 * 读取配置文件
 * 
 * @author qiushi.zhou  
 * @date 2016年9月1日 上午8:55:26
 */
public class PropertiesUtils {
	
	public static String getPropertyByKey(String fileName,String key) {  
	       Properties props = new Properties();
	        try {  
	            InputStream inStream = PropertiesUtils.class.getClassLoader()  
	                    .getResourceAsStream(fileName);  
	            props.load(inStream);  
	        } catch (Exception ex) {  
	            throw new RuntimeException("配置文件读取出错" + ex.getMessage(), ex);  
	        }
	        return props.getProperty(key);
	    }
	   
	 
	   
	 
	   
	    public static void main(String[] args) {
	        System.out.println(PropertiesUtils.getPropertyByKey("jdbc.properties","jdbc.driver"));
	    }
}

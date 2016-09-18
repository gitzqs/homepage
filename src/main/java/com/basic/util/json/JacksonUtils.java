/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: JacksonUtils.java
* @Prject: memory-util
* @Package: com.jshuabo.frame.server.util.json
* @author: lianghe.yuan
* @date: Feb 26, 2014 2:50:32 AM
* @version: v1.0
* @Description: 
*/
package com.basic.util.json;

import java.io.IOException;
import java.text.DateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.util.date.DateFormatUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json转化
 * 
 * @author qiushi.zhou  
 * @date 2016年8月24日 上午10:47:41
 */
public class JacksonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static ObjectMapper getInstance() {
        return objectMapper;
    }
    
    public static String object2json(Object object) {
        return object2json(object, DateFormatUtils.getDateFormat(DateFormatUtils.ymd_hms));
    }
    
    public static String object2jsonYmd(Object object) {
        return object2json(object, DateFormatUtils.getDateFormat(DateFormatUtils.ymd));
    }
    
    public static String object2json(Object object, DateFormat df) {
        try {
            objectMapper.setDateFormat(df);
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("jackson object2json with JsonGenerationException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson object2json with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson object2json with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson object2json with Exception: {}", e.getLocalizedMessage());
        }
        return "";
    }
    
    public static <T> T json2object(String json, Class<T> c) {
        try {
            return objectMapper.readValue(json, c);
        } catch (JsonParseException e) {
            logger.error("jackson json2object with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2object with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2object with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2object with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    public static <T> T[] json2objectArray(String json, Class<T[]> c) {
        try {
            return objectMapper.readValue(json, c);
        } catch (JsonParseException e) {
            logger.error("jackson json2objectArray with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2objectArray with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2objectArray with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2objectArray with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    public static Map<?, ?> json2map(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            logger.error("jackson json2map with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2map with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2map with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2map with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public static List<LinkedHashMap<String, Object>> json2mapArray(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (JsonParseException e) {
            logger.error("jackson json2mapArray with JsonParseException: {}", e.getLocalizedMessage());
        } catch (JsonMappingException e) {
            logger.error("jackson json2mapArray with JsonMappingException: {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("jackson json2mapArray with IOException: {}", e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("jackson json2mapArray with Exception: {}", e.getLocalizedMessage());
        }
        return null;
    }
}

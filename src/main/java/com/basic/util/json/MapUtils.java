package com.basic.util.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年9月7日 下午1:30:39
 */
public class MapUtils {

    /**
     * @Title: valueEmpty2Null
     * @Description: 将 值为 empty 转为 null
     * @param map
     * @return
     * @return: Map<String,Object>
     */
    public static Map<String, Object> valueEmpty2Null(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (StringUtils.isEmpty(params.get(entry.getKey()))) {
                params.put(entry.getKey(), null);
            }
        }
        return params;
    }

    /**
     * @Title: valueEmpty2Null
     * @Description: 将 值为 null 转为 empty
     * @param map
     * @return
     * @return: Map<String,Object>
     */
    public static Map<String, Object> valueNull2Empty(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (StringUtils.isEmpty(params.get(entry.getKey()))) {
                params.put(entry.getKey(), "");
            }
        }
        return params;
    }

    /**
     * @Title: requestMap 获取页面 传如后台的 map
     * @return: Map<String,Object>
     * @author: peng.wu
     * @date: 2014年9月25日 下午10:17:00
     */
    public static Map<String, String> requestMap(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();

        Map<String, String> map = new HashMap<String, String>();
        Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
        for (Entry<String, String[]> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        return map;
    }
    
    /**
     * @Title: requestObjMap
     * @Description: Key<String> Value<Object>
     * @return: Map<String,String>
     * @author: peng.wu
     * @date: 2014年12月1日 上午11:59:51
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> requestObjMap(HttpServletRequest request) {
        Map paramsMap = request.getParameterMap();

        Map<String, Object> map = new HashMap<String, Object>();
        Set<Entry<String, Object[]>> entrySet = paramsMap.entrySet();
        for (Entry<String, Object[]> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        return map;
    }
    
    /*
	 *获取id集合
	 **/
	public static List<String> getIdList(String id){
		String[] userIds = StringUtils.commaDelimitedListToStringArray(id);
		List<String> idList = Arrays.asList(userIds);
		return idList;
		
	}
}

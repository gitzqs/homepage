package com.basic.web.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.util.BeanMethodInvocation;
import com.basic.util.String.StringUtil;
import com.basic.util.json.JacksonUtils;

/**
 * 基础 controller
 * 
 * @author qiushi.zhou  
 * @date 2016年8月26日 下午4:16:08
 */
@Controller
@RequestMapping(value="base")
public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public String page(HttpServletRequest request){
		Map<String, String[]> paramsMap = request.getParameterMap();
		//初始化
		String jsonResult = StringUtil.EMPTY;
		
		 Map<String, Object> map = new HashMap<String, Object>();
	     Set<Entry<String, String[]>> entrySet = paramsMap.entrySet();
	     for (Entry<String, String[]> entry : entrySet) {
	          map.put(entry.getKey(), entry.getValue()[0]);
	     }
		String beanName = (String) map.get("bean");
		if(StringUtil.isEmpty(beanName)){
			logger.error("beanName must not be null!");
			return jsonResult;
		}
		
		String methodName = (String) map.get("method");
		if(StringUtil.isEmpty(methodName)){
			logger.error("methodName must not be null !");
			return jsonResult;
		}
		
		try{
			jsonResult = (String) BeanMethodInvocation.execute(beanName, methodName, map);
		}catch(Throwable tx){
			logger.error("invoke method : targetBean: {}, method: {} , with error: \n\tparams is : {}; \n\texception is [{}]",
	                new Object[]{beanName, methodName, JacksonUtils.object2json(map), tx.getLocalizedMessage()});
		}
		
		return jsonResult;
	}
}

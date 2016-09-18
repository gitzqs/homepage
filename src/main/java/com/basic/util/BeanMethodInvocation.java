package com.basic.util;

import java.awt.List;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.util.String.StringUtil;
import com.basic.util.exception.BaseRuntimeException;
import com.basic.util.exception.BusinessLayerException;

/**
 * 根据bean、方法名、请求参数获取结果
 * 
 * @author qiushi.zhou  
 * @date 2016年8月26日 下午4:34:58
 */
public class BeanMethodInvocation {
	
	private static final Logger logger = LoggerFactory.getLogger(BeanMethodInvocation.class);
	
	public static Object execute(String targetBeanName,String methodName,Object params){
		//初始化返回值
		Object methodResult = null;
		
		if(StringUtil.isEmpty(targetBeanName)){
			logger.error("targetBeanName must not be null or empty!");
			throw new  BusinessLayerException("targetBeanName must not be null or empty!");
		}
		
		Object targetBean = ApplicationContextHolder.getBean(targetBeanName+"Service");
		if(null == targetBean){
			logger.error("targetBean is null with bean name :[{}]",targetBeanName);
			throw new BusinessLayerException("targetBean is null with bean name :[{}]",new String[]{targetBeanName});
		}
		
		Class<?> paramsClass = null;
		if(params instanceof Map) paramsClass = Map.class;
		else if(params instanceof List) paramsClass = List.class;
		else if(params instanceof Set) paramsClass = Set.class;
		else paramsClass = params.getClass();
		
		Method method = MethodUtils.getAccessibleMethod(targetBean.getClass(), methodName, paramsClass);
		if(null == method){
			logger.error("can't find method: [{}],and this params is: {}",new Object[]{targetBean.getClass(),methodName,paramsClass});
		}
		
		try {
			methodResult = method.invoke(targetBean, params);
		} catch (IllegalAccessException e) {
			logger.error("invoke method : target: {}, method: {} ,with IllegalAccessException:\n\t {}", new Object[]{targetBean, methodName, e.getLocalizedMessage()});
            throw new BaseRuntimeException(e.getLocalizedMessage());
            
		} catch (IllegalArgumentException e) {
			logger.error("invoke method : target: {}, method: {} ,with IllegalArgumentException:\n\t {}", new Object[]{targetBean, methodName, e.getLocalizedMessage()});
            throw new BaseRuntimeException(e.getLocalizedMessage());
            
		} catch (InvocationTargetException e) {
			 logger.error("invoke method : target: {}, method: {} ,with InvocationTargetException:\n\t {}", new Object[]{targetBean, methodName, e.getTargetException()});
	         throw new BaseRuntimeException(e.getLocalizedMessage());
		}
		
		return methodResult;
	}
}

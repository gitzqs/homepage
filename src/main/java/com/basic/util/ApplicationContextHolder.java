package com.basic.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 
/**
 * 获取bean
 *  
 * 
 * @author qiushi.zhou  
 * @date 2016年8月22日 下午1:50:42
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    public ApplicationContextHolder() {
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
}

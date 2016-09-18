/**
 * Copyright©2013 www.jshuabo.com. all rights reserved.
 * 
 * @Title: IBaseMapper.java
 * @Prject: memory-persist
 * @Package: com.jshuabo.frame.server.dao
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:17:20 PM
 * @version: v1.0
 * @Description:
 */
package com.basic.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年9月7日 下午1:37:40
 */
public interface IBaseExport {
    
    @Transactional
    List<Map<String,Object>> exportPage(Map<String, Object> params);
    
    @Transactional
    Long total(Map<String, Object> params);
     
}

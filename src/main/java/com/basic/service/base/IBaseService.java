package com.basic.service.base;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
/**
 * 基础service
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午11:28:31
 */
public interface IBaseService {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return String
	 */
	@Transactional
	String page(Map<String,Object> params);
	/**
	 * 统计查询
	 * 
	 * @param params
	 * @return Long
	 */
	@Transactional
	Long total(Map<String,Object> params);
}

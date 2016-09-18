package com.basic.dao.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.basic.model.base.Entity;
/**
 * 基础mapper
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午9:49:14
 */
public interface IBaseMapper<T extends Entity> {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return List<?>
	 */
	@Transactional
	List<?> page(Map<String,Object> params);
	
	/**
	 * 统计查询
	 * 
	 * @param params 
	 * @return Long
	 */
	@Transactional
	Long total(Map<String,Object> params);
	
	/**
	 * 根据id进行查询
	 * 
	 * @param id
	 * @return T
	 */
	T load(Long id);
	
	/**
	 * 查询所有
	 * 
	 * @param 
	 * @return List<T>
	 */
	List<T> loadAll();
	
	/**
	 * 新增
	 * 
	 * @param entity 
	 * @return void
	 */
	void save(T entity);
	
	/**
	 * 新增
	 * 
	 * @param 
	 * @return int
	 */
	int insert(Map<String,Object> params);
	
	/**
	 * 更新
	 * 
	 * @param entity 
	 * @return Integer
	 */
	Integer update(T entity);
	
	/**
	 * 删除
	 * 
	 * @param entity 
	 * @return Integer
	 */
	Integer delete(T entity);
	
	/**
	 * 根据id进行删除
	 * 
	 * @param id
	 * @return Integer
	 */
	Integer deleteById(Long id);
}

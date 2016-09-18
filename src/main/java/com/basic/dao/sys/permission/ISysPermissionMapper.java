package com.basic.dao.sys.permission;

import java.util.List;
import java.util.Map;

import com.basic.dao.base.IBaseMapper;
import com.basic.model.sys.permission.SysPermission;
/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:55:10
 */
public interface ISysPermissionMapper extends IBaseMapper<SysPermission>{
	
	/**
	 * 根据用户id查询出权限主目录
	 * 
	 * @param 
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> loadMainPermissionByUsername(String username);
	/**
	 * 根据主目录id获取权限子目录
	 * 
	 * @param 
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> loadChildPermission(Long parentId);
}

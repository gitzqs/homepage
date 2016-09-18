package com.basic.service.sys.permission;

import java.util.List;
import java.util.Map;

import com.basic.service.base.IBaseService;
/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月24日 下午1:52:12
 */
public interface ISysPermissionService extends IBaseService{
	
	/**
	 * 根据用户名获取用户权限目录
	 * 
	 * @param 
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> loadUserPermission(String username);

}

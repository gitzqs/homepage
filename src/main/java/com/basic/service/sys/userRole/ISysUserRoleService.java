package com.basic.service.sys.userRole;

import org.springframework.transaction.annotation.Transactional;

import com.basic.model.sys.userRole.SysUserRole;
/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 下午1:42:30
 */
public interface ISysUserRoleService{
	
	/**
	 * 根据username进行查询
	 * 
	 * @param 
	 * @return SysUserRole
	 */
	@Transactional
	SysUserRole loadByUsername(String username);
	
	/**
	 * 根据userId获取roleId
	 * 
	 * @param 
	 * @return SysUserRole
	 */
	@Transactional
	SysUserRole loadByUserId(Long userId);
}

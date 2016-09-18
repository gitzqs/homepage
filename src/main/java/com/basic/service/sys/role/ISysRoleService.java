package com.basic.service.sys.role;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.basic.model.sys.role.SysRole;
import com.basic.service.base.IBaseService;
/**
 * 角色service
 * 
 * @author qiushi.zhou  
 * @date 2016年8月26日 下午4:18:29
 */
public interface ISysRoleService extends IBaseService{
	
	/**
	 * 新增处理
	 * 
	 * @param 
	 * @return void
	 */
	@Transactional
	String addRoleOperation(Map<String,Object> params);
	
	/**
	 * 根据id或者code或者name进行查询
	 * 
	 * @param 
	 * @return SysRole
	 */
	@Transactional
	SysRole loadByParam(Map<String,Object> params);
	
	/**
	 * 编辑处理
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	String editRoleOperation(Map<String,Object> params);
	
	/**
	 * 删除
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	String remove(String ids);
	
	/**
	 * 获取全部角色
	 * 
	 * @param 
	 * @return List<SysRole>
	 */
	@Transactional
	List<SysRole> loadAll();
}

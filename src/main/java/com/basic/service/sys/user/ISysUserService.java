package com.basic.service.sys.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.basic.model.sys.user.SysUser;
import com.basic.service.base.IBaseExport;
import com.basic.service.base.IBaseService;
/**
 * user的service
 * 
 * @author qiushi.zhou  
 * @date 2016年8月24日 上午10:06:28
 */
public interface ISysUserService extends IBaseService ,IBaseExport{
	
	/**
	 * 根据username进行查询
	 * 
	 * @param username
	 * @return SysUser
	 */
	@Transactional
	SysUser loadByUsername(String username);
	
	/**
	 * 注册
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	String registerAdd(String username,String password,String password_again,HttpServletRequest request);
	
	/**
	 * 新增处理
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	String add(Map<String,Object> params);
	
	/**
	 * 根据id进行查询
	 * 
	 * @param 
	 * @return SysUser
	 */
	@Transactional
	SysUser load(Long id);
	
	/**
	 * 编辑操作
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	String edit(Map<String,Object> params);
	
	/**
	 * 删除操作
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	String remove(String ids);
}

package com.basic.dao.sys.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.basic.dao.base.IBaseMapper;
import com.basic.model.sys.user.SysUser;
/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:55:35
 */
public interface ISysUserMapper extends IBaseMapper<SysUser>{
	
	/**
	 * 根据username进行查询
	 * 
	 * @param  username
	 * @return SysUser
	 */
	SysUser loadByUsername(String username);
	
	/**
	 * 新增用户
	 * 
	 * @param 
	 * @return int
	 */
	int insert(SysUser user);
	
	/**
	 * 按id删除
	 * 
	 * @param 
	 * @return void
	 */
	void remove(@Param("idList")List<String> idList);
	
	/**
	 * 导出
	 * 
	 * @param 
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> exportPage(Map<String,Object> params);
}

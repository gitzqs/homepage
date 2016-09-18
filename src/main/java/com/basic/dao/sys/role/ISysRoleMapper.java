package com.basic.dao.sys.role;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.basic.dao.base.IBaseMapper;
import com.basic.model.sys.role.SysRole;
/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:55:27
 */
public interface ISysRoleMapper extends IBaseMapper<SysRole>{
	
	/**
	 * 根据code或者name判断角色是否唯一
	 * 
	 * @param 
	 * @return int
	 */
	int judgeOnlyByCodeOrName(Map<String,Object> params);
	
	/**
	 * 根据id或者code或者name查询
	 * 
	 * @param 
	 * @return SysRole
	 */
	SysRole loadByParam(Map<String,Object> params);
	
	/**
	 * 删除
	 * 
	 * @param 
	 * @return void
	 */
	void remove(@Param("idList") List<String> idList);
}

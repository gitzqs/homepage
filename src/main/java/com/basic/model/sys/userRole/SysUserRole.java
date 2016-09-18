package com.basic.model.sys.userRole;

import com.basic.model.base.REntity;
import com.basic.model.sys.role.SysRole;
import com.basic.model.sys.user.SysUser;

/**
 * 用户-角色
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:13:02
 */
public class SysUserRole extends REntity{

	private static final long serialVersionUID = -1690932859789527943L;
	
	/** 用户*/
	private SysUser user;
	
	/** 角色*/
	private SysRole role;
	
	/** 状态{@link EBaseStatus.java}*/
	private String status;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

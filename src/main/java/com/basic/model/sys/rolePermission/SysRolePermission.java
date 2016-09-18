package com.basic.model.sys.rolePermission;

import com.basic.model.base.REntity;
import com.basic.model.sys.permission.SysPermission;
import com.basic.model.sys.role.SysRole;

/**
 * 角色-权限
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:15:39
 */
public class SysRolePermission extends REntity {

	private static final long serialVersionUID = 6716427507015279343L;
	
	/** 角色*/
	private SysRole role;
	
	/** 权限*/
	private SysPermission permission;
	
	/** 状态{@link EBaseStatus.java}*/
	private String status;

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public SysPermission getPermission() {
		return permission;
	}

	public void setPermission(SysPermission permission) {
		this.permission = permission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

package com.basic.model.sys.user;

import com.basic.model.base.REntity;
/**
 * 用户
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:03:48
 */
public class SysUser extends REntity{

	private static final long serialVersionUID = -7338009131249464979L;
	
	/** 用户名*/
	private String username;
	
	/** 密码*/
	private String password;
	
	/** 状态{@link EBaseStatus.java}*/
	private String status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

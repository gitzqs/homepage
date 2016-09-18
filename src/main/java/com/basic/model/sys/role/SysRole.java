package com.basic.model.sys.role;

import com.basic.model.base.REntity;

/**
 * 角色
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:04:12
 */
public class SysRole extends REntity{

	private static final long serialVersionUID = 6771235999756133811L;
	
	/** 角色编码*/
	private String code;
	
	/** 角色名称*/
	private String name;
	
	/** 状态{@link EBaseStatus.java}*/
	private String status;
	
	/** 备注*/
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

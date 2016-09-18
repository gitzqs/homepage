package com.basic.model.sys.permission;

import com.basic.model.base.REntity;

/**
 * 权限
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午10:06:40
 */
public class SysPermission extends REntity{

	private static final long serialVersionUID = 563100069008244525L;
	
	/** 权限编码*/
	private String code;
	
	/** 权限名称*/
	private String name;
	
	/** 上级权限id*/
	private String parentPermissionId;
	
	/** 权限类型  {@link EPermissionType.java}*/
	private String type;
	
	/** 地址*/
	private String url;
	
	/** 优先级*/
	private int priority;
	
	/** ״状态{@link EBaseStatus.java}*/
	private String status;
	
	/** icon*/
	private String icon;

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

	public String getParentPermissionId() {
		return parentPermissionId;
	}

	public void setParentPermissionId(String parentPermissionId) {
		this.parentPermissionId = parentPermissionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}

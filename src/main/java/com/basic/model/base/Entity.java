package com.basic.model.base;

import java.io.Serializable;
/**
 *  a base entity
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午9:55:33
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = -7525068279847257559L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

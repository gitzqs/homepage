package com.basic.model.base;

import java.io.Serializable;
import java.util.Date;
/**
 * rich info for entity
 *  
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午9:47:58
 */
public class RInfo implements Serializable {

	private static final long serialVersionUID = 2811752749939036316L;
	
	/** 创建人id*/
	private Long creatorId;
	
	/** 创建人名称*/
	private String creator;
	
	/** 创建时间*/
	private Date createdTime;
	
	/** 最后修改人id*/
	private Long lastOperatorId;
	
	/** 最后修改人名称*/
	private String lastOperator;
	
	/** 最后修改时间*/
	private Date lastOperatedTime;

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getLastOperatorId() {
		return lastOperatorId;
	}

	public void setLastOperatorId(Long lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public Date getLastOperatedTime() {
		return lastOperatedTime;
	}

	public void setLastOperatedTime(Date lastOperatedTime) {
		this.lastOperatedTime = lastOperatedTime;
	}
	
	
	
}

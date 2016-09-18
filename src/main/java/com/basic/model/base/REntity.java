package com.basic.model.base;
/**
 * a rich entity based on Entity
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 上午9:58:48
 */
public class REntity extends Entity{

	private static final long serialVersionUID = 1296256355457780698L;
	
	private RInfo info;

	public RInfo getInfo() {
		return info;
	}

	public void setInfo(RInfo info) {
		this.info = info;
	}
}

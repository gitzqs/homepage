package com.basic.util.exception;

/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月26日 下午4:49:09
 */
public class BusinessLayerException extends BaseRuntimeException {
	private static final long serialVersionUID = -2497331288743160972L;

	/**
	 * Create a new BusinessException with the specified message.
	 * 
	 * @param msg
	 *            
	 */
	public BusinessLayerException(String msg) {
		super(msg);
	}
	
	public BusinessLayerException(String msg, String[] params) {
	    super(msg);
	    if(params == null || params.length == 0)
            return;
        super.setParams(params);
	}

	/**
	 * Create a new BusinessException with the specified message and root cause.
	 * 
	 * @param msg
	 *            
	 * @param ex
	 *            
	 */
	public BusinessLayerException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public BusinessLayerException(String msg, Throwable ex, String[] params) {
	    super(msg, ex);
	    if(params == null || params.length == 0)
            return;
        super.setParams(params);
	}
}


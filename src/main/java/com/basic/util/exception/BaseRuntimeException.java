package com.basic.util.exception;
/**
 * 
 * 
 * @author qiushi.zhou  
 * @date 2016年8月26日 下午4:39:18
 */
public class BaseRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 5374159890497984842L;
	private String[] params = new String[0];

	/**
	 * Create a new BaseRuntimeException with the specified message.
	 * 
	 * @param msg
	 *            
	 */
	public BaseRuntimeException(String msg) {
		super(msg);
	}
	
	public BaseRuntimeException(String msg, String[] params) {
	    super(msg);
	    if(params == null || params.length == 0)
	        return;
	    this.params = params;
	}

	/**
	 * Create a new BaseRuntimeException with the specified message and root
	 * cause.
	 * 
	 * @param msg
	 *            
	 * @param ex
	 *            
	 */
	public BaseRuntimeException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	public BaseRuntimeException(String msg, Throwable ex, String[] params) {
	    super(msg, ex);
	    if(params == null || params.length == 0)
            return;
        this.params = params;
	}

    /**
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(String[] params) {
        this.params = params;
    }
}

package com.basic.model.base;
/**
 * 返回数据code以及msg
 * 
 * @author qiushi.zhou  
 * @date 2016年8月30日 上午10:28:29
 */
public interface ReturnData {
	
	/** 未知错误**/
	String UNKNOWN_ERROR_CODE = "9999";
	
	String UNKNOWN_ERROR_MSG = "未知错误";
	
	/** 处理成功**/
	String SUCCESS_CODE = "0000";
	
	String SUCCESS_MSG = "处理成功";
	
	/** 处理失败**/
	String FAIL_CODE = "1111";
	
	String FAIL_MSG = "处理失败";
	
	/** 参数为空**/
	String EMPTY_PARAMETER_CODE = "0001";
	
	String EMPTY_PARAMETER_MSG = "参数为空";
	
	/** 数据已存在 **/
	String DATA_EXIST_CODE = "0002";
	
	String DATA_EXIST_MSG = "数据已经存在";
	
	
	/** 两次参数不相等**/
	String DATA_NOT_EQUAL_CODE = "0003";
	
	String DATA_NOT_EQUAL_MSG = "两次参数不相等";
	
	/** 数据不存在**/
	String UNKNOWN_DATA_CODE = "0004";
	
	String UNKNOWN_DATA_MSG = "数据不存在";
}

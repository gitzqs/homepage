package com.basic.web.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 权限
 * 
 * @author qiushi.zhou  
 * @date 2016年8月29日 下午2:11:42
 */
@Controller
@RequestMapping(value="sysPermission")
public class PermissionController {
	
	/**
	 * 权限管理页面
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String list(){
		return "system/permission/listPermission";
	}
}

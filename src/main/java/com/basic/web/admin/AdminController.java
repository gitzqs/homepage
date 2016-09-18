package com.basic.web.admin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basic.model.sys.user.SysUser;
import com.basic.model.sys.userRole.SysUserRole;
import com.basic.service.sys.permission.ISysPermissionService;
import com.basic.service.sys.userRole.ISysUserRoleService;

/**
 * 后台
 * 
 * @author qiushi.zhou  
 * @date 2016年8月23日 下午3:43:03
 */
@Controller
@RequestMapping(value="admin")
public class AdminController {
	
	@Autowired
	private ISysPermissionService sysPermissionService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	/**
	 * 登录中转页面
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String admin(){
		if(SecurityUtils.getSubject().isAuthenticated()){
			return "redirect:/admin/main";
		}else{
			return "redirect:/login/";
		}
	}
	
	/**
	 * 后台首页
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String main(Model model){
		
		
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()){
			return "/login/";
		}
		
		SysUser user = (SysUser)subject.getPrincipal();
		
		//获取用户权限目录
		List<Map<String,Object>> mainList = sysPermissionService.loadUserPermission(user.getUsername());
		
		//获取用户角色
		SysUserRole userRole = sysUserRoleService.loadByUsername(user.getUsername());
		if(userRole !=null){
			model.addAttribute("role", userRole.getRole().getName());
		}
		
		model.addAttribute("username", user.getUsername());
		model.addAttribute("parentList", mainList);
		return "index";
	}
}

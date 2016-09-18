package com.basic.web.sys;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.model.base.EBaseStatus;
import com.basic.model.sys.role.SysRole;
import com.basic.service.sys.role.ISysRoleService;
import com.basic.util.String.StringUtil;

/**
 * 角色管理
 * 
 * @author qiushi.zhou  
 * @date 2016年8月26日 下午1:29:16
 */
@Controller
@RequestMapping(value="/sysRole")
public class RoleController {
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	/**
	 * 展示页面
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String listRole(){
		return "system/role/sysRole";
	}
	
	/**
	 * 新增角色
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "system/role/addRole";
	}
	/**
	 * 新增角色处理
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/add_operation",method=RequestMethod.POST)
	@ResponseBody
	public String add_role_operation(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("code", request.getParameter("roleCode"));
		params.put("name", request.getParameter("roleName"));
		params.put("description", request.getParameter("description"));
		
		return sysRoleService.addRoleOperation(params);
	}
	/**
	 * 编辑页面-展示
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,String id){
		
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", Long.parseLong(id));
			param.put("status", EBaseStatus.ENABLED);
			SysRole sysRole = sysRoleService.loadByParam(param);
			if(sysRole != null){
				model.addAttribute("role", sysRole);
			}
		}
		return "system/role/editRole";
	}
	
	/**
	 * 编辑-逻辑处理
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/edit_operation",method=RequestMethod.POST)
	@ResponseBody
	public String edit_operation(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", request.getParameter("roleId"));
		params.put("code", request.getParameter("roleCode"));
		params.put("name", request.getParameter("roleName"));
		params.put("description", request.getParameter("description"));
		return sysRoleService.editRoleOperation(params);
	}
	/**
	 * 删除
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	@ResponseBody
	public String remove(String ids){
		return sysRoleService.remove(ids);
	}
	
}

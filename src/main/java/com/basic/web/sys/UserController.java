package com.basic.web.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.model.sys.role.SysRole;
import com.basic.model.sys.user.SysUser;
import com.basic.model.sys.userRole.SysUserRole;
import com.basic.service.sys.role.ISysRoleService;
import com.basic.service.sys.user.ISysUserService;
import com.basic.service.sys.userRole.ISysUserRoleService;
import com.basic.util.String.StringUtil;
import com.basic.util.date.DateFormatUtils;
import com.basic.util.exception.BaseRuntimeException;
import com.basic.util.poi.FileUtil;
import com.basic.util.poi.csv.CsvUtil;
import com.basic.util.poi.excel.ExcelUtil;
/**
 * 用户
 * 
 * @author qiushi.zhou  
 * @date 2016年8月29日 下午1:55:03
 */
@Controller
@RequestMapping(value="sysUser")
public class UserController {
	
	@Value("${sysUser.headers}")
	private String INEXTENDLOAN_HEADERS;
	@Value("${sysUser.propertys}")
	private String INEXTENDLOAN_PROPERTYS;
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysRoleService sysRoleService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	/**
	 * 用户管理页面
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String list(){
		return "system/user/listUser";
	}
	
	/**
	 * 新增用户
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		List<SysRole> roleList = sysRoleService.loadAll();
		model.addAttribute("roleList", roleList);
		return "system/user/addUser";
	}
	
	/**
	 * 新增处理
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/add_operation",method=RequestMethod.POST)
	@ResponseBody
	public String add_operation(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", request.getParameter("roleId"));
		params.put("username", request.getParameter("username"));
		params.put("password", request.getParameter("password"));
		params.put("password_again", request.getParameter("password_again"));
		
		return sysUserService.add(params);
	}
	
	/**
	 * 编辑
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,HttpServletRequest request){
		//获取全部角色
		List<SysRole> roleList = sysRoleService.loadAll();
		model.addAttribute("roleList", roleList);
		
		//获取当前角色id
		String userId = request.getParameter("id");
		if(!StringUtil.isEmpty(userId)){
			SysUser user = sysUserService.load(Long.parseLong(userId));
			if(user != null){
				model.addAttribute("user", user);
				SysUserRole userRole = sysUserRoleService.loadByUserId(Long.parseLong(userId));
				if(userRole != null){
					model.addAttribute("roleId", userRole.getRole().getId());
				}
			}
			
		}
		return "system/user/editUser";
	}
	
	/**
	 * 编辑操作
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/edit_operation",method=RequestMethod.POST)
	@ResponseBody
	public String edit_operation(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", request.getParameter("userId"));
		params.put("roleId", request.getParameter("roleId"));
		
		return sysUserService.edit(params);
	}
	
	/**
	 * 删除操作
	 * 
	 * @param 
	 * @return String
	 */
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	@ResponseBody
	public String remove(String ids){
		
		return sysUserService.remove(ids);
	}
	
	@RequestMapping(value = "/exportXls", method = RequestMethod.GET)
	public void exportXls(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		try {
//			Map<String, Object> maps = MapUtils.requestObjMap(request);
			String[] bisTransLocation_headers = org.springframework.util.StringUtils
					.tokenizeToStringArray(INEXTENDLOAN_HEADERS, ",");
			String[] bisTransLocation_propertys = org.springframework.util.StringUtils
					.tokenizeToStringArray(INEXTENDLOAN_PROPERTYS, ",");
			ExcelUtil.export2Excel(sysUserService, request, response,
					params, bisTransLocation_headers, bisTransLocation_propertys,
					"用户");
		}
		catch (Exception e) {
			throw new BaseRuntimeException("用户导出失败:"+e);
		}
	}
	
	/**
	 * 导出成csv
	 * 
	 * @param 
	 * @return void
	 */
	@RequestMapping(value="/exportCsv",method = RequestMethod.GET)
	public void exportCsv(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> params = new HashMap<String,Object>();
		//头部信息
		List<Object> head = com.basic.util.String.StringUtil.stringToList2(INEXTENDLOAN_HEADERS);
		
		//主体数据
		List<Map<String,Object>> dataList = sysUserService.exportPage(params);
		
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")+"/tempFile/";
		
		String filename = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		
		CsvUtil.createCSVFile(head, mapToList(dataList), filePath, filename);
		FileUtil.downFile(response, filePath,filename+".csv");
		FileUtil.deleteFile(filePath+filename+".csv");
		
	}
	
	private List<List<Object>> mapToList(List<Map<String,Object>> params){
		List<List<Object>> newList = new ArrayList<List<Object>>();
		for(int i=0; i<params.size(); i++){
			List<Object> object = new ArrayList<Object>();
			object.add((Object)params.get(i).get("ID"));
			object.add((Object)params.get(i).get("USERNAME"));
			object.add((Object)params.get(i).get("ROLENAME"));
			object.add((Object)params.get(i).get("STATUS"));
			newList.add(object);
		}
		return newList;
	}
}

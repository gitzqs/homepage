package com.basic.service.sys.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.basic.dao.sys.role.ISysRoleMapper;
import com.basic.dao.sys.user.ISysUserMapper;
import com.basic.dao.sys.userRole.ISysUserRoleMapper;
import com.basic.model.base.ReturnData;
import com.basic.model.sys.role.SysRole;
import com.basic.model.sys.user.SysUser;
import com.basic.service.sys.user.ISysUserService;
import com.basic.util.String.StringUtil;
import com.basic.util.json.JacksonUtils;
import com.basic.util.mysql.DatabaseCreatorMysql;
@Service("sysUserService")
public class DefaultSysUserServiceImpl implements ISysUserService {
	
	@Autowired
	private ISysUserMapper sysUserMapper;
	
	
	@Autowired
	private ISysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private ISysRoleMapper sysRoleMapper;
	
	private DatabaseCreatorMysql databaseCreator = new DatabaseCreatorMysql();
	
	private static Logger logger = LoggerFactory.getLogger(DefaultSysUserServiceImpl.class);
	
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.incomplete.url}")
	private String inconpleteUrl;
	@Value("${jdbc.username}")
	private String databaseUsername;
	@Value("${jdbc.password}")
	private String databasePassword;
	
	@SuppressWarnings("unchecked")
	@Override
	public String page(Map<String, Object> params) {
		//当offset跟rows出现没有值的情况，赋值默认
		params.put("offset", Integer.parseInt((params.get("offset")==null ? '0' : params.get("offset")).toString()));
		params.put("rows", Integer.parseInt((params.get("rows")==null ? '5' : params.get("rows")).toString()));
		
		List<Map<String,Object>> rows = (List<Map<String,Object>>)sysUserMapper.page(params);
		Long total = sysUserMapper.total(params);
		params.clear();
		params.put("rows", rows);
		params.put("total", total);
		
		return JacksonUtils.object2json(params);
	}

	@Override
	public Long total(Map<String, Object> params) {
		return sysUserMapper.total(params);
	}

	@Override
	public SysUser loadByUsername(String username) {
		return sysUserMapper.loadByUsername(username);
	}

	@Override
	public String registerAdd(String username, String password,
			String password_again,HttpServletRequest request) {
		
		//初始化
		Map<String,Object> returnParams = new HashMap<String,Object>();
		String returnCode = ReturnData.FAIL_CODE;
		String returnMsg = "注册失败";
		
		//1、判断参数是否为空
		if(!StringUtil.isEmpty(username)&& !StringUtil.isEmpty(password)){
			//2、验证两次输入密码是否相同
			if(password.equals(password_again)){
				//3、验证用户名是否存在
				SysUser user = sysUserMapper.loadByUsername(username);
				if(null == user){
					//4、新增用户
					SysUser sysUser = new SysUser();
					sysUser.setUsername(username);
					sysUser.setPassword(DigestUtils.md5Hex(password));
					int index = sysUserMapper.insert(sysUser);
					if(index > 0){
						Long userId = sysUser.getId();
						//5、新建数据库和导入初始数据
						String filePath = request.getSession().getServletContext()
								.getRealPath("/")+"/tempFile/root.sql";
						String databaseName = "database_"+userId;
						try{
							String result = databaseCreator.databaseCreateAndImportMysql(databaseName, filePath);
							if(result.equals(ReturnData.SUCCESS_CODE)){
								//6、新建用户-数据库对应关系
								Map<String,Object> params = new HashMap<String,Object>();
								params.put("userId", userId);
								params.put("databaseDriver", this.driver);
								params.put("databaseUrl",this.inconpleteUrl
										+databaseName+"?useUnicode=true&characterEncoding=utf-8");
								params.put("databaseName", databaseName);
								params.put("databaseUser", this.databaseUsername);
								params.put("databasePassword", this.databasePassword);
//								int index_2 = userDatabaseMapper.insert(params);
//								if(index_2 > 0){
//									returnCode = ReturnData.SUCCESS_CODE;
//									returnMsg = "注册成功";
//								}
							}
						}catch(Exception e){
							logger.error("create database failed: [{}]",e);
						}
						
					}
				}else{
					returnCode = ReturnData.DATA_EXIST_CODE;
					returnMsg = "用户名已经存在！";
				}
			}else{
				returnCode = ReturnData.DATA_NOT_EQUAL_CODE;
				returnMsg = "两次输入密码不一致！";
			}
		}else{
			returnCode = ReturnData.EMPTY_PARAMETER_CODE;
			returnMsg = "用户名或者密码不能为空！";
		}
		returnParams.put("returnCode", returnCode);
		returnParams.put("returnMsg", returnMsg);
		
		return JacksonUtils.object2json(returnParams);
	}

	@Override
	public String add(Map<String, Object> params) {
		//初始化
		Map<String,Object> returnParams = new HashMap<String,Object>();
		String returnCode = ReturnData.SUCCESS_CODE;
		String returnMsg = "新增用户成功！";
		
		String username = params.get("username")==null ? StringUtil.EMPTY : params.get("username").toString();
		String password = params.get("password")==null ? StringUtil.EMPTY : params.get("password").toString();
		String passwordAgain = params.get("password_again")==null ? StringUtil.EMPTY : params.get("password_again").toString();
		String roleId = params.get("roleId")==null ? StringUtil.EMPTY : params.get("roleId").toString();
		
		//1、验证参数是否为空
		if(!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password) && !StringUtil.isEmpty(roleId)){
			//2、验证两次输入密码是否一样
			if(password.equals(passwordAgain)){
				//3、验证用户名是否存在
				SysUser user = sysUserMapper.loadByUsername(username);
				if(null == user){
					//4、存储用户
					SysUser sysUser = new SysUser();
					sysUser.setUsername(username);
					sysUser.setPassword(DigestUtils.md5Hex(password));
					try{
						sysUserMapper.insert(sysUser);
						//5、关联角色
						params.clear();
						params.put("userId", sysUser.getId());
						params.put("roleId", Long.parseLong(roleId));
						try{
							sysUserRoleMapper.insert(params);
						}catch(Exception e1){
							logger.error("user associat role failed : [{}]",e1);
							returnCode = ReturnData.UNKNOWN_ERROR_CODE;
							returnMsg = "新增用户失败！";
						}
					}catch(Exception e){
						logger.error("add user failed : [{}]",e);
						returnCode = ReturnData.UNKNOWN_ERROR_CODE;
						returnMsg = "新增用户失败！";
					}
					
				}else{
					returnCode = ReturnData.DATA_EXIST_CODE;
					returnMsg = "用户名已经存在！";
				}
			}else{
				returnCode = ReturnData.DATA_NOT_EQUAL_CODE;
				returnMsg = "两次输入密码必须一致！";
			}
		}else{
			returnCode = ReturnData.EMPTY_PARAMETER_CODE;
			returnMsg = "用户名或者密码或者角色不能为空！";
		}
		returnParams.put("returnCode", returnCode);
		returnParams.put("returnMsg", returnMsg);
		
		return JacksonUtils.object2json(returnParams);
	}

	@Override
	public SysUser load(Long id) {
		return sysUserMapper.load(id);
	}

	@Override
	public String edit(Map<String, Object> params) {
		Map<String,Object> returnParams = new HashMap<String,Object>();
		String returnCode = ReturnData.SUCCESS_CODE;
		String returnMsg = "编辑用户成功！";
		
		String userId = params.get("userId")==null ? StringUtil.EMPTY : params.get("userId").toString();
		String roleId = params.get("roleId")==null ? StringUtil.EMPTY : params.get("roleId").toString();
		
		//1、验证参数是否为空
		if(!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(roleId)){
			//2、验证用户是否存在
			SysUser user = sysUserMapper.load(Long.parseLong(userId));
			if(user != null){
				//3、验证角色是否存在
				params.clear();
				params.put("id", Long.parseLong(roleId));
				SysRole role = sysRoleMapper.loadByParam(params);
				if(role != null){
					//4、更新用户-角色关系表
					params.clear();
					params.put("userId", Long.parseLong(userId));
					params.put("roleId", Long.parseLong(roleId));
					try{
						sysUserRoleMapper.updateRole(params);
					}catch(Exception e){
						logger.error("update user's role failed : [{}]",e);
						returnCode = ReturnData.UNKNOWN_ERROR_CODE;
						returnMsg = "编辑用户失败";
					}
				}else{
					returnCode = ReturnData.UNKNOWN_DATA_CODE;
					returnMsg = "角色不存在";
				}
			}else{
				returnCode = ReturnData.UNKNOWN_DATA_CODE;
				returnMsg = "用户不存在";
			}
		}else{
			returnCode = ReturnData.EMPTY_PARAMETER_CODE;
			returnMsg = "所属角色不能为空！";
		}
		
		returnParams.put("returnCode", returnCode);
		returnParams.put("returnMsg", returnMsg);
		
		return JacksonUtils.object2json(returnParams);
	}

	@Override
	public String remove(String ids) {
		Map<String,Object> params = new HashMap<String,Object>();
		String returnCode = ReturnData.SUCCESS_CODE;
		String returnMsg = "删除成功！";
		
		List<String> idList = new ArrayList<String>();
		if(!StringUtil.isEmpty(ids)){
			idList = StringUtil.stringToList(ids);
			sysUserMapper.remove(idList);
		}else{
			returnCode = ReturnData.EMPTY_PARAMETER_CODE;
			returnMsg = "请选中至少一条记录！";
		}
		params.put("returnCode", returnCode);
		params.put("returnMsg", returnMsg);
		
		return JacksonUtils.object2json(params);
	}

	@Override
	public List<Map<String, Object>> exportPage(Map<String, Object> params) {
		return sysUserMapper.exportPage(params);
	}

}

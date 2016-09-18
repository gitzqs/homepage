package com.basic.service.sys.userRole.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.sys.userRole.ISysUserRoleMapper;
import com.basic.model.sys.userRole.SysUserRole;
import com.basic.service.sys.userRole.ISysUserRoleService;
@Service("sysUserRoleService")
public class DefaultSysUserRoleServiceImpl implements ISysUserRoleService {

	@Autowired
	private ISysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public SysUserRole loadByUsername(String username) {
		return sysUserRoleMapper.loadByUsername(username);
	}

	@Override
	public SysUserRole loadByUserId(Long userId) {
		return sysUserRoleMapper.loadByUserId(userId);
	}

}

package com.basic.service.sys.permission.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.sys.permission.ISysPermissionMapper;
import com.basic.service.sys.permission.ISysPermissionService;
import com.basic.util.json.JacksonUtils;
@Service("sysPermissionService")
public class DefaultSysPermissionServiceImpl implements ISysPermissionService {
	
	@Autowired
	private ISysPermissionMapper sysPermissionMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public String page(Map<String, Object> params) {
		//当offset跟rows出现没有值的情况，赋值默认
		params.put("offset", Integer.parseInt((params.get("offset")==null ? '0' : params.get("offset")).toString()));
		params.put("rows", Integer.parseInt((params.get("rows")==null ? '5' : params.get("rows")).toString()));
		params.put("parentId", 0);
		List<Map<String,Object>> rows = (List<Map<String,Object>>)
				sysPermissionMapper.page(params);
		Long total = sysPermissionMapper.total(params);
		params.clear();
		params.put("rows", rows);
		params.put("total", total);
		
		return JacksonUtils.object2json(params);
	}

	@Override
	public Long total(Map<String, Object> params) {
		return sysPermissionMapper.total(params);
	}

	@Override
	public List<Map<String, Object>> loadUserPermission(String username) {
		//初始化权限子目录
		
		//获取权限主目录
		List<Map<String,Object>> mainList = sysPermissionMapper.loadMainPermissionByUsername(username);
		if(mainList !=null && mainList.size() >0){
			for(int i=0; i<mainList.size(); i++){
				List<Map<String,Object>> childList = new ArrayList<Map<String,Object>>();
				childList = sysPermissionMapper.loadChildPermission(Long.parseLong(mainList.get(i).get("ID").toString()));
				mainList.get(i).put("childList", childList);
			}
		}
		
		return mainList;
	}

}

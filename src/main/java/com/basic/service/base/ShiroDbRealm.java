package com.basic.service.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.model.base.EBaseStatus;
import com.basic.model.sys.user.SysUser;
import com.basic.model.sys.userRole.SysUserRole;
import com.basic.service.sys.user.ISysUserService;
import com.basic.service.sys.userRole.ISysUserRoleService;
/**
 * shiro权限、登录认证
 * 
 * @author qiushi.zhou  
 * @date 2016年8月24日 上午9:30:53
 */
@Service("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm{
	
	@Autowired
	ISysUserRoleService sysUserRoleService;
	@Autowired
	ISysUserService sysUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SysUser user = (SysUser) principals.fromRealm(getName()).iterator().next();
		if(null == user) return null;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		SysUserRole userRole = sysUserRoleService.loadByUsername(user.getUsername());
		if(null == userRole) return null;
		info.addRole(userRole.getRole().getCode());
		return info;
	}
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken anthenticationToken) throws AuthenticationException {
		//登录时候将数据源切换至默认数据源
//		DBContextHolder.setDbType(DynamicDataSource.DEFAULT_DATA_SOURCE);
		
		UsernamePasswordToken token = (UsernamePasswordToken) anthenticationToken;
		SysUser user = sysUserService.loadByUsername(token.getUsername());
		if(null != user){
			if((new String(token.getPassword())).equals(user.getPassword())){
				if(user.getStatus().equals(EBaseStatus.DISABLED)){
					throw new DisabledAccountException("user is disabled");
				}
				SecurityUtils.getSubject().getSession().setAttribute("user", user);
				SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 60 * 24);//会话时间设置：24h
				//切换数据库
//				UserDatabase database = userDatabaseService.loadByUserId(user.getId());
//				SecurityUtils.getSubject().getSession().setAttribute("database", database);
//				DBContextHolder.setDbType("dataSource_"+String.valueOf(user.getId()));
				return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
			}else{
				logger.info("user [{}] authenticated fail with wrong password.", token.getUsername());
			}
		}else{
			logger.info("user [{}] authenticated fail with not exists.", token.getUsername());
		}
		throw new AuthenticationException("can't.find.user");
	}
	
}

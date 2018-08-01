package com.tg.demo.shiroapi.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class SampleRealm extends AuthorizingRealm {

	
	public SampleRealm() {
		super();
	}
	/**
	 *  认证信息，主要针对用户登录， 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// get from db password 123
		if(!("admin".equals(token.getUsername())&&"123".equals(new String(token.getPassword())))){
			throw new AccountException("帐号或密码不正确！");
		}

		return new SimpleAuthenticationInfo(token,token.getPassword(), getName());
    }

	 /** 
     * 授权 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
    	
//    	Long userId = TokenManager.getUserId();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
//		Set<String> roles = roleService.findRoleByUserId(userId);
//		info.setRoles(roles);
//		//根据用户ID查询权限（permission），放入到Authorization里。
//		Set<String> permissions = permissionService.findPermissionByUserId(userId);
//		info.setStringPermissions(permissions);
//		info.addStringPermission("sys:hello");
        return info;  
    }  


}

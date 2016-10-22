package org.cd.sport.utils;

import java.util.Collection;
import java.util.Iterator;

import org.cd.sport.vo.UserAuth;
import org.cd.sport.vo.UserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户权限工具类
 * 
 * @author liuyk
 *
 */
public class AuthenticationUtils {

	public static Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@SuppressWarnings("unchecked")
	public static UserVo getUser() {
		Authentication auth = getAuth();
		UserAuth userAuth = (UserAuth) auth.getPrincipal();
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
		// 用户角色 目前只有一个
		Iterator<SimpleGrantedAuthority> iterator = authorities.iterator();
		while (iterator.hasNext()) {
			SimpleGrantedAuthority next = iterator.next();
			String authority = next.getAuthority();
			UserVo user = new UserVo();
			user.setRole(authority);
			user.setLoginName(auth.getName());
			user.setUserId(userAuth.getUserId());
			user.setUserName(userAuth.getUserName());
			user.setOrgName(userAuth.getOrgName());
			user.setOrganization(userAuth.getOrgId());
			return user;
		}
		return null;
	}

}

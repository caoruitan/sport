package org.cd.sport.utils;

import java.util.Collection;
import java.util.Iterator;

import org.cd.sport.domain.UserDomain;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthenticationUtils {

	public static Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@SuppressWarnings("unchecked")
	public static UserDomain getUser() {
		Authentication auth = getAuth();
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
		// 用户角色 目前只有一个
		Iterator<SimpleGrantedAuthority> iterator = authorities.iterator();
		while (iterator.hasNext()) {
			SimpleGrantedAuthority next = iterator.next();
			String authority = next.getAuthority();
			UserDomain user = new UserDomain();
			user.setRole(authority);
			user.setLoginName(auth.getName());
			return user;
		}
		return null;
	}

}

package org.cd.sport.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.cd.sport.constant.Constants;
import org.cd.sport.dao.UserDao;
import org.cd.sport.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException, DataAccessException {
		// 得到登录用户对象
		// UserDomain user = this.userDao.findUserByLoginName(loginName);

		// 模拟用户
		Map<String, UserDomain> userMap = new HashMap<String, UserDomain>();
		UserDomain lyk = new UserDomain();
		lyk.setLoginName("lyk");
		lyk.setPassword("e10adc3949ba59abbe56e057f20f883e");
		lyk.setStatus(1);
		lyk.setRole("ROLE_USER");
		userMap.put("lyk", lyk);

		UserDomain admin = new UserDomain();
		admin.setLoginName("admin");
		admin.setPassword("e10adc3949ba59abbe56e057f20f883e");
		admin.setStatus(1);
		admin.setRole("ROLE_ADMIN");
		userMap.put("admin", admin);

		UserDomain user = userMap.get(loginName);

		// 用户权限集合
		Collection<GrantedAuthority> grantedAuthority = this.getGrantedAuthority(user);
		User userdetail = new User(user.getLoginName(), user.getPassword(), Constants.User.isActive(user.getStatus()),
				true, true, true, grantedAuthority);
		return userdetail;
	}

	// 获取用户角色，授予权限
	private Set<GrantedAuthority> getGrantedAuthority(UserDomain user) {
		Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
		// Set<Roles> roles = user.getRoles();
		// for (Roles role : roles) {
		// System.out.println("用户角色：" + role.getRolename());
		grantedAuthority.add(new SimpleGrantedAuthority(user.getRole()));
		// }
		return grantedAuthority;
	}
}
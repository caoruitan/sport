package org.cd.sport.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.cd.sport.constant.Constants;
import org.cd.sport.dao.UserDao;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.exception.EntityNotFoundExcetion;
import org.cd.sport.exception.NameIsExistException;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.support.UserSupport;
import org.cd.sport.utils.Md5Util;
import org.cd.sport.view.UserView;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务层
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends UserSupport implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validLoginName(String loginName) {
		UserDomain user = this.userDao.findByLoginName(loginName);
		if (user != null) {
			throw new NameIsExistException("用户名已经存在。");
		}
	}

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validLoginName(String loginName, UserDomain user) {
		UserDomain _user = this.userDao.findByLoginName(loginName);
		if (_user != null) {
			if (!_user.getUserId().equals(user.getUserId())) {
				throw new NameIsExistException("用户名已经存在。");
			}
		}
	}

	/**
	 * 校验密码
	 */
	public synchronized void validPassword(UserDomain user, String oldPwd, String newPwd) {
		if (user == null) {
			throw new EntityNotFoundException();
		}
		this.valid(oldPwd);
		this.valid(oldPwd);
		oldPwd = Md5Util.digestMD5(oldPwd);
		newPwd = Md5Util.digestMD5(newPwd);
		if (!oldPwd.equals(user.getPassword()) || oldPwd.equals(newPwd)) {
			throw new ParameterIsWrongException();
		}
		user.setPassword(newPwd);
	}

	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException, DataAccessException {
		// 得到登录用户对象
		// UserDomain user = this.userDao.findUserByLoginName(loginName);

		// 模拟用户
		Map<String, UserDomain> userMap = new HashMap<String, UserDomain>();
		UserDomain lyk = new UserDomain();
		lyk.setLoginName("lyk");
		lyk.setPassword("e10adc3949ba59abbe56e057f20f883e");
		lyk.setRole("ROLE_USER");
		userMap.put("lyk", lyk);

		UserDomain admin = new UserDomain();
		admin.setLoginName("admin");
		admin.setPassword("e10adc3949ba59abbe56e057f20f883e");
		admin.setRole("ROLE_KJS");
		userMap.put("admin", admin);

		UserDomain user = userMap.get(loginName);
		if (user == null) {
			return null;
		}
		// 用户权限集合
		Collection<GrantedAuthority> grantedAuthority = this.getGrantedAuthority(user);
		User userdetail = new User(user.getLoginName(), user.getPassword(), true, true, true, true, grantedAuthority);
		return userdetail;
	}

	// 获取用户角色，授予权限
	private Set<GrantedAuthority> getGrantedAuthority(UserDomain user) {
		Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority(user.getRole()));
		return grantedAuthority;
	}

	@Override
	@Transactional
	public boolean create(UserView user) {
		UserDomain process = this.process(user);
		this.validLoginName(process.getLoginName());
		this.userDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(UserView user) {
		this.validate(user);
		UserDomain userDomain = this.userDao.findById(user.getUserId());
		if (userDomain == null) {
			throw new EntityNotFoundExcetion("数据不存在");
		}
		this.validLoginName(user.getLoginName(), userDomain);
		BeanUtils.copyProperties(user, userDomain);
		this.userDao.update(userDomain);
		return true;
	}

	@Override
	@Transactional
	public boolean updatePassword(String userId, String oldPwd, String newPwd) {
		this.valid(userId);
		UserDomain user = this.userDao.findById(userId);
		this.validPassword(user, oldPwd, newPwd);
		this.userDao.update(user);
		return true;
	}

	@Override
	@Transactional
	public boolean resetPassword(String loginName) {
		// 角色验证 TODO
		UserDomain user = this.getByLoginName(loginName);
		if (user == null) {
			throw new EntityNotFoundExcetion("数据不存在");
		}
		user.setPassword(Md5Util.digestMD5(Constants.User.DEFAULT_PASSWORD));
		this.userDao.update(user);
		return true;
	}

	@Override
	public boolean delete(String userId) {
		// 角色验证 TODO
		return this.userDao.deleteById(userId);
	}

	@Override
	public boolean delete(String[] userId) {
		// 角色验证 TODO
		return this.userDao.deleteById(userId);
	}

	@Override
	public UserDomain getById(String userId) {
		return this.userDao.findById(userId);
	}

	@Override
	public UserDomain getByLoginName(String loginName) {
		return this.userDao.findByLoginName(loginName);
	}

	@Override
	public List<UserVo> getByRole(String role, int start, int limit) {
		return this.userDao.findByRole(role, start, limit);
	}

	@Override
	public List<UserVo> get(int start, int limit) {
		return this.userDao.find(start, limit);
	}

	@Override
	public long getTotal(String role) {
		return this.userDao.count(role);
	}

	@Override
	public long getTotal() {
		return this.userDao.count();
	}
}
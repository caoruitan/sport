package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.view.UserView;
import org.cd.sport.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户业务层
 * 
 * @author liuyk
 *
 */
public interface UserService extends UserDetailsService {

	public boolean create(UserView user);

	public boolean update(UserView user);

	public boolean updatePassword(String userId, String oldPwd, String newPwd);

	public boolean resetPassword(String loginName);

	public boolean delete(String userId);

	public boolean delete(String[] userId);

	public UserDomain getById(String userId);

	public UserVo getVoById(String userId);

	public UserVo getMangerByOrgId(String orgId);

	public UserVo getVoByLoginName(String loginName);

	public UserDomain getByLoginName(String loginName);

	public List<UserVo> getByRole(String[] role, int start, int limit);

	public List<UserVo> getByRole(String[] role, String name, int start, int limit);

	public List<UserVo> get(int start, int limit);

	public List<UserVo> getVoByOrgId(String orgId, int start, int limit);

	public long getTotal(String[] role);

	public long getTotal(String[] role, String name);

	public long getTotalByOrgId(String orgId);

	public long getTotal();
}

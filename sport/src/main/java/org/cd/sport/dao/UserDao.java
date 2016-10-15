package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.IBaseDao;
import org.cd.sport.vo.UserVo;

/**
 * 用户数据接口
 * 
 * @author liuyk
 *
 */
public interface UserDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public UserDomain findById(String id);

	public UserVo findVoById(String id);

	public UserDomain findByLoginName(String loginName);

	public List<UserDomain> findByRole(String role, int start, int limit);

	public List<UserDomain> find(int start, int limit);

	public long count(String role);

	public long count();

}

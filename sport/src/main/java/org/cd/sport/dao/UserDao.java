package org.cd.sport.dao;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.IBaseDao;

public interface UserDao extends IBaseDao {
	public UserDomain findUserById(String id);

	public UserDomain findUserByLoginName(String loginName);
}

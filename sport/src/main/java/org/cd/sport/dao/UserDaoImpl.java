package org.cd.sport.dao;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDomain> implements UserDao {

	@Override
	public UserDomain findUserById(String id) {
		return this.getEntityById(UserDomain.class, id);
	}

	@Override
	public UserDomain findUserByLoginName(String loginName) {
		return (UserDomain) this.getHibernateQuery("from UserDomain where loginName=:loginName").setParameter("loginName", loginName).uniqueResult();
	}

}
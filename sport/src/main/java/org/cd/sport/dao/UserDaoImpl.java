package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.vo.UserVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDomain> implements UserDao {

	@Override
	public UserDomain findById(String id) {
		return this.getEntityById(UserDomain.class, id);
	}

	@Override
	public UserDomain findByLoginName(String loginName) {
		return (UserDomain) this.getHibernateQuery("from UserDomain where loginName=:loginName")
				.setParameter("loginName", loginName).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDomain> findByRole(String role, int start, int limit) {
		String queryHql = "from UserDomain where role=:role";
		return this.getHibernateQuery(queryHql).setParameter("role", role).setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDomain> find(int start, int limit) {
		String queryHql = "from UserDomain";
		return this.getHibernateQuery(queryHql).setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public long count(String role) {
		String updateHql = "select count(1) from UserDomain where role=:role";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameter("role", role).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public long count() {
		String updateHql = "select count(1) from UserDomain";
		Long count = (Long) this.getHibernateQuery(updateHql).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public boolean deleteById(String userId) {
		String deleteHql = "delete from UserDomain where userId=:userId";
		return this.getHibernateQuery(deleteHql).setParameter("userId", userId).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] userId) {
		String deleteHql = "delete from UserDomain where userId in (:userId)";
		return this.getHibernateQuery(deleteHql).setParameterList("userId", userId).executeUpdate() != 0;
	}

	@Override
	public UserVo findVoById(String id) {
		String querySql = "select U.USER_ID AS \"userId\",O.FULL_NAME as \"orgName\" from SPORT_USER U LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID";
		SQLQuery hibernateSqlQuery = this.getHibernateSqlQuery(querySql);
		hibernateSqlQuery.addScalar("userId");
		hibernateSqlQuery.addScalar("loginName");
		hibernateSqlQuery.addScalar("userName");
		hibernateSqlQuery.addScalar("gender");
		hibernateSqlQuery.addScalar("credType");
		hibernateSqlQuery.addScalar("credNo");
		hibernateSqlQuery.addScalar("role");
		hibernateSqlQuery.addScalar("organization");
		hibernateSqlQuery.addScalar("birthday");
		hibernateSqlQuery.addScalar("zc");
		hibernateSqlQuery.addScalar("zw");
		hibernateSqlQuery.addScalar("dept");
		hibernateSqlQuery.addScalar("degrees");
		hibernateSqlQuery.addScalar("major");
		hibernateSqlQuery.addScalar("telephone");
		hibernateSqlQuery.addScalar("phone");
		hibernateSqlQuery.addScalar("address");
		hibernateSqlQuery.addScalar("orgName");
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(UserVo.class));
		return (UserVo) hibernateSqlQuery.uniqueResult();
	}

}
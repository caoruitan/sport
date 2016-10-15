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
	public List<UserDomain> findByRole(String[] role, int start, int limit) {
		String queryHql = "from UserDomain where role in (:role)";
		return this.getHibernateQuery(queryHql).setParameterList("role", role).setFirstResult(start).setMaxResults(limit)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDomain> find(int start, int limit) {
		String queryHql = "from UserDomain";
		return this.getHibernateQuery(queryHql).setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public long count(String[] role) {
		String updateHql = "select count(1) from UserDomain where role in (:role)";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameterList("role", role).uniqueResult();
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
		String querySql = "select U.USER_ID AS \"userId\",U.LOGIN_NAME AS \"loginName\",U.USER_NAME AS \"userName\","
				+ "U.GENDER AS \"gender\",U.CRED_TYPE AS \"credType\",U.CRED_NO AS \"credNo\",U.ROLE AS \"role\","
				+ "U.ORGANIZATION AS \"organization\",U.BIRTHDAY AS \"birthday\",U.ZC AS \"zc\",U.ZW AS \"zw\","
				+ "U.DEPT AS \"dept\",U.DEGREES AS \"degrees\",U.MAJOR AS \"major\",U.TELEPHONE AS \"telephone\","
				+ "U.PHONE AS \"phone\",U.ADDRESS AS \"address\",O.FULL_NAME as \"orgName\" from SPORT_USER U LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID";
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

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDomain> findByRole(String[] role, String name, int start, int limit) {
		String queryHql = "from UserDomain where role in (:role) and (loginName like :name or userName like :name)";
		return this.getHibernateQuery(queryHql).setParameterList("role", role).setParameter("name", "%" + name + "%")
				.setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public long count(String[] role, String name) {
		String updateHql = "select count(1) from UserDomain where role in (:role) and (loginName like :name or userName like :name)";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameterList("role", role)
				.setParameter("name", "%" + name + "%").uniqueResult();
		return count == null ? 0 : count.intValue();
	}

}
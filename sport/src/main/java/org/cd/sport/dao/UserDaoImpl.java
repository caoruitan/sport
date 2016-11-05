package org.cd.sport.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.vo.UserQuery;
import org.cd.sport.vo.UserVo;
import org.hibernate.Query;
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
		return this.getHibernateQuery(queryHql).setParameterList("role", role).setFirstResult(start)
				.setMaxResults(limit).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDomain> find(int start, int limit) {
		String queryHql = "from UserDomain";
		return this.getHibernateQuery(queryHql).setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public long findTotal(String[] role) {
		String updateHql = "select count(1) from UserDomain where role in (:role)";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameterList("role", role).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public long findTotal() {
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
				+ "U.PHONE AS \"phone\",U.ADDRESS AS \"address\",O.FULL_NAME as \"orgName\" from SPORT_USER U LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID WHERE USER_ID=:userId";
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
		hibernateSqlQuery.setParameter("userId", id);
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(UserVo.class));
		return (UserVo) hibernateSqlQuery.uniqueResult();
	}

	@Override
	public UserVo findVoByLoginName(String loginName) {
		String querySql = "select U.USER_ID AS \"userId\",U.LOGIN_NAME AS \"loginName\",U.USER_NAME AS \"userName\","
				+ "U.GENDER AS \"gender\",U.CRED_TYPE AS \"credType\",U.CRED_NO AS \"credNo\",U.ROLE AS \"role\","
				+ "U.ORGANIZATION AS \"organization\",U.BIRTHDAY AS \"birthday\",U.ZC AS \"zc\",U.ZW AS \"zw\","
				+ "U.DEPT AS \"dept\",U.DEGREES AS \"degrees\",U.MAJOR AS \"major\",U.TELEPHONE AS \"telephone\","
				+ "U.PHONE AS \"phone\",U.ADDRESS AS \"address\",O.FULL_NAME as \"orgName\" from SPORT_USER U LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID WHERE LOGIN_NAME=:loginName";
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
		hibernateSqlQuery.setParameter("loginName", loginName);
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
	public long findTotal(String[] role, String name) {
		String updateHql = "select count(1) from UserDomain where role in (:role) and (loginName like :name or userName like :name)";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameterList("role", role)
				.setParameter("name", "%" + name + "%").uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public UserVo findMangerByOrgId(String orgId, String role) {
		String querySql = "select U.USER_ID AS \"userId\",U.LOGIN_NAME AS \"loginName\",U.USER_NAME AS \"userName\","
				+ "U.GENDER AS \"gender\",U.CRED_TYPE AS \"credType\",U.CRED_NO AS \"credNo\",U.ROLE AS \"role\","
				+ "U.ORGANIZATION AS \"organization\",U.BIRTHDAY AS \"birthday\",U.ZC AS \"zc\",U.ZW AS \"zw\","
				+ "U.DEPT AS \"dept\",U.DEGREES AS \"degrees\",U.MAJOR AS \"major\",U.TELEPHONE AS \"telephone\","
				+ "U.PHONE AS \"phone\",U.ADDRESS AS \"address\",O.FULL_NAME as \"orgName\",O.EMAIL AS \"email\" from SPORT_USER U LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID WHERE ORGANIZATION=:orgId and U.ROLE=:role";
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
		hibernateSqlQuery.addScalar("email");
		hibernateSqlQuery.setParameter("orgId", orgId);
		hibernateSqlQuery.setParameter("role", role);
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(UserVo.class));
		return (UserVo) hibernateSqlQuery.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserVo> findVoByOrgId(String orgId, int start, int limit) {
		StringBuffer querySql = new StringBuffer(
				"select U.USER_ID AS \"userId\",U.LOGIN_NAME AS \"loginName\",U.USER_NAME AS \"userName\","
						+ "U.GENDER AS \"gender\",U.CRED_TYPE AS \"credType\",U.CRED_NO AS \"credNo\",U.ROLE AS \"role\","
						+ "U.ORGANIZATION AS \"organization\",U.BIRTHDAY AS \"birthday\",U.ZC AS \"zc\",U.ZW AS \"zw\","
						+ "U.DEPT AS \"dept\",U.DEGREES AS \"degrees\",U.MAJOR AS \"major\",U.TELEPHONE AS \"telephone\","
						+ "U.PHONE AS \"phone\",U.ADDRESS AS \"address\",O.FULL_NAME as \"orgName\",O.EMAIL AS \"email\", D.NAME AS \"credTypeName\" from SPORT_USER U "
						+ "LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID "
						+ "LEFT JOIN SPORT_DIC D ON D.CODE = U.CRED_TYPE WHERE U.ORGANIZATION=:orgId ");
		SQLQuery hibernateSqlQuery = this.getHibernateSqlQuery(querySql.toString());
		hibernateSqlQuery.addScalar("userId");
		hibernateSqlQuery.addScalar("loginName");
		hibernateSqlQuery.addScalar("userName");
		hibernateSqlQuery.addScalar("gender");
		hibernateSqlQuery.addScalar("credType");
		hibernateSqlQuery.addScalar("credTypeName");
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
		hibernateSqlQuery.addScalar("email");
		hibernateSqlQuery.setParameter("orgId", orgId);
		hibernateSqlQuery.setMaxResults(limit);
		hibernateSqlQuery.setFirstResult(start);
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(UserVo.class));
		return hibernateSqlQuery.list();
	}

	@Override
	public long findTotalByOrgId(String orgId) {
		String updateHql = "select count(1) from UserDomain where organization=:organization";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameter("organization", orgId).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserVo> findVoByWhere(UserQuery query, int start, int limit) {
		StringBuffer querySql = new StringBuffer(
				"select U.USER_ID AS \"userId\",U.LOGIN_NAME AS \"loginName\",U.USER_NAME AS \"userName\","
						+ "U.GENDER AS \"gender\",U.CRED_TYPE AS \"credType\",U.CRED_NO AS \"credNo\",U.ROLE AS \"role\","
						+ "U.ORGANIZATION AS \"organization\",U.BIRTHDAY AS \"birthday\",U.ZC AS \"zc\",U.ZW AS \"zw\","
						+ "U.DEPT AS \"dept\",U.DEGREES AS \"degrees\",U.MAJOR AS \"major\",U.TELEPHONE AS \"telephone\","
						+ "U.PHONE AS \"phone\",U.ADDRESS AS \"address\",O.FULL_NAME as \"orgName\",O.EMAIL AS \"email\", D.NAME AS \"credTypeName\" from SPORT_USER U "
						+ "LEFT JOIN SPORT_ORGANIZATION O ON U.ORGANIZATION=O.ORG_ID "
						+ "LEFT JOIN SPORT_DIC D ON D.CODE = U.CRED_TYPE WHERE 1=1 ");

		Map<String, Object> params = new HashMap<String, Object>();
		if (query != null) {
			if (StringUtils.isNotBlank(query.getOrgId())) {
				querySql.append(" and U.ORGANIZATION =:orgId ");
				params.put("orgId", query.getOrgId());
			}
			if (query.getRole() != null && query.getRole().length != 0) {
				querySql.append(" and U.ROLE in(:role) ");
				params.put("role", query.getRole());
			}

			if (StringUtils.isNotBlank(query.getName())) {
				querySql.append(" and (U.LOGIN_NAME like :name or U.USER_NAME like :name) ");
				params.put("name", "%" + query.getName() + "%");
			}
		}
		SQLQuery hibernateSqlQuery = this.getHibernateSqlQuery(querySql.toString());
		hibernateSqlQuery.addScalar("userId");
		hibernateSqlQuery.addScalar("loginName");
		hibernateSqlQuery.addScalar("userName");
		hibernateSqlQuery.addScalar("gender");
		hibernateSqlQuery.addScalar("credType");
		hibernateSqlQuery.addScalar("credTypeName");
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
		hibernateSqlQuery.addScalar("email");
		this.processQuery(hibernateSqlQuery, params);
		hibernateSqlQuery.setMaxResults(limit);
		hibernateSqlQuery.setFirstResult(start);
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(UserVo.class));
		return hibernateSqlQuery.list();
	}

	private void processQuery(Query hibernateQuery, Map<String, Object> map) {
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			Object value = entry.getValue();
			if (value instanceof Object[] || value instanceof Collection) {
				hibernateQuery.setParameterList(entry.getKey(), (Object[]) value);
			} else {
				hibernateQuery.setParameter(entry.getKey(), value);
			}
		}
	}

	@Override
	public long findTotalByWhere(UserQuery query) {
		if (query == null) {
			return this.findTotal();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("select count(1) from UserDomain where 1=1 ");
		if (StringUtils.isNotBlank(query.getOrgId())) {
			queryHql.append(" and organization =:orgId ");
			params.put("orgId", query.getOrgId());
		}
		if (query.getRole() != null && query.getRole().length != 0) {
			queryHql.append(" and role in(:role) ");
			params.put("role", query.getRole());
		}

		if (StringUtils.isNotBlank(query.getName())) {
			queryHql.append(" and (loginName like :name or userName like :name) ");
			params.put("name", "%" + query.getName() + "%");
		}
		return this.getCountByHQL(queryHql.toString(), params);
	}

	@Override
	public boolean deleteByOrgId(String organization) {
		String deleteHql = "delete from UserDomain where organization=:organization";
		return this.getHibernateQuery(deleteHql).setParameter("organization", organization).executeUpdate() != 0;
	}

}
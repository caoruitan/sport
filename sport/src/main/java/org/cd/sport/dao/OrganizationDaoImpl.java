package org.cd.sport.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.vo.OrgQuery;
import org.cd.sport.vo.OrgVo;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * 组织单位数据接口
 * 
 * @author liuyk
 *
 */
@Repository
public class OrganizationDaoImpl extends BaseDaoImpl<OrganizationDomain> implements OrganizationDao {

	@Override
	public boolean delete(String orgId) {
		String deleteHql = "delete from OrganizationDomain where orgId=:orgId";
		return this.getHibernateQuery(deleteHql).setParameter("orgId", orgId).executeUpdate() != 0;
	}

	@Override
	public boolean delete(String[] orgId) {
		String deleteHql = "delete from OrganizationDomain where orgId in (:orgId)";
		return this.getHibernateQuery(deleteHql).setParameterList("orgId", orgId).executeUpdate() != 0;
	}

	@Override
	public long findTotal() {
		String queryHql = "select count(1) from OrganizationDomain ";
		Long count = (Long) this.getHibernateQuery(queryHql).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public OrganizationDomain findByFullName(String fullName) {
		String deleteHql = "from OrganizationDomain where fullName=:fullName";
		return (OrganizationDomain) this.getHibernateQuery(deleteHql).setParameter("fullName", fullName).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrganizationDomain> find(int start, int limit) {
		String queryHql = "from OrganizationDomain order by createTime desc";
		return (List<OrganizationDomain>) this.getHibernateQuery(queryHql).setMaxResults(limit).setFirstResult(start)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrganizationDomain> findByWhere(OrgQuery query, int start, int limit) {
		if (query == null) {
			return this.find(start, limit);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("from OrganizationDomain where 1=1  ");
		if (!StringUtils.isBlank(query.getStatus())) {
			Integer[] status = Constants.Org.parseStatus(query.getStatus());
			queryHql.append(" and status in (:status) ");
			params.put("status", status);
		}
		if (StringUtils.isNotBlank(query.getFullName())) {
			queryHql.append(
					" and (fullName like :fullName or shortName like :fullName or englishName like :fullName ) ");
			params.put("fullName", "%" + query.getFullName() + "%");
		}
		if (StringUtils.isNotBlank(query.getLegalLeader())) {
			queryHql.append(" and legalLeader like :legalLeader ");
			params.put("legalLeader", "%" + query.getLegalLeader() + "%");
		}
		queryHql.append(" order by createTime desc ");

		Query hibernateQuery = this.getHibernateQuery(queryHql.toString());
		this.processQuery(hibernateQuery, params);
		return hibernateQuery.list();
	}

	private void processQuery(Query hibernateQuery, Map<String, Object> map) {
		if (map != null && !map.isEmpty()) {
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
	}

	@Override
	public long findTotalByWhere(OrgQuery query) {
		if (query == null) {
			return this.findTotal();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("select count(1) from OrganizationDomain where 1=1 ");
		if (!StringUtils.isBlank(query.getStatus())) {
			Integer[] status = Constants.Org.parseStatus(query.getStatus());
			queryHql.append(" and status in (:status) ");
			params.put("status", status);
		}
		if (StringUtils.isNotBlank(query.getFullName())) {
			queryHql.append(" and fullName like :fullName ");
			params.put("fullName", "%" + query.getFullName() + "%");
		}
		if (StringUtils.isNotBlank(query.getLegalLeader())) {
			queryHql.append(" and legalLeader like :legalLeader ");
			params.put("legalLeader", "%" + query.getLegalLeader() + "%");
		}
		if (query.getRole() != null) {
			queryHql.append(" and role  =:role ");
			params.put("role", query.getRole());
		}
		return this.getCountByHQL(queryHql.toString(), params);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrganizationDomain> findbyRole(int role) {
		String queryHql = "from OrganizationDomain where role=:role order by createTime desc";
		return (List<OrganizationDomain>) this.getHibernateQuery(queryHql).setParameter("role", role).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrganizationDomain> findbyRole(int role, int start, int limit) {
		String queryHql = "from OrganizationDomain where role=:role order by createTime desc";
		return (List<OrganizationDomain>) this.getHibernateQuery(queryHql).setParameter("role", role)
				.setMaxResults(limit).setFirstResult(start).list();
	}

	@Override
	public long findTotalbyRole(int role) {
		String queryHql = "select count(1) from OrganizationDomain  where role=:role";
		Long count = (Long) this.getHibernateQuery(queryHql).setParameter("role", role).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrgVo> findVoByWhere(OrgQuery query, int start, int limit) {
		StringBuffer querySql = new StringBuffer(
				"SELECT ORG_ID AS \"orgId\",FULL_NAME AS \"fullName\",LEGAL_LEADER AS \"legalLeader\",D.name AS \"region\",TELPHONE AS \"telphone\",STATUS AS \"status\" FROM SPORT_ORGANIZATION O "
						+ " LEFT JOIN SPORT_DIC D ON D.CODE= O.REGION WHERE 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isBlank(query.getStatus())) {
			Integer[] status = Constants.Org.parseStatus(query.getStatus());
			querySql.append(" and O.STATUS in (:status) ");
			params.put("status", status);
		}
		if (StringUtils.isNotBlank(query.getFullName())) {
			querySql.append(
					" and (FULL_NAME like :fullName or SHORT_NAME like :fullName or ENGLISH_NAME like :fullName ) ");
			params.put("fullName", "%" + query.getFullName() + "%");
		}
		if (StringUtils.isNotBlank(query.getLegalLeader())) {
			querySql.append(" and LEGAL_LEADER like :legalLeader ");
			params.put("legalLeader", "%" + query.getLegalLeader() + "%");
		}

		if (query.getRole() != null) {
			querySql.append(" and ROLE  =:role ");
			params.put("role", query.getRole());
		}
		querySql.append(" order by CREATE_TIME desc ");

		SQLQuery hibernateSqlQuery = this.getHibernateSqlQuery(querySql.toString());
		hibernateSqlQuery.addScalar("orgId");
		hibernateSqlQuery.addScalar("fullName");
		hibernateSqlQuery.addScalar("legalLeader");
		hibernateSqlQuery.addScalar("region");
		hibernateSqlQuery.addScalar("telphone");
		hibernateSqlQuery.addScalar("status");
		this.processQuery(hibernateSqlQuery, params);
		hibernateSqlQuery.setMaxResults(limit);
		hibernateSqlQuery.setFirstResult(start);
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(OrgVo.class));
		return hibernateSqlQuery.list();
	}
}

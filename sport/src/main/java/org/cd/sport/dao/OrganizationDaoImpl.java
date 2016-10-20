package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
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
}

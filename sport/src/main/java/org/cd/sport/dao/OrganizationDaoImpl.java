package org.cd.sport.dao;

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
		// TODO Auto-generated method stub
		return 0;
	}
}

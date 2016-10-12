package org.cd.sport.dao;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.hibernate.IBaseDao;
import org.cd.sport.view.OrganizationView;

/**
 * 组织单位数据接口
 * 
 * @author liuyk
 *
 */
public interface OrganizationDao extends IBaseDao {
	
	public boolean create(OrganizationView organization);

	public boolean update(OrganizationView organization);

	public boolean delete(String orgId);

	public boolean delete(String[] orgId);

	public OrganizationDomain getById(String orgId);

	public long getTotal();
}

package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.hibernate.IBaseDao;
import org.cd.sport.vo.OrgQuery;

/**
 * 组织单位数据接口
 * 
 * @author liuyk
 *
 */
public interface OrganizationDao extends IBaseDao {

	public boolean delete(String orgId);

	public boolean delete(String[] orgId);

	public OrganizationDomain findByFullName(String fullName);

	public List<OrganizationDomain> find(int start, int limit);

	public List<OrganizationDomain> findByWhere(OrgQuery query, int start, int limit);

	public long findTotal();

	public long findTotalByWhere(OrgQuery query);

}

package org.cd.sport.dao;

import org.cd.sport.hibernate.IBaseDao;

/**
 * 组织单位数据接口
 * 
 * @author liuyk
 *
 */
public interface OrganizationDao extends IBaseDao {

	public boolean delete(String orgId);

	public boolean delete(String[] orgId);

	public long findTotal();
}

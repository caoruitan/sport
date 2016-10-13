package org.cd.sport.service;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.view.OrganizationView;

/**
 * 组织单位业务层
 * 
 * @author liuyk
 *
 */
public interface OrganizationService {

	public boolean create(OrganizationView organization);

	public boolean update(OrganizationView organization);

	public boolean delete(String orgId);

	public boolean delete(String[] orgId);

	public OrganizationDomain getById(String orgId);

	public OrganizationDomain getByName(String name);

	public long getTotal();
}

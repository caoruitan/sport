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

	public OrganizationDomain create(OrganizationView organization);

	public OrganizationDomain update(OrganizationView organization);

	public boolean delete(String orgId);

	public boolean pass(String orgId);

	public boolean unpass(String orgId);

	public OrganizationDomain getById(String orgId);

	public OrganizationDomain getByFullName(String name);

	public long getTotal();
}

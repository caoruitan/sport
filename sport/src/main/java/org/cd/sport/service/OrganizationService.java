package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.view.OrganizationView;
import org.cd.sport.vo.OrgQuery;
import org.cd.sport.vo.OrgVo;

/**
 * 组织单位业务层
 * 
 * @author liuyk
 *
 */
public interface OrganizationService {

	public OrganizationDomain create(OrganizationView organization);

	public OrganizationDomain update(OrganizationView organization);

	public boolean writeWord(String orgId, String basePath);

	public boolean delete(String orgId);

	public boolean delete(String[] orgId);

	public boolean pass(String orgId);

	public boolean unpass(String orgId, String reason);

	public OrganizationDomain getById(String orgId);

	public OrganizationView getViewById(String orgId);

	public OrganizationDomain getByFullName(String name);

	public List<OrgVo> getByWhere(OrgQuery query, int start, int limit);

	public List<OrgVo> get(int start, int limit);

	public List<OrgVo> getbyRole(int role);

	public List<OrgVo> getbyRole(int role, int start, int limit);

	public long getTotal();

	public long getTotalByWhere(OrgQuery query);

	public long getTotalbyRole(int role);
}

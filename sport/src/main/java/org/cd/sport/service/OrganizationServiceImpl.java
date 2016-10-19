package org.cd.sport.service;

import org.cd.sport.dao.OrganizationDao;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.NameIsExistException;
import org.cd.sport.support.OrganizationSupport;
import org.cd.sport.view.OrganizationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 组织单位业务层
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl extends OrganizationSupport implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validName(String fullName) {
		OrganizationDomain user = this.getByFullName(fullName);
		if (user != null) {
			throw new NameIsExistException("单位名称已经存在。");
		}
	}

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validName(String fullName, OrganizationDomain org) {
		OrganizationDomain _org = this.getByFullName(fullName);
		if (_org != null) {
			if (!_org.getOrgId().equals(org.getOrgId())) {
				throw new NameIsExistException("单位名称已经存在。");
			}
		}
	}

	/**
	 * 新增单位
	 */
	@Transactional
	public boolean create(OrganizationView organization) {
		OrganizationDomain process = this.process(organization);
		this.validName(process.getFullName());
		this.organizationDao.save(process);
		return true;
	}

	/**
	 * 修改单位信息
	 */
	@Transactional
	public boolean update(OrganizationView organization) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 通过id,获得单位
	 */
	public OrganizationDomain getById(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过id删除
	 */
	public boolean delete(String orgId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 通过多个id删除多个单位
	 */
	public boolean delete(String[] orgId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 查询所有单位
	 */
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrganizationDomain getByFullName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
package org.cd.sport.service;

import org.cd.sport.dao.OrganizationDao;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.view.OrganizationView;

/**
 * 用户业务层
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	/**
	 * 新增单位
	 */
	public boolean create(OrganizationView organization) {
		 this.organizationDao.create(organization);
		return true;
	}

	/**
	 * 修改单位信息
	 */
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
}
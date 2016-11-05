package org.cd.sport.service;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.OrganizationDao;
import org.cd.sport.dao.UserDao;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ForbiddenExcetion;
import org.cd.sport.exception.NameIsExistException;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.support.OrganizationSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.view.OrganizationView;
import org.cd.sport.vo.OrgQuery;
import org.cd.sport.vo.OrgVo;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.BeanUtils;
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

	@Autowired
	private UserDao userDao;

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
	public OrganizationDomain create(OrganizationView organization) {
		OrganizationDomain process = this.process(organization);
		this.validName(process.getFullName());
		process.setStatus(Constants.Org.wait_verify);
		process.setCreateTime(new Timestamp(System.currentTimeMillis()));
		this.organizationDao.save(process);
		return process;
	}

	/**
	 * 修改单位信息
	 */
	@Transactional
	public OrganizationDomain update(OrganizationView organization) {
		this.validateUpdate(organization);
		OrganizationDomain org = this.getById(organization.getOrgId());
		this.validName(organization.getFullName(), org);
		BeanUtils.copyProperties(organization, org);
		this.organizationDao.update(org);
		return org;
	}

	@Override
	@Transactional
	public boolean pass(String orgId) {
		OrganizationDomain org = this.getById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("单位不存在");
		}
		if (org.getStatus() == Constants.Org.pass_verify) {
			throw new ParameterIsWrongException("单位状态不对");
		}
		org.setStatus(Constants.Org.pass_verify);
		this.organizationDao.update(org);
		return true;
	}

	@Override
	@Transactional
	public boolean unpass(String orgId, String reason) {
		OrganizationDomain org = this.getById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("单位不存在");
		}

		if (StringUtils.isBlank(reason)) {
			throw new ParameterIsWrongException("审核不同原因不能为空");
		}
		if (org.getStatus() != Constants.Org.wait_verify) {
			throw new ParameterIsWrongException("单位状态不对");
		}
		org.setStatus(Constants.Org.unpass_verify);
		org.setReason(reason);
		this.organizationDao.update(org);
		return true;
	}

	/**
	 * 通过id,获得单位
	 */
	public OrganizationDomain getById(String orgId) {
		if (StringUtils.isBlank(orgId)) {
			return null;
		}
		return this.organizationDao.getEntityById(OrganizationDomain.class, orgId);
	}

	@Override
	public OrganizationView getViewById(String orgId) {
		if (StringUtils.isBlank(orgId)) {
			return null;
		}
		OrganizationDomain org = this.organizationDao.getEntityById(OrganizationDomain.class, orgId);
		OrganizationView result = this.result(OrganizationView.class, org);
		String code = org.getCode();
		if (!StringUtils.isBlank(code)) {
			String[] split = code.split("-");
			result.setCodePre(split[0]);
			result.setCodeSufix(split[1]);
		}
		return result;
	}

	/**
	 * 通过id删除
	 */
	@Transactional
	public boolean delete(String orgId) {
		if (StringUtils.isBlank(orgId)) {
			return false;
		}
		OrganizationDomain entity = this.organizationDao.getEntityById(OrganizationDomain.class, orgId);
		if (entity == null) {
			return false;
		}
		// 判断是否有权限删除
		UserVo user = AuthenticationUtils.getUser();
		if (user == null) {
			throw new ForbiddenExcetion("您没有权限删除组织");
		}

		if (entity.getRole() == Constants.Org.KJS_ROLE) {
			throw new ForbiddenExcetion("您没有权限删除组织");
		}

		if (Constants.Role.ROLE_KJS_ADMIN.equals(user.getRole())) {
			this.organizationDao.delete(orgId);
			this.userDao.deleteByOrgId(orgId);
			return true;
		} else if (Constants.Role.ROLE_ORG_ADMIN.equals(user.getRole())) {
			if (entity.getRole() == Constants.Org.ORG_ROLE) {
				this.organizationDao.delete(orgId);
				this.userDao.deleteByOrgId(orgId);
				return true;
			}
		} else if (Constants.Role.ROLE_SB_ADMIN.equals(user.getRole())) {
			if (entity.getRole() == Constants.Org.SB_ROLE) {
				this.organizationDao.delete(orgId);
				this.userDao.deleteByOrgId(orgId);
				return true;
			}
		}
		throw new ForbiddenExcetion("您没有权限删除组织");
	}

	/**
	 * 通过多个id删除多个单位
	 */
	public boolean delete(String[] orgId) {
		if (orgId == null || orgId.length == 0) {
			return false;
		}
		boolean result = true;
		for (int i = 0; i < orgId.length; i++) {
			for (String id : orgId) {
				result &= this.delete(id);
			}
		}
		return result;
	}

	/**
	 * 查询所有单位
	 */
	public long getTotal() {
		return this.organizationDao.findTotal();
	}

	@Override
	public OrganizationDomain getByFullName(String fullName) {
		if (StringUtils.isBlank(fullName)) {
			return null;
		}
		return this.organizationDao.findByFullName(fullName);
	}

	@Override
	public List<OrgVo> getByWhere(OrgQuery query, int start, int limit) {
		List<OrgVo> orgVos = this.organizationDao.findVoByWhere(query, start, limit);
		return this.processVo(orgVos);
	}

	@Override
	public List<OrgVo> get(int start, int limit) {
		List<OrganizationDomain> orgs = this.organizationDao.find(start, limit);
		return this.process(orgs);
	}

	@Override
	public long getTotalByWhere(OrgQuery query) {
		return this.organizationDao.findTotalByWhere(query);
	}

	@Override
	public List<OrgVo> getbyRole(int role) {
		List<OrganizationDomain> orgs = this.organizationDao.findbyRole(role);
		return this.process(orgs);
	}

	@Override
	public List<OrgVo> getbyRole(int role, int start, int limit) {
		List<OrganizationDomain> orgs = this.organizationDao.findbyRole(role, start, limit);
		return this.process(orgs);
	}

	@Override
	public long getTotalbyRole(int role) {
		return this.organizationDao.findTotalbyRole(role);
	}
}
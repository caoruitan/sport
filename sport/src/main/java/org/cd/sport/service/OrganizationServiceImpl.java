package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.OrganizationDao;
import org.cd.sport.dao.UserDao;
import org.cd.sport.domain.Dic;
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

	@Autowired
	private DicService dicService;

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

	@Override
	public boolean writeWord(String orgId, String basePath) {
		OrganizationDomain org = this.organizationDao.getEntityById(OrganizationDomain.class, orgId);
		if (org == null) {
			return false;
		}
		UserVo user = this.userDao.findMangerByOrgId(orgId, Constants.Role.ROLE_SB_ADMIN);
		FileInputStream in = null;
		OutputStream os = null;
		try {
			in = new FileInputStream(new File(basePath + Constants.Org.TEMPLATE_DOC));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			range.replaceText("${fullName}", org.getFullName());
			range.replaceText("${englishName}", org.getEnglishName());
			range.replaceText("${shortName}", org.getShortName());
			range.replaceText("${address}", org.getAddress());
			range.replaceText("${homepage}", org.getHomepage());
			range.replaceText("${legalLeader}", org.getLegalLeader());

			Dic region = this.dicService.getByCode(org.getRegion());
			range.replaceText("${region}", region == null ? "" : region.getName());

			Dic quality = this.dicService.getByCode(org.getQuality());
			range.replaceText("${quality}", quality == null ? "" : quality.getName());
			range.replaceText("${code}", org.getCode());
			range.replaceText("${telphone}", org.getTelphone());
			range.replaceText("${fax}", org.getFax());
			range.replaceText("${email}", org.getEmail());
			range.replaceText("${post}", org.getPost());
			range.replaceText("${managerName", org.getManagerName());
			range.replaceText("${managerPhone}", org.getManagerPhone());
			range.replaceText("${managerfax}", org.getManagerfax());
			range.replaceText("${managerEmail}", org.getManagerEmail());
			range.replaceText("${loginName}", user.getLoginName());
			range.replaceText("${userName}", user.getUserName());
			range.replaceText("${gender}", Constants.User.getGenderName(user.getGender()));
			Dic credType = this.dicService.getByCode(user.getCredType());
			range.replaceText("${credType}", credType == null ? "" : credType.getName());
			range.replaceText("${credNo}", user.getCredNo());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String bir = "";
			Date birthday = user.getBirthday();
			if (birthday != null) {
				bir = sf.format(birthday);
			}
			range.replaceText("${birthday}", bir);
			range.replaceText("${email}", user.getEmail());

			Dic zc = this.dicService.getByCode(user.getZc());
			range.replaceText("${zc}", zc == null ? "" : zc.getName());
			Dic zw = this.dicService.getByCode(user.getZw());
			range.replaceText("${zw}", zw == null ? "" : zw.getName());
			range.replaceText("${organization}", org.getFullName());
			Dic degress = this.dicService.getByCode(user.getDegrees());
			range.replaceText("${degrees}", degress == null ? "" : degress.getName());

			Dic major = this.dicService.getByCode(user.getMajor());
			range.replaceText("${major}", major == null ? "" : major.getName());
			range.replaceText("${telephone}", user.getTelephone());
			range.replaceText("${phone}", user.getPhone());
			range.replaceText("${user_address}", user.getAddress());
			range.replaceText("${createTime}", sf.format(org.getCreateTime()));

			os = new FileOutputStream(basePath + "/doc/" + user.getLoginName() + "register.doc");
			hdt.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
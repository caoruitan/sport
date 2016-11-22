package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.OrganizationService;
import org.cd.sport.service.UserService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.vo.OrgQuery;
import org.cd.sport.vo.OrgVo;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户相关
 * 
 * @author liuyk
 */
public class BaseOrgAction {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UserService userService;

	@Autowired
	private DicService dicService;

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void queryOrgDatas(OrgQuery query, HttpServletRequest request, HttpServletResponse response) {
		String fullName = request.getParameter("fullName");
		String legalLeader = request.getParameter("legalLeader");
		String status = request.getParameter("status");
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		query.setFullName(fullName);
		query.setLegalLeader(legalLeader);
		query.setStatus(status);
		List<OrgVo> datas = this.organizationService.getByWhere(query, (start - 1) * Constants.Common.PAGE_SIZE,
				Constants.Common.PAGE_SIZE);
		long total = this.organizationService.getTotalByWhere(query);
		PageModel<OrgVo> model = new PageModel<OrgVo>();
		model.setPage(start);
		model.setRecords(total);
		model.setRows(datas);
		model.setTotal((long) Math.ceil(total * 0.1 / Constants.Common.PAGE_SIZE));
		PageWrite.writeTOPage(response, GsonUtils.toJson(model));
	}

	public String orgManagerUser(HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		OrganizationDomain org = this.organizationService.getById(orgId);
		request.setAttribute("org", org);
		return "sborg/user";
	}

	public void orgManagerUserDatas(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		List<UserVo> datas = this.userService.getVoByOrgId(orgId, (start - 1) * Constants.Common.PAGE_SIZE,
				Constants.Common.PAGE_SIZE);
		long total = this.userService.getTotalByOrgId(orgId);
		PageModel<UserVo> model = new PageModel<UserVo>();
		model.setPage(start);
		model.setRecords(total);
		model.setRows(datas);
		model.setTotal((long) Math.ceil(total * 0.1 / Constants.Common.PAGE_SIZE));
		PageWrite.writeTOPage(response, GsonUtils.toJson(model));
	}

	public String orgDetail(HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		OrganizationDomain org = this.organizationService.getById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("单位不存在");
		}
		Dic dic = this.dicService.getByCode(org.getQuality());
		if (dic != null) {
			org.setQuality(dic.getName());
		}
		request.setAttribute("org", org);
		return "sborg/detail";
	}

	public String verfiyView(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		OrganizationDomain org = this.organizationService.getById(orgId);
		if (org == null || org.getStatus() != Constants.Org.wait_verify) {
			throw new EntityNotFoundException("单位不存在");
		}
		request.setAttribute("org", org);
		return "sborg/verify";
	}

	public void verifyPass(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		if (StringUtils.isBlank(orgId)) {
			throw new ParameterIsWrongException("注册单位id为空");
		}
		boolean pass = this.organizationService.pass(orgId);
		PageWrite.writeTOPage(response, pass);
	}

	public void verifyUnpass(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		String msg = request.getParameter("msg");
		if (StringUtils.isBlank(orgId)) {
			throw new ParameterIsWrongException("注册单位id为空");
		}
		boolean unpass = this.organizationService.unpass(orgId, msg);
		PageWrite.writeTOPage(response, unpass);
	}
}

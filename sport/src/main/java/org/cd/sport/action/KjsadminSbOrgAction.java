package org.cd.sport.action;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户相关
 * 
 * @author liuyk
 */
@Controller
@RequestMapping("sborg")
public class KjsadminSbOrgAction {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/kjsadmin/list", method = RequestMethod.GET)
	public String gotoOrgList(OrgQuery org, HttpServletRequest request) {
		Map<Integer, String> status = Constants.Org.getStatus();
		request.setAttribute("status", status);
		return "sborg/list";
	}

	@RequestMapping(value = "/kjsadmin/datas", method = RequestMethod.GET)
	public void gotoOrgDatas(HttpServletRequest request, HttpServletResponse response) {
		String fullName = request.getParameter("fullName");
		String legalLeader = request.getParameter("legalLeader");
		String status = request.getParameter("status");
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		OrgQuery query = new OrgQuery();
		query.setFullName(fullName);
		query.setLegalLeader(legalLeader);
		query.setStatus(status);
		query.setRole(Constants.Org.SB_ROLE);
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

	@RequestMapping(value = "/kjsadmin/user.htm", method = RequestMethod.GET)
	public String orgManagerUser(HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		OrganizationDomain org = this.organizationService.getById(orgId);
		request.setAttribute("org", org);
		return "sborg/user";
	}

	@RequestMapping(value = "/kjsadmin/userDatas.action", method = RequestMethod.GET)
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

	@RequestMapping(value = "/kjsadmin/detail.htm", method = RequestMethod.GET)
	public String orgDetail(OrgQuery query, HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		OrganizationDomain org = this.organizationService.getById(orgId);
		request.setAttribute("org", org);
		return "sborg/detail";
	}

	@RequestMapping(value = "/kjsadmin/verify.htm", method = RequestMethod.GET)
	public String verfiyView(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		OrganizationDomain org = this.organizationService.getById(orgId);
		if (org == null || org.getStatus() != Constants.Org.wait_verify) {
			throw new EntityNotFoundException("单位不存在");
		}
		request.setAttribute("org", org);
		return "sborg/verify";
	}

	@RequestMapping(value = "/kjsadmin/pass.action", method = RequestMethod.POST)
	public void verifyPass(HttpServletRequest request, HttpServletResponse response) {
		String orgId = request.getParameter("orgId");
		if (StringUtils.isBlank(orgId)) {
			throw new ParameterIsWrongException("注册单位id为空");
		}
		boolean pass = this.organizationService.pass(orgId);
		PageWrite.writeTOPage(response, pass);
	}

	@RequestMapping(value = "/kjsadmin/unpass.action", method = RequestMethod.POST)
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

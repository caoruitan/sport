package org.cd.sport.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.constant.Constants;
import org.cd.sport.vo.OrgQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 注册单位管理
 * 
 * @author liuyk
 */
@Controller
@RequestMapping("sborg")
public class KjsadminSbOrgAction extends BaseOrgAction {

	@RequestMapping(value = "/kjsadmin/list", method = RequestMethod.GET)
	public String gotoOrgList(HttpServletRequest request) {
		Map<Integer, String> status = Constants.Org.getStatus();
		request.setAttribute("status", status);
		return "sborg/list";
	}

	@RequestMapping(value = "/kjsadmin/datas", method = RequestMethod.GET)
	public void queryOrgDatas(HttpServletRequest request, HttpServletResponse response) {
		OrgQuery query = new OrgQuery();
		query.setRole(Constants.Org.SB_ROLE);
		super.queryOrgDatas(query, request, response);
	}

	@RequestMapping(value = "/kjsadmin/user.htm", method = RequestMethod.GET)
	public String orgManagerUser(HttpServletRequest request) {
		super.orgManagerUser(request);
		return "sborg/user";
	}

	@RequestMapping(value = "/kjsadmin/userDatas.action", method = RequestMethod.GET)
	public void orgManagerUserDatas(HttpServletRequest request, HttpServletResponse response) {
		super.orgManagerUserDatas(request, response);
	}

	@RequestMapping(value = "/kjsadmin/detail.htm", method = RequestMethod.GET)
	public String orgDetail(HttpServletRequest request) {
		super.orgDetail(request);
		return "sborg/detail";
	}

	@RequestMapping(value = "/kjsadmin/verify.htm", method = RequestMethod.GET)
	public String verfiyView(HttpServletRequest request, HttpServletResponse response) {
		return super.verfiyView(request, response);
	}

	@RequestMapping(value = "/kjsadmin/pass.action", method = RequestMethod.POST)
	public void verifyPass(HttpServletRequest request, HttpServletResponse response) {
		super.verifyPass(request, response);
	}

	@RequestMapping(value = "/kjsadmin/unpass.action", method = RequestMethod.POST)
	public void verifyUnpass(HttpServletRequest request, HttpServletResponse response) {
		super.verifyUnpass(request, response);
	}
}

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
@RequestMapping("org")
public class KjsadminOrgAction extends BaseOrgAction {

	@RequestMapping(value = "/kjsadmin/list", method = RequestMethod.GET)
	public String gotoOrgList(HttpServletRequest request) {
		Map<Integer, String> status = Constants.Org.getStatus();
		request.setAttribute("status", status);
		return "org/list";
	}

	@RequestMapping(value = "/kjsadmin/datas", method = RequestMethod.GET)
	public void queryOrgDatas(HttpServletRequest request, HttpServletResponse response) {
		OrgQuery query = new OrgQuery();
		query.setRole(Constants.Org.ORG_ROLE);
		super.queryOrgDatas(query, request, response);
	}

	@RequestMapping(value = "/kjsadmin/user.htm", method = RequestMethod.GET)
	public String orgManagerUser(HttpServletRequest request) {
		return super.orgManagerUser(request);
	}

	@RequestMapping(value = "/kjsadmin/userDatas.action", method = RequestMethod.GET)
	public void orgManagerUserDatas(HttpServletRequest request, HttpServletResponse response) {
		super.orgManagerUserDatas(request, response);
	}

	@RequestMapping(value = "/kjsadmin/detail.htm", method = RequestMethod.GET)
	public String orgDetail(HttpServletRequest request) {
		return super.orgDetail(request);
	}
}
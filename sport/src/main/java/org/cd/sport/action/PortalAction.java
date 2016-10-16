package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("portal")
public class PortalAction {

	@RequestMapping("kjsadmin/index.htm")
	public String gotoIndex(HttpServletRequest request) {
		request.setAttribute("user_type", "kjsadmin");
		return "portal/kjsadmin";
	}

	@RequestMapping("sbadmin/index.htm")
	public String sbadminIndex(HttpServletRequest request) {
		return "portal/sbadmin";
	}

	@RequestMapping("orgadmin/index.htm")
	public String orgadminIndex(HttpServletRequest request) {
		return "portal/orgadmin";
	}

	@RequestMapping("sboper/index.htm")
	public String sboperIndex(HttpServletRequest request) {
		return "portal/sboper";
	}

}

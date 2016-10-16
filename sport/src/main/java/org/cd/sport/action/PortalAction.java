package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.utils.AuthenticationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("portal")
public class PortalAction {

	@RequestMapping("kjsadmin/index.htm")
	public String gotoIndex(HttpServletRequest request) {
		return "portal/kjsadmin";
	}

	@RequestMapping("sboper/index.htm")
	public String sboperIndex(HttpServletRequest request) {
		return "portal/sboper";
	}

}

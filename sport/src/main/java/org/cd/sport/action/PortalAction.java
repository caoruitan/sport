package org.cd.sport.action;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.utils.AuthenticationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("portal")
public class PortalAction {

	@RequestMapping("kjsadmin/index.htm")
	public String gotoIndex(){
		UserDomain user = AuthenticationUtils.getUser();
		return "portal/kjs";
	}
	
}

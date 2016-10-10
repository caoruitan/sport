package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.domain.UserDomain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestAction {

	@RequestMapping("index")
	public String toLogin(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping("admin/index")
	public String Admin(HttpServletRequest request) {
		return "admin";
	}

	@RequestMapping("user/index")
	public String User(HttpServletRequest request) {
		return "user";
	}

	@RequestMapping("doLogin")
	public String doLogin(UserDomain user, HttpServletRequest request) {
//		if (user.getLoginName().equals("lyk")) {
//			return this.User(request);
//		}
		return this.Admin(request);
	}
}

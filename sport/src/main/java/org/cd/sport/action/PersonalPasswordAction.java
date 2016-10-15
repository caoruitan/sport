package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.utils.AuthenticationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户个人密码修改控制层
 * @author liuyk
 */
@Controller
@RequestMapping("password")
public class PersonalPasswordAction extends ExceptionWrapper {

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String gotoUpdatePasswordView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("user", user);
		return "password_update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updatePassword(HttpServletRequest request) {
	}
}

package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.service.UserService;
import org.cd.sport.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户相关
 * 
 * @author liuyk
 *
 */
@Controller
public class UserAction extends ExceptionWrapper {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/password/update", method = RequestMethod.GET)
	public String gotoUpdatePasswordView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("user", user);
		return "password_update";
	}
	
	@RequestMapping(value = "/password/update", method = RequestMethod.POST)
	public void updatePassword(HttpServletRequest request) {
	}
	
	@RequestMapping(value = "/password/reset", method = RequestMethod.GET)
	public String gotoResetPasswordView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("user", user);
		return "password_reset";
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.POST)
	public void resetPassword(HttpServletRequest request) {
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public String gotoCreateUserView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("user", user);
		return "user_create";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public void createUser(HttpServletRequest request) {

	}

	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public String gotoUpdateUserView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("user", user);
		return "user_update";
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request) {

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String gotoUserList(HttpServletRequest request) {
		return "user_list";
	}
	
	@RequestMapping(value = "/user/datas", method = RequestMethod.GET)
	public void getUserDatas(HttpServletRequest request) {
		this.userService.get(0, 10);
	}
}

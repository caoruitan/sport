package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.view.UserView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户相关
 * 
 * @author liuyk
 */
@Controller
@RequestMapping("user")
public class OrgAdminUserAction extends BaseUserAction {

	@RequestMapping(value = "/orgadmin/resetpassword.htm", method = RequestMethod.GET)
	public String gotoResetPasswordView(HttpServletRequest request) {
		request.setAttribute("user_type", "orgadmin");
		return super.gotoResetPasswordView(request);
	}

	@RequestMapping(value = "/orgadmin/resetpassword.action", method = RequestMethod.POST)
	public void resetPassword(String loginName, HttpServletRequest request, HttpServletResponse response) {
		super.resetPassword(loginName, request, response);
	}

	@RequestMapping(value = "/orgadmin/create.htm", method = RequestMethod.GET)
	public String gotoCreateUserView(HttpServletRequest request) {
		request.setAttribute("user_type", "orgadmin");
		return super.gotoCreateUserView(request);
	}

	@RequestMapping(value = "/orgadmin/create.action", method = RequestMethod.POST)
	public void createUser(UserView user, HttpServletRequest request, HttpServletResponse response) {
		super.createUser(user, request, response);
	}

	@RequestMapping(value = "/orgadmin/update.htm", method = RequestMethod.GET)
	public String gotoUpdateUserView(String userId, HttpServletRequest request) {
		request.setAttribute("user_type", "orgadmin");
		return super.gotoUpdateUserView(userId, request);
	}

	@RequestMapping(value = "/orgadmin/update.action", method = RequestMethod.POST)
	public void updateUser(UserView user, HttpServletRequest request, HttpServletResponse response) {
		super.updateUser(user, request, response);
	}

	@RequestMapping(value = "/orgadmin/delete.action", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		super.deleteUser(request, response);
	}

	@RequestMapping(value = "/orgadmin/list", method = RequestMethod.GET)
	public String gotoUserList(HttpServletRequest request) {
		request.setAttribute("user_type", "orgadmin");
		return super.gotoUserList(request);
	}

	@RequestMapping(value = "/orgadmin/datas.action", method = RequestMethod.GET)
	public void getUserDatas(HttpServletRequest request, HttpServletResponse response) {
		super.getUserDatas(request, response);
	}
}

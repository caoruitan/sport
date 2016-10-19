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
public class KjsadminUserAction extends BaseUserAction {

	@RequestMapping(value = "/kjsadmin/resetpassword.htm", method = RequestMethod.GET)
	public String gotoResetPasswordView(HttpServletRequest request) {
		return super.gotoResetPasswordView(request);
	}

	@RequestMapping(value = "/kjsadmin/resetpassword.action", method = RequestMethod.POST)
	public void resetPassword(String loginName, HttpServletRequest request, HttpServletResponse response) {
		super.resetPassword(loginName, request, response);
	}

	@RequestMapping(value = "/kjsadmin/create.htm", method = RequestMethod.GET)
	public String gotoCreateUserView(HttpServletRequest request) {
		return super.gotoCreateUserView(request);
	}

	@RequestMapping(value = "/kjsadmin/create.action", method = RequestMethod.POST)
	public void createUser(UserView user, HttpServletRequest request, HttpServletResponse response) {
		 super.createUser(user, request, response);
	}

	@RequestMapping(value = "/kjsadmin/update.htm", method = RequestMethod.GET)
	public String gotoUpdateUserView(String userId, HttpServletRequest request) {
		return super.gotoUpdateUserView(userId, request);
	}

	@RequestMapping(value = "/kjsadmin/update.action", method = RequestMethod.POST)
	public void updateUser(UserView user, HttpServletRequest request, HttpServletResponse response) {
		super.updateUser(user, request, response);
	}

	@RequestMapping(value = "/kjsadmin/delete.action", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		super.deleteUser(request, response);
	}

	@RequestMapping(value = "/kjsadmin/list", method = RequestMethod.GET)
	public String gotoUserList(HttpServletRequest request) {
		return super.gotoUserList(request);
	}

	@RequestMapping(value = "/kjsadmin/datas.action", method = RequestMethod.GET)
	public void getUserDatas(HttpServletRequest request, HttpServletResponse response) {
		super.getUserDatas(request, response);
	}
}

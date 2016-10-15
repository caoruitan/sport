package org.cd.sport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.exception.ForbiddenExcetion;
import org.cd.sport.exception.SportException;
import org.cd.sport.service.UserService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.RSAGenerator;
import org.cd.sport.utils.UUIDUtil;
import org.cd.sport.view.UserView;
import org.cd.sport.vo.KV;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

/**
 * 用户相关
 * @author liuyk
 */
@Controller
@RequestMapping("user")
public class UserAction extends ExceptionWrapper {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/kjsadmin/resetpassword.htm", method = RequestMethod.GET)
	public String gotoResetPasswordView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("default_password", Constants.User.DEFAULT_PASSWORD);
		request.setAttribute("user_type", "kjsadmin");
		return "user/resetpassword";
	}

	@RequestMapping(value = "/kjs/resetpassword.action", method = RequestMethod.POST)
	public void resetPassword(HttpServletRequest request) {
	}

	@RequestMapping(value = "/kjsadmin/create.htm", method = RequestMethod.GET)
	public String gotoCreateUserView(HttpServletRequest request) {
		// 判断当前用户的角色,是否有创建用户的权限
		UserDomain userDomain = AuthenticationUtils.getUser();
		List<KV> roles = Constants.Role.getRoles(userDomain.getRole());
		request.setAttribute("roles", roles);
		request.setAttribute("userDomain", userDomain);
		// 初始化公钥
		RSAGenerator generator = new RSAGenerator();
		String pubKey = generator.generateBase64PublicKey();
		request.setAttribute("pubKey", pubKey);
		String guid = UUIDUtil.getGuid();
		request.setAttribute("uuid", guid);
		// 缓存 rsa对象
		request.getSession().setAttribute(Constants.User.RSA_KEY, generator);
		request.getSession().setAttribute(Constants.User.UUID_KEY, guid);
		request.setAttribute("user_type", "kjsadmin");
		return "user/create";
	}

	@RequestMapping(value = "/kjsadmin/create.action", method = RequestMethod.POST)
	public void createUser(UserView user, HttpServletRequest request, HttpServletResponse response) {
		String uuid = request.getParameter("uuid");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		JsonObject json = new JsonObject();
		RSAGenerator generator = (RSAGenerator) session.getAttribute(Constants.User.RSA_KEY);
		if (generator == null || StringUtils.isBlank(uuid)
				|| !uuid.equals(session.getAttribute(Constants.User.UUID_KEY))) {
			json.addProperty("success", false);
			json.addProperty("msg", "非法操作");
		} else {
			try {
				password = generator.decryptBase64(password);
				user.setPassword(password);
				boolean result = this.userService.create(user);
				json.addProperty("success", result);
			} catch (SportException e) {
				json.addProperty("success", false);
				json.addProperty("msg", "非法操作");
			}
		}
		PageWrite.writeTOPage(response, json);
	}

	@RequestMapping(value = "/kjsadmin/update.htm", method = RequestMethod.GET)
	public String gotoUpdateUserView(HttpServletRequest request) {
		UserDomain user = AuthenticationUtils.getUser();
		request.setAttribute("user", user);
		request.setAttribute("user_type", "kjsadmin");
		return "user/update";
	}

	@RequestMapping(value = "/kjsadmin/update.action", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request) {

	}

	@RequestMapping(value = "/kjsadmin/list", method = RequestMethod.GET)
	public String gotoUserList(HttpServletRequest request) {
		// 判断当前用户的角色,是否有创建用户的权限
		UserDomain userDomain = AuthenticationUtils.getUser();
		boolean hasRole = Constants.Role.hasOper(userDomain.getRole());
		// 按钮控制
		request.setAttribute("hasOper", hasRole);
		request.setAttribute("user_type", "kjsadmin");
		return "user/list";
	}

	@RequestMapping(value = "/kjsadmin/datas.action", method = RequestMethod.GET)
	public void getUserDatas(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		UserDomain userDomain = AuthenticationUtils.getUser();
		String[] roles = Constants.Role.getQueryRoles(userDomain.getRole());
		if (roles == null) {
			throw new ForbiddenExcetion("");
		}
		List<UserVo> datas = this.userService.getByRole(roles, name, (start - 1) * Constants.Common.PAGE_SIZE,
				Constants.Common.PAGE_SIZE);
		long total = this.userService.getTotal(roles, name);
		PageModel<UserVo> page = new PageModel<UserVo>();
		page.setPage(start);
		page.setTotal((long) Math.ceil(total / Constants.Common.PAGE_SIZE));
		page.setRecords(total);
		page.setRows(datas);
		PageWrite.writeTOPage(response, GsonUtils.toJson(page));
	}

	@RequestMapping(value = "/kjsadmin/check.action", method = RequestMethod.POST)
	public void checkUser(String loginName, HttpServletRequest request, HttpServletResponse response) {
		UserDomain user = this.userService.getByLoginName(loginName);
		PageWrite.writeTOPage(response, user == null);
	}
}

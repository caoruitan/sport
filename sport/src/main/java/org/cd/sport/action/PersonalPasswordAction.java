package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.exception.SportException;
import org.cd.sport.service.UserService;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.Md5Util;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.RSAGenerator;
import org.cd.sport.utils.UUIDUtil;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

/**
 * 用户个人密码修改控制层
 * 
 * @author liuyk
 */
@Controller
@RequestMapping("password")
public class PersonalPasswordAction extends ExceptionWrapper {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/kjsadmin/update.htm", method = RequestMethod.GET)
	public String gotoUpdatePasswordView(HttpServletRequest request) {
		UserVo user = AuthenticationUtils.getUser();
		// 初始化公钥
		RSAGenerator generator = new RSAGenerator();
		String pubKey = generator.generateBase64PublicKey();
		String guid = UUIDUtil.getGuid();
		request.setAttribute("uuid", guid);
		request.setAttribute("pubKey", pubKey);
		request.getSession().setAttribute(Constants.User.RSA_KEY, generator);
		request.getSession().setAttribute(Constants.User.UUID_KEY, guid);
		request.setAttribute("user", user);
		request.setAttribute("user_type", "kjsadmin");
		return "password/update";
	}

	@RequestMapping(value = "/kjsadmin/update.action", method = RequestMethod.POST)
	public void updatePassword(String oldPassword, String newPassword, HttpServletRequest request,
			HttpServletResponse response) {
		JsonObject json = new JsonObject();
		json.addProperty("success", false);
		HttpSession session = request.getSession();
		RSAGenerator generator = (RSAGenerator) session.getAttribute(Constants.User.RSA_KEY);
		if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || generator == null) {
			json.addProperty("success", false);
			json.addProperty("msg", "系统异常,请稍后重试");
		} else {
			try {
				oldPassword = generator.decryptBase64(oldPassword);
				newPassword = generator.decryptBase64(newPassword);
				UserVo user = AuthenticationUtils.getUser();
				boolean updatePassword = this.userService.updatePassword(user.getUserId(), oldPassword, newPassword);
				// 销毁session
				if (updatePassword) {
					session.invalidate();
				}
				json.addProperty("success", updatePassword);
			} catch (SportException e) {
				json.addProperty("success", false);
				json.addProperty("msg", "系统异常,请稍后重试");
			}
		}
		PageWrite.writeTOPage(response, json);
	}

	@RequestMapping(value = "/check.action", method = RequestMethod.POST)
	public void checkPassword(String oldPassword, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		RSAGenerator generator = (RSAGenerator) session.getAttribute(Constants.User.RSA_KEY);
		if (StringUtils.isBlank(oldPassword) || generator == null) {
			PageWrite.writeTOPage(response, false);
			return;
		}
		try {
			oldPassword = generator.decryptBase64(oldPassword);
			UserVo user = AuthenticationUtils.getUser();
			UserDomain userDomain = this.userService.getById(user.getUserId());
			PageWrite.writeTOPage(response, Md5Util.digestMD5(oldPassword).equals(userDomain.getPassword()));
			return;
		} catch (SportException e) {
			e.printStackTrace();
			PageWrite.writeTOPage(response, false);
			return;
		}
	}
}

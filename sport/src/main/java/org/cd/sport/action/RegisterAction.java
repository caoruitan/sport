package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.OrganizationService;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.RSAGenerator;
import org.cd.sport.utils.UUIDUtil;
import org.cd.sport.view.OrganizationView;
import org.cd.sport.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册控制层
 * 
 * @author liuyk
 *
 */
@Controller
@RequestMapping("org")
public class RegisterAction extends BaseUserAction {

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping("/fullname/check.action")
	public void orgCheck(String fullName, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain org = this.organizationService.getByFullName(fullName);
		PageWrite.writeTOPage(response, org == null);
	}

	@RequestMapping("/register.htm")
	public String register(HttpServletRequest request) {
		return "register/one";
	}

	@RequestMapping("/register.action")
	public void register(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain domain = this.organizationService.create(org);
		PageWrite.writeTOPage(response, domain.getOrgId());
	}

	@RequestMapping("/update.htm")
	public String updateRegisterPage(HttpServletRequest request) {
		return "register/one";
	}

	@RequestMapping("/update.action")
	public void updateRegister(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain create = this.organizationService.create(org);
		PageWrite.writeTOPage(response, create.getOrgId());
	}

	@RequestMapping("/manager/register.htm")
	public String secondRegisterPage(HttpServletRequest request) {
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
		request.setAttribute("credCode", Constants.Dic.DIC_CRED_CODE);
		request.setAttribute("degreesCode", Constants.Dic.DIC_DEGREES_CODE);
		String orgId = request.getParameter("orgId");
		if (StringUtils.isBlank(orgId)) {
			throw new ParameterIsWrongException("组织机构id为空");
		}
		request.setAttribute("organization", orgId);
		return "register/two";
	}

	@RequestMapping("/manager/register.action")
	public void secondRegister(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_ORG_ADMIN);
		super.createUser(user, request, response);
	}

	@RequestMapping("/success.htm")
	public String success(HttpServletRequest request) {
		return "register/success";
	}
}

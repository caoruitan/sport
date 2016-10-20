package org.cd.sport.action;

import javax.persistence.EntityNotFoundException;
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
import org.cd.sport.vo.UserVo;
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

	@RequestMapping("/fullname/update/check.action")
	public void orgCheck(String fullName, String orgId, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain org = this.organizationService.getByFullName(fullName);
		boolean result = false;
		if (org == null || org.getOrgId().equals(orgId)) {
			result = true;
		}
		PageWrite.writeTOPage(response, result);
	}

	@RequestMapping("/register.htm")
	public String register(HttpServletRequest request) {
		return "register/org";
	}

	@RequestMapping("/register.action")
	public void register(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain domain = this.organizationService.create(org);
		PageWrite.writeTOPage(response, domain.getOrgId());
	}

	@RequestMapping("/update.htm")
	public String updateRegisterPage(String orgId, HttpServletRequest request) {
		OrganizationView org = this.organizationService.getViewById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("单位不存在！");
		}
		request.setAttribute("org", org);
		return "register/org_update";
	}

	@RequestMapping("/update.action")
	public void updateRegister(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain create = this.organizationService.update(org);
		PageWrite.writeTOPage(response, create.getOrgId());
	}

	@RequestMapping("/manager/register.htm")
	public String secondRegisterPage(HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		if (StringUtils.isBlank(orgId)) {
			throw new ParameterIsWrongException("组织机构id为空");
		}
		UserVo user = this.getUserService().getMangerByOrgId(orgId);
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
		request.setAttribute("organization", orgId);
		if (user != null) {
			request.setAttribute("user", user);
			return "register/manager_update";
		}
		return "register/manager";
	}

	@RequestMapping("/manager/register.action")
	public void secondRegister(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_ORG_ADMIN);
		super.createUser(user, request, response);
	}

	@RequestMapping("/manager/update.action")
	public void secondRegisterUpdate(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_ORG_ADMIN);
		super.updateUser(user, request, response);
	}

	@RequestMapping("/success.htm")
	public String success(HttpServletRequest request) {
		return "register/success";
	}
}

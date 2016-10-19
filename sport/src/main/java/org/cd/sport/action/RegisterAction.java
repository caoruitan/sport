package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.service.OrganizationService;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.OrganizationView;
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
public class RegisterAction extends BaseUserAction {

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping("/org/fullname/check.action")
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
		boolean create = this.organizationService.create(org);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping("/update.htm")
	public String updateRegisterPage(HttpServletRequest request) {
		return "register/one";
	}

	@RequestMapping("/update.action")
	public void updateRegister(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.organizationService.create(org);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping("/second/register.htm")
	public String secondRegisterPage(HttpServletRequest request) {
		return "register/two";
	}

	@RequestMapping("/second/register.action")
	public void secondRegister(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.organizationService.create(org);
		PageWrite.writeTOPage(response, create);
	}
}

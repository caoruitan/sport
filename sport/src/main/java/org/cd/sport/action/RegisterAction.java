package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.OrganizationService;
import org.cd.sport.service.UserService;
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
@RequestMapping("regist")
public class RegisterAction extends BaseUserAction {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private DicService dicService;

	@Autowired
	private UserService userService;

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

	@RequestMapping("/regist.htm")
	public String register(HttpServletRequest request) {
		List<Dic> dics = this.dicService.getByPcode(Constants.Dic.DIC_QUALITY_CODE);
		List<Dic> address = this.dicService.getByPcode(Constants.Dic.DIC_ADDRESS_CODE);
		request.setAttribute("dics", dics);
		request.setAttribute("address", address);
		return "register/org";
	}

	@RequestMapping("/regist.action")
	public void register(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		org.setRole(Constants.Org.SB_ROLE);
		OrganizationDomain domain = this.organizationService.create(org);
		PageWrite.writeTOPage(response, domain.getOrgId());
	}

	@RequestMapping("/update.htm")
	public String updateRegisterPage(String orgId, HttpServletRequest request) {
		OrganizationView org = this.organizationService.getViewById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("单位不存在！");
		}
		List<Dic> dics = this.dicService.getByPcode(Constants.Dic.DIC_QUALITY_CODE);
		List<Dic> address = this.dicService.getByPcode(Constants.Dic.DIC_ADDRESS_CODE);
		request.setAttribute("dics", dics);
		request.setAttribute("address", address);
		return "register/org_update";
	}

	@RequestMapping("/update.action")
	public void updateRegister(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		org.setRole(Constants.Org.SB_ROLE);
		OrganizationDomain create = this.organizationService.update(org);
		PageWrite.writeTOPage(response, create.getOrgId());
	}

	@RequestMapping("/manager/regist.htm")
	public String secondRegisterPage(HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		if (StringUtils.isBlank(orgId)) {
			throw new ParameterIsWrongException("组织机构id为空");
		}
		UserVo user = this.getUserService().getMangerByOrgId(orgId, Constants.Role.ROLE_SB_ADMIN);
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
		List<Dic> dics = this.dicService.getByPcode(Constants.Dic.DIC_QUALITY_CODE);
		List<Dic> address = this.dicService.getByPcode(Constants.Dic.DIC_ADDRESS_CODE);
		request.setAttribute("dics", dics);
		request.setAttribute("credCode", Constants.Dic.DIC_CRED_CODE);
		request.setAttribute("degreesCode", Constants.Dic.DIC_DEGREES_CODE);
		request.setAttribute("address", address);
		request.setAttribute("organization", orgId);
		if (user != null) {
			request.setAttribute("user", user);
			return "register/manager_update";
		}
		return "register/manager";
	}

	@RequestMapping("/manager/regist.action")
	public void secondRegister(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_SB_ADMIN);
		super.createUser(user, request, response);
	}

	@RequestMapping("/manager/update.action")
	public void secondRegisterUpdate(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_SB_ADMIN);
		super.updateUser(user, request, response);
	}

	@RequestMapping("/success.htm")
	public String success(String orgId, HttpServletRequest request) {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		this.organizationService.writeWord(orgId, basePath);
		request.setAttribute("orgId", orgId);
		return "register/success";
	}

	@RequestMapping("/download.action")
	public void download(String orgId, HttpServletRequest request, HttpServletResponse response) {
		OrganizationDomain org = this.organizationService.getById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("组织机构不存在");
		}
		UserVo user = this.userService.getMangerByOrgId(orgId, Constants.Role.ROLE_SB_ADMIN);
		if (user == null) {
			throw new EntityNotFoundException("组织机构管理员不存在");
		}
		String realPath = request.getSession().getServletContext().getRealPath("/" + UploadAction.DOC_DIR);
		UploadAction.downloadFile(realPath + "/" + user.getLoginName() + "_register.doc", "register.doc", request,
				response);
	}
}

package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.exception.SportException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.OrganizationService;
import org.cd.sport.service.UserService;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.RSAGenerator;
import org.cd.sport.utils.UUIDUtil;
import org.cd.sport.view.OrganizationView;
import org.cd.sport.view.UserView;
import org.cd.sport.vo.OrgQuery;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

/**
 * 组织单位管理
 * 
 * @author liuyk
 */
@Controller
@RequestMapping("org")
public class KjsadminOrgAction extends BaseOrgAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/kjsadmin/list", method = RequestMethod.GET)
	public String gotoOrgList(HttpServletRequest request) {
		return "org/list";
	}

	@RequestMapping(value = "/kjsadmin/datas", method = RequestMethod.GET)
	public void queryOrgDatas(HttpServletRequest request, HttpServletResponse response) {
		OrgQuery query = new OrgQuery();
		query.setRole(Constants.Org.ORG_ROLE);
		super.queryOrgDatas(query, request, response);
	}

	@RequestMapping(value = "/kjsadmin/user.htm", method = RequestMethod.GET)
	public String orgManagerUser(HttpServletRequest request) {
		super.orgManagerUser(request);
		return "org/user";
	}

	@RequestMapping(value = "/kjsadmin/userDatas.action", method = RequestMethod.GET)
	public void orgManagerUserDatas(HttpServletRequest request, HttpServletResponse response) {
		super.orgManagerUserDatas(request, response);
	}

	@RequestMapping(value = "/kjsadmin/detail.htm", method = RequestMethod.GET)
	public String orgDetail(HttpServletRequest request) {
		super.orgDetail(request);
		return "org/detail";
	}

	@RequestMapping(value = "/kjsadmin/create.htm", method = RequestMethod.GET)
	public String createView(HttpServletRequest request) {
		List<Dic> dics = this.dicService.getByPcode(Constants.Dic.DIC_QUALITY_CODE);
		List<Dic> address = this.dicService.getByPcode(Constants.Dic.DIC_ADDRESS_CODE);
		request.setAttribute("dics", dics);
		request.setAttribute("address", address);
		return "org/create";
	}

	@RequestMapping(value = "/kjsadmin/create.action", method = RequestMethod.POST)
	public void create(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		org.setRole(Constants.Org.ORG_ROLE);
		OrganizationDomain domain = this.organizationService.create(org);
		PageWrite.writeTOPage(response, domain.getOrgId());
	}

	@RequestMapping(value = "/kjsadmin/delete.action", method = RequestMethod.POST)
	public void delete(HttpServletRequest request) {

	}

	@RequestMapping("/kjsadmin/update.htm")
	public String updateRegisterPage(String orgId, HttpServletRequest request) {
		OrganizationView org = this.organizationService.getViewById(orgId);
		if (org == null) {
			throw new EntityNotFoundException("单位不存在！");
		}
		List<Dic> dics = this.dicService.getByPcode(Constants.Dic.DIC_QUALITY_CODE);
		List<Dic> address = this.dicService.getByPcode(Constants.Dic.DIC_ADDRESS_CODE);
		request.setAttribute("dics", dics);
		request.setAttribute("address", address);
		request.setAttribute("org", org);
		return "org/update";
	}

	@RequestMapping("/kjsadmin/update.action")
	public void updateRegister(OrganizationView org, HttpServletRequest request, HttpServletResponse response) {
		org.setRole(Constants.Org.ORG_ROLE);
		OrganizationDomain create = this.organizationService.update(org);
		PageWrite.writeTOPage(response, create.getOrgId());
	}

	@RequestMapping(value = "/kjsadmin/manager/update.htm", method = RequestMethod.GET)
	public String createManagerView(HttpServletRequest request) {
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
		List<Dic> credCodes = dicService.getByPcode(Constants.Dic.DIC_CRED_CODE);
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zcDics = dicService.getByPcode(Constants.Dic.DIC_ZC_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZW_CODE);
		request.setAttribute("creds", credCodes);
		request.setAttribute("degrees", degrees);
		request.setAttribute("zcDics", zcDics);
		request.setAttribute("zwDics", zwDics);
		request.setAttribute("organization", orgId);
		if (user != null) {
			request.setAttribute("user", user);
			return "org/manager_update";
		}
		return "org/manager";
	}

	@RequestMapping(value = "/kjsadmin/manager/create.action", method = RequestMethod.POST)
	public void createManager(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_ORG_ADMIN);
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

	@RequestMapping("/kjsadmin/manager/update.action")
	public void secondRegisterUpdate(UserView user, HttpServletRequest request, HttpServletResponse response) {
		user.setRole(Constants.Role.ROLE_ORG_ADMIN);
		String uuid = request.getParameter("uuid");
		HttpSession session = request.getSession();
		JsonObject json = new JsonObject();
		if (StringUtils.isBlank(uuid) || !uuid.equals(session.getAttribute(Constants.User.UUID_KEY))) {
			json.addProperty("success", false);
			json.addProperty("msg", "非法操作");
		} else {
			boolean result = this.userService.update(user);
			json.addProperty("success", result);
		}
		PageWrite.writeTOPage(response, json);
	}
}
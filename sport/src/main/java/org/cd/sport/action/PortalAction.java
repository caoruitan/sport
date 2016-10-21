package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("portal")
public class PortalAction {
	/**
	 * 科教司管理员主页
	 */
	@RequestMapping("kjsadmin/index.htm")
	public String gotoIndex(HttpServletRequest request) {
		request.setAttribute("user_type", "kjsadmin");
		return "portal/kjsadmin";
	}

	/**
	 * 科教司领导主页
	 */
	@RequestMapping("kjsleader/index.htm")
	public String kjsleaderIndex(HttpServletRequest request) {
		request.setAttribute("user_type", "kjsleader");
		return "portal/kjsleader";
	}

	/**
	 * 科教司专家主页
	 */
	@RequestMapping("kjsexpert/index.htm")
	public String kjsexpertIndex(HttpServletRequest request) {
		request.setAttribute("user_type", "kjsexpert");
		return "portal/kjsexpert";
	}

	/**
	 * 注册单位管理员主页（申报单位）
	 */
	@RequestMapping("sbadmin/index.htm")
	public String sbadminIndex(HttpServletRequest request) {
		return "portal/sbadmin";
	}

	/**
	 * 注册单位操作员主页(申报单位）
	 */
	@RequestMapping("sboper/index.htm")
	public String sboperIndex(HttpServletRequest request) {
		return "portal/sboper";
	}

	/**
	 * 组织机构管理员主页（科教司创建的组织）
	 */
	@RequestMapping("orgadmin/index.htm")
	public String orgadminIndex(HttpServletRequest request) {
		return "portal/orgadmin";
	}

	/**
	 * 组织机构操作员主页（科教司创建的组织）
	 */
	@RequestMapping("orgoper/index.htm")
	public String orgoperIndex(HttpServletRequest request) {
		return "portal/orgoper";
	}

}

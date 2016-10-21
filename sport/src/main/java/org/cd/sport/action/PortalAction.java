package org.cd.sport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.constant.Constants;
import org.cd.sport.service.NewsService;
import org.cd.sport.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("portal")
public class PortalAction {

	@Autowired
	private NewsService newsService;

	/**
	 * 查询新闻
	 */
	public void queryNews(HttpServletRequest request) {
		// 查询课题总数(招标回退)
		request.setAttribute("zb_return_total", 10);
		// 查询课题总数(招标结题)
		request.setAttribute("zb_end_total", 10);
		// 查询课题总数(科研回退)
		request.setAttribute("ky_return_total", 10);
		// 查询课题总数(科研结题)
		request.setAttribute("ky_end_total", 10);
		// 查询新闻
		Integer[] columns = new Integer[] { Constants.News.SB_NEWS };
		List<NewsVo> news = this.newsService.getByColumn(columns, 0, 10);
		long total = this.newsService.getTotalByColumn(columns);
		request.setAttribute("sbNews", news);
		request.setAttribute("sbTotal", total);
		columns = new Integer[] { Constants.News.ZC_NEWS };
		news = this.newsService.getByColumn(columns, 0, 10);
		total = this.newsService.getTotalByColumn(columns);
		request.setAttribute("zcNews", news);
		request.setAttribute("zcTotal", total);
	}

	/**
	 * 科教司管理员主页
	 */
	@RequestMapping("kjsadmin/index.htm")
	public String gotoIndex(HttpServletRequest request) {
		queryNews(request);
		request.setAttribute("user_type", "kjsadmin");
		return "portal/kjsadmin";
	}

	/**
	 * 科教司领导主页
	 */
	@RequestMapping("kjsleader/index.htm")
	public String kjsleaderIndex(HttpServletRequest request) {
		queryNews(request);
		request.setAttribute("user_type", "kjsleader");
		return "portal/kjsleader";
	}

	/**
	 * 科教司专家主页
	 */
	@RequestMapping("kjsexpert/index.htm")
	public String kjsexpertIndex(HttpServletRequest request) {
		queryNews(request);
		request.setAttribute("user_type", "kjsexpert");
		return "portal/kjsexpert";
	}

	/**
	 * 注册单位管理员主页（申报单位）
	 */
	@RequestMapping("sbadmin/index.htm")
	public String sbadminIndex(HttpServletRequest request) {
		queryNews(request);
		return "portal/sbadmin";
	}

	/**
	 * 注册单位操作员主页(申报单位）
	 */
	@RequestMapping("sboper/index.htm")
	public String sboperIndex(HttpServletRequest request) {
		queryNews(request);
		return "portal/sboper";
	}

	/**
	 * 组织机构管理员主页（科教司创建的组织）
	 */
	@RequestMapping("orgadmin/index.htm")
	public String orgadminIndex(HttpServletRequest request) {
		queryNews(request);
		return "portal/orgadmin";
	}

	/**
	 * 组织机构操作员主页（科教司创建的组织）
	 */
	@RequestMapping("orgoper/index.htm")
	public String orgoperIndex(HttpServletRequest request) {
		queryNews(request);
		return "portal/orgoper";
	}
}

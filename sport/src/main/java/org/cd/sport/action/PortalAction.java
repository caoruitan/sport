package org.cd.sport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.constant.Constants;
import org.cd.sport.service.NewsService;
import org.cd.sport.service.SubjectService;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.vo.NewsVo;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("portal")
public class PortalAction {

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private SubjectService subjectService;

	/**
	 * 查询新闻
	 */
	public void queryNews(HttpServletRequest request) {
		// 查询新闻
		Integer[] columns = new Integer[] { Constants.News.SB_NEWS };
		List<NewsVo> news = this.newsService.getByColumn(columns, 0, 5);
		long total = this.newsService.getTotalByColumn(columns);
		request.setAttribute("sbNews", news);
		request.setAttribute("sbTotal", total);
		columns = new Integer[] { Constants.News.ZC_NEWS };
		news = this.newsService.getByColumn(columns, 0, 5);
		total = this.newsService.getTotalByColumn(columns);
		request.setAttribute("zcNews", news);
		request.setAttribute("zcTotal", total);
		columns = new Integer[] { Constants.News.NOTICE_NEWS };
		news = this.newsService.getByColumn(columns, 0, 5);
		total = this.newsService.getTotalByColumn(columns);
		request.setAttribute("noticeNews", news);
		request.setAttribute("noticeTotal", total);
	}

	/**
	 * 科教司管理员主页
	 */
	@RequestMapping("kjsadmin/index.htm")
	public String gotoIndex(HttpServletRequest request) {
		queryNews(request);
		int kt_zs = subjectService.getAllSubjectCount();
		int zbkt_zs = subjectService.getAllSubjectCountByType(Constants.Subject.SUBJECT_TYPE_ZBKT);
		int zbkt_sbs = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_ZBKT, Constants.Subject.SUBJECT_STAGE_SBSTB);
		int zbkt_rws = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_ZBKT, Constants.Subject.SUBJECT_STAGE_RWSTB);
		int zbkt_jt = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_ZBKT, Constants.Subject.SUBJECT_STAGE_JTEND);
		int kyggkt_zs = subjectService.getAllSubjectCountByType(Constants.Subject.SUBJECT_TYPE_KYGGKT);
		int kyggkt_sbs = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_KYGGKT, Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kyggkt_rws = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_KYGGKT, Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kyggkt_jt = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_KYGGKT, Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("zbkt_zs", zbkt_zs);
		request.setAttribute("zbkt_sbs", zbkt_sbs);
		request.setAttribute("zbkt_rws", zbkt_rws);
		request.setAttribute("zbkt_jt", zbkt_jt);
		request.setAttribute("kyggkt_zs", kyggkt_zs);
		request.setAttribute("kyggkt_sbs", kyggkt_sbs);
		request.setAttribute("kyggkt_rws", kyggkt_rws);
		request.setAttribute("kyggkt_jt", kyggkt_jt);
		request.setAttribute("user_type", "kjsadmin");
		return "portal/kjsadmin";
	}

	/**
	 * 科教司领导主页
	 */
	@RequestMapping("kjsleader/index.htm")
	public String kjsleaderIndex(HttpServletRequest request) {
		queryNews(request);
		int kt_zs = subjectService.getAllSubjectCount();
		int zbkt_zs = subjectService.getAllSubjectCountByType(Constants.Subject.SUBJECT_TYPE_ZBKT);
		int zbkt_sbs = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_ZBKT, Constants.Subject.SUBJECT_STAGE_SBSTB);
		int zbkt_rws = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_ZBKT, Constants.Subject.SUBJECT_STAGE_RWSTB);
		int zbkt_jt = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_ZBKT, Constants.Subject.SUBJECT_STAGE_JTEND);
		int kyggkt_zs = subjectService.getAllSubjectCountByType(Constants.Subject.SUBJECT_TYPE_KYGGKT);
		int kyggkt_sbs = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_KYGGKT, Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kyggkt_rws = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_KYGGKT, Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kyggkt_jt = subjectService.getAllSubjectCountByTypeAndStage(Constants.Subject.SUBJECT_TYPE_KYGGKT, Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("zbkt_zs", zbkt_zs);
		request.setAttribute("zbkt_sbs", zbkt_sbs);
		request.setAttribute("zbkt_rws", zbkt_rws);
		request.setAttribute("zbkt_jt", zbkt_jt);
		request.setAttribute("kyggkt_zs", kyggkt_zs);
		request.setAttribute("kyggkt_sbs", kyggkt_sbs);
		request.setAttribute("kyggkt_rws", kyggkt_rws);
		request.setAttribute("kyggkt_jt", kyggkt_jt);
		request.setAttribute("user_type", "kjsleader");
		return "portal/kjsleader";
	}

	/**
	 * 科教司专家主页
	 */
	@RequestMapping("kjsexpert/index.htm")
	public String kjsexpertIndex(HttpServletRequest request) {
		queryNews(request);
		UserVo user = AuthenticationUtils.getUser();
		int kt_zs = subjectService.getSubjectCountByExpert(user.getOrganization());
		int kt_sbs = subjectService.getSubjectCountByExpertAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kt_rws = subjectService.getSubjectCountByExpertAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kt_jt = subjectService.getSubjectCountByExpertAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("kt_sbs", kt_sbs);
		request.setAttribute("kt_rws", kt_rws);
		request.setAttribute("kt_jt", kt_jt);
		request.setAttribute("user_type", "kjsexpert");
		return "portal/kjsexpert";
	}

	/**
	 * 注册单位管理员主页（申报单位）
	 */
	@RequestMapping("sbadmin/index.htm")
	public String sbadminIndex(HttpServletRequest request) {
		queryNews(request);
		UserVo user = AuthenticationUtils.getUser();
		int kt_zs = subjectService.getSubjectCountByCreateUnit(user.getOrganization());
		int kt_sbs = subjectService.getSubjectCountByCreateUnitAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kt_rws = subjectService.getSubjectCountByCreateUnitAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kt_jt = subjectService.getSubjectCountByCreateUnitAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("kt_sbs", kt_sbs);
		request.setAttribute("kt_rws", kt_rws);
		request.setAttribute("kt_jt", kt_jt);
		request.setAttribute("user_type", "sbadmin");
		return "portal/sbadmin";
	}

	/**
	 * 注册单位操作员主页(申报单位）
	 */
	@RequestMapping("sboper/index.htm")
	public String sboperIndex(HttpServletRequest request) {
		queryNews(request);
		UserVo user = AuthenticationUtils.getUser();
		int kt_zs = subjectService.getSubjectCountByCreator(user.getLoginName());
		int kt_sbs = subjectService.getSubjectCountByCreatorAndStage(user.getLoginName(), Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kt_rws = subjectService.getSubjectCountByCreatorAndStage(user.getLoginName(), Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kt_jt = subjectService.getSubjectCountByCreatorAndStage(user.getLoginName(), Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("kt_sbs", kt_sbs);
		request.setAttribute("kt_rws", kt_rws);
		request.setAttribute("kt_jt", kt_jt);
		request.setAttribute("user_type", "sboper");
		return "portal/sboper";
	}

	/**
	 * 组织机构管理员主页（科教司创建的组织）
	 */
	@RequestMapping("orgadmin/index.htm")
	public String orgadminIndex(HttpServletRequest request) {
		queryNews(request);
		UserVo user = AuthenticationUtils.getUser();
		int kt_zs = subjectService.getSubjectCountByOrg(user.getOrganization());
		int kt_sbs = subjectService.getSubjectCountByOrgAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kt_rws = subjectService.getSubjectCountByOrgAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kt_jt = subjectService.getSubjectCountByOrgAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("kt_sbs", kt_sbs);
		request.setAttribute("kt_rws", kt_rws);
		request.setAttribute("kt_jt", kt_jt);
		request.setAttribute("user_type", "orgadmin");
		return "portal/orgadmin";
	}

	/**
	 * 组织机构操作员主页（科教司创建的组织）
	 */
	@RequestMapping("orgoper/index.htm")
	public String orgoperIndex(HttpServletRequest request) {
		queryNews(request);
		UserVo user = AuthenticationUtils.getUser();
		int kt_zs = subjectService.getSubjectCountByOrg(user.getOrganization());
		int kt_sbs = subjectService.getSubjectCountByOrgAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_SBSTB);
		int kt_rws = subjectService.getSubjectCountByOrgAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_RWSTB);
		int kt_jt = subjectService.getSubjectCountByOrgAndStage(user.getOrganization(), Constants.Subject.SUBJECT_STAGE_JTEND);
		request.setAttribute("kt_zs", kt_zs);
		request.setAttribute("kt_sbs", kt_sbs);
		request.setAttribute("kt_rws", kt_rws);
		request.setAttribute("kt_jt", kt_jt);
		request.setAttribute("user_type", "orgoper");
		return "portal/orgoper";
	}
}

package org.cd.sport.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.service.SubjectSbsService;
import org.cd.sport.service.SubjectService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("subject/orgadmin")
public class SubjectOrgAdminAction {
	
	@Autowired
	private SubjectService subjectService; 
	
	@Autowired
	private SubjectSbsService subjectSbsService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		int startYear = Integer.parseInt(Constants.Subject.SUBJECT_START_YEAR);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int endYear = Integer.parseInt(sdf.format(date));
		List<String> years = new LinkedList<String>();
		for(int i = 0; endYear - i >= startYear; i ++) {
			years.add(String.valueOf(endYear - i));
		}

		request.setAttribute("endYear", String.valueOf(endYear));
		request.setAttribute("years", years);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		request.setAttribute("stages", Constants.Subject.getSubjectStages());
		return "subject/orgadmin/list";
	}

	@RequestMapping("/datas.action")
	public void datas(HttpServletRequest request, HttpServletResponse response) {
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		String stage = request.getParameter("stage");
		String startStr = request.getParameter("page");
		UserVo user = AuthenticationUtils.getUser();
		if(year == null || year.equals("")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(date);
		}
		int start = SportSupport.processLimit(startStr);
		List<Subject> list = subjectService.getSubjectListByOrg(user.getOrganization(), year, type, stage, (start - 1) * Constants.Common.PAGE_SIZE, Constants.Common.PAGE_SIZE);
		long total = subjectService.getSubjectCountByOrg(user.getOrganization(), year, type, stage);
		PageModel<Subject> page = new PageModel<Subject>();
		page.setPage(start);
		page.setTotal((long) Math.ceil(new Double(String.valueOf(total))/Constants.Common.PAGE_SIZE));
		page.setRecords(total);
		page.setRows(list);
		PageWrite.writeTOPage(response, GsonUtils.toJson(page));
	}
	
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = subjectSbsService.getSbsBySubjectId(subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("sbs", sbs);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		return "subject/orgadmin/detail";
	}
	
	@RequestMapping(value = "sbstb")
	public String sbstb(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = subjectSbsService.getSbsBySubjectId(subjectId);
		request.setAttribute("status", Constants.SubjectSbs.getSubjectSbsStatus());
		request.setAttribute("subjectId", subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("sbs", sbs);
		return "subject/orgadmin/sbstb";
	}
	
	@RequestMapping(value = "pass.action")
	public void pass(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		this.subjectSbsService.orgadminPass(subjectId);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "unpass.action")
	public void unpass(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String comment = request.getParameter("comment");
		this.subjectSbsService.orgadminUnpass(subjectId, comment);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
}

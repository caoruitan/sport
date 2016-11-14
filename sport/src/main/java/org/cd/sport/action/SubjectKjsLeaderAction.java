package org.cd.sport.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.service.SubjectConclusionService;
import org.cd.sport.service.SubjectRwsService;
import org.cd.sport.service.SubjectSbsService;
import org.cd.sport.service.SubjectService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("subject/kjsleader")
public class SubjectKjsLeaderAction {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectSbsService subjectSbsService;

	@Autowired
	private SubjectRwsService subjectRwsService;

	@Autowired
	private SubjectConclusionService subjectConclusionService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		int startYear = Integer.parseInt(Constants.Subject.SUBJECT_START_YEAR);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int endYear = Integer.parseInt(sdf.format(date));
		List<String> years = new LinkedList<String>();
		for (int i = 0; endYear - i >= startYear; i++) {
			years.add(String.valueOf(endYear - i));
		}

		request.setAttribute("endYear", String.valueOf(endYear));
		request.setAttribute("years", years);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		request.setAttribute("stages", Constants.Subject.getSubjectStages());
		return "subject/kjsleader/list";
	}

	@RequestMapping("/datas.action")
	public void datas(HttpServletRequest request, HttpServletResponse response) {
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		String stage = request.getParameter("stage");
		String startStr = request.getParameter("page");
		if (year == null || year.equals("")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(date);
		}
		int start = SportSupport.processLimit(startStr);
		List<Subject> list = subjectService.getAllSubjectList(year, type, stage,
				(start - 1) * Constants.Common.PAGE_SIZE, Constants.Common.PAGE_SIZE);
		long total = subjectService.getAllSubjectCount(year, type, stage);
		PageModel<Subject> page = new PageModel<Subject>();
		page.setPage(start);
		page.setTotal((long) Math.ceil(new Double(String.valueOf(total)) / Constants.Common.PAGE_SIZE));
		page.setRecords(total);
		page.setRows(list);
		PageWrite.writeTOPage(response, GsonUtils.toJson(page));
	}

	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = subjectSbsService.getSbsBySubjectId(subjectId);
		SubjectRws rws = subjectRwsService.getRwsBySubjectId(subjectId);
		SubjectConclusion sc = this.subjectConclusionService.getSubjectConclusionBySubjectId(subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("sbs", sbs);
		request.setAttribute("rws", rws);
		request.setAttribute("sc", sc);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		return "subject/kjsleader/detail";
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
		return "subject/kjsleader/sbstb";
	}

	@RequestMapping(value = "rwstb")
	public String rwstb(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectRws rws = subjectRwsService.getRwsBySubjectId(subjectId);
		request.setAttribute("status", Constants.SubjectRws.getSubjectRwsStatus());
		request.setAttribute("subjectId", subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("rws", rws);
		return "subject/kjsleader/rwstb";
	}

	@RequestMapping(value = "conclusiontb")
	public String conclusiontb(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		List<SubjectConclusionAttachment> sas = subjectConclusionService.getAttachmentBySubjectId(subjectId);
		SubjectConclusion sc = this.subjectConclusionService.getSubjectConclusionBySubjectId(subjectId);
		request.setAttribute("status", Constants.SubjectRws.getSubjectRwsStatus());
		request.setAttribute("subjectId", subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("sas", sas);
		request.setAttribute("sc", sc);
		return "subject/kjsleader/conclusiontb";
	}
}

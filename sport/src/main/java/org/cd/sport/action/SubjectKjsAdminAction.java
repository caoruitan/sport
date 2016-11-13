package org.cd.sport.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.service.DicService;
import org.cd.sport.service.OrganizationService;
import org.cd.sport.service.SubjectConclusionService;
import org.cd.sport.service.SubjectRwsService;
import org.cd.sport.service.SubjectSbsService;
import org.cd.sport.service.SubjectService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.vo.OrgVo;
import org.cd.sport.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("subject/kjsadmin")
public class SubjectKjsAdminAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private OrganizationService organizationService;

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
		return "subject/kjsadmin/list";
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

	@RequestMapping(value = "updateSubject", method = RequestMethod.GET)
	public String toUpdateSubject(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		List<Dic> secretList = dicService.getByPcode(Constants.Dic.DIC_SECRET_CODE);
		List<Dic> expectList = dicService.getByPcode(Constants.Dic.DIC_EXPECT_CODE);
		List<OrgVo> kjsList = organizationService.getbyRole(Constants.Org.KJS_ROLE);
		List<OrgVo> orgList = organizationService.getbyRole(Constants.Org.ORG_ROLE);
		request.setAttribute("subject", subject);
		request.setAttribute("kjsList", kjsList);
		request.setAttribute("orgList", orgList);
		request.setAttribute("secretList", secretList);
		request.setAttribute("expectList", expectList);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		return "subject/kjsadmin/update";
	}

	@RequestMapping(value = "updateSubject", method = RequestMethod.POST)
	public String updateSubject(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String subjectId = request.getParameter("subjectId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String organizationId = request.getParameter("organizationId");
		String security = request.getParameter("security");
		String organizationCount = request.getParameter("organizationCount");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String[] resultsList = request.getParameterValues("results");
		String results = "";
		if (resultsList != null && resultsList.length > 0) {
			for (String result : resultsList) {
				results += result + ",";
			}
			if (!results.equals("")) {
				results = results.substring(0, results.length() - 1);
			}
		}
		String integration = request.getParameter("integration");
		SubjectVo vo = new SubjectVo();
		vo.setName(name);
		vo.setType(type);
		vo.setOrganizationId(organizationId);
		vo.setSecurity(security);
		vo.setOrganizationCount(StringUtils.isBlank(organizationCount) ? "0" : organizationCount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(beginDate)) {
			vo.setBeginDate(sdf.parse(beginDate));
		}
		if (StringUtils.isNotBlank(endDate)) {
			vo.setEndDate(sdf.parse(endDate));
		}
		vo.setResults(results);
		vo.setIntegration(Boolean.valueOf(integration));
		subjectService.updateSubject(subjectId, vo);
		return "redirect:list.htm";
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
		return "subject/kjsadmin/detail";
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
		return "subject/kjsadmin/sbstb";
	}

	@RequestMapping(value = "pass.action")
	public void pass(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		this.subjectSbsService.kjsadminPass(subjectId);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}

	@RequestMapping(value = "unpass.action")
	public void unpass(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String comment = request.getParameter("comment");
		this.subjectSbsService.kjsadminUnpass(subjectId, comment);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
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
		return "subject/kjsadmin/rwstb";
	}

	@RequestMapping(value = "rwsPass.action")
	public void rwsPass(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		this.subjectRwsService.kjsadminPass(subjectId);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}

	@RequestMapping(value = "rwsUnpass.action")
	public void rwsUnpass(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String comment = request.getParameter("comment");
		this.subjectRwsService.kjsadminUnpass(subjectId, comment);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}

}

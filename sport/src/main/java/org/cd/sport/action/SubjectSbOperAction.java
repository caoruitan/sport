package org.cd.sport.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Subject;
import org.cd.sport.service.SubjectService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.vo.SubjectVo;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("subject/sboper")
public class SubjectSbOperAction {

	@Autowired
	private SubjectService subjectService; 

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
		return "subject/sboper/list";
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
		List<Subject> list = subjectService.getSubjectListByCreator(user.getLoginName(), year, type, stage, (start - 1) * Constants.Common.PAGE_SIZE, Constants.Common.PAGE_SIZE);
		long total = subjectService.getSubjectCountByCreator(user.getLoginName(), year, type, stage);
		PageModel<Subject> page = new PageModel<Subject>();
		page.setPage(start);
		page.setTotal((long) Math.ceil(new Double(String.valueOf(total))/Constants.Common.PAGE_SIZE));
		page.setRecords(total);
		page.setRows(list);
		PageWrite.writeTOPage(response, GsonUtils.toJson(page));
	}
	
	@RequestMapping(value = "createSubject", method = RequestMethod.GET)
	public String toCreateSubject(HttpServletRequest request) {
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		return "subject/sboper/create";
	}
	
	@RequestMapping(value = "createSubject", method = RequestMethod.POST)
	public String createSubject(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String organizationId = request.getParameter("organizationId");
		String security = request.getParameter("security");
		String organizationCount = request.getParameter("organizationCount");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String[] resultsList = request.getParameterValues("results");
		String results = "";
		if(resultsList != null && resultsList.length > 0) {
			for(String result : resultsList) {
				results += result + ",";
			}
			if(!results.equals("")) {
				results = results.substring(0, results.length() - 1);
			}
		}
		String integration = request.getParameter("integration");
		SubjectVo vo = new SubjectVo();
		vo.setName(name);
		vo.setType(type);
		vo.setOrganizationId(organizationId);
		vo.setSecurity(security);
		vo.setOrganizationCount(organizationCount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		vo.setBeginDate(sdf.parse(beginDate));
		vo.setEndDate(sdf.parse(endDate));
		vo.setResults(results);
		vo.setIntegration(Boolean.valueOf(integration));
		subjectService.createSubject(vo);
		return "redirect:list.htm";
	}
	
}

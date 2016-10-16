package org.cd.sport.action;

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
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		for(int i = 0; startYear + i <= endYear; i ++) {
			years.add(String.valueOf(startYear + i));
		}
		request.setAttribute("years", years);
		return "subject/sboper/list";
	}

	@RequestMapping("/datas.action")
	public void datas(HttpServletRequest request, HttpServletResponse response) {
		UserVo user = AuthenticationUtils.getUser();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		String type = request.getParameter("type");
		String stage = request.getParameter("stage");
		String startStr = request.getParameter("page");
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
	
}

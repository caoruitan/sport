package org.cd.sport.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.domain.Subject;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.service.SubjectService;
import org.cd.sport.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("subject/sboper")
public class SubjectSbOperAction {

	@Autowired
	private SubjectService subjectService; 

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		UserDomain user = AuthenticationUtils.getUser();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		String type = request.getParameter("type");
		String stage = request.getParameter("stage");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<Subject> list = subjectService.getSubjectListByCreator(user.getUserId(), year, type, stage, start, limit);
		return "subject/sboper/list";
	}
	
}

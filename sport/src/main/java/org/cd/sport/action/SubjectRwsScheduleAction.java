package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.SubjectRwsScheduleService;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.SubjectRwsScheduleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subject/schedule")
public class SubjectRwsScheduleAction {

	@Autowired
	private SubjectRwsScheduleService subjectRwsSchedulingService;

	@RequestMapping(value = "/sboper/list.htm", method = RequestMethod.GET)
	public String sboperListView(String rwsId, String subjectId, HttpServletRequest request) {
		List<SubjectRwsSchedule> ssDatas = this.subjectRwsSchedulingService.getByRwsId(rwsId);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("ssDatas", GsonUtils.toJson(ssDatas));
		request.setAttribute("subjectId", subjectId);
		return "subject/schedule/list";
	}

	@RequestMapping(value = "/sbadmin/list.htm", method = RequestMethod.GET)
	public String ssbadminListView(String sbsId, HttpServletRequest request) {
		return "subject/schedule/list_readonly";
	}

	@RequestMapping(value = "/kjsadmin/list.htm", method = RequestMethod.GET)
	public String kjsadminListView(String sbsId, HttpServletRequest request) {
		return "subject/schedule/list_readonly";
	}

	@RequestMapping(value = "/kjsleader/list.htm", method = RequestMethod.GET)
	public String kjsleaderListView(String sbsId, HttpServletRequest request) {
		return "subject/schedule/list_readonly";
	}

	@RequestMapping(value = "/kjsexpert/list.htm", method = RequestMethod.GET)
	public String kjsexpertListView(String sbsId, HttpServletRequest request) {
		return "subject/schedule/list_readonly";
	}

	@RequestMapping(value = "/orgadmin/list.htm", method = RequestMethod.GET)
	public String orgadminListView(String sbsId, HttpServletRequest request) {
		return "subject/schedule/list_readonly";
	}

	@RequestMapping(value = "/orgoper/list.htm", method = RequestMethod.GET)
	public String orgoperListView(String sbsId, HttpServletRequest request) {
		return "subject/schedule/list_readonly";
	}

	@RequestMapping(value = "/sboper/create.htm", method = RequestMethod.GET)
	public String sboperCreateView(String rwsId, String subjectId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/schedule/create";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void sboperCreate(SubjectRwsScheduleView ss, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectRwsSchedulingService.create(ss);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/update.htm", method = RequestMethod.GET)
	public String sboperUpdateView(String sId, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sId)) {
			throw new ParameterIsWrongException("进度安排id为空");
		}
		SubjectRwsSchedule ss = this.subjectRwsSchedulingService.getById(sId);

		if (ss == null) {
			throw new EntityNotFoundException();
		}
		request.setAttribute("ss", ss);

		request.setAttribute("s_time", ss.getYear() + "-" + ss.getMonth());
		return "subject/schedule/update";
	}

	@RequestMapping(value = "/sboper/update.action", method = RequestMethod.POST)
	public void sboperUpdate(SubjectRwsScheduleView ss, HttpServletRequest request, HttpServletResponse response) {
		boolean update = this.subjectRwsSchedulingService.update(ss);
		PageWrite.writeTOPage(response, update);
	}

	@RequestMapping(value = "/sboper/delete.action", method = RequestMethod.POST)
	public void sboperDelete(String sIds, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sIds)) {
			throw new ParameterIsWrongException("进度安排id为空");
		}
		boolean delete = this.subjectRwsSchedulingService.deleteById(sIds.split(","));
		PageWrite.writeTOPage(response, delete);
	}

}

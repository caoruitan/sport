package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.SubjectRwsAppropriationService;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.SubjectRwsAppropriationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subject/appropriation")
public class SubjectRwsAppropriationAction {

	@Autowired
	private SubjectRwsAppropriationService subjectRwsAppropriationService;

	@RequestMapping(value = "/sboper/list.htm", method = RequestMethod.GET)
	public String sboperListView(String rwsId, String subjectId, HttpServletRequest request) {
		List<SubjectRwsAppropriation> ssDatas = this.subjectRwsAppropriationService.getByRwsId(rwsId);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("ssDatas", GsonUtils.toJson(ssDatas));
		request.setAttribute("subjectId", subjectId);
		return "subject/appropriation/list";
	}

	@RequestMapping(value = "/listReadOnly.htm", method = RequestMethod.GET)
	public String ssbadminListView(String rwsId, String subjectId, HttpServletRequest request) {
		List<SubjectRwsAppropriation> ssDatas = this.subjectRwsAppropriationService.getByRwsId(rwsId);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("ssDatas", GsonUtils.toJson(ssDatas));
		request.setAttribute("subjectId", subjectId);
		return "subject/appropriation/list_readonly";
	}

	@RequestMapping(value = "/sboper/create.htm", method = RequestMethod.GET)
	public String sboperCreateView(String rwsId, String subjectId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/appropriation/create";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void sboperCreate(SubjectRwsAppropriationView ss, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectRwsAppropriationService.create(ss);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/update.htm", method = RequestMethod.GET)
	public String sboperUpdateView(String sId, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sId)) {
			throw new ParameterIsWrongException("拨款id为空");
		}
		SubjectRwsAppropriation ss = this.subjectRwsAppropriationService.getById(sId);

		if (ss == null) {
			throw new EntityNotFoundException();
		}
		request.setAttribute("ss", ss);

		return "subject/appropriation/update";
	}

	@RequestMapping(value = "/sboper/update.action", method = RequestMethod.POST)
	public void sboperUpdate(SubjectRwsAppropriationView ss, HttpServletRequest request, HttpServletResponse response) {
		boolean update = this.subjectRwsAppropriationService.update(ss);
		PageWrite.writeTOPage(response, update);
	}

	@RequestMapping(value = "/sboper/delete.action", method = RequestMethod.POST)
	public void sboperDelete(String sIds, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sIds)) {
			throw new ParameterIsWrongException("拨款id为空");
		}
		boolean delete = this.subjectRwsAppropriationService.deleteById(sIds.split(","));
		PageWrite.writeTOPage(response, delete);
	}

}

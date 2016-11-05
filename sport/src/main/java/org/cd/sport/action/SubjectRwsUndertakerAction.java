package org.cd.sport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.SubjectRwsUndertakerService;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.SubjectRwsUndertakerView;
import org.cd.sport.vo.SubjectRwsUndertakerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subject/undertaker")
public class SubjectRwsUndertakerAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private SubjectRwsUndertakerService subjectRwsUndertakerService;

	@RequestMapping(value = "/sbadmin/list.htm", method = RequestMethod.GET)
	public String sbadminListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list_readonly";
	}

	@RequestMapping(value = "/sboper/list.htm", method = RequestMethod.GET)
	public String sboperListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list";
	}

	@RequestMapping(value = "/kjsadmin/list.htm", method = RequestMethod.GET)
	public String kjsadminListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list_readonly";
	}

	@RequestMapping(value = "/kjsleader/list.htm", method = RequestMethod.GET)
	public String kjsleaderListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list_readonly";
	}

	@RequestMapping(value = "/kjsexpert/list.htm", method = RequestMethod.GET)
	public String kjsexpertListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list_readonly";
	}

	@RequestMapping(value = "/orgadmin/list.htm", method = RequestMethod.GET)
	public String orgadminListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list_readonly";
	}

	@RequestMapping(value = "/orgoper/list.htm", method = RequestMethod.GET)
	public String orgoperListView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectRwsUndertakerVo> undertakers = this.subjectRwsUndertakerService.getByRwsId(rwsId);
		request.setAttribute("undertakers", GsonUtils.toJson(undertakers));
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/list_readonly";
	}

	@RequestMapping(value = "/sboper/create.htm", method = RequestMethod.GET)
	public String createOtherView(String rwsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZW_CODE);
		request.setAttribute("degrees", degrees);
		request.setAttribute("zwDics", zwDics);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/undertaker/create";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void createOhter(SubjectRwsUndertakerView proposer, HttpServletRequest request,
			HttpServletResponse response) {
		boolean create = this.subjectRwsUndertakerService.create(proposer);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/update.htm", method = RequestMethod.GET)
	public String updateView(String sId, HttpServletRequest request) {
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZW_CODE);
		SubjectRwsUndertaker undertaker = this.subjectRwsUndertakerService.getById(sId);
		if (undertaker == null) {
			throw new ParameterIsWrongException();
		}
		request.setAttribute("degrees", degrees);
		request.setAttribute("zwDics", zwDics);
		request.setAttribute("undertaker", undertaker);
		return "subject/undertaker/update";
	}

	@RequestMapping(value = "/sboper/update.action", method = RequestMethod.POST)
	public void update(SubjectRwsUndertakerView proposer, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectRwsUndertakerService.update(proposer);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/delete.action", method = RequestMethod.POST)
	public void delete(String undertakerId, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(undertakerId)) {
			throw new ParameterIsWrongException("参数为空");
		}
		boolean create = this.subjectRwsUndertakerService.deleteById(undertakerId.split(","));
		PageWrite.writeTOPage(response, create);
	}
}

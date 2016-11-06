package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.SubjectRwsDeviceService;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.SubjectRwsDeviceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subject/device")
public class SubjectRwsDeviceAction {

	@Autowired
	private SubjectRwsDeviceService subjectRwsDeviceService;

	@RequestMapping(value = "/sboper/list.htm", method = RequestMethod.GET)
	public String sboperListView(String rwsId, String subjectId, HttpServletRequest request) {
		List<SubjectRwsDevice> ssDatas = this.subjectRwsDeviceService.getByRwsId(rwsId);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("ssDatas", GsonUtils.toJson(ssDatas));
		request.setAttribute("subjectId", subjectId);
		return "subject/device/list";
	}

	@RequestMapping(value = "/listReadOnly.htm", method = RequestMethod.GET)
	public String ssbadminListView(String rwsId, String subjectId, HttpServletRequest request) {
		List<SubjectRwsDevice> ssDatas = this.subjectRwsDeviceService.getByRwsId(rwsId);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("ssDatas", GsonUtils.toJson(ssDatas));
		request.setAttribute("subjectId", subjectId);
		return "subject/device/list_readonly";
	}

	@RequestMapping(value = "/sboper/create.htm", method = RequestMethod.GET)
	public String sboperCreateView(String rwsId, String subjectId, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/device/create";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void sboperCreate(SubjectRwsDeviceView ss, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectRwsDeviceService.create(ss);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/update.htm", method = RequestMethod.GET)
	public String sboperUpdateView(String sId, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sId)) {
			throw new ParameterIsWrongException("设备id为空");
		}
		SubjectRwsDevice ss = this.subjectRwsDeviceService.getById(sId);

		if (ss == null) {
			throw new EntityNotFoundException();
		}
		request.setAttribute("ss", ss);
		return "subject/device/update";
	}

	@RequestMapping(value = "/sboper/update.action", method = RequestMethod.POST)
	public void sboperUpdate(SubjectRwsDeviceView ss, HttpServletRequest request, HttpServletResponse response) {
		boolean update = this.subjectRwsDeviceService.update(ss);
		PageWrite.writeTOPage(response, update);
	}

	@RequestMapping(value = "/sboper/delete.action", method = RequestMethod.POST)
	public void sboperDelete(String sIds, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sIds)) {
			throw new ParameterIsWrongException("进度安排id为空");
		}
		boolean delete = this.subjectRwsDeviceService.deleteById(sIds.split(","));
		PageWrite.writeTOPage(response, delete);
	}

}

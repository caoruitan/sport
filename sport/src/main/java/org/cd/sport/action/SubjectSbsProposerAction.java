package org.cd.sport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.constant.Constants.SubjectSbs;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.SubjectSbsProposerService;
import org.cd.sport.service.SubjectSbsService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.SubjectSbsProposerView;
import org.cd.sport.vo.SubjectSbsProposerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subject/proposer")
public class SubjectSbsProposerAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private SubjectSbsService subjectSbsService;

	@Autowired
	private SubjectSbsProposerService subjectSbsProposerService;

	@RequestMapping(value = "/listReadOnly.htm", method = RequestMethod.GET)
	public String sbadminListView(String sbsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(sbsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectSbsProposerVo> primaryProposers = this.subjectSbsProposerService.getBySbsId(sbsId,
				Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
		request.setAttribute("primaryProposers", primaryProposers);
		request.setAttribute("sbsId", sbsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/proposer/list_readonly";
	}

	@RequestMapping(value = "/sboper/list.htm", method = RequestMethod.GET)
	public String sboperListView(String sbsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		if (StringUtils.isBlank(sbsId)) {
			org.cd.sport.domain.SubjectSbs sbs = this.subjectSbsService.getSbsBySubjectId(subjectId);
			if (sbs == null) {
				sbs = this.subjectSbsService.createSubjectSbs(subjectId);
				sbsId = sbs.getSbsId();
			}
		}
		List<SubjectSbsProposerVo> primaryProposers = this.subjectSbsProposerService.getBySbsId(sbsId,
				Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
		request.setAttribute("primaryProposers", primaryProposers);
		request.setAttribute("sbsId", sbsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/proposer/list";
	}

	@RequestMapping(value = "/datas.action", method = RequestMethod.GET)
	public void otherDatas(String sbsId, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(sbsId)) {
			throw new ParameterIsWrongException();
		}
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		List<SubjectSbsProposerVo> datas = this.subjectSbsProposerService.getBySbsId(sbsId,
				Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_OTHER, (start - 1) * Constants.Common.PAGE_SIZE,
				Constants.Common.PAGE_SIZE);
		long total = this.subjectSbsProposerService.getTotalBySbsId(sbsId, SubjectSbs.SUBJECT_SBS_PROPOSER_OTHER);
		PageModel<SubjectSbsProposerVo> page = new PageModel<SubjectSbsProposerVo>();
		page.setPage(start);
		page.setTotal((long) Math.ceil(total * 0.1 / Constants.Common.PAGE_SIZE));
		page.setRecords(total);
		page.setRows(datas);
		PageWrite.writeTOPage(response, GsonUtils.toJson(page));
	}

	@RequestMapping(value = "/sboper/primary/create.htm", method = RequestMethod.GET)
	public String createView(String sbsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(sbsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException("课题id或者申报书id为空");
		}
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZC_CODE);
		request.setAttribute("degrees", degrees);
		request.setAttribute("zwDics", zwDics);
		request.setAttribute("sbsId", sbsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/proposer/create";
	}

	@RequestMapping(value = "/sboper/primary/create.action", method = RequestMethod.POST)
	public void create(SubjectSbsProposerView proposer, HttpServletRequest request, HttpServletResponse response) {
		proposer.setPrimary(Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
		boolean create = this.subjectSbsProposerService.create(proposer);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/other/create.htm", method = RequestMethod.GET)
	public String createOtherView(String sbsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(sbsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZC_CODE);
		request.setAttribute("degrees", degrees);
		request.setAttribute("zwDics", zwDics);
		request.setAttribute("sbsId", sbsId);
		request.setAttribute("subjectId", subjectId);
		return "subject/proposer/create";
	}

	@RequestMapping(value = "/sboper/other/create.action", method = RequestMethod.POST)
	public void createOhter(SubjectSbsProposerView proposer, HttpServletRequest request, HttpServletResponse response) {
		proposer.setPrimary(Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_OTHER);
		boolean create = this.subjectSbsProposerService.create(proposer);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/update.htm", method = RequestMethod.GET)
	public String updateView(String proposerId, HttpServletRequest request) {
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZC_CODE);
		SubjectSbsProposer proposer = this.subjectSbsProposerService.getById(proposerId);
		if (proposer == null) {
			throw new ParameterIsWrongException();
		}
		request.setAttribute("degrees", degrees);
		request.setAttribute("zwDics", zwDics);
		request.setAttribute("proposer", proposer);
		return "subject/proposer/update";
	}

	@RequestMapping(value = "/sboper/update.action", method = RequestMethod.POST)
	public void update(SubjectSbsProposerView proposer, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectSbsProposerService.update(proposer);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/sboper/delete.action", method = RequestMethod.POST)
	public void delete(String pId, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(pId)) {
			throw new ParameterIsWrongException();
		}
		boolean create = this.subjectSbsProposerService.deleteById(pId.split(","));
		PageWrite.writeTOPage(response, create);
	}

}

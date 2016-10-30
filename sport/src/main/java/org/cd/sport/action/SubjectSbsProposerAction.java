package org.cd.sport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.SubjectSbsProposerService;
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
	private SubjectSbsProposerService subjectSbsProposerService;

	@RequestMapping(value = "/sboper/list.htm", method = RequestMethod.GET)
	public String listView(String sbsId, HttpServletRequest request) {
		if (StringUtils.isBlank(sbsId)) {
			throw new ParameterIsWrongException();
		}
		List<SubjectSbsProposerVo> primaryProposers = this.subjectSbsProposerService.getBySbsId(sbsId,
				Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
		request.setAttribute("primaryProposers", primaryProposers);
		return "subject/proposer/list";
	}

	@RequestMapping(value = "/sboper/create.htm", method = RequestMethod.GET)
	public String createView(String sbsId, HttpServletRequest request) {
		List<Dic> degrees = dicService.getByPcode(Constants.Dic.DIC_DEGREES_CODE);
		List<Dic> zwDics = dicService.getByPcode(Constants.Dic.DIC_ZW_CODE);
		request.setAttribute("degrees", degrees);
		request.setAttribute("zwDics", zwDics);
		return "subject/proposer/create";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void create(HttpServletRequest request) {
	}

}

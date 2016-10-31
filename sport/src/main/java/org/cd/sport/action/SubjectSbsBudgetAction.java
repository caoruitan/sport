package org.cd.sport.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.DicType;
import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.DicTypeService;
import org.cd.sport.service.SubjectSbsBudgetService;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.SbsBudgetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 经费运算
 * 
 * @author liuyk
 *
 */
@Controller
@RequestMapping("/subject/budget")
public class SubjectSbsBudgetAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private DicTypeService dicTypeService;

	@Autowired
	private SubjectSbsBudgetService subjectSbsBudgetService;

	@RequestMapping(value = "/sboper/cost.htm", method = RequestMethod.GET)
	public String sboperListView(String sbsId, HttpServletRequest request) {
		if (StringUtils.isBlank(sbsId)) {
			throw new ParameterIsWrongException();
		}
		DicType income = this.dicTypeService.getByCode(Constants.Dic.DIC_SBS_INCOME_CODE);
		DicType cost = this.dicTypeService.getByCode(Constants.Dic.DIC_SBS_COST_CODE);
		// 经费来源
		List<Dic> incomeDics = this.dicService.getByPcode(Constants.Dic.DIC_SBS_INCOME_CODE);
		// 经费支出
		List<DicType> costDics = this.dicTypeService.getByPid(Constants.Dic.DIC_SBS_COST_CODE);
		// 经费总和
		List<Dic> costTotalDics = this.dicService.getByPcode(Constants.Dic.DIC_SBS_KYCOST_TOTAL_CODE);
		// 查询预算
		Map<String, SubjectSbsBudget> ssbMap = this.subjectSbsBudgetService.getMapBySbsId(sbsId);
		request.setAttribute("sbsId", sbsId);
		request.setAttribute("costTotalCode", Constants.Dic.DIC_SBS_KYCOST_TOTAL_CODE);
		request.setAttribute("income", income);
		request.setAttribute("cost", cost);
		request.setAttribute("incomeDics", incomeDics);
		request.setAttribute("costDics", costDics);
		request.setAttribute("costTotalDics", costTotalDics);
		request.setAttribute("ssbMap", ssbMap);
		return "subject/budget/detail";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void sboperCreate(SbsBudgetView budget, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectSbsBudgetService.create(budget);
		PageWrite.writeTOPage(response, create);
	}
}

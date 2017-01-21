package org.cd.sport.action;

import java.io.IOException;
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
import org.cd.sport.vo.Node;
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
@RequestMapping("/subject/sbsbudget")
public class SubjectSbsBudgetAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private DicTypeService dicTypeService;

	@Autowired
	private SubjectSbsBudgetService subjectSbsBudgetService;

	private void sbsCost(String sbsId, String subjectId, HttpServletRequest request) {
		if (StringUtils.isBlank(sbsId) || StringUtils.isBlank(subjectId)) {
			throw new ParameterIsWrongException();
		}
		DicType income = this.dicTypeService.getByCode(Constants.Dic.DIC_SBS_INCOME_CODE);
		// 经费来源
		List<Dic> incomeDics = this.dicService.getByPcode(Constants.Dic.DIC_SBS_INCOME_CODE);
		// 经费支出
		Node costs = this.dicService.getNodeByPcode(Constants.Dic.DIC_RWS_COST_CODE);
		// 查询预算
		Map<String, SubjectSbsBudget> ssbMap = this.subjectSbsBudgetService.getMapBySbsId(sbsId);
		request.setAttribute("sbsId", sbsId);
		request.setAttribute("subjectId", subjectId);
		request.setAttribute("costTotalCode", Constants.Dic.DIC_SBS_KYCOST_TOTAL_CODE);
		request.setAttribute("income", income);
		request.setAttribute("cost", costs);
		request.setAttribute("incomeDics", incomeDics);
		request.setAttribute("ssbMap", ssbMap);
	}

	@RequestMapping(value = "/sboper/cost.htm", method = RequestMethod.GET)
	public String sboperListView(String sbsId, String subjectId, HttpServletRequest request) {
		this.sbsCost(sbsId, subjectId, request);
		return "subject/sbsbudget/detail";
	}

	@RequestMapping(value = "/costReadOnly.htm", method = RequestMethod.GET)
	public String ssbadminListView(String sbsId, String subjectId, HttpServletRequest request) {
		this.sbsCost(sbsId, subjectId, request);
		return "subject/sbsbudget/detail_readonly";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void sboperCreate(SbsBudgetView budget, String subjectId, HttpServletRequest request,
			HttpServletResponse response) {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		boolean create = false;
		try {
			create = this.subjectSbsBudgetService.create(budget, basePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PageWrite.writeTOPage(response, create);
	}
}

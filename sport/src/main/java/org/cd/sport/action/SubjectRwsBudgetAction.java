package org.cd.sport.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.DicType;
import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.service.DicTypeService;
import org.cd.sport.service.SubjectRwsBudgetService;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.RwsBudgetView;
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
@RequestMapping("/subject/rwsbudget")
public class SubjectRwsBudgetAction {

	@Autowired
	private DicService dicService;

	@Autowired
	private DicTypeService dicTypeService;

	@Autowired
	private SubjectRwsBudgetService subjectRwsBudgetService;

	private void sbsCost(String rwsId, HttpServletRequest request) {
		if (StringUtils.isBlank(rwsId)) {
			throw new ParameterIsWrongException();
		}
		// 经费来源
		DicType income = this.dicTypeService.getByCode(Constants.Dic.DIC_SBS_INCOME_CODE);
		List<Dic> incomeDics = this.dicService.getByPcode(Constants.Dic.DIC_SBS_INCOME_CODE);
		// 经费支出
		DicType cost = this.dicTypeService.getByCode(Constants.Dic.DIC_RWS_COST_CODE);
		List<DicType> costDics = this.dicTypeService.getByPid(Constants.Dic.DIC_RWS_COST_CODE);
		// 经费总和
		List<Dic> costTotalDics = this.dicService.getByPcode(Constants.Dic.DIC_SBS_KYCOST_TOTAL_CODE);
		// 查询预算
		Map<String, SubjectRwsBudget> ssbMap = this.subjectRwsBudgetService.getMapByRwsId(rwsId);
		request.setAttribute("rwsId", rwsId);
		request.setAttribute("costTotalCode", Constants.Dic.DIC_SBS_KYCOST_TOTAL_CODE);
		request.setAttribute("income", income);
		request.setAttribute("cost", cost);
		request.setAttribute("incomeDics", incomeDics);
		request.setAttribute("costDics", costDics);
		request.setAttribute("costTotalDics", costTotalDics);
		request.setAttribute("ssbMap", ssbMap);
	}

	@RequestMapping(value = "/sboper/cost.htm", method = RequestMethod.GET)
	public String sboperListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail";
	}

	@RequestMapping(value = "/sbadmin/cost.htm", method = RequestMethod.GET)
	public String ssbadminListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail_readonly";
	}

	@RequestMapping(value = "/kjsadmin/cost.htm", method = RequestMethod.GET)
	public String kjsadminListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail_readonly";
	}

	@RequestMapping(value = "/kjsleader/cost.htm", method = RequestMethod.GET)
	public String kjsleaderListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail_readonly";
	}

	@RequestMapping(value = "/kjsexpert/cost.htm", method = RequestMethod.GET)
	public String kjsexpertListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail_readonly";
	}

	@RequestMapping(value = "/orgadmin/cost.htm", method = RequestMethod.GET)
	public String orgadminListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail_readonly";
	}

	@RequestMapping(value = "/orgoper/cost.htm", method = RequestMethod.GET)
	public String orgoperListView(String rwsId, HttpServletRequest request) {
		this.sbsCost(rwsId, request);
		return "subject/rwsbudget/detail_readonly";
	}

	@RequestMapping(value = "/sboper/create.action", method = RequestMethod.POST)
	public void sboperCreate(RwsBudgetView budget, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.subjectRwsBudgetService.create(budget);
		PageWrite.writeTOPage(response, create);
	}
}

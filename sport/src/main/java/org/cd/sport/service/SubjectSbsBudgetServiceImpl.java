package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.dao.SubjectSbsBudgetDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.utils.NullableUtils;
import org.cd.sport.view.Budget;
import org.cd.sport.view.SbsBudgetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 申报书申请人服务类
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjectSbsBudgetServiceImpl implements SubjectSbsBudgetService {

	@Autowired
	private SubjectSbsBudgetDao subjectSbsBudgetDao;

	@Autowired
	private SubjectSbsService subjectSbsService;

	@Autowired
	private SubjectService subjectService;

	@Override
	public List<SubjectSbsBudget> getBySbsId(String sbsId) {
		return this.subjectSbsBudgetDao.findBySbsId(sbsId);
	}

	@Override
	@Transactional
	public boolean create(SbsBudgetView view, String basePath) throws IOException {
		if (view == null) {
			return false;
		}
		String sbsId = view.getSbsId();
		// 处理收入
		// List<Budget> incomes = view.getIncome();
		// NullableUtils.clean(incomes);
		// if (incomes != null && !incomes.isEmpty()) {
		// for (Budget in : incomes) {
		// this.create(sbsId, view.getSubjectId(), in.getCode(), in.getCost(),
		// in.getName(), in.getReason());
		// }
		// }
		// 处理支出
		List<Budget> costs = view.getCost();
		NullableUtils.clean(costs);
		if (costs != null && !costs.isEmpty()) {
			for (Budget co : costs) {
				this.create(sbsId, view.getSubjectId(), co.getCode(), co.getCost(), co.getName(), co.getReason());
			}
		}

		Subject subject = this.subjectService.getSubjectById(view.getSubjectId());
		String sbs_prefix = "";
		if (subject.getNewState() == 2) {
			sbs_prefix = "/doc/sbs_new_";
		} else {
			sbs_prefix = "/doc/sbs_";
		}

		File file = new File(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_011.doc");
		if (!file.exists()) {
			// 封面信息修改
			FileInputStream fmIs = new FileInputStream(new File(basePath + sbs_prefix + "template_011.doc"));
			HWPFDocument hdt = new HWPFDocument(fmIs);
			Range range = hdt.getRange();
			// 填充预算信息
			for (Budget budget : view.getCost()) {
				range.replaceText("${D_" + budget.getCode().substring(3) + "}",
						budget.getReason() == null ? "" : budget.getReason());
			}
			OutputStream os = new FileOutputStream(
					basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_011.doc");
			fmIs.close();
			hdt.write(os);
			os.close();
		}
		return true;
	}

	@Override
	@Transactional
	public boolean create(String sbsId, String subjectId, String code, String cost, String name, String reason) {
		try {
			BigDecimal money = new BigDecimal(StringUtils.isBlank(cost) ? "0" : cost);
			SubjectSbs subject = subjectSbsService.getSbsBySubjectId(subjectId);
			if (subject == null) {
				subjectSbsService.createSubjectSbs(subjectId);
			}
			this.subjectSbsBudgetDao.deleteById(sbsId, code);
			SubjectSbsBudget ssb = new SubjectSbsBudget();
			ssb.setCode(code);
			ssb.setCost(money);
			ssb.setSbsId(sbsId);
			ssb.setName(name);
			ssb.setReason(reason);
			this.subjectSbsBudgetDao.save(ssb);
		} catch (Exception e) {
			throw new ParameterIsWrongException("经费格式不正确");
		}

		return true;
	}

	@Override
	@Transactional
	public boolean update(String sbsId, String subjectId, String code, String cost, String name, String reason) {
		SubjectSbs subject = subjectSbsService.getSubjectSbsById(sbsId);
		if (subject == null) {
			subjectSbsService.createSubjectSbs(subjectId);
		}
		this.subjectSbsBudgetDao.deleteById(sbsId, code);
		return this.create(sbsId, subjectId, code, cost, name, reason);
	}

	@Override
	public Map<String, SubjectSbsBudget> getMapBySbsId(String sbsId) {
		List<SubjectSbsBudget> ssbs = this.getBySbsId(sbsId);
		Map<String, SubjectSbsBudget> maps = new HashMap<String, SubjectSbsBudget>();
		if (ssbs == null || ssbs.isEmpty()) {
			return maps;
		}
		for (SubjectSbsBudget sb : ssbs) {
			maps.put(sb.getCode(), sb);
		}
		return maps;
	}

	@Override
	public boolean deleteById(String sbsId, String code) {
		return this.subjectSbsBudgetDao.deleteById(sbsId, code);
	}

	@Override
	public boolean create(SubjectSbsBudget budget) {
		this.subjectSbsBudgetDao.save(budget);
		return true;
	}
}

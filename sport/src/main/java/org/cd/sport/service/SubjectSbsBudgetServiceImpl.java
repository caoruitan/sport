package org.cd.sport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.cd.sport.dao.SubjectSbsBudgetDao;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.domain.SubjectSbsBudget;
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
	private DicService dicService;

	@Override
	public List<SubjectSbsBudget> getBySbsId(String sbsId) {
		return this.subjectSbsBudgetDao.findBySbsId(sbsId);
	}

	@Override
	@Transactional
	public boolean create(SbsBudgetView view) {
		if (view == null) {
			return false;
		}
		String sbsId = view.getSbsId();
		// 处理收入
		List<Budget> incomes = view.getIncome();
		NullableUtils.clean(incomes);
		if (incomes != null && !incomes.isEmpty()) {
			for (Budget in : incomes) {
				this.create(sbsId, in.getCode(), in.getCost(), in.getReason());
			}
		}
		// 处理支出
		List<Budget> costs = view.getCost();
		NullableUtils.clean(costs);
		if (costs != null && !costs.isEmpty()) {
			for (Budget co : costs) {
				this.create(sbsId, co.getCode(), co.getCost(), co.getReason());
			}
		}
		return true;
	}

	@Override
	@Transactional
	public boolean create(String sbsId, String code, String cost, String reason) {
		SubjectSbs subject = subjectSbsService.getSubjectSbsById(sbsId);
		if (subject == null) {
			throw new EntityNotFoundException("申报书对象不存在");
		}
		Dic dic = dicService.getByCode(code);
		if (dic == null) {
			throw new EntityNotFoundException("申报书预算类型不存在");
		}
		SubjectSbsBudget ssb = new SubjectSbsBudget();
		ssb.setCode(code);
		ssb.setSbsId(sbsId);
		ssb.setName(dic.getName());
		ssb.setReason(reason);
		this.subjectSbsBudgetDao.save(ssb);
		return true;
	}

	@Override
	@Transactional
	public boolean update(String sbsId, String code, String cost, String reason) {
		SubjectSbs subject = subjectSbsService.getSubjectSbsById(sbsId);
		if (subject == null) {
			throw new EntityNotFoundException("申报书对象不存在");
		}
		Dic dic = dicService.getByCode(code);
		if (dic == null) {
			throw new EntityNotFoundException("申报书预算类型不存在");
		}
		this.subjectSbsBudgetDao.deleteById(sbsId, code);
		return this.create(sbsId, code, cost, reason);
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
}

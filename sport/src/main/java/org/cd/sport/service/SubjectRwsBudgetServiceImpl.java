package org.cd.sport.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.dao.SubjectRwsBudgetDao;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.utils.NullableUtils;
import org.cd.sport.view.Budget;
import org.cd.sport.view.RwsBudgetView;
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
public class SubjectRwsBudgetServiceImpl implements SubjectRwsBudgetService {

	@Autowired
	private SubjectRwsBudgetDao subjectRwsBudgetDao;

	@Autowired
	private SubjectRwsService subjectRwsService;

	@Override
	public List<SubjectRwsBudget> getByRwsId(String sbsId) {
		return this.subjectRwsBudgetDao.findByRwsId(sbsId);
	}

	@Override
	@Transactional
	public boolean create(RwsBudgetView view) {
		if (view == null) {
			return false;
		}
		String rwsId = view.getRwsId();
		// 处理收入
//		List<Budget> incomes = view.getIncome();
//		NullableUtils.clean(incomes);
//		if (incomes != null && !incomes.isEmpty()) {
//			for (Budget in : incomes) {
//				this.create(rwsId, view.getSubjectId(), in.getCode(), in.getCost(), in.getName(), in.getReason());
//			}
//		}
		// 处理支出
		List<Budget> costs = view.getCost();
		NullableUtils.clean(costs);
		if (costs != null && !costs.isEmpty()) {
			for (Budget co : costs) {
				this.create(rwsId, view.getSubjectId(), co.getCode(), co.getCost(), co.getName(), co.getReason());
			}
		}
		return true;
	}

	@Override
	@Transactional
	public boolean create(String rwsId, String subjectId, String code, String cost, String name, String reason) {
		try {
			BigDecimal money = new BigDecimal(StringUtils.isBlank(cost) ? "0" : cost);
			SubjectRws rws = subjectRwsService.getSubjectRwsById(rwsId);
			if (rws == null) {
				this.subjectRwsService.createSubjectRws(subjectId);
			}
			this.subjectRwsBudgetDao.deleteById(rwsId, code);
			SubjectRwsBudget ssb = new SubjectRwsBudget();
			ssb.setCode(code);
			ssb.setCost(money);
			ssb.setRwsId(rwsId);
			ssb.setName(name);
			ssb.setReason(reason);
			this.subjectRwsBudgetDao.save(ssb);
		} catch (Exception e) {
			throw new ParameterIsWrongException("经费格式不正确");
		}

		return true;
	}

	@Override
	@Transactional
	public boolean update(String rwsId, String subjectId, String code, String cost, String name, String reason) {
		this.subjectRwsBudgetDao.deleteById(rwsId, code);
		return this.create(rwsId, subjectId, code, cost, name, reason);
	}

	@Override
	public Map<String, SubjectRwsBudget> getMapByRwsId(String sbsId) {
		List<SubjectRwsBudget> ssbs = this.getByRwsId(sbsId);
		Map<String, SubjectRwsBudget> maps = new HashMap<String, SubjectRwsBudget>();
		if (ssbs == null || ssbs.isEmpty()) {
			return maps;
		}
		for (SubjectRwsBudget sb : ssbs) {
			maps.put(sb.getCode(), sb);
		}
		return maps;
	}
}

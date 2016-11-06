package org.cd.sport.service;

import java.util.List;
import java.util.Map;

import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.view.RwsBudgetView;

/**
 * 申报预算
 * 
 * @author liuyk
 */
public interface SubjectRwsBudgetService {

	public boolean create(RwsBudgetView view);

	public boolean create(String rwsId, String subjectId, String code, String cost, String name, String reason);

	public boolean update(String rwsId, String subjectId, String code, String cost, String name, String reason);

	public List<SubjectRwsBudget> getByRwsId(String rwsId);

	public Map<String, SubjectRwsBudget> getMapByRwsId(String rwsId);

}

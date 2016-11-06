package org.cd.sport.service;

import java.util.List;
import java.util.Map;

import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.view.SbsBudgetView;

/**
 * 申报预算
 * 
 * @author liuyk
 */
public interface SubjectSbsBudgetService {

	public boolean create(SbsBudgetView view);

	public boolean create(String sbsId, String subjectId, String code, String cost, String name, String reason);

	public boolean update(String sbsId, String subjectId, String code, String cost, String name, String reason);

	public List<SubjectSbsBudget> getBySbsId(String sbsId);

	public Map<String, SubjectSbsBudget> getMapBySbsId(String sbsId);

}

package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 任务书预算
 * 
 * @author liuyk
 *
 */
public interface SubjectRwsBudgetDao extends IBaseDao {

	public boolean deleteById(String sbsId, String code);

	public List<SubjectRwsBudget> findByRwsId(String sbsId);
}

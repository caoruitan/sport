package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 申报书申请人
 * 
 * @author liuyk
 *
 */
public interface SubjectSbsBudgetDao extends IBaseDao {

	public boolean deleteById(String sbsId, String code);

	public List<SubjectSbsBudget> findBySbsId(String sbsId);
}

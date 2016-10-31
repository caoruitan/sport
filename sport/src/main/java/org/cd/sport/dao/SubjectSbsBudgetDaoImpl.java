package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 申报书预算
 * 
 * @author liuyk
 *
 */
@Repository
public class SubjectSbsBudgetDaoImpl extends BaseDaoImpl<SubjectSbsBudget> implements SubjectSbsBudgetDao {

	@Override
	public boolean deleteById(String sbsId, String code) {
		String deleteHql = "delete from SubjectSbsBudget where sbsId=:sbsId and code=:code";
		return this.getHibernateQuery(deleteHql).setParameter("sbsId", sbsId).setParameter("code", code)
				.executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectSbsBudget> findBySbsId(String sbsId) {
		String queryHql = "from SubjectSbsBudget where sbsId =:sbsId";
		return this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId).list();
	}
}

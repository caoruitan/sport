package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 申报书预算
 * 
 * @author liuyk
 *
 */
@Repository
public class SubjectRwsBudgetDaoImpl extends BaseDaoImpl<SubjectRwsBudget> implements SubjectRwsBudgetDao {

	@Override
	public boolean deleteById(String rwsId, String code) {
		String deleteHql = "delete from SubjectRwsBudget where rwsId=:rwsId and code=:code";
		return this.getHibernateQuery(deleteHql).setParameter("rwsId", rwsId).setParameter("code", code)
				.executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsBudget> findByRwsId(String rwsId) {
		String queryHql = "from SubjectRwsBudget where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).list();
	}
}

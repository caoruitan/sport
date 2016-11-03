package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
@Repository
public class SubjectRwsAppropriationDaoImpl extends BaseDaoImpl<SubjectRwsAppropriation> implements SubjectRwsAppropriationDao {

	@Override
	public boolean deleteById(String id) {
		String deleteHql = "delete from SubjectRwsAppropriation where approId=:id";
		return this.getHibernateQuery(deleteHql).setParameter("id", id).executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsAppropriation> findByRwsId(String rwsId, int start, int limit) {
		String queryHql = "from SubjectRwsAppropriation where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).setMaxResults(limit).setFirstResult(start)
				.list();
	}

	@Override
	public long findTotalByApproId(String rwsId) {
		String queryHql = "select count(1) from SubjectRwsAppropriation where rwsId=:rwsId";
		Long count = (Long) this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsAppropriation> findByRwsId(String rwsId) {
		String queryHql = "from SubjectRwsAppropriation where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).list();
	}

	@Override
	public boolean deleteById(String[] id) {
		String deleteHql = "delete from SubjectRwsAppropriation where approId in (:id)";
		return this.getHibernateQuery(deleteHql).setParameterList("id", id).executeUpdate() != 0;
	}
}

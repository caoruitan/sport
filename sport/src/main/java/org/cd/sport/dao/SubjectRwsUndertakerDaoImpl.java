package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 任务书承担者
 * 
 * @author liuyk
 *
 */
@Repository
public class SubjectRwsUndertakerDaoImpl extends BaseDaoImpl<SubjectRwsUndertaker> implements SubjectRwsUndertakerDao {

	@Override
	public boolean deleteById(String id) {
		String deleteHql = "delete from SubjectRwsUndertaker where id=:id";
		return this.getHibernateQuery(deleteHql).setParameter("id", id).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] id) {
		String deleteHql = "delete from SubjectRwsUndertaker where id in (:id)";
		return this.getHibernateQuery(deleteHql).setParameterList("id", id).executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsUndertaker> findByRwsId(String rwsId) {
		String queryHql = "from SubjectRwsUndertaker where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsUndertaker> findByRwsId(String rwsId, int start, int limit) {
		String queryHql = "from SubjectSbsProposer where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).setMaxResults(limit).setFirstResult(start)
				.list();
	}

	@Override
	public long findTotalByRwsId(String rwsId) {
		String queryHql = "select count(1) from SubjectRwsUndertaker where rwsId=:rwsId";
		Long count = (Long) this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).uniqueResult();
		return count == null ? 0 : count.intValue();
	}
}

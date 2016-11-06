package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 申报书申请人
 * 
 * @author liuyk
 *
 */
@Repository
public class SubjectSbsProposerDaoImpl extends BaseDaoImpl<SubjectSbsProposer> implements SubjectSbsProposerDao {

	@Override
	public boolean deleteById(String id) {
		String deleteHql = "delete from SubjectSbsProposer where id=:id";
		return this.getHibernateQuery(deleteHql).setParameter("id", id).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] id) {
		String deleteHql = "delete from SubjectSbsProposer where id in (:id)";
		return this.getHibernateQuery(deleteHql).setParameterList("id", id).executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectSbsProposer> findBySbsId(String sbsId) {
		String queryHql = "from SubjectSbsProposer where sbsId =:sbsId";
		return this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectSbsProposer> findBySbsId(String sbsId, int start, int limit) {
		String queryHql = "from SubjectSbsProposer where sbsId =:sbsId";
		return this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId).setMaxResults(limit).setFirstResult(start)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectSbsProposer> findBySbsId(String sbsId, String primary) {
		String queryHql = "from SubjectSbsProposer where sbsId =:sbsId and primary=:primary order by sort asc ";
		return this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId).setParameter("primary", primary).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectSbsProposer> findBySbsId(String sbsId, String primary, int start, int limit) {
		String queryHql = "from SubjectSbsProposer where sbsId =:sbsId and primary=:primary";
		return this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId).setParameter("primary", primary)
				.setMaxResults(limit).setFirstResult(start).list();
	}

	@Override
	public long findTotalBySbsId(String sbsId) {
		String queryHql = "select count(1) from SubjectSbsProposer where sbsId=:sbsId";
		Long count = (Long) this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public long findTotalBySbsId(String sbsId, String primary) {
		String queryHql = "select count(1) from SubjectSbsProposer where sbsId=:sbsId and primary=:primary";
		Long count = (Long) this.getHibernateQuery(queryHql).setParameter("sbsId", sbsId)
				.setParameter("primary", primary).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public synchronized int findMaxSort(String primary) {
		String queryHql = "select max(sort) from SubjectSbsProposer where primary=:primary";
		Integer count = (Integer) this.getHibernateQuery(queryHql).setParameter("primary", primary).uniqueResult();
		return count == null ? 0 : count.intValue();
	}
}

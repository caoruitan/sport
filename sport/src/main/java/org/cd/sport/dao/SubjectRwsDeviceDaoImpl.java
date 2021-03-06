package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
@Repository
public class SubjectRwsDeviceDaoImpl extends BaseDaoImpl<SubjectRwsDevice> implements SubjectRwsDeviceDao {

	@Override
	public boolean deleteById(String id) {
		String deleteHql = "delete from SubjectRwsDevice where dId=:id";
		return this.getHibernateQuery(deleteHql).setParameter("id", id).executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsDevice> findByRwsId(String rwsId, int start, int limit) {
		String queryHql = "from SubjectRwsDevice where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).setMaxResults(limit).setFirstResult(start)
				.list();
	}

	@Override
	public long findTotalByRwsId(String rwsId) {
		String queryHql = "select count(1) from SubjectRwsDevice where rwsId=:rwsId";
		Long count = (Long) this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectRwsDevice> findByRwsId(String rwsId) {
		String queryHql = "from SubjectRwsDevice where rwsId =:rwsId";
		return this.getHibernateQuery(queryHql).setParameter("rwsId", rwsId).list();
	}

	@Override
	public boolean deleteById(String[] id) {
		String deleteHql = "delete from SubjectRwsDevice where dId in (:id)";
		return this.getHibernateQuery(deleteHql).setParameterList("id", id).executeUpdate() != 0;
	}
}

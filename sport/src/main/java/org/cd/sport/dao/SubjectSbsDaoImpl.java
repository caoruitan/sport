package org.cd.sport.dao;

import java.util.HashMap;
import java.util.Map;

import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectSbsDaoImpl extends BaseDaoImpl<SubjectSbs> implements SubjectSbsDao {

	@Override
	public boolean deleteById(String sbsId) {
		String hql = "delete from SubjectSbs where sbsId=:sbsId";
		return this.getHibernateQuery(hql).setParameter("sbsId", sbsId).executeUpdate() != 0;
	}

	@Override
	public SubjectSbs getSbsBySubjectId(String subjectId) {
		String hql = "from SubjectSbs ss where ss.subjectId = :subjectId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("subjectId", subjectId);
		return this.getDataByHQL(hql, params);
	}

}

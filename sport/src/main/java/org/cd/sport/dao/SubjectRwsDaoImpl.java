package org.cd.sport.dao;

import java.util.HashMap;
import java.util.Map;

import org.cd.sport.domain.SubjectRws;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRwsDaoImpl extends BaseDaoImpl<SubjectRws> implements SubjectRwsDao {

	@Override
	public boolean deleteById(String sbsId) {
		String hql = "delete from SubjectRws where sbsId=:sbsId";
		return this.getHibernateQuery(hql).setParameter("sbsId", sbsId).executeUpdate() != 0;
	}

	@Override
	public SubjectRws getRwsBySubjectId(String subjectId) {
		String hql = "from SubjectRws ss where ss.subjectId = :subjectId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("subjectId", subjectId);
		return this.getDataByHQL(hql, params);
	}

}

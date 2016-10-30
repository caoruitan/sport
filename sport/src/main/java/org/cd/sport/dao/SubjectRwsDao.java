package org.cd.sport.dao;

import org.cd.sport.domain.SubjectRws;
import org.cd.sport.hibernate.IBaseDao;

public interface SubjectRwsDao extends IBaseDao {

	public boolean deleteById(String id);

	public SubjectRws getRwsBySubjectId(String subjectId);

}

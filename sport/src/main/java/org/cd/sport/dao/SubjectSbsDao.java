package org.cd.sport.dao;

import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.hibernate.IBaseDao;

public interface SubjectSbsDao extends IBaseDao {

	public boolean deleteById(String id);

	public SubjectSbs getSbsBySubjectId(String subjectId);

}

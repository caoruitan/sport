package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.Subject;
import org.cd.sport.hibernate.IBaseDao;

public interface SubjectDao extends IBaseDao {
	
	public List<Subject> getAllSubjectList(String year, String type, String stage, int start, int limit);
	
	public List<Subject> getSubjectListByCreator(String userId, String year, String type, String stage, int start, int limit);
	
	public List<Subject> getSubjectListByCreateUnit(String unitId, String year, String type, String stage, int start, int limit);
	
	public List<Subject> getSubjectListByOrg(String orgId, String year, String type, String stage, int start, int limit);
	
}

package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.Subject;
import org.cd.sport.hibernate.IBaseDao;

public interface SubjectDao extends IBaseDao {
	
	public void updateEndDates(String[] subjectIds, String sbsEndDate, String rwsEndDate, String subjectEndDate);
	
	public boolean deleteSubjectById(String sId);
	
	public List<Subject> getAllSubjectList(String year, String type, String stage, int start, int limit);
	
	public int getAllSubjectCount(String year, String type, String stage);
	
	public List<Subject> getSubjectListByCreator(String userId, String year, String type, String stage, int start, int limit);
	
	public int getSubjectCountByCreator(String userId, String year, String type, String stage);
	
	public List<Subject> getSubjectListByCreateUnit(String unitId, String year, String type, String stage, int start, int limit);
	
	public int getSubjectCountByCreateUnit(String unitId, String year, String type, String stage);
	
	public List<Subject> getSubjectListByOrg(String orgId, String year, String type, String stage, int start, int limit);
	
	public int getSubjectCountByOrg(String orgId, String year, String type, String stage);
	
	public int getAllSubjectCount();
	
	public int getAllSubjectCountByType(String type);
	
	public int getAllSubjectCountByTypeAndStage(String type, String stage);
	
	public int getSubjectCountByExpert(String expertId);
	
	public int getSubjectCountByExpertAndStage(String expertId, String stage);
	
	public int getSubjectCountByOrg(String orgId);
	
	public int getSubjectCountByOrgAndStage(String orgId, String stage);
	
	public int getSubjectCountByCreateUnit(String unitId);

	public int getSubjectCountByCreateUnitAndStage(String unitId, String stage);
	
	public int getSubjectCountByCreator(String creator);

	public int getSubjectCountByCreatorAndStage(String creator, String stage);
	
	public String getMaxSubjectNum(String year, String type);
	
}

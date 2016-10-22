package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.Subject;
import org.cd.sport.vo.SubjectVo;

/**
 * 课题服务类
 * @author caort
 *
 */
public interface SubjectService {
	
	public List<Subject> getAllSubjectList(String year, String type, String stage, int start, int limit);
	
	public int getAllSubjectCount(String year, String type, String stage);
	
	public List<Subject> getSubjectListByCreator(String userId, String year, String type, String stage, int start, int limit);
	
	public int getSubjectCountByCreator(String userId, String year, String type, String stage);
	
	public List<Subject> getSubjectListByCreateUnit(String unitId, String year, String type, String stage, int start, int limit);
	
	public int getSubjectCountByCreateUnit(String unitId, String year, String type, String stage);
	
	public List<Subject> getSubjectListByOrg(String orgId, String year, String type, String stage, int start, int limit);
	
	public int getSubjectCountByOrg(String orgId, String year, String type, String stage, int start, int limit);
	
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
	
	public Subject getSubjectById(String id);
	
	public Subject createSubject(SubjectVo subjectVo);
	
}

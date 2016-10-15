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
	
	public List<Subject> getSubjectListByCreator(String userId, String year, String type, String stage, int start, int limit);
	
	public List<Subject> getSubjectListByCreateUnit(String unitId, String year, String type, String stage, int start, int limit);
	
	public List<Subject> getSubjectListByOrg(String orgId, String year, String type, String stage, int start, int limit);
	
	public Subject createSubject(SubjectVo subjectVo);
	
}

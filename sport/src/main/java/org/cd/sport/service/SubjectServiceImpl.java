package org.cd.sport.service;

import java.util.Date;
import java.util.List;

import org.cd.sport.dao.SubjectDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.support.SubjectSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectServiceImpl extends SubjectSupport implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public List<Subject> getAllSubjectList(String year, String type, String stage, int start, int limit) {
		return subjectDao.getAllSubjectList(year, type, stage, start, limit);
	}

	@Override
	public List<Subject> getSubjectListByCreator(String userId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectListByCreator(userId, year, type, stage, start, limit);
	}

	@Override
	public List<Subject> getSubjectListByCreateUnit(String unitId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectListByCreateUnit(unitId, year, type, stage, start, limit);
	}

	@Override
	public List<Subject> getSubjectListByOrg(String orgId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectListByOrg(orgId, year, type, stage, start, limit);
	}

	@Override
	public Subject createSubject(SubjectVo subjectVo) {
		Subject subject = this.process(subjectVo);
		UserDomain userDomain = AuthenticationUtils.getUser();
		subject.setCreator(userDomain.getUserId());
		subject.setCreateUnitId(userDomain.getOrganization());
		subject.setCreateTime(new Date());
		subject.setStage("CREATE");
		subjectDao.save(subject);
		return subject;
	}

}

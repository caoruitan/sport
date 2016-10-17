package org.cd.sport.service;

import java.util.Date;
import java.util.List;

import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.support.SubjectSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.vo.SubjectVo;
import org.cd.sport.vo.UserVo;
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
	public int getAllSubjectCount(String year, String type, String stage) {
		return subjectDao.getAllSubjectCount(year, type, stage);
	}

	@Override
	public List<Subject> getSubjectListByCreator(String userId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectListByCreator(userId, year, type, stage, start, limit);
	}

	@Override
	public int getSubjectCountByCreator(String userId, String year, String type, String stage) {
		return subjectDao.getSubjectCountByCreator(userId, year, type, stage);
	}

	@Override
	public List<Subject> getSubjectListByCreateUnit(String unitId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectListByCreateUnit(unitId, year, type, stage, start, limit);
	}

	@Override
	public int getSubjectCountByCreateUnit(String unitId, String year, String type, String stage) {
		return subjectDao.getSubjectCountByCreateUnit(unitId, year, type, stage);
	}

	@Override
	public List<Subject> getSubjectListByOrg(String orgId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectListByOrg(orgId, year, type, stage, start, limit);
	}

	@Override
	public int getSubjectCountByOrg(String orgId, String year, String type, String stage, int start, int limit) {
		return subjectDao.getSubjectCountByOrg(orgId, year, type, stage, start, limit);
	}

	@Override
	public Subject getSubjectById(String id) {
		return subjectDao.getEntityById(Subject.class, id);
	}

	@Override
	public Subject createSubject(SubjectVo subjectVo) {
		Subject subject = this.process(subjectVo);
		UserVo userDomain = AuthenticationUtils.getUser();
		subject.setCreator(userDomain.getLoginName());
		subject.setCreateUnitId(userDomain.getOrganization());
		subject.setCreateTime(new Date());
		subject.setStage(Constants.Subject.SUBJECT_STAGE_RWSTB);
		subjectDao.save(subject);
		return subject;
	}

}
package org.cd.sport.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.OrganizationDomain;
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
	
	@Autowired
	private DicService dicService;
	
	@Autowired
	private OrganizationService organizationService;

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
	public int getSubjectCountByOrg(String orgId, String year, String type, String stage) {
		return subjectDao.getSubjectCountByOrg(orgId, year, type, stage);
	}

	@Override
	public int getAllSubjectCount() {
		return subjectDao.getAllSubjectCount();
	}

	@Override
	public int getAllSubjectCountByType(String type) {
		return subjectDao.getAllSubjectCountByType(type);
	}

	@Override
	public int getAllSubjectCountByTypeAndStage(String type, String stage) {
		return subjectDao.getAllSubjectCountByTypeAndStage(type, stage);
	}

	@Override
	public int getSubjectCountByExpert(String expertId) {
		return subjectDao.getSubjectCountByExpert(expertId);
	}

	@Override
	public int getSubjectCountByExpertAndStage(String expertId, String stage) {
		return subjectDao.getSubjectCountByExpertAndStage(expertId, stage);
	}

	@Override
	public int getSubjectCountByOrg(String orgId) {
		return subjectDao.getSubjectCountByOrg(orgId);
	}

	@Override
	public int getSubjectCountByOrgAndStage(String orgId, String stage) {
		return subjectDao.getSubjectCountByOrgAndStage(orgId, stage);
	}

	@Override
	public int getSubjectCountByCreateUnit(String unitId) {
		return subjectDao.getSubjectCountByCreateUnit(unitId);
	}

	@Override
	public int getSubjectCountByCreateUnitAndStage(String unitId, String stage) {
		return subjectDao.getSubjectCountByCreateUnitAndStage(unitId, stage);
	}

	@Override
	public int getSubjectCountByCreator(String creator) {
		return subjectDao.getSubjectCountByCreator(creator);
	}

	@Override
	public int getSubjectCountByCreatorAndStage(String creator, String stage) {
		return subjectDao.getSubjectCountByCreatorAndStage(creator, stage);
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
		subject.setCreatorName(userDomain.getUserName());
		subject.setCreateUnitId(userDomain.getOrganization());
		subject.setCreateUnitName(userDomain.getOrgName());
		subject.setCreateTime(new Date());
		subject.setStage(Constants.Subject.SUBJECT_STAGE_SBSTB);
		subject.setNewState(2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
				Dic sbsDic = this.dicService.getByCode(Constants.Subject.SUBJECT_ZBKT_SBS_END_DATE_CODE);
				subject.setSbsEndDate(sdf.parse(sbsDic.getValue()));
				Dic rwsDic = this.dicService.getByCode(Constants.Subject.SUBJECT_ZBKT_RWS_END_DATE_CODE);
				subject.setRwsEndDate(sdf.parse(rwsDic.getValue()));
				Dic subjectDic = this.dicService.getByCode(Constants.Subject.SUBJECT_ZBKT_END_DATE_CODE);
				subject.setSubjectEndDate(sdf.parse(subjectDic.getValue()));
			} else if(subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
				Dic sbsDic = this.dicService.getByCode(Constants.Subject.SUBJECT_KYGGKT_SBS_END_DATE_CODE);
				subject.setSbsEndDate(sdf.parse(sbsDic.getValue()));
				Dic rwsDic = this.dicService.getByCode(Constants.Subject.SUBJECT_KYGGKT_RWS_END_DATE_CODE);
				subject.setRwsEndDate(sdf.parse(rwsDic.getValue()));
				Dic subjectDic = this.dicService.getByCode(Constants.Subject.SUBJECT_KYGGKT_END_DATE_CODE);
				subject.setSubjectEndDate(sdf.parse(subjectDic.getValue()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Dic security = this.dicService.getByCode(subject.getSecurity());
		subject.setSecurityName(security.getName());
		
		List<Dic> resultList = this.dicService.getByPcode(Constants.Dic.DIC_EXPECT_CODE);
		Map<String, String> resultMap = new HashMap<String, String>();
		for(Dic dic : resultList) {
			resultMap.put(dic.getCode(), dic.getName());
		}
		
		String results = subject.getResults();
		if(results != null && !results.equals("")) {
			String resultsName = "";
			for(String result : results.split(",")) {
				resultsName += resultMap.get(result) + ",";
			}
			resultsName = resultsName.substring(0, resultsName.length() - 1);
			subject.setResultsName(resultsName);
		}
		
		OrganizationDomain org = this.organizationService.getById(subject.getOrganizationId());
		subject.setOrganizationName(org.getFullName());
		
		subjectDao.save(subject);
		return subject;
	}

}

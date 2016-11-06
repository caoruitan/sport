package org.cd.sport.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.dao.SubjectRwsAppropriationDao;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.support.SubjectRwsSupport;
import org.cd.sport.view.SubjectRwsAppropriationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjectRwsAppropriationServiceImpl extends SubjectRwsSupport implements SubjectRwsAppropriationService {

	@Autowired
	private SubjectRwsAppropriationDao subjectRwsAppropriationDao;

	@Autowired
	private SubjectRwsService SubjectRwsService;

	@Override
	@Transactional
	public boolean create(SubjectRwsAppropriationView ss) {
		SubjectRwsAppropriation process = this.process(ss);
		SubjectRws rws = this.SubjectRwsService.getRwsBySubjectId(ss.getSubjectId());
		if (rws == null) {
			this.SubjectRwsService.createSubjectRws(ss.getSubjectId());
		}
		this.subjectRwsAppropriationDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(SubjectRwsAppropriationView ss) {
		SubjectRwsAppropriation entity = this.subjectRwsAppropriationDao.getEntityById(SubjectRwsAppropriation.class,
				ss.getApproId());
		this.process(entity, ss);
		this.subjectRwsAppropriationDao.update(entity);
		return false;
	}

	@Override
	@Transactional
	public boolean deleteById(String id) {
		return this.subjectRwsAppropriationDao.deleteById(id);
	}

	@Override
	@Transactional
	public boolean deleteById(String[] id) {
		if (id == null || id.length == 0) {
			return false;
		}
		return this.subjectRwsAppropriationDao.deleteById(id);
	}

	@Override
	public List<SubjectRwsAppropriation> getByRwsId(String approId) {
		return this.subjectRwsAppropriationDao.findByRwsId(approId);
	}

	@Override
	public long getTotalByRwsId(String approId) {
		return this.subjectRwsAppropriationDao.findTotalByApproId(approId);
	}

	@Override
	public SubjectRwsAppropriation getById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return this.subjectRwsAppropriationDao.getEntityById(SubjectRwsAppropriation.class, id);
	}
}

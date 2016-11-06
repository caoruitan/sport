package org.cd.sport.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.dao.SubjectRwsScheduleDao;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.support.SubjectRwsSupport;
import org.cd.sport.view.SubjectRwsScheduleView;
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
public class SubjectRwsScheduleServiceImpl extends SubjectRwsSupport implements SubjectRwsScheduleService {

	@Autowired
	private SubjectRwsScheduleDao subjectRwsSchedulingDao;

	@Autowired
	private SubjectRwsService subjectRwsService;

	@Override
	@Transactional
	public boolean create(SubjectRwsScheduleView ss) {
		SubjectRwsSchedule process = this.process(ss);
		SubjectRws rws = this.subjectRwsService.getRwsBySubjectId(ss.getSubjectId());
		if (rws == null) {
			this.subjectRwsService.createSubjectRws(ss.getSubjectId());
		}
		this.subjectRwsSchedulingDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(SubjectRwsScheduleView ss) {
		SubjectRwsSchedule entity = this.subjectRwsSchedulingDao.getEntityById(SubjectRwsSchedule.class, ss.getsId());
		this.process(entity, ss);
		this.subjectRwsSchedulingDao.update(entity);
		return false;
	}

	@Override
	@Transactional
	public boolean deleteById(String id) {
		return this.subjectRwsSchedulingDao.deleteById(id);
	}

	@Override
	@Transactional
	public boolean deleteById(String[] id) {
		if (id == null || id.length == 0) {
			return false;
		}
		return this.subjectRwsSchedulingDao.deleteById(id);
	}

	@Override
	public List<SubjectRwsSchedule> getByRwsId(String rwsId) {
		return this.subjectRwsSchedulingDao.findByRwsId(rwsId);
	}

	@Override
	public long getTotalByRwsId(String rwsId) {
		return this.subjectRwsSchedulingDao.findTotalByRwsId(rwsId);
	}

	@Override
	public SubjectRwsSchedule getById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return this.subjectRwsSchedulingDao.getEntityById(SubjectRwsSchedule.class, id);
	}
}

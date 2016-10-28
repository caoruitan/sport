package org.cd.sport.service;

import java.util.List;

import org.cd.sport.dao.SubjectSbsProposerDao;
import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.support.SubjectSbsProposerSupport;
import org.cd.sport.view.SubjectSbsProposerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 申报书申请人服务类
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjectSbsProposerServiceImpl extends SubjectSbsProposerSupport implements SubjectSbsProposerService {

	@Autowired
	private SubjectSbsProposerDao subjectSbsProposerDao;

	@Override
	public boolean create(SubjectSbsProposerView view) {
		SubjectSbsProposer process = this.process(view);
		process.setId(null);
		this.subjectSbsProposerDao.save(process);
		return true;
	}

	@Override
	public boolean update(SubjectSbsProposerView view) {
		SubjectSbsProposer process = this.process(view);
		process.setId(view.getId());
		this.subjectSbsProposerDao.update(process);
		return true;
	}

	@Override
	public boolean deleteById(String id) {
		return this.subjectSbsProposerDao.deleteById(id);
	}

	@Override
	public boolean deleteById(String[] id) {
		return this.subjectSbsProposerDao.deleteById(id);
	}

	@Override
	public List<SubjectSbsProposer> getBySbsId(String sbsId) {
		return this.subjectSbsProposerDao.findBySbsId(sbsId);
	}

	@Override
	public List<SubjectSbsProposer> getBySbsId(String sbsId, int start, int limit) {
		return this.subjectSbsProposerDao.findBySbsId(sbsId, start, limit);
	}

	@Override
	public List<SubjectSbsProposer> getBySbsId(String sbsId, String primary) {
		return this.subjectSbsProposerDao.findBySbsId(sbsId, primary);
	}

	@Override
	public List<SubjectSbsProposer> getBySbsId(String sbsId, String primary, int start, int limit) {
		return this.subjectSbsProposerDao.findBySbsId(sbsId, primary, start, limit);
	}

	@Override
	public long getTotalBySbsId(String sbsId) {
		return this.subjectSbsProposerDao.findTotalBySbsId(sbsId);
	}

	@Override
	public long getTotalBySbsId(String sbsId, String primary) {
		return this.subjectSbsProposerDao.findTotalBySbsId(sbsId, primary);
	}
}

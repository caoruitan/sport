package org.cd.sport.service;

import java.util.List;

import org.cd.sport.dao.SubjectSbsProposerDao;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.support.SubjectSbsSupport;
import org.cd.sport.view.SubjectSbsProposerView;
import org.cd.sport.vo.SubjectSbsProposerVo;
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
public class SubjectSbsProposerServiceImpl extends SubjectSbsSupport implements SubjectSbsProposerService {

	@Autowired
	private SubjectSbsProposerDao subjectSbsProposerDao;

	@Autowired
	private SubjectSbsService subjectSbsService;

	@Override
	@Transactional
	public boolean create(SubjectSbsProposerView view) {
		SubjectSbsProposer process = this.process(view);
		process.setId(null);
		SubjectSbs subject = this.subjectSbsService.getSbsBySubjectId(view.getSubjectId());
		if (subject == null) {
			this.subjectSbsService.createSubjectSbs(view.getSubjectId());
		}
		this.subjectSbsProposerDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(SubjectSbsProposerView view) {
		SubjectSbsProposer proposer = this.getById(view.getId());
		this.process(proposer, view);
		this.subjectSbsProposerDao.update(proposer);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(String id) {
		return this.subjectSbsProposerDao.deleteById(id);
	}

	@Override
	@Transactional
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
	public List<SubjectSbsProposerVo> getBySbsId(String sbsId, String primary) {
		List<SubjectSbsProposer> ps = this.subjectSbsProposerDao.findBySbsId(sbsId, primary);
		return this.processVo(ps);
	}

	@Override
	public List<SubjectSbsProposerVo> getBySbsId(String sbsId, String primary, int start, int limit) {
		List<SubjectSbsProposer> ps = this.subjectSbsProposerDao.findBySbsId(sbsId, primary, start, limit);
		return this.processVo(ps);
	}

	@Override
	public long getTotalBySbsId(String sbsId) {
		return this.subjectSbsProposerDao.findTotalBySbsId(sbsId);
	}

	@Override
	public long getTotalBySbsId(String sbsId, String primary) {
		return this.subjectSbsProposerDao.findTotalBySbsId(sbsId, primary);
	}

	@Override
	public SubjectSbsProposer getById(String id) {
		return this.subjectSbsProposerDao.getEntityById(SubjectSbsProposer.class, id);
	}
}

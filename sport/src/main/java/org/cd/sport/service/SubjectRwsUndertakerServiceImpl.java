package org.cd.sport.service;

import java.util.List;

import org.cd.sport.dao.SubjectRwsUndertakerDao;
import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.support.SubjectRwsSupport;
import org.cd.sport.view.SubjectRwsUndertakerView;
import org.cd.sport.vo.SubjectRwsUndertakerVo;
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
public class SubjectRwsUndertakerServiceImpl extends SubjectRwsSupport implements SubjectRwsUndertakerService {

	@Autowired
	private SubjectRwsUndertakerDao subjectRwsUndertakerDao;

	@Override
	@Transactional
	public boolean create(SubjectRwsUndertakerView view) {
		SubjectRwsUndertaker process = this.process(view);
		process.setId(null);
		this.subjectRwsUndertakerDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(SubjectRwsUndertakerView view) {
		SubjectRwsUndertaker proposer = this.getById(view.getId());
		this.process(proposer, view);
		this.subjectRwsUndertakerDao.update(proposer);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(String id) {
		return this.subjectRwsUndertakerDao.deleteById(id);
	}

	@Override
	@Transactional
	public boolean deleteById(String[] id) {
		return this.subjectRwsUndertakerDao.deleteById(id);
	}

	@Override
	public List<SubjectRwsUndertakerVo> getByRwsId(String sbsId) {
		List<SubjectRwsUndertaker> datas = this.subjectRwsUndertakerDao.findByRwsId(sbsId);
		return this.processVo(datas);
	}

	@Override
	public List<SubjectRwsUndertaker> getByRwsId(String sbsId, int start, int limit) {
		return this.subjectRwsUndertakerDao.findByRwsId(sbsId, start, limit);
	}

	@Override
	public long getTotalByRwsId(String sbsId) {
		return this.subjectRwsUndertakerDao.findTotalByRwsId(sbsId);
	}

	@Override
	public SubjectRwsUndertaker getById(String id) {
		return this.subjectRwsUndertakerDao.getEntityById(SubjectRwsUndertaker.class, id);
	}
}

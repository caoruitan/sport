package org.cd.sport.service;

import org.cd.sport.dao.SubjectSbsDao;
import org.cd.sport.domain.SubjectSbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectSbsServiceImpl implements SubjectSbsService {

	@Autowired
	private SubjectSbsDao subjectSbsDao;

	@Override
	public SubjectSbs getSubjectSbsById(String id) {
		return this.subjectSbsDao.getEntityById(SubjectSbs.class, id);
	}
	
	public SubjectSbs getSbsBySubjectId(String subjectId) {
		return this.subjectSbsDao.getSbsBySubjectId(subjectId);
	}

	@Override
	public SubjectSbs createSubjectSbs(String subjectId) {
		SubjectSbs sbs = new SubjectSbs();
		sbs.setSubjectId(subjectId);
		this.subjectSbsDao.save(sbs);
		return sbs;
	}
	
	private SubjectSbs getOrCreateSubjectSbs(String subjectId) {
		SubjectSbs sbs = this.getSbsBySubjectId(subjectId);
		if(sbs == null) {
			return this.createSubjectSbs(subjectId);
		} else {
			return sbs;
		}
	}
	
	@Override
	public void saveBaseInfo(String subjectId, String address, String phone, String fax, String email, String years) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setAddress(address);
		sbs.setPhone(phone);
		sbs.setFax(fax);
		sbs.setEmail(email);
		sbs.setYears(years);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveXtyj(String subjectId, String xtyj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setXtyj(xtyj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveYjmb(String subjectId, String yjmb) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setYjmb(yjmb);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveJsgj(String subjectId, String jsgj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setJsgj(jsgj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveYjff(String subjectId, String yjff) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setYjff(yjff);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveSyfa(String subjectId, String syfa) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setSyfa(syfa);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveJdap(String subjectId, String jdap) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setJdap(jdap);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveYqjg(String subjectId, String yqjg) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setYqjg(yqjg);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveGztj(String subjectId, String gztj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setGztj(gztj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveTjyj(String subjectId, String tjyj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setTjyj(tjyj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void submit(String sbsId) {
		
	}

}

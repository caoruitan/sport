package org.cd.sport.service;

import org.cd.sport.dao.SubjectRwsDao;
import org.cd.sport.domain.SubjectRws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectRwsServiceImpl implements SubjectRwsService {

	@Autowired
	private SubjectRwsDao subjectRwsDao;

	@Override
	public SubjectRws getSubjectRwsById(String id) {
		return this.subjectRwsDao.getEntityById(SubjectRws.class, id);
	}

	@Override
	public SubjectRws createSubjectRws(String subjectId) {
		SubjectRws rws = new SubjectRws();
		rws.setSubjectId(subjectId);
		this.subjectRwsDao.save(rws);
		return rws;
	}
	
	private SubjectRws getOrCreateSubjectRws(String rwsId, String subjectId) {
		SubjectRws rws = this.getSubjectRwsById(rwsId);
		if(rws == null) {
			return this.createSubjectRws(subjectId);
		} else {
			return rws;
		}
	}
	
	@Override
	public void saveBaseInfo(String rwsId, String subjectId, String address, String phone, String fax, String email, String years) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setAddress(address);
		rws.setPhone(phone);
		rws.setFax(fax);
		rws.setEmail(email);
		rws.setYears(years);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveXtyj(String rwsId, String subjectId, String xtyj) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setXtyj(xtyj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYjmb(String rwsId, String subjectId, String yjmb) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setYjmb(yjmb);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveJsgj(String rwsId, String subjectId, String jsgj) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setJsgj(jsgj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYjff(String rwsId, String subjectId, String yjff) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setYjff(yjff);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveSyfa(String rwsId, String subjectId, String syfa) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setSyfa(syfa);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveJdap(String rwsId, String subjectId, String jdap) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setJdap(jdap);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYqjg(String rwsId, String subjectId, String yqjg) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setYqjg(yqjg);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveGztj(String rwsId, String subjectId, String gztj) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setGztj(gztj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveTjyj(String rwsId, String subjectId, String tjyj) {
		SubjectRws rws = getOrCreateSubjectRws(rwsId, subjectId);
		rws.setTjyj(tjyj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void submit(String rwsId) {
		
	}

}

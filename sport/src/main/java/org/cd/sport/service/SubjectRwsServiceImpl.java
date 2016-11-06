package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.dao.SubjectRwsDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectRws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectRwsServiceImpl implements SubjectRwsService {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectRwsDao subjectRwsDao;

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public SubjectRws getSubjectRwsById(String id) {
		return this.subjectRwsDao.getEntityById(SubjectRws.class, id);
	}

	@Override
	public SubjectRws getRwsBySubjectId(String subjectId) {
		return this.subjectRwsDao.getRwsBySubjectId(subjectId);
	}

	@Override
	public SubjectRws createSubjectRws(String subjectId) {
		SubjectRws rws = new SubjectRws();
		rws.setSubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_SBOPER_TB);
		this.subjectRwsDao.save(rws);
		return rws;
	}

	private SubjectRws getOrCreateSubjectRws(String subjectId) {
		SubjectRws rws = this.getRwsBySubjectId(subjectId);
		if (rws == null) {
			return this.createSubjectRws(subjectId);
		} else {
			return rws;
		}
	}

	@Override
	public void saveBaseInfo(String subjectId, String address, String phone, String cooperateOrg) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setAddress(address);
		rws.setPhone(phone);
		rws.setCooperateOrg(cooperateOrg);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYjmb(String subjectId, String yjmb) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setYjmb(yjmb);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveJsgj(String subjectId, String jsgj) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setJsgj(jsgj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYjff(String subjectId, String yjff) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setYjff(yjff);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveSyfa(String subjectId, String syfa) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setSyfa(syfa);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYqjg(String subjectId, String yqjg) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setYqjg(yqjg);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveGztj(String subjectId, String gztj) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setGztj(gztj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void checkAndSubmit(String subjectId, String basePath) {
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectRws rws = this.getRwsBySubjectId(subjectId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		FileInputStream in = null;
		OutputStream os = null;
		try {
			in = new FileInputStream(new File(basePath + Constants.SubjectRws.SUBJECT_RWS_DOC_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			range.replaceText("${subjectName}", subject.getName());
			range.replaceText("${createUnitName}", subject.getCreateUnitName());
			range.replaceText("${creatorName}", subject.getCreatorName());
			range.replaceText("${address}", rws.getAddress());
			range.replaceText("${phone}", rws.getPhone());
			range.replaceText("${createTime}", sdf.format(subject.getCreateTime()));
			range.replaceText("${yjmb}", rws.getYjmb());
			range.replaceText("${jsgj}", rws.getJsgj());
			range.replaceText("${yjff}", rws.getYjff());
			range.replaceText("${syfa}", rws.getSyfa());
			range.replaceText("${yqjg}", rws.getYqjg());
			range.replaceText("${gztj}", rws.getGztj());
			os = new FileOutputStream(basePath + "/doc/rws_" + subject.getId() + subject.getCreator() + ".doc");
			hdt.write(os);

			rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_SBADMIN_SP);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sbadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
			rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_KJS_SP);
			this.subjectRwsDao.update(rws);
		} else if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
			rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_ORG_SP);
			this.subjectRwsDao.update(rws);
		}
	}

	@Override
	public void sbadminUnpass(String subjectId, String comment) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
		rws.setComment(comment);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void orgadminPass(String subjectId) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_KJS_SP);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void orgadminUnpass(String subjectId, String comment) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
		rws.setComment(comment);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void kjsadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_COMPLETE);
		this.subjectRwsDao.update(rws);
		subject.setStage(Constants.Subject.SUBJECT_STAGE_JTBG);
		this.subjectDao.save(subject);
	}

	@Override
	public void kjsadminUnpass(String subjectId, String comment) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
		rws.setComment(comment);
		this.subjectRwsDao.update(rws);
	}

}

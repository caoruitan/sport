package org.cd.sport.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectConclusionDao;
import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.view.FileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 结题报告业务
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjectConclusionServiceImpl implements SubjectConclusionService {

	@Autowired
	private SubjectConclusionDao subjectConclusionDao;

	@Override
	@Transactional
	public SubjectConclusion createSubjectConclusion(String subjectId) {
		SubjectConclusion sc = this.subjectConclusionDao.findSubjectConclusionBySubjectId(subjectId);
		if (sc != null) {
			return sc;
		}
		sc = new SubjectConclusion();
		sc.setSubjectId(subjectId);
		sc.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_SBOPER_TB);
		this.subjectConclusionDao.save(sc);
		return sc;
	}

	@Override
	@Transactional
	public boolean createConclusionAttachment(List<SubjectConclusionAttachment> cas) {
		if (cas == null || cas.isEmpty()) {
			return false;
		}
		for (SubjectConclusionAttachment cs : cas) {
			this.subjectConclusionDao.save(cs);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean createConclusionAttachment(String conclusionId, List<FileView> files) {
		if (StringUtils.isBlank(conclusionId) || files == null || files.isEmpty()) {
			return false;
		}
		SubjectConclusion sc = getSubjectConclusionById(conclusionId);
		if (sc == null) {
			throw new EntityNotFoundException("结题报告不存在");
		}
		List<SubjectConclusionAttachment> cas = new ArrayList<SubjectConclusionAttachment>();
		for (FileView fileView : files) {
			SubjectConclusionAttachment sa = new SubjectConclusionAttachment();
			sa.setConclusionId(conclusionId);
			sa.setFileId(fileView.getId());
			sa.setFileName(fileView.getName());
			sa.setPath(fileView.getPath());
			sa.setSubjectId(sc.getSubjectId());
			cas.add(sa);
		}
		return this.createConclusionAttachment(cas);
	}

	@Override
	@Transactional
	public boolean deleteAttachmentByConclusionId(String conclusionId) {
		return this.subjectConclusionDao.deleteAttachmentByConclusionId(conclusionId);
	}

	@Override
	@Transactional
	public boolean deleteAttachmentByConclusionId(String conclusionId, String fileId) {
		return this.subjectConclusionDao.deleteAttachmentByConclusionId(conclusionId, fileId);
	}

	@Override
	public SubjectConclusion getSubjectConclusionBySubjectId(String subjectId) {
		return this.createSubjectConclusion(subjectId);
	}

	@Override
	public SubjectConclusion getSubjectConclusionById(String conclusionId) {
		if (StringUtils.isBlank(conclusionId)) {
			return null;
		}
		return this.subjectConclusionDao.getEntityById(SubjectConclusion.class, conclusionId);
	}

	@Override
	public List<SubjectConclusionAttachment> getAttachmentByConclusionId(String conclusionId) {
		return this.subjectConclusionDao.findAttachmentByConclusionId(conclusionId);
	}

	@Override
	public List<SubjectConclusionAttachment> getAttachmentBySubjectId(String subjectId) {
		return this.subjectConclusionDao.findAttachmentBySubjectId(subjectId);
	}
}

package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.view.FileView;

/**
 * 结题报告业务层
 * 
 * @author liuyk
 *
 */
public interface SubjectConclusionService {

	public SubjectConclusion createSubjectConclusion(String subjectId);

	public boolean createConclusionAttachment(List<SubjectConclusionAttachment> cas);

	public boolean createConclusionAttachment(String conclusionId, List<FileView> files);

	public boolean deleteAttachmentByConclusionId(String conclusionId);

	public boolean deleteAttachmentByConclusionId(String conclusionId, String fileId);

	public SubjectConclusion getSubjectConclusionBySubjectId(String subjectId);

	public SubjectConclusion getSubjectConclusionById(String conclusionId);

	public List<SubjectConclusionAttachment> getAttachmentByConclusionId(String conclusionId);

	public List<SubjectConclusionAttachment> getAttachmentBySubjectId(String subjectId);

}

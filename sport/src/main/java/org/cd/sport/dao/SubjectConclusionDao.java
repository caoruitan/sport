package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 结题报告数据层
 * 
 * @author liuyk
 *
 */
public interface SubjectConclusionDao extends IBaseDao {

	public boolean deleteAttachmentByConclusionId(String conclusionId);

	public boolean deleteAttachmentByConclusionId(String conclusionId, String fileId);

	public SubjectConclusion findSubjectConclusionBySubjectId(String subjectId);

	public List<SubjectConclusionAttachment> findAttachmentByConclusionId(String conclusionId);

}

package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectConclusionDaoImpl extends BaseDaoImpl<SubjectConclusion> implements SubjectConclusionDao {

	@Override
	public boolean deleteAttachmentByConclusionId(String conclusionId) {
		String deleteHql = "delete from SubjectConclusionAttachment where conclusionId=:conclusionId";
		return this.getHibernateQuery(deleteHql).setParameter("conclusionId", conclusionId).executeUpdate() != 0;
	}

	@Override
	public boolean deleteAttachmentByConclusionId(String conclusionId, String fileId) {
		String deleteHql = "delete from SubjectConclusionAttachment where conclusionId=:conclusionId and fielId=:fileId";
		return this.getHibernateQuery(deleteHql).setParameter("conclusionId", conclusionId)
				.setParameter("fileId", fileId).executeUpdate() != 0;
	}

	@Override
	public SubjectConclusion findSubjectConclusionBySubjectId(String subjectId) {
		String queryHql = "from SubjectConclusion where subjectId=:subjectId";
		return (SubjectConclusion) this.getHibernateQuery(queryHql).setParameter("subjectId", subjectId).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectConclusionAttachment> findAttachmentByConclusionId(String conclusionId) {
		String queryHql = "from SubjectConclusionAttachment where conclusionId=:conclusionId";
		return this.getHibernateQuery(queryHql).setParameter("conclusionId", conclusionId).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectConclusionAttachment> findAttachmentBySubjectId(String subjectId) {
		String queryHql = "from SubjectConclusionAttachment where subjectId=:subjectId";
		return this.getHibernateQuery(queryHql).setParameter("subjectId", subjectId).list();
	}
}

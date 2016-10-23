package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.NewsAttachment;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class NewsAttachmentDaoImpl extends BaseDaoImpl<NewsAttachment> implements NewsAttachmentDao {

	@Override
	public boolean deleteByNewsId(String newsId) {
		String deleteHql = "delete from NewsAttachment where newsId=:newsId";
		return this.getHibernateQuery(deleteHql).setParameter("newsId", newsId).executeUpdate() != 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NewsAttachment> findByNewsId(String newsId) {
		String queryHql = "from NewsAttachment where newsId=:newsId";
		return this.getHibernateQuery(queryHql).setParameter("newsId", newsId).list();
	}
}

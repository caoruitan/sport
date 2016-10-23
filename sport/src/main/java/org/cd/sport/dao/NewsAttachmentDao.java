package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.NewsAttachment;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 新闻附件
 * @author liuyk
 *
 */
public interface NewsAttachmentDao extends IBaseDao {

	public boolean deleteByNewsId(String newsId);

	public List<NewsAttachment> findByNewsId(String newsId);
}	

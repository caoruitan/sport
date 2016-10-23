package org.cd.sport.service;

import java.util.List;

import org.cd.sport.dao.NewsAttachmentDao;
import org.cd.sport.domain.NewsAttachment;
import org.cd.sport.support.NewsSupport;
import org.cd.sport.view.FileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新闻业务接口
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class NewsAttachmentServiceImpl extends NewsSupport implements NewsAttachmentService {

	@Autowired
	private NewsAttachmentDao newsAttachmentDao;

	@Override
	@Transactional
	public boolean create(String newsId, List<FileView> files) {
		this.deleteByNewsId(newsId);
		for (FileView file : files) {
			NewsAttachment na = new NewsAttachment();
			na.setId(file.getId());
			na.setNewsId(newsId);
			na.setName(file.getName());
			na.setPath(file.getPath());
			this.newsAttachmentDao.save(na);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean deleteByNewsId(String newsId) {
		return this.newsAttachmentDao.deleteByNewsId(newsId);
	}

	@Override
	public List<NewsAttachment> getByNewsId(String newsId) {
		return this.newsAttachmentDao.findByNewsId(newsId);
	}
}

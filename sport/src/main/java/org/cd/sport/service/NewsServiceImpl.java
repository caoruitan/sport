package org.cd.sport.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.NewsDao;
import org.cd.sport.domain.News;
import org.cd.sport.domain.NewsAttachment;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.support.NewsSupport;
import org.cd.sport.view.NewsView;
import org.cd.sport.vo.NewsQuery;
import org.cd.sport.vo.NewsVo;
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
public class NewsServiceImpl extends NewsSupport implements NewsService {

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private NewsAttachmentService newsAttachmentService;

	@Override
	@Transactional
	public boolean create(NewsView news) {
		News process = this.process(news);
		this.newsDao.save(process);
		this.newsAttachmentService.create(process.getId(), news.getFiles());
		return true;
	}

	@Override
	@Transactional
	public boolean update(NewsView news) {
		this.validateUpdate(news);
		News oldNews = this.newsDao.getEntityById(News.class, news.getId());
		if (oldNews == null) {
			throw new EntityNotFoundException("新闻实体没有找到");
		}
		oldNews.setColumnId(Integer.parseInt(news.getColumnId()));
		oldNews.setContent(news.getContent().getBytes());
		oldNews.setTitle(news.getTitle());
		this.newsDao.update(oldNews);
		this.newsAttachmentService.create(oldNews.getId(), news.getFiles());
		return true;
	}

	@Override
	@Transactional
	public boolean publish(String id) {
		if (StringUtils.isBlank(id)) {
			throw new ParameterIsWrongException("新闻实体id为空");
		}
		News news = this.newsDao.getEntityById(News.class, id);
		if (news == null || (news.getStatus() != Constants.News.news_create
				&& news.getStatus() != Constants.News.news_unpublish)) {
			throw new EntityNotFoundException("新闻实体没有找到或者状态不正确");
		}
		news.setStatus(Constants.News.news_publish);
		news.setPublishTime(new Date(System.currentTimeMillis()));
		this.newsDao.update(news);
		return true;
	}

	@Override
	@Transactional
	public boolean publish(String[] ids) {
		if (ids == null || ids.length == 0) {
			return false;
		}
		boolean result = true;
		for (String id : ids) {
			result &= this.publish(id);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean unpublish(String id) {
		if (StringUtils.isBlank(id)) {
			throw new ParameterIsWrongException("新闻实体id为空");
		}
		News news = this.newsDao.getEntityById(News.class, id);
		if (news == null || news.getStatus() != Constants.News.news_publish) {
			throw new EntityNotFoundException("新闻实体没有找到或者状态不正确");
		}
		news.setStatus(Constants.News.news_unpublish);
		news.setPublishTime(new Date(System.currentTimeMillis()));
		this.newsDao.update(news);
		return true;
	}

	@Override
	@Transactional
	public boolean unpublish(String[] ids) {
		if (ids == null || ids.length == 0) {
			return false;
		}
		boolean result = true;
		for (String id : ids) {
			result &= this.unpublish(id);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean delete(String[] ids) {
		return this.newsDao.deleteById(ids);
	}

	@Override
	public NewsVo getById(String id) {
		News news = this.newsDao.getEntityById(News.class, id);
		List<NewsAttachment> files = this.newsAttachmentService.getByNewsId(id);
		NewsVo process = this.process(news);
		process.setFiles(this.processFile(files));
		return process;
	}

	@Override
	public List<NewsVo> getByWhere(NewsQuery query, int start, int limit) {
		List<News> news = this.newsDao.findByWhere(query, start, limit);
		return this.process(news);
	}

	@Override
	public long getTotalByWhere(NewsQuery query) {
		return this.newsDao.findTotalByWhere(query);
	}

	@Override
	public NewsVo getLatestNotice(int column) {
		News news = this.newsDao.findLatest(column, Constants.News.news_publish);
		return this.process(news);
	}

	@Override
	public List<NewsVo> getByColumn(Integer[] column, int start, int limit) {
		if (column == null || column.length == 0) {
			List<News> news = this.newsDao.find(start, limit);
			return this.process(news);
		}
		return this.newsDao.findByColumn(column, start, limit);
	}

	@Override
	public long getTotalByColumn(Integer[] column) {
		if (column == null || column.length == 0) {
			return this.newsDao.findTotal();
		}
		return this.newsDao.findTotalByColumn(column);
	}
}

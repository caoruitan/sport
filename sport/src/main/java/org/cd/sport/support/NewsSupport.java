package org.cd.sport.support;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.News;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.view.NewsView;
import org.cd.sport.vo.NewsVo;

/**
 * 
 * 用户相关支持类
 * 
 * @author liuyk
 *
 */
public class NewsSupport extends SportSupport {

	/**
	 * 验证用户信息合法性
	 */
	public void validate(NewsView news) {
		if (news == null) {
			throw new ParameterIsWrongException("新闻对象为空");
		}
		if (StringUtils.isBlank(news.getTitle()) || news.getTitle().length() < 4 || news.getTitle().length() > 200) {
			throw new ParameterIsWrongException("新闻标题或者格式不对");
		}
		if (StringUtils.isBlank(news.getColumnId())) {
			throw new ParameterIsWrongException("新闻分类不能为空");
		}

		if (StringUtils.isBlank(news.getContent())) {
			throw new ParameterIsWrongException("新闻内容不能为空");
		}
		if (StringUtils.isBlank(news.getFileId())) {
			throw new ParameterIsWrongException("新闻附件id不能为空");
		}
		if (StringUtils.isBlank(news.getFileName())) {
			throw new ParameterIsWrongException("新闻附件名称不能为空");
		}
	}

	/**
	 * 验证用户信息合法性
	 */
	public void validateUpdate(NewsView news) {
		validate(news);
		if (StringUtils.isBlank(news.getId())) {
			throw new ParameterIsWrongException("新闻id不能为空");
		}
	}

	public News process(NewsView user) {
		this.validate(user);
		News news = this.result(News.class, user);
		news.setCreateTime(new Date(System.currentTimeMillis()));
		news.setStatus(Constants.News.news_create);
		return news;
	}

	public List<NewsVo> process(List<News> newss) {
		if (newss == null || newss.isEmpty()) {
			return null;
		}

		List<NewsVo> vos = new ArrayList<NewsVo>();
		for (News news : newss) {
			vos.add(this.process(news));
		}
		return vos;
	}

	public NewsVo process(News news) {
		if (news == null) {
			return null;
		}
		NewsVo newsVo = this.result(NewsVo.class, news);
		String cName = Constants.News.getColumns().get(Long.parseLong(news.getColumnId()));
		newsVo.setColumnName(cName);
		newsVo.setStatusName(Constants.News.getStatusName(news.getStatus()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		newsVo.setCreateTime(format.format(news.getCreateTime()));
		Date publishTime = news.getPublishTime();
		if (publishTime != null) {
			newsVo.setPublishTime(format.format(publishTime));
		}
		newsVo.setContent(new String(news.getContent()));
		return newsVo;
	}

}

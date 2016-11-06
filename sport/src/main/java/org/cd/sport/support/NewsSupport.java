package org.cd.sport.support;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.News;
import org.cd.sport.domain.NewsAttachment;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.utils.NullableUtils;
import org.cd.sport.utils.UBBDecoder;
import org.cd.sport.view.FileView;
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
		NullableUtils.clean(news.getFiles());

		if (StringUtils.isBlank(news.getTitle()) || news.getTitle().length() < 4 || news.getTitle().length() > 200) {
			throw new ParameterIsWrongException("新闻标题或者格式不对");
		}
		if (StringUtils.isBlank(news.getColumnId())) {
			throw new ParameterIsWrongException("新闻分类不能为空");
		}

		if (StringUtils.isBlank(news.getContent())) {
			throw new ParameterIsWrongException("新闻内容不能为空");
		}
		if (news.getFiles() == null || news.getFiles().isEmpty()) {
			throw new ParameterIsWrongException("新闻附件不能为空");
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
		news.setColumnId(Integer.parseInt(user.getColumnId()));
		try {
			news.setContent(user.getContent().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
		String cName = Constants.News.getColumns().get(news.getColumnId());
		newsVo.setColumnId(String.valueOf(news.getColumnId()));
		newsVo.setColumnName(cName);
		newsVo.setStatusName(Constants.News.getStatusName(news.getStatus()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		Date createTime = news.getCreateTime();
		if (createTime != null) {
			newsVo.setCreateTime(format.format(createTime));
		}
		Date publishTime = news.getPublishTime();
		if (publishTime != null) {
			newsVo.setPublishTime(format.format(publishTime));
		}
		try {
			newsVo.setContent(UBBDecoder.decode(new String(news.getContent(),"UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newsVo;
	}

	public List<FileView> processFile(List<NewsAttachment> files) {
		if (files == null || files.isEmpty()) {
			return null;
		}
		List<FileView> vos = new ArrayList<FileView>();
		for (NewsAttachment file : files) {
			vos.add(this.result(FileView.class, file));
		}
		return vos;
	}
}

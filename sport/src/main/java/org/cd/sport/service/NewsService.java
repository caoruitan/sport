package org.cd.sport.service;

import java.util.List;

import org.cd.sport.view.NewsView;
import org.cd.sport.vo.NewsQuery;
import org.cd.sport.vo.NewsVo;

/**
 * 新闻业务接口
 * 
 * @author liuyk
 *
 */
public interface NewsService {

	public boolean create(NewsView news);

	public boolean update(NewsView news);

	public boolean publish(String id);

	public boolean publish(String[] ids);

	public boolean unpublish(String id);

	public boolean unpublish(String[] ids);

	public boolean delete(String[] ids);

	public NewsVo getById(String id);

	public NewsVo getLatestNotice(String column);

	public List<NewsVo> getByWhere(NewsQuery query, int start, int limit);

	public long getTotalByWhere(NewsQuery query);
}

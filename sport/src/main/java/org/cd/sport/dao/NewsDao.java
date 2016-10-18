package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.News;
import org.cd.sport.hibernate.IBaseDao;
import org.cd.sport.vo.NewsQuery;

/**
 * 数据字典分类数据层
 * 
 * @author liuyk
 *
 */
public interface NewsDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] ids);

	public List<News> find(int start, int limit);

	public List<News> findByWhere(NewsQuery query, int start, int limit);

	public long findTotalByWhere(NewsQuery query);

	public long findTotal();

}

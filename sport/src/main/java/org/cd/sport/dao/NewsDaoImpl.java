package org.cd.sport.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.News;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.vo.NewsQuery;
import org.cd.sport.vo.NewsVo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 新闻数据层
 * 
 * @author liuyk
 *
 */
@Repository
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {

	@Override
	public boolean deleteById(String id) {
		String deleteHql = "delete from News where id=:id";
		return this.getHibernateQuery(deleteHql).setParameter("id", id).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] ids) {
		String deleteHql = "delete from News where id in (:ids)";
		return this.getHibernateQuery(deleteHql).setParameterList("ids", ids).executeUpdate() != 0;
	}

	private void processQuery(Query hibernateQuery, Map<String, Object> map) {
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			Object value = entry.getValue();
			if (value instanceof Object[] || value instanceof Collection) {
				hibernateQuery.setParameterList(entry.getKey(), (Object[]) value);
			} else {
				hibernateQuery.setParameter(entry.getKey(), value);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<News> findByWhere(NewsQuery query, int start, int limit) {
		if (query == null) {
			return this.find(start, limit);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("from News where 1=1 ");
		if (!StringUtils.isBlank(query.getStatus())) {
			Integer[] status = Constants.News.parseStatus(query.getStatus());
			queryHql.append(" and status in (:status) ");
			params.put("status", status);
		}

		if (StringUtils.isNotBlank(query.getColumn())) {
			queryHql.append(" and columnId =:column ");
			params.put("column", query.getColumn());
		}

		if (StringUtils.isNotBlank(query.getTitle())) {
			queryHql.append(" and title like :name ");
			params.put("name", "%" + query.getTitle() + "%");
		}
		queryHql.append(" order by publishTime,createTime desc ");

		Query hibernateQuery = this.getHibernateQuery(queryHql.toString());
		this.processQuery(hibernateQuery, params);
		return hibernateQuery.list();
	}

	@Override
	public long findTotalByWhere(NewsQuery query) {
		if (query == null) {
			return this.findTotal();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("select count(1) from News where 1=1 ");
		if (!StringUtils.isBlank(query.getStatus())) {
			Integer[] status = Constants.News.parseStatus(query.getStatus());
			queryHql.append(" and status in (:status) ");
			params.put("status", status);
		}
		if (StringUtils.isNotBlank(query.getColumn())) {
			queryHql.append(" and columnId =:column ");
			params.put("column", query.getColumn());
		}
		if (StringUtils.isNotBlank(query.getTitle())) {
			queryHql.append(" and title like :name ");
			params.put("name", "%" + query.getTitle() + "%");
		}
		return this.getCountByHQL(queryHql.toString(), params);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<News> find(int start, int limit) {
		String queryHql = "from News order by publishTime,createTime desc";
		return this.getHibernateQuery(queryHql).setMaxResults(limit).setFirstResult(start).list();
	}

	@Override
	public long findTotal() {
		String updateHql = "select count(1) from News ";
		Long count = (Long) this.getHibernateQuery(updateHql).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public News findLatest(int column, int status) {
		String queryHql = "from News where columnId=:columnId and status=:status order by publishTime desc ";
		return (News) this.getHibernateQuery(queryHql).setParameter("columnId", column).setMaxResults(1)
				.setFirstResult(0).setParameter("status", status).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NewsVo> findByColumn(Integer[] column, int start, int limit) {
		String queryHql = "from News where columnId in (:columnId) order by publishTime,createTime desc";
		return this.getHibernateQuery(queryHql).setParameterList("columnId", column).setMaxResults(limit)
				.setFirstResult(start).list();
	}

	@Override
	public long findTotalByColumn(Integer[] column) {
		String updateHql = "select count(1) from News where columnId in (:columnId) ";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameterList("columnId", column).uniqueResult();
		return count == null ? 0 : count.intValue();
	}
}

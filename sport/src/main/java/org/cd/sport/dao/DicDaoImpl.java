package org.cd.sport.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.Dic;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.vo.DicQuery;
import org.springframework.stereotype.Repository;

/**
 * 数据字典数据层
 * 
 * @author liuyk
 *
 */
@Repository
public class DicDaoImpl extends BaseDaoImpl<Dic> implements DicDao {

	@Override
	public boolean deleteByCode(String code) {
		String deleteHql = "delete from Dic where code=:code";
		return this.getHibernateQuery(deleteHql).setParameter("code", code).executeUpdate() != 0;
	}

	@Override
	public boolean deleteByCode(String[] code) {
		String deleteHql = "delete from Dic where code in (:code)";
		return this.getHibernateQuery(deleteHql).setParameterList("code", code).executeUpdate() != 0;
	}

	@Override
	public Dic findByName(String name) {
		String queryHql = "from Dic where name=:name";
		return (Dic) this.getHibernateQuery(queryHql).setParameter("name", name).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dic> findByPcode(String pCode) {
		String queryHql = "from Dic where pCode=:pCode order by sort asc";
		return this.getHibernateQuery(queryHql).setParameter("pCode", pCode).list();
	}

	@Override
	public String findMaxCode(String pCode) {
		String queryHql = "select max(code) from Dic where pCode=:pCode";
		return (String) this.getHibernateQuery(queryHql).setParameter("pCode", pCode).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dic> findByPcode(String pCode, int start, int limit) {
		String queryHql = "from Dic where pCode=:pCode order by sort asc";
		return this.getHibernateQuery(queryHql).setParameter("pCode", pCode).setMaxResults(limit).setFirstResult(start)
				.list();
	}

	@Override
	public long findTotalByPcode(String pCode) {
		String updateHql = "select count(1) from Dic where  pCode=:pCode ";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameter("pCode", pCode).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public List<Dic> findByWhere(DicQuery query, int start, int limit) {
		if (query == null) {
			return this.find(start, limit);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("from Dic where 1=1 ");
		if (!StringUtils.isBlank(query.getpCode())) {
			queryHql.append(" and pCode=:pCode ");
			params.put("pCode", query.getpCode());
		}

		if (StringUtils.isNotBlank(query.getName())) {
			queryHql.append(" and name like :name ");
			params.put("name", "%" + query.getName() + "%");
		}

		if (StringUtils.isNotBlank(query.getCode())) {
			queryHql.append(" and code like :code ");
			params.put("code", "%" + query.getCode() + "%");
		}
		queryHql.append(" order by sort asc ");
		return this.getDatasByHQL(queryHql.toString(), params);
	}

	@Override
	public long findTotalByWhere(DicQuery query) {
		if (query == null) {
			return this.findTotal();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryHql = new StringBuffer("select count(1) from Dic where 1=1 ");
		if (!StringUtils.isBlank(query.getpCode())) {
			queryHql.append(" and pCode=:pCode ");
			params.put("pCode", query.getpCode());
		}

		if (StringUtils.isNotBlank(query.getName())) {
			queryHql.append(" and name like :name ");
			params.put("name", "%" + query.getName() + "%");
		}

		if (StringUtils.isNotBlank(query.getCode())) {
			queryHql.append(" and code like :code ");
			params.put("code", "%" + query.getCode() + "%");
		}
		return this.getCountByHQL(queryHql.toString(), params);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dic> find(int start, int limit) {
		String queryHql = "from Dic where order by sort asc ";
		return this.getHibernateQuery(queryHql).setMaxResults(limit).setFirstResult(start).list();
	}

	@Override
	public long findTotal() {
		String updateHql = "select count(1) from Dic ";
		Long count = (Long) this.getHibernateQuery(updateHql).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dic> find() {
		String queryHql = "from Dic where order ";
		return this.getHibernateQuery(queryHql).list();
	}

}

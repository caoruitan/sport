package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.DicType;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 数据字典分类数据层
 * 
 * @author liuyk
 *
 */
@Repository
public class DicTypeDaoImpl extends BaseDaoImpl<DicType> implements DicTypeDao {

	@Override
	public boolean deleteById(String dicId) {
		String deleteHql = "delete from DicType where dicId=:dicId";
		return this.getHibernateQuery(deleteHql).setParameter("dicId", dicId).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] dicId) {
		String deleteHql = "delete from DicType where dicId in (:dicId)";
		return this.getHibernateQuery(deleteHql).setParameterList("dicId", dicId).executeUpdate() != 0;
	}

	@Override
	public DicType findByName(String name) {
		String queryHql = "from DicType where name=:name";
		return (DicType) this.getHibernateQuery(queryHql).setParameter("name", name).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DicType> findByPid(String pId) {
		String queryHql = "from DicType where pId=:pId";
		return this.getHibernateQuery(queryHql).setParameter("pId", pId).list();
	}

	@Override
	public DicType findByCode(String code) {
		String queryHql = "from DicType where code=:code";
		return (DicType) this.getHibernateQuery(queryHql).setParameter("code", code).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DicType> find() {
		String queryHql = "from DicType";
		return this.getHibernateQuery(queryHql).list();
	}
}

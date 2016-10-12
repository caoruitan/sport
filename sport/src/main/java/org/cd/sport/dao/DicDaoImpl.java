package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.Dic;
import org.cd.sport.hibernate.BaseDaoImpl;
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
	public boolean deleteById(String dicId) {
		String deleteHql = "delete from Dic where id=:dicId";
		return this.getHibernateQuery(deleteHql).setParameter("dicId", dicId).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] dicId) {
		String deleteHql = "delete from Dic where id in (:dicId)";
		return this.getHibernateQuery(deleteHql).setParameterList("dicId", dicId).executeUpdate() != 0;
	}

	@Override
	public Dic findByName(String name) {
		String queryHql = "from Dic where name=:name";
		return (Dic) this.getHibernateQuery(queryHql).setParameter("name", name).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Dic> findByType(String typeId) {
		String queryHql = "from Dic where typeId=:typeId";
		return this.getHibernateQuery(queryHql).setParameter("typeId", typeId).list();
	}
}

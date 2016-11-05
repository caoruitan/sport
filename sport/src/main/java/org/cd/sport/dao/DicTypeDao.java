package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.DicType;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 数据字典分类数据层
 * 
 * @author liuyk
 *
 */
public interface DicTypeDao extends IBaseDao {

	public boolean deleteById(String dicId);

	public boolean deleteById(String[] dicId);

	public DicType findByName(String name);

	public DicType findByCode(String code);

	public List<DicType> find();

	public List<DicType> findByPid(String pid);

}

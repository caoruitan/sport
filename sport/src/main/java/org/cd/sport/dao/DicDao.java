package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.Dic;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 数据字典分类数据层
 * 
 * @author liuyk
 *
 */
public interface DicDao extends IBaseDao {

	public boolean deleteById(String dicId);

	public boolean deleteById(String[] dicId);

	public Dic findByName(String name);

	public List<Dic> findByType(String typeId);

}

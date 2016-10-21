package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.Dic;
import org.cd.sport.hibernate.IBaseDao;
import org.cd.sport.vo.DicQuery;

/**
 * 数据字典分类数据层
 * 
 * @author liuyk
 *
 */
public interface DicDao extends IBaseDao {

	public boolean deleteByCode(String dicId);

	public boolean deleteByCode(String[] dicId);

	public Dic findByName(String name);

	public String findMaxCode(String pCode);

	public List<Dic> find(int start, int limit);

	public List<Dic> findByPcode(String pCode);

	public List<Dic> findByPcode(String pCode, int start, int limit);

	public long findTotalByPcode(String pCode);

	public List<Dic> findByWhere(DicQuery query, int start, int limit);

	public long findTotalByWhere(DicQuery query);

	public long findTotal();

}

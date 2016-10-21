package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.Dic;
import org.cd.sport.view.DicView;
import org.cd.sport.vo.DicQuery;

/**
 * 
 * 数据字典业务接口
 * 
 * @author liuyk
 *
 */
public interface DicService {

	public boolean create(DicView dic);

	public boolean update(DicView dic);

	public boolean deleteByCode(String dicId);

	public boolean deleteByCode(String[] dicId);

	public Dic getByCode(String dicId);

	public Dic getByName(String name);

	public List<Dic> getByPcode(String pcode);

	public List<Dic> getByPcode(String pcode, int start, int limit);

	public List<Dic> getByWhere(DicQuery query, int start, int limit);

	public long getTotalByPCode(String pcode);

	public long getTotalByWhere(DicQuery query);

}

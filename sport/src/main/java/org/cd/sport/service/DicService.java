package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.Dic;
import org.cd.sport.view.DicView;

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

	public boolean deleteById(String dicId);

	public boolean deleteById(String[] dicId);

	public Dic getById(String dicId);

	public Dic getByName(String name);

	public List<Dic> getByType(String typeId);

}

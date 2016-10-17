package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.DicType;
import org.cd.sport.view.DicTypeView;
import org.cd.sport.vo.Node;

/**
 * 
 * 数据字典业务接口
 * 
 * @author liuyk
 *
 */
public interface DicTypeService {

	public boolean create(DicTypeView dicType);

	public boolean update(DicTypeView dicType);

	public boolean deleteById(String dicId);

	public boolean deleteById(String[] dicId);

	public DicType getById(String dicId);

	public DicType getByCode(String code);

	public DicType getByName(String name);

	public List<DicType> getByPid(String pid);

	public List<Node> getNodeByPid(String pid);

}

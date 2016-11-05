package org.cd.sport.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.DicTypeDao;
import org.cd.sport.domain.DicType;
import org.cd.sport.exception.EntityNotFoundExcetion;
import org.cd.sport.exception.NameIsExistException;
import org.cd.sport.support.DicTypeSupport;
import org.cd.sport.view.DicTypeView;
import org.cd.sport.vo.Node;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 数据字典业务接口
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class DicTypeServiceImpl extends DicTypeSupport implements DicTypeService {

	@Autowired
	private DicTypeDao dicTypeDao;

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validName(String name) {
		DicType dic = this.getByName(name);
		if (dic != null) {
			throw new NameIsExistException("数据字典名称已经存在。");
		}
	}

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validName(String name, DicType dic) {
		DicType _dic = this.getByName(name);
		if (_dic != null) {
			if (!_dic.getCode().equals(dic.getCode())) {
				throw new NameIsExistException("数据字典名称已经存在。");
			}
		}
	}

	@Override
	@Transactional
	public boolean create(DicTypeView dicType) {
		DicType process = this.process(dicType);
		this.validName(process.getName());
		this.dicTypeDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(DicTypeView dicType) {
		this.validate(dicType);
		DicType dic = this.getById(dicType.getId());
		if (dic == null) {
			throw new EntityNotFoundExcetion("数据不存在");
		}
		this.validName(dic.getName(), dic);
		BeanUtils.copyProperties(dicType, dic);
		this.dicTypeDao.update(dic);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(String dicId) {
		if (StringUtils.isBlank(dicId)) {
			return false;
		}
		return this.dicTypeDao.deleteById(dicId);
	}

	@Override
	@Transactional
	public boolean deleteById(String[] dicId) {
		if (dicId == null || dicId.length == 0) {
			return false;
		}
		return this.dicTypeDao.deleteById(dicId);
	}

	@Override
	public DicType getById(String dicId) {
		if (StringUtils.isBlank(dicId)) {
			return null;
		}
		return this.dicTypeDao.getEntityById(DicType.class, dicId);
	}

	@Override
	public List<DicType> getByPid(String pid) {
		if (StringUtils.isBlank(pid)) {
			pid = Constants.Dic.DEFAULT_PID;
		}
		return this.dicTypeDao.findByPid(pid);
	}

	@Override
	public DicType getByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return this.dicTypeDao.findByName(name);
	}

	@Override
	public List<Node> getNodeByPid(String pid) {
		List<DicType> datas = this.getByPid(pid);
		List<Node> nodes = new ArrayList<Node>();
		if (datas != null && !datas.isEmpty()) {
			for (DicType dic : datas) {
				Node node = new Node();
				node.setName(dic.getName());
				node.setpId(dic.getpId());
				node.setCode(dic.getCode());
				node.setHasChild(dic.isHasChild());
				nodes.add(node);
			}
		}
		return nodes;
	}

	@Override
	public DicType getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		return this.dicTypeDao.findByCode(code);
	}
}

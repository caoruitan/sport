package org.cd.sport.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.dao.DicDao;
import org.cd.sport.dao.DicTypeDao;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.DicType;
import org.cd.sport.exception.EntityNotFoundExcetion;
import org.cd.sport.exception.NameIsExistException;
import org.cd.sport.support.DicSupport;
import org.cd.sport.utils.NumUtils;
import org.cd.sport.view.DicView;
import org.cd.sport.vo.DicQuery;
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
public class DicServiceImpl extends DicSupport implements DicService {

	@Autowired
	private DicDao dicDao;

	@Autowired
	private DicTypeDao dicTypeDao;

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validName(String name) {
		Dic dic = this.getByName(name);
		if (dic != null) {
			throw new NameIsExistException("数据字典名称已经存在。");
		}
	}

	/**
	 * 校验登录名是否重复
	 */
	public synchronized void validName(String name, Dic dic) {
		Dic _dic = this.getByName(name);
		if (_dic != null) {
			if (!_dic.getCode().equals(dic.getCode())) {
				throw new NameIsExistException("数据字典名称已经存在。");
			}
		}
	}

	private synchronized String getCode(String typeCode) {
		String maxCode = this.dicDao.findMaxCode(typeCode);
		if (StringUtils.isBlank(maxCode)) {
			return typeCode + "001";
		}
		try {
			// 截掉typeCode
			maxCode = maxCode.substring(typeCode.length(), maxCode.length());
			int parseInt = Integer.parseInt(maxCode);
			parseInt = parseInt + 1;
			return typeCode + NumUtils.format(parseInt, 3);
		} catch (Exception e) {
			return typeCode + "001";
		}
	}

	@Override
	@Transactional
	public boolean create(DicView dic) {
		Dic process = this.process(dic);
		// this.validName(process.getName());
		DicType dicType = dicTypeDao.findByCode(dic.getpCode());
		if (dicType == null) {
			throw new EntityNotFoundException("数据对象类型不存在");
		}
		// code处理
		process.setpCode(dicType.getCode());
		process.setCode(this.getCode(dicType.getCode()));
		this.dicDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(DicView dic) {
		this.validateUpdate(dic);
		Dic oldDic = this.getByCode(dic.getCode());
		if (oldDic == null) {
			throw new EntityNotFoundExcetion("数据不存在");
		}
		String code = oldDic.getCode();
		String pCode = oldDic.getpCode();
		int sort = oldDic.getSort();
		// this.validName(dic.getName(), oldDic);
		BeanUtils.copyProperties(dic, oldDic);
		oldDic.setpCode(pCode);
		oldDic.setCode(code);
		oldDic.setSort(sort);
		this.dicDao.update(oldDic);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteByCode(String dicId) {
		if (StringUtils.isBlank(dicId)) {
			return false;
		}
		return this.dicDao.deleteByCode(dicId);
	}

	@Override
	@Transactional
	public boolean deleteByCode(String[] dicId) {
		if (dicId == null || dicId.length == 0) {
			return false;
		}
		return this.dicDao.deleteByCode(dicId);
	}

	@Override
	public Dic getByCode(String dicId) {
		if (StringUtils.isBlank(dicId)) {
			return null;
		}
		return this.dicDao.getEntityById(Dic.class, dicId);
	}

	@Override
	public Dic getByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return this.dicDao.findByName(name);
	}

	@Override
	public List<Dic> getByPcode(String typeId) {
		if (StringUtils.isBlank(typeId)) {
			return null;
		}
		return this.dicDao.findByPcode(typeId);
	}

	@Override
	public List<Dic> getByPcode(String typeId, int start, int limit) {
		if (StringUtils.isBlank(typeId)) {
			return null;
		}
		return this.dicDao.findByPcode(typeId, start, limit);
	}

	@Override
	public long getTotalByPCode(String typeId) {
		if (StringUtils.isBlank(typeId)) {
			return 0;
		}
		return this.dicDao.findTotalByPcode(typeId);
	}

	@Override
	public List<Dic> getByWhere(DicQuery query, int start, int limit) {
		return this.dicDao.findByWhere(query, start, limit);
	}

	@Override
	public long getTotalByWhere(DicQuery query) {
		return this.dicDao.findTotalByWhere(query);
	}

	@Override
	public Node getNodeByPcode(String pcode) {
		if (StringUtils.isBlank(pcode)) {
			return null;
		}
		List<DicType> dicTypes = this.dicTypeDao.find();
		if (dicTypes != null && !dicTypes.isEmpty()) {
			for (DicType dicType : dicTypes) {
				if (pcode.equals(dicType.getCode())) {
					Node process = this.process(dicType);
					this.findClidType(process, dicTypes);
					return process;
				}
			}
		}
		return null;
	}

	public void findClidType(Node node, List<DicType> types) {
		List<Node> clids = new ArrayList<Node>();
		if (types != null && !types.isEmpty()) {
			for (DicType dicType : types) {
				if (dicType.getpId().equals(node.getCode())) {
					Node process = this.process(dicType);
					clids.add(process);
				}
			}
		}
		node.setChildren(clids);
		List<Dic> dics = this.dicDao.find();
		this.findClidType(clids, types, dics, null);
	}

	public void findClidType(List<Node> nodes, List<DicType> types, List<Dic> dics, Node Node) {
		// type为空查询node
		if (nodes != null && !nodes.isEmpty()) {
			for (Node node : nodes) {
				List<Node> clids = new ArrayList<Node>();
				for (DicType dicType : types) {
					if (dicType.getpId().equals(node.getCode())) {
						Node process = this.process(dicType);
						clids.add(process);
					}
				}
				node.setChildren(clids);
				this.findClidType(clids, types, dics, node);
			}
		} else {
			List<Node> clids = new ArrayList<Node>();
			if (dics != null && !dics.isEmpty()) {
				for (Dic dic : dics) {
					if (dic.getpCode().equals(Node.getCode())) {
						Node process = this.process(dic);
						clids.add(process);
						this.findClid(process, dics);
					}
				}
			}
			Node.setChildren(clids);
			if (clids == null || clids.isEmpty()) {
				Node.setParent(false);
			}
		}
	}

	public void findClid(Node node, List<Dic> dics) {
		List<Node> clids = new ArrayList<Node>();
		if (dics != null && !dics.isEmpty()) {
			for (Dic dic : dics) {
				if (dic.getpCode().equals(node.getCode())) {
					Node process = this.process(dic);
					clids.add(process);
				}
			}
		}
		node.setChildren(clids);
		if (clids == null || clids.isEmpty()) {
			node.setParent(false);
		}
		this.findClid(clids, dics);
	}

	public void findClid(List<Node> nodes, List<Dic> dics) {
		if (nodes != null && !nodes.isEmpty()) {
			for (Node node : nodes) {
				List<Node> clids = new ArrayList<Node>();
				for (Dic dic : dics) {
					if (dic.getpCode().equals(node.getCode())) {
						Node process = this.process(dic);
						clids.add(process);
					}
				}
				node.setChildren(clids);
				if (clids == null || clids.isEmpty()) {
					node.setParent(false);
				}
				this.findClid(clids, dics);
			}
		}
	}

	public Node process(DicType dicType) {
		Node node = new Node();
		node.setCode(dicType.getCode());
		node.setName(dicType.getName());
		return node;
	}

	public Node process(Dic dicType) {
		Node node = new Node();
		node.setCode(dicType.getCode());
		node.setName(dicType.getName());
		return node;
	}
}

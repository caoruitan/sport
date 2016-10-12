package org.cd.sport.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.dao.DicDao;
import org.cd.sport.domain.Dic;
import org.cd.sport.exception.EntityNotFoundExcetion;
import org.cd.sport.exception.NameIsExistException;
import org.cd.sport.support.DicSupport;
import org.cd.sport.view.DicView;
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
			if (!_dic.getId().equals(dic.getId())) {
				throw new NameIsExistException("数据字典名称已经存在。");
			}
		}
	}

	@Override
	@Transactional
	public boolean create(DicView dic) {
		Dic process = this.process(dic);
		this.validName(process.getName());
		this.dicDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(DicView dic) {
		this.validate(dic);
		Dic oldDic = this.getById(dic.getId());
		if (oldDic == null) {
			throw new EntityNotFoundExcetion("数据不存在");
		}
		this.validName(dic.getName(), oldDic);
		BeanUtils.copyProperties(dic, oldDic);
		this.dicDao.update(oldDic);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(String dicId) {
		if (StringUtils.isBlank(dicId)) {
			return false;
		}
		return this.dicDao.deleteById(dicId);
	}

	@Override
	@Transactional
	public boolean deleteById(String[] dicId) {
		if (dicId == null || dicId.length == 0) {
			return false;
		}
		return this.dicDao.deleteById(dicId);
	}

	@Override
	public Dic getById(String dicId) {
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
	public List<Dic> getByType(String typeId) {
		if (StringUtils.isBlank(typeId)) {
			return null;
		}
		return this.dicDao.findByType(typeId);
	}
}

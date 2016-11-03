package org.cd.sport.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.dao.SubjectRwsDeviceDao;
import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.support.SubjectRwsSupport;
import org.cd.sport.view.SubjectRwsDeviceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjectRwsDeviceServiceImpl extends SubjectRwsSupport implements SubjectRwsDeviceService {

	@Autowired
	private SubjectRwsDeviceDao subjectRwsDeviceDao;

	@Override
	@Transactional
	public boolean create(SubjectRwsDeviceView ss) {
		SubjectRwsDevice process = this.process(ss);
		this.subjectRwsDeviceDao.save(process);
		return true;
	}

	@Override
	@Transactional
	public boolean update(SubjectRwsDeviceView ss) {
		SubjectRwsDevice entity = this.subjectRwsDeviceDao.getEntityById(SubjectRwsDevice.class, ss.getdId());
		this.process(entity, ss);
		this.subjectRwsDeviceDao.update(entity);
		return false;
	}

	@Override
	@Transactional
	public boolean deleteById(String id) {
		return this.subjectRwsDeviceDao.deleteById(id);
	}

	@Override
	@Transactional
	public boolean deleteById(String[] id) {
		if (id == null || id.length == 0) {
			return false;
		}
		return this.subjectRwsDeviceDao.deleteById(id);
	}

	@Override
	public List<SubjectRwsDevice> getByRwsId(String rwsId) {
		return this.subjectRwsDeviceDao.findByRwsId(rwsId);
	}

	@Override
	public long getTotalByRwsId(String rwsId) {
		return this.subjectRwsDeviceDao.findTotalByRwsId(rwsId);
	}

	@Override
	public SubjectRwsDevice getById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return this.subjectRwsDeviceDao.getEntityById(SubjectRwsDevice.class, id);
	}
}

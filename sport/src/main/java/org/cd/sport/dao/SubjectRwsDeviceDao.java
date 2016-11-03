package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
public interface SubjectRwsDeviceDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public List<SubjectRwsDevice> findByRwsId(String rwsId, int start, int limit);

	public List<SubjectRwsDevice> findByRwsId(String rwsId);

	public long findTotalByRwsId(String rwsId);
}

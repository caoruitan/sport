package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
public interface SubjectRwsScheduleDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public List<SubjectRwsSchedule> findByRwsId(String rwsId, int start, int limit);

	public List<SubjectRwsSchedule> findByRwsId(String rwsId);

	public long findTotalByRwsId(String rwsId);
}

package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
public interface SubjectRwsAppropriationDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public List<SubjectRwsAppropriation> findByRwsId(String rwsId, int start, int limit);

	public List<SubjectRwsAppropriation> findByRwsId(String rwsId);

	public long findTotalByApproId(String approId);
}

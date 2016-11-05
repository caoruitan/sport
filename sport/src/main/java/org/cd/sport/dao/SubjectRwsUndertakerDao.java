package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 申报书申请人
 * 
 * @author liuyk
 *
 */
public interface SubjectRwsUndertakerDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public List<SubjectRwsUndertaker> findByRwsId(String rwsId);

	public List<SubjectRwsUndertaker> findByRwsId(String rwsId, int start, int limit);

	public long findTotalByRwsId(String rwsId);

}

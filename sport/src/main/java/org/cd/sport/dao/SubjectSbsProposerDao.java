package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.hibernate.IBaseDao;

/**
 * 申报书申请人
 * 
 * @author liuyk
 *
 */
public interface SubjectSbsProposerDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public int findMaxSort(String primary);

	public List<SubjectSbsProposer> findBySbsId(String sbsId);

	public List<SubjectSbsProposer> findBySbsId(String sbsId, int start, int limit);

	public List<SubjectSbsProposer> findBySbsId(String sbsId, String primary);

	public List<SubjectSbsProposer> findBySbsId(String sbsId, String primary, int start, int limit);

	public long findTotalBySbsId(String sbsId);

	public long findTotalBySbsId(String sbsId, String primary);
}

package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.view.SubjectSbsProposerView;

/**
 * 课题服务类
 * 
 * @author caort
 */
public interface SubjectSbsProposerService {

	public boolean create(SubjectSbsProposerView view);

	public boolean update(SubjectSbsProposerView view);
	
	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public List<SubjectSbsProposer> getBySbsId(String sbsId);

	public List<SubjectSbsProposer> getBySbsId(String sbsId, int start, int limit);

	public List<SubjectSbsProposer> getBySbsId(String sbsId, String primary);

	public List<SubjectSbsProposer> getBySbsId(String sbsId, String primary, int start, int limit);

	public long getTotalBySbsId(String sbsId);

	public long getTotalBySbsId(String sbsId, String primary);
}

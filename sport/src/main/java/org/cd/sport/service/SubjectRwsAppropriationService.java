package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.view.SubjectRwsAppropriationView;

/**
 * 任务进度安排服务接口
 * 
 * @author liuyk
 */
public interface SubjectRwsAppropriationService {

	public boolean create(SubjectRwsAppropriationView ss);

	public boolean update(SubjectRwsAppropriationView ss);

	public boolean deleteById(String id);
	
	public boolean deleteById(String[] id);
	
	public SubjectRwsAppropriation getById(String id);

	public List<SubjectRwsAppropriation> getByRwsId(String rwsId);

	public long getTotalByRwsId(String rwsId);
}

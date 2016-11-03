package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.view.SubjectRwsScheduleView;

/**
 * 任务进度安排服务接口
 * 
 * @author liuyk
 */
public interface SubjectRwsScheduleService {

	public boolean create(SubjectRwsScheduleView ss);

	public boolean update(SubjectRwsScheduleView ss);

	public boolean deleteById(String id);
	
	public boolean deleteById(String[] id);
	
	public SubjectRwsSchedule getById(String id);

	public List<SubjectRwsSchedule> getByRwsId(String rwsId);

	public long getTotalByRwsId(String rwsId);
}

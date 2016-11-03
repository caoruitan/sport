package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.view.SubjectRwsDeviceView;

/**
 * 任务进度安排服务接口
 * 
 * @author liuyk
 */
public interface SubjectRwsDeviceService {

	public boolean create(SubjectRwsDeviceView sd);

	public boolean update(SubjectRwsDeviceView sd);

	public boolean deleteById(String id);
	
	public boolean deleteById(String[] id);
	
	public SubjectRwsDevice getById(String id);

	public List<SubjectRwsDevice> getByRwsId(String rwsId);

	public long getTotalByRwsId(String rwsId);
}

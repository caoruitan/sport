package org.cd.sport.service;

import org.cd.sport.domain.SubjectRws;

/**
 * 课题服务类
 * @author caort
 */
public interface SubjectRwsService {
	
	public SubjectRws getSubjectRwsById(String id);
	
	public SubjectRws createSubjectRws(String subjectId);
	
	public void saveBaseInfo(String rwsId, String subjectId, String address, String phone, String fax, String email, String years);
	
	public void saveXtyj(String rwsId, String subjectId, String xtyj);
	
	public void saveYjmb(String rwsId, String subjectId, String yjmb);
	
	public void saveJsgj(String rwsId, String subjectId, String jsgj);
	
	public void saveYjff(String rwsId, String subjectId, String yjff);
	
	public void saveSyfa(String rwsId, String subjectId, String syfa);
	
	public void saveJdap(String rwsId, String subjectId, String jdap);
	
	public void saveYqjg(String rwsId, String subjectId, String yqjg);
	
	public void saveGztj(String rwsId, String subjectId, String gztj);
	
	public void saveTjyj(String rwsId, String subjectId, String tjyj);
	
	public void submit(String rwsId);
	
}

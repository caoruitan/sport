package org.cd.sport.service;

import org.cd.sport.domain.SubjectSbs;

/**
 * 课题服务类
 * @author caort
 */
public interface SubjectSbsService {
	
	public SubjectSbs getSubjectSbsById(String id);
	
	public SubjectSbs getSbsBySubjectId(String subjectId);
	
	public SubjectSbs createSubjectSbs(String subjectId);
	
	public void saveBaseInfo(String subjectId, String address, String phone, String fax, String email, String years);
	
	public void saveXtyj(String subjectId, String xtyj);
	
	public void saveYjmb(String subjectId, String yjmb);
	
	public void saveJsgj(String subjectId, String jsgj);
	
	public void saveYjff(String subjectId, String yjff);
	
	public void saveSyfa(String subjectId, String syfa);
	
	public void saveJdap(String subjectId, String jdap);
	
	public void saveYqjg(String subjectId, String yqjg);
	
	public void saveGztj(String subjectId, String gztj);
	
	public void saveTjyj(String subjectId, String tjyj);
	
	public void checkAndSubmit(String subjectId, String basePath);
	
}
